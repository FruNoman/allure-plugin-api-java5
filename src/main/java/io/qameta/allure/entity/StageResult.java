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


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class StageResult implements Serializable, Summarizable {

    private static final long serialVersionUID = 1L;

    protected String name;
    protected Time time;

    protected String description;
    protected String descriptionHtml;

    protected Status status;
    protected String statusMessage;
    protected String statusTrace;

    protected List<Step> steps = new ArrayList<>();
    protected List<Attachment> attachments = new ArrayList<>();

    protected List<Parameter> parameters = new ArrayList<>();

    public StageResult() {
    }

    @Override
    public String getStatusMessage() {
        return null;
    }

    @Override
    public List<Step> getSteps() {
        return null;
    }

    @Override
    public List<Attachment> getAttachments() {
        return null;
    }

    @Override
    public List<Parameter> getParameters() {
        return null;
    }

    @Override
    public long getStepsCount() {
        return 0;
    }

    @Override
    public long getAttachmentsCount() {
        return 0;
    }

    @Override
    public boolean shouldDisplayMessage() {
        return false;
    }

    @Override
    public boolean hasMessage(String message) {
        return false;
    }

    @Override
    public boolean hasContent() {
        return false;
    }

    public String getName() {
        return this.name;
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

    public String getStatusTrace() {
        return this.statusTrace;
    }

    public StageResult setName(String name) {
        this.name = name;
        return this;
    }

    public StageResult setTime(Time time) {
        this.time = time;
        return this;
    }

    public StageResult setDescription(String description) {
        this.description = description;
        return this;
    }

    public StageResult setDescriptionHtml(String descriptionHtml) {
        this.descriptionHtml = descriptionHtml;
        return this;
    }

    public StageResult setStatus(Status status) {
        this.status = status;
        return this;
    }

    public StageResult setStatusMessage(String statusMessage) {
        this.statusMessage = statusMessage;
        return this;
    }

    public StageResult setStatusTrace(String statusTrace) {
        this.statusTrace = statusTrace;
        return this;
    }

    public StageResult setSteps(List<Step> steps) {
        this.steps = steps;
        return this;
    }

    public StageResult setAttachments(List<Attachment> attachments) {
        this.attachments = attachments;
        return this;
    }

    public StageResult setParameters(List<Parameter> parameters) {
        this.parameters = parameters;
        return this;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof StageResult)) return false;
        final StageResult other = (StageResult) o;
        if (!other.canEqual((Object) this)) return false;
        final Object this$name = this.getName();
        final Object other$name = other.getName();
        if (this$name == null ? other$name != null : !this$name.equals(other$name)) return false;
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
        final Object this$steps = this.getSteps();
        final Object other$steps = other.getSteps();
        if (this$steps == null ? other$steps != null : !this$steps.equals(other$steps)) return false;
        final Object this$attachments = this.getAttachments();
        final Object other$attachments = other.getAttachments();
        if (this$attachments == null ? other$attachments != null : !this$attachments.equals(other$attachments))
            return false;
        final Object this$parameters = this.getParameters();
        final Object other$parameters = other.getParameters();
        if (this$parameters == null ? other$parameters != null : !this$parameters.equals(other$parameters))
            return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof StageResult;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $name = this.getName();
        result = result * PRIME + ($name == null ? 43 : $name.hashCode());
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
        final Object $steps = this.getSteps();
        result = result * PRIME + ($steps == null ? 43 : $steps.hashCode());
        final Object $attachments = this.getAttachments();
        result = result * PRIME + ($attachments == null ? 43 : $attachments.hashCode());
        final Object $parameters = this.getParameters();
        result = result * PRIME + ($parameters == null ? 43 : $parameters.hashCode());
        return result;
    }

    public String toString() {
        return "StageResult(name=" + this.getName() + ", time=" + this.getTime() + ", description=" + this.getDescription() + ", descriptionHtml=" + this.getDescriptionHtml() + ", status=" + this.getStatus() + ", statusMessage=" + this.getStatusMessage() + ", statusTrace=" + this.getStatusTrace() + ", steps=" + this.getSteps() + ", attachments=" + this.getAttachments() + ", parameters=" + this.getParameters() + ")";
    }
}
