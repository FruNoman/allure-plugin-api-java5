/*
 *  Copyright 2019 Qameta Software OÜ
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package io.qameta.allure.tree;

import io.qameta.allure.entity.LabelName;
import io.qameta.allure.entity.Statistic;
import io.qameta.allure.entity.TestResult;

import javax.xml.bind.DatatypeConverter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;



import java8.util.Objects;
import java8.util.function.BiFunction;
import java8.util.function.BinaryOperator;
import java8.util.function.Function;
import java8.util.function.Predicate;
import java8.util.stream.Collectors;
import java8.util.stream.RefStreams;
import java8.util.stream.StreamSupport;
import org.apache.commons.lang3.StringUtils;

import static java.nio.charset.StandardCharsets.UTF_8;

/**
 * @author charlie (Dmitry Baev).
 */
public final class TreeUtils {

    private TreeUtils() {
        throw new IllegalStateException("Do not instance");
    }

    public static String createGroupUid(final String parentUid, final String groupName) {
        final MessageDigest md = getMessageDigest();
        try {
            md.update(Objects.toString(parentUid).getBytes("UTF-8"));
            md.update(Objects.toString(groupName).getBytes("UTF-8"));
        }catch (Exception e){

        }
        return DatatypeConverter.printHexBinary(md.digest()).toLowerCase();
    }

    public static List<TreeLayer> groupByLabels(final TestResult testResult,
                                                final LabelName... labelNames) {
        return RefStreams.of(labelNames)
                .map(new Function<LabelName, List<String>>() {
                    @Override
                    public List<String> apply(LabelName labelName) {
                        return testResult.findAllLabels(labelName);
                    }
                })
                .filter(new Predicate<List<String>>() {
                    @Override
                    public boolean test(List<String> strings) {
                        return !strings.isEmpty();
                    }
                })
                .map(new Function<List<String>, DefaultTreeLayer>() {
                    @Override
                    public DefaultTreeLayer apply(List<String> strings) {
                        return new DefaultTreeLayer(strings);
                    }
                })
                .collect(Collectors.toList());
    }

    public static Statistic calculateStatisticByLeafs(final TestResultTreeGroup group) {
        return StreamSupport.stream(group.getChildren())
                .reduce(
                        new Statistic(),
                        new BiFunction<Statistic, TreeNode, Statistic>() {
                            @Override
                            public Statistic apply(Statistic statistic, TreeNode treeNode) {
                                return updateStatisticRecursive(statistic, treeNode);
                            }
                        },
                        new BinaryOperator<Statistic>() {
                            @Override
                            public Statistic apply(Statistic statistic, Statistic statistic2) {
                                return mergeStatistic(statistic, statistic2);
                            }
                        }
                );
    }

    public static Statistic calculateStatisticByChildren(final TestResultTreeGroup group) {
        return StreamSupport.stream(group.getChildren())
                .reduce(
                        new Statistic(),
                        new BiFunction<Statistic, TreeNode, Statistic>() {
                            @Override
                            public Statistic apply(Statistic statistic, TreeNode treeNode) {
                                return updateStatistic(statistic, treeNode);
                            }
                        },
                        new BinaryOperator<Statistic>() {
                            @Override
                            public Statistic apply(Statistic statistic, Statistic statistic2) {
                                return mergeStatistic(statistic, statistic2);
                            }
                        }
                );
    }

    public static Statistic updateStatisticRecursive(final Statistic statistic, final TreeNode treeNode) {
        if (treeNode instanceof TestResultTreeGroup) {
            statistic.merge(calculateStatisticByLeafs((TestResultTreeGroup) treeNode));
        } else if (treeNode instanceof TestResultTreeLeaf) {
            statistic.update(((TestResultTreeLeaf) treeNode).getStatus());
        }
        return statistic;
    }

    public static Statistic updateStatistic(final Statistic statistic, final TreeNode treeNode) {
        if (treeNode instanceof TestResultTreeGroup) {
            final Statistic byLeafs = calculateStatisticByLeafs((TestResultTreeGroup) treeNode);
            statistic.update(byLeafs.getStatus());
        } else if (treeNode instanceof TestResultTreeLeaf) {
            statistic.update(((TestResultTreeLeaf) treeNode).getStatus());
        }
        return statistic;
    }

    public static Statistic mergeStatistic(final Statistic a, final Statistic b) {
        final Statistic statistic = new Statistic();
        statistic.merge(a);
        statistic.merge(b);
        return statistic;
    }

    private static MessageDigest getMessageDigest() {
        try {
            return MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalStateException("Can not find hashing algorithm", e);
        }
    }
}
