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
package io.qameta.allure;

import io.qameta.allure.core.Configuration;
import io.qameta.allure.core.LaunchResults;
import io.qameta.allure.entity.TestResult;
import io.qameta.allure.metric.Metric;
import io.qameta.allure.metric.MetricLine;
import java8.util.function.Function;
import java8.util.stream.Stream;
import java8.util.stream.StreamSupport;

import java.io.IOException;

import java.util.Collection;
import java.util.List;
import java.util.Set;

import java8.util.stream.Collectors;


/**
 * @author charlie (Dmitry Baev).
 */
public abstract class CommonMetricAggregator implements Aggregator {

    private final String location;

    private final String fileName;

    protected CommonMetricAggregator(final String fileName) {
        this(Constants.EXPORT_DIR, fileName);
    }

    protected CommonMetricAggregator(final String location, final String fileName) {
        this.location = location;
        this.fileName = fileName;
    }

    @Override
    public void aggregate(final Configuration configuration,
                          final List<LaunchResults> launchesResults,
                          final String outputDirectory) throws IOException {
//        final Path dataFolder = Files.createDirectories(outputDirectory.resolve(location));
//        final Path dataFile = dataFolder.resolve(fileName);
//        try (Writer writer = Files.newBufferedWriter(dataFile, StandardCharsets.UTF_8)) {
//            writer.write(getData(launchesResults));
//        }
    }

    public abstract List<Metric> getMetrics();

    @SuppressWarnings("MultipleStringLiterals")
    protected String getData(final List<LaunchResults> launchesResults) {
        final List<Metric> metrics = getMetrics();
        final List<TestResult> results = StreamSupport.stream(launchesResults)
                .map(new Function<LaunchResults, Set<TestResult>>() {
                    @Override
                    public  Set<TestResult> apply(LaunchResults launchResults) {
                        return launchResults.getAllResults();
                    }
                })
                .flatMap(new Function<Set<TestResult>, Stream<TestResult>>() {
                    @Override
                    public Stream<TestResult> apply(Set<TestResult> testResults) {
                        return StreamSupport.stream(testResults);
                    }
                })
                .collect(Collectors.toList());

        for (TestResult result : results) {
            for (Metric metric : metrics) {
                metric.update(result);
            }
        }

        return StreamSupport.stream(metrics)
                .map(new Function<Metric, List<MetricLine>>() {
                    @Override
                    public List<MetricLine> apply(Metric metric) {
                        return metric.getLines();
                    }
                })
                .flatMap(new Function<List<MetricLine>, Stream<MetricLine>>() {
                    @Override
                    public Stream<MetricLine> apply(List<MetricLine> metricLines) {
                        return StreamSupport.stream(metricLines);
                    }
                })
                .map(new Function<MetricLine, String>() {
                    @Override
                    public String apply(MetricLine metricLine) {
                        return metricLine.asString();
                    }
                })
                .collect(Collectors.joining("\n", "", "\n"));
    }

}
