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

import java.io.Serializable;
import java.util.*;

import java8.util.*;
import java8.util.Optional;
import java8.util.function.Function;
import java8.util.function.Predicate;
import java8.util.stream.Collector;
import java8.util.stream.Collectors;
import java8.util.stream.StreamSupport;

import static java.util.Comparator.*;


@SuppressWarnings("PMD.TooManyFields")
public class TestResult implements Serializable, Nameable, Parameterizable, Statusable, Timeable {

    private static final long serialVersionUID = 1L;

    protected String uid;
    protected String name;
    protected String fullName;
    protected String historyId;
    protected String testId;
    protected Time time = new Time();
    protected String description;
    protected String descriptionHtml;
    protected Status status;
    protected String statusMessage;
    protected String statusTrace;

    protected boolean flaky;
    protected boolean newFailed;

    //    Execution
    protected List<StageResult> beforeStages = new ArrayList<>();
    protected StageResult testStage;
    protected List<StageResult> afterStages = new ArrayList<>();

    //    Meta
    protected List<Label> labels = new ArrayList<>();
    protected List<Parameter> parameters = new ArrayList<>();
    protected List<Link> links = new ArrayList<>();
    protected boolean hidden;
    protected boolean retry;
    protected final Map<String, Object> extra = new HashMap<>();

    public TestResult() {
    }

    @JsonProperty
    public String getSource() {
        return getUid() + ".json";
    }

    public void addExtraBlock(final String blockName, final Object block) {
        extra.put(blockName, block);
    }

    @SuppressWarnings("unchecked")
    public <T> T getExtraBlock(final String blockName, final T defaultValue) {
        return (T) extra.computeIfAbsent(blockName, name -> defaultValue);
    }

    @SuppressWarnings("unchecked")
    public <T> T getExtraBlock(final String blockName) {
        return (T) extra.get(blockName);
    }

    public boolean hasExtraBlock(final String blockName) {
        return extra.containsKey(blockName);
    }

    public <T> T findAllLabels(final LabelName name, final Collector<String, ?, T> collector) {
        return findAllLabels(name.value(), collector);
    }

