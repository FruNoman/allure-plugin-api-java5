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

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import java8.util.Objects;
import java8.util.Optional;
import java8.util.function.BinaryOperator;
import java8.util.function.Function;
import java8.util.function.Predicate;
import java8.util.stream.StreamSupport;

import static java.util.Collections.emptyList;
import static java8.util.Objects.isNull;

public interface Summarizable {

    String getStatusMessage();

    List<Step> getSteps();

    List<Attachment> getAttachments();

    List<Parameter> getParameters();

    @JsonProperty
    default long getStepsCount() {
        final List<Step> steps = isNull(getSteps()) ? emptyList() : getSteps();
        final long stepsCount = steps.size();
        return StreamSupport.stream(steps)
                .map(new Function<Step, Long>() {
                    @Override
                    public Long apply(Step step) {
                        return step.getStepsCount();
                    }
                })
                .reduce(stepsCount, new BinaryOperator<Long>() {
                    @Override
                    public Long apply(Long aLong, Long aLong2) {
                        return Long.sum(aLong,aLong2);
                    }
                });
    }

    @JsonProperty
    default long getAttachmentsCount() {
        final List<Attachment> attachments = isNull(getAttachments()) ? emptyList() : getAttachments();
        final List<Step> steps = isNull(getSteps()) ? emptyList() : getSteps();
        final long attachmentsCount = isNull(attachments) ? 0 : attachments.size();
        return StreamSupport.stream(steps)
                .map(new Function<Step, Long>() {
                    @Override
                    public Long apply(Step step) {
                        return step.getAttachmentsCount();
                    }
                })
                .reduce(attachmentsCount, new BinaryOperator<Long>() {
                    @Override
                    public Long apply(Long aLong, Long aLong2) {
                        return Long.sum(aLong,aLong2);
                    }
                });
    }

    @JsonProperty
    default boolean shouldDisplayMessage() {
        final Optional<String> message = Optional.ofNullable(getStatusMessage());
        return message.isPresent() && StreamSupport.stream(getSteps())
                .noneMatch(new Predicate<Step>() {
                    @Override
                    public boolean test(Step step) {
                        return step.hasMessage(message.get());
                    }
                });
    }

    default boolean hasMessage(String message) {
        final Optional<String> current = Optional.ofNullable(getStatusMessage())
                .filter(new Predicate<String>() {
                    @Override
                    public boolean test(String s) {
                        return Objects.equals(s, message);
                    }
                });
        return current.isPresent() || StreamSupport.stream(getSteps())
                .anyMatch(new Predicate<Step>() {
                    @Override
                    public boolean test(Step step) {
                        return step.hasMessage(message);
                    }
                });
    }

    @JsonProperty
    default boolean hasContent() {
        final List<Attachment> attachments = isNull(getAttachments()) ? emptyList() : getAttachments();
        final List<Step> steps = isNull(getSteps()) ? emptyList() : getSteps();
        final List<Parameter> parameters = isNull(getParameters()) ? emptyList() : getParameters();
        return steps.size() + attachments.size() + parameters.size() > 0 || shouldDisplayMessage();
    }
}
