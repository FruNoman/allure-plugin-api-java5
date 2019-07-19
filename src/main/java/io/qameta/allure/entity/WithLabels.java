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
package io.qameta.allure.entity;

import java.util.List;
import java8.util.Optional;
import java8.util.function.Function;
import java8.util.function.Predicate;
import java8.util.stream.Collector;
import java8.util.stream.Collectors;
import java8.util.stream.StreamSupport;

/**
 * @author Dmitry Baev baev@qameta.io
 * Date: 31.01.16
 */
public interface WithLabels {

    List<Label> getLabels();

    void setLabels(List<Label> labels);

    default <T> T findAll(LabelName name, Collector<String, ?, T> collector) {
        return findAll(name.value(), collector);
    }

    default <T> T findAll(String name, Collector<String, ?, T> collector) {
        return StreamSupport.stream(getLabels())
                .filter(new Predicate<Label>() {
                    @Override
                    public boolean test(Label label) {
                        return name.equals(label.getName());
                    }
                })
                .map(new Function<Label, String>() {
                    @Override
                    public String apply(Label label) {
                        return label.getValue();
                    }
                })
                .collect(collector);
    }

    default List<String> findAll(LabelName name) {
        return findAll(name, Collectors.toList());
    }

    default List<String> findAll(String name) {
        return findAll(name, Collectors.toList());
    }

    default Optional<String> findOne(LabelName name) {
        return findOne(name.value());
    }

    default Optional<String> findOne(String name) {
        return StreamSupport.stream(getLabels())
                .filter(new Predicate<Label>() {
                    @Override
                    public boolean test(Label label) {
                        return name.equals(label.getName());
                    }
                })
                .map(new Function<Label, String>() {
                    @Override
                    public String apply(Label label) {
                        return label.getValue();
                    }
                })
                .findAny();
    }

    default void addLabelIfNotExists(LabelName name, String value) {
        addLabelIfNotExists(name.value(), value);
    }

    default void addLabelIfNotExists(String name, String value) {
        if (value == null || name == null) {
            return;
        }
        final Optional<String> any = StreamSupport.stream(getLabels())
                .map(new Function<Label, String>() {
                    @Override
                    public String apply(Label label) {
                        return label.getName();
                    }
                })
                .filter(new Predicate<String>() {
                    @Override
                    public boolean test(String s) {
                        return name.equals(s);
                    }
                })
                .findAny();
        if (!any.isPresent()) {
            addLabel(name, value);
        }
    }

    default void addLabel(String name, String value) {
        getLabels().add(new Label().setName(name).setValue(value));
    }
}