    public <T> T findAllLabels(final String name, final Collector<String, ?, T> collector) {
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

    public List<String> findAllLabels(final LabelName name) {
        return findAllLabels(name, Collectors.toList());
    }

    public List<String> findAllLabels(final String name) {
        return findAllLabels(name, Collectors.toList());
    }

    public Optional<String> findOneLabel(final LabelName name) {
        return findOneLabel(name.value());
    }

    public Optional<String> findOneLabel(final String name) {
        return StreamSupport.stream(getLabels())
                .filter(new Predicate<Label>() {
                    @Override
                    public boolean test(Label label) {
                        return name.equals(label.getName());
                    }
                })
                .findAny()
                .map(new Function<Label, String>() {
                    @Override
                    public String apply(Label label) {
                        return label.getValue();
                    }
                });
    }

    public void addLabelIfNotExists(final LabelName name, final String value) {
        addLabelIfNotExists(name.value(), value);
    }

    public void addLabelIfNotExists(final String name, final String value) {
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

    public void addLabel(final String name, final String value) {
        getLabels().add(new Label().setName(name).setValue(value));
    }

    public static Comparator<TestResult> comparingByTime() {
        return comparingByTimeAsc().reversed();
    }

    public static Comparator<TestResult> comparingByTimeAsc() {
        return comparing(
                TestResult::getTime,
                nullsFirst(comparing(Time::getStart, nullsFirst(naturalOrder())))
        );
    }

    public Map<String, String> toMap() {
        final Map<String, String> map = new HashMap<>();
        for (Label l : getLabels()) {
            map.put(l.getName(), l.getValue());
        }
        return map;
    }

    public String getUid() {
        return this.uid;
    }

    public String getName() {
        return this.name;
    }

    public String getFullName() {
        return this.fullName;
    }

    public String getHistoryId() {
        return this.historyId;
    }

    public String getTestId() {
        return this.testId;
    }

    public Time getTime() {
        return this.time;
    }

    public String getDescription() {
        return this.description;
    }

    public String getDescriptionHtml() {
        return this.descriptionHtml;
    }

    public Status getStatus() {
        return this.status;
    }

    public String getStatusMessage() {
        return this.statusMessage;
    }

    public String getStatusTrace() {
        return this.statusTrace;
    }

    public boolean isFlaky() {
        return this.flaky;
    }

    public boolean isNewFailed() {
        return this.newFailed;
    }

    public List<StageResult> getBeforeStages() {
        return this.beforeStages;
    }

    public StageResult getTestStage() {
        return this.testStage;
    }

    public List<StageResult> getAfterStages() {
        return this.afterStages;
    }

    public List<Label> getLabels() {
        return this.labels;
    }

    public List<Parameter> getParameters() {
        return this.parameters;
    }

    public List<Link> getLinks() {
        return this.links;
    }

    public boolean isHidden() {
        return this.hidden;
    }

    public boolean isRetry() {
        return this.retry;
    }

    public Map<String, Object> getExtra() {
        return this.extra;
    }

    public TestResult setUid(String uid) {
        this.uid = uid;
        return this;
    }

    public TestResult setName(String name) {
        this.name = name;
        return this;
    }

    public TestResult setFullName(String fullName) {
        this.fullName = fullName;
        return this;
    }

    public TestResult setHistoryId(String historyId) {
        this.historyId = historyId;
        return this;
    }

    public TestResult setTestId(String testId) {
        this.testId = testId;
        return this;
    }

    public TestResult setTime(Time time) {
        this.time = time;
        return this;
    }

    public TestResult setDescription(String description) {
        this.description = description;
        return this;
    }

    public TestResult setDescriptionHtml(String descriptionHtml) {
        this.descriptionHtml = descriptionHtml;
        return this;
    }

    public TestResult setStatus(Status status) {
        this.status = status;
        return this;
    }

    public TestResult setStatusMessage(String statusMessage) {
        this.statusMessage = statusMessage;
        return this;
    }

    public TestResult setStatusTrace(String statusTrace) {
        this.statusTrace = statusTrace;
        return this;
    }

    public TestResult setFlaky(boolean flaky) {
        this.flaky = flaky;
        return this;
    }

    public TestResult setNewFailed(boolean newFailed) {
        this.newFailed = newFailed;
        return this;
    }

    public TestResult setBeforeStages(List<StageResult> beforeStages) {
        this.beforeStages = beforeStages;
        return this;
    }

    public TestResult setTestStage(StageResult testStage) {
        this.testStage = testStage;
        return this;
    }

    public TestResult setAfterStages(List<StageResult> afterStages) {
        this.afterStages = afterStages;
        return this;
    }

    public TestResult setLabels(List<Label> labels) {
        this.labels = labels;
        return this;
    }

    public TestResult setParameters(List<Parameter> parameters) {
        this.parameters = parameters;
        return this;
    }

    public TestResult setLinks(List<Link> links) {
        this.links = links;
        return this;
    }

    public TestResult setHidden(boolean hidden) {
        this.hidden = hidden;
        return this;
    }

    public TestResult setRetry(boolean retry) {
        this.retry = retry;
        return this;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof TestResult)) return false;
        final TestResult other = (TestResult) o;
        if (!other.canEqual((Object) this)) return false;
        final Object this$uid = this.getUid();
        final Object other$uid = other.getUid();
        if (this$uid == null ? other$uid != null : !this$uid.equals(other$uid)) return false;
        final Object this$name = this.getName();
        final Object other$name = other.getName();
        if (this$name == null ? other$name != null : !this$name.equals(other$name)) return false;
        final Object this$fullName = this.getFullName();
        final Object other$fullName = other.getFullName();
        if (this$fullName == null ? other$fullName != null : !this$fullName.equals(other$fullName)) return false;
        final Object this$historyId = this.getHistoryId();
        final Object other$historyId = other.getHistoryId();
        if (this$historyId == null ? other$historyId != null : !this$historyId.equals(other$historyId)) return false;
        final Object this$testId = this.getTestId();
        final Object other$testId = other.getTestId();
        if (this$testId == null ? other$testId != null : !this$testId.equals(other$testId)) return false;
        final Object this$time = this.getTime();
        final Object other$time = other.getTime();
        if (this$time == null ? other$time != null : !this$time.equals(other$time)) return false;
        final Object this$description = this.getDescription();
        final Object other$description = other.getDescription();
        if (this$description == null ? other$description != null : !this$description.equals(other$description))
            return false;
        final Object this$descriptionHtml = this.getDescriptionHtml();
        final Object other$descriptionHtml = other.getDescriptionHtml();
        if (this$descriptionHtml == null ? other$descriptionHtml != null : !this$descriptionHtml.equals(other$descriptionHtml))
            return false;
        final Object this$status = this.getStatus();
        final Object other$status = other.getStatus();
        if (this$status == null ? other$status != null : !this$status.equals(other$status)) return false;
        final Object this$statusMessage = this.getStatusMessage();
        final Object other$statusMessage = other.getStatusMessage();
        if (this$statusMessage == null ? other$statusMessage != null : !this$statusMessage.equals(other$statusMessage))
            return false;
        final Object this$statusTrace = this.getStatusTrace();
        final Object other$statusTrace = other.getStatusTrace();
        if (this$statusTrace == null ? other$statusTrace != null : !this$statusTrace.equals(other$statusTrace))
            return false;
        if (this.isFlaky() != other.isFlaky()) return false;
        if (this.isNewFailed() != other.isNewFailed()) return false;
        final Object this$beforeStages = this.getBeforeStages();
        final Object other$beforeStages = other.getBeforeStages();
        if (this$beforeStages == null ? other$beforeStages != null : !this$beforeStages.equals(other$beforeStages))
            return false;
        final Object this$testStage = this.getTestStage();
        final Object other$testStage = other.getTestStage();
        if (this$testStage == null ? other$testStage != null : !this$testStage.equals(other$testStage)) return false;
        final Object this$afterStages = this.getAfterStages();
        final Object other$afterStages = other.getAfterStages();
        if (this$afterStages == null ? other$afterStages != null : !this$afterStages.equals(other$afterStages))
            return false;
        final Object this$labels = this.getLabels();
        final Object other$labels = other.getLabels();
        if (this$labels == null ? other$labels != null : !this$labels.equals(other$labels)) return false;
        final Object this$parameters = this.getParameters();
        final Object other$parameters = other.getParameters();
        if (this$parameters == null ? other$parameters != null : !this$parameters.equals(other$parameters))
            return false;
        final Object this$links = this.getLinks();
        final Object other$links = other.getLinks();
        if (this$links == null ? other$links != null : !this$links.equals(other$links)) return false;
        if (this.isHidden() != other.isHidden()) return false;
        if (this.isRetry() != other.isRetry()) return false;
        final Object this$extra = this.getExtra();
        final Object other$extra = other.getExtra();
        if (this$extra == null ? other$extra != null : !this$extra.equals(other$extra)) return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof TestResult;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $uid = this.getUid();
        result = result * PRIME + ($uid == null ? 43 : $uid.hashCode());
        final Object $name = this.getName();
        result = result * PRIME + ($name == null ? 43 : $name.hashCode());
        final Object $fullName = this.getFullName();
        result = result * PRIME + ($fullName == null ? 43 : $fullName.hashCode());
        final Object $historyId = this.getHistoryId();
        result = result * PRIME + ($historyId == null ? 43 : $historyId.hashCode());
        final Object $testId = this.getTestId();
        result = result * PRIME + ($testId == null ? 43 : $testId.hashCode());
        final Object $time = this.getTime();
        result = result * PRIME + ($time == null ? 43 : $time.hashCode());
        final Object $description = this.getDescription();
        result = result * PRIME + ($description == null ? 43 : $description.hashCode());
        final Object $descriptionHtml = this.getDescriptionHtml();
        result = result * PRIME + ($descriptionHtml == null ? 43 : $descriptionHtml.hashCode());
        final Object $status = this.getStatus();
        result = result * PRIME + ($status == null ? 43 : $status.hashCode());
        final Object $statusMessage = this.getStatusMessage();
        result = result * PRIME + ($statusMessage == null ? 43 : $statusMessage.hashCode());
        final Object $statusTrace = this.getStatusTrace();
        result = result * PRIME + ($statusTrace == null ? 43 : $statusTrace.hashCode());
        result = result * PRIME + (this.isFlaky() ? 79 : 97);
        result = result * PRIME + (this.isNewFailed() ? 79 : 97);
        final Object $beforeStages = this.getBeforeStages();
        result = result * PRIME + ($beforeStages == null ? 43 : $beforeStages.hashCode());
        final Object $testStage = this.getTestStage();
        result = result * PRIME + ($testStage == null ? 43 : $testStage.hashCode());
        final Object $afterStages = this.getAfterStages();
        result = result * PRIME + ($afterStages == null ? 43 : $afterStages.hashCode());
        final Object $labels = this.getLabels();
        result = result * PRIME + ($labels == null ? 43 : $labels.hashCode());
        final Object $parameters = this.getParameters();
        result = result * PRIME + ($parameters == null ? 43 : $parameters.hashCode());
        final Object $links = this.getLinks();
        result = result * PRIME + ($links == null ? 43 : $links.hashCode());
        result = result * PRIME + (this.isHidden() ? 79 : 97);
        result = result * PRIME + (this.isRetry() ? 79 : 97);
        final Object $extra = this.getExtra();
        result = result * PRIME + ($extra == null ? 43 : $extra.hashCode());
        return result;
    }

    public String toString() {
        return "TestResult(uid=" + this.getUid() + ", name=" + this.getName() + ", fullName=" + this.getFullName() + ", historyId=" + this.getHistoryId() + ", testId=" + this.getTestId() + ", time=" + this.getTime() + ", description=" + this.getDescription() + ", descriptionHtml=" + this.getDescriptionHtml() + ", status=" + this.getStatus() + ", statusMessage=" + this.getStatusMessage() + ", statusTrace=" + this.getStatusTrace() + ", flaky=" + this.isFlaky() + ", newFailed=" + this.isNewFailed() + ", beforeStages=" + this.getBeforeStages() + ", testStage=" + this.getTestStage() + ", afterStages=" + this.getAfterStages() + ", labels=" + this.getLabels() + ", parameters=" + this.getParameters() + ", links=" + this.getLinks() + ", hidden=" + this.isHidden() + ", retry=" + this.isRetry() + ", extra=" + this.getExtra() + ")";
    }
}
