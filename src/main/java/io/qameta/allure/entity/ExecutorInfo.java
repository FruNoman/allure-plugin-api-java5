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

/**
 * @author charlie (Dmitry Baev).
 */
public class ExecutorInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    protected String name;
    protected String type;
    protected String url;
    protected Long buildOrder;
    protected String buildName;
    protected String buildUrl;
    protected String reportName;
    protected String reportUrl;

    public ExecutorInfo() {
    }

    public String getName() {
        return this.name;
    }

    public String getType() {
        return this.type;
    }

    public String getUrl() {
        return this.url;
    }

    public Long getBuildOrder() {
        return this.buildOrder;
    }

    public String getBuildName() {
        return this.buildName;
    }

    public String getBuildUrl() {
        return this.buildUrl;
    }

    public String getReportName() {
        return this.reportName;
    }

    public String getReportUrl() {
        return this.reportUrl;
    }

    public ExecutorInfo setName(String name) {
        this.name = name;
        return this;
    }

    public ExecutorInfo setType(String type) {
        this.type = type;
        return this;
    }

    public ExecutorInfo setUrl(String url) {
        this.url = url;
        return this;
    }

    public ExecutorInfo setBuildOrder(Long buildOrder) {
        this.buildOrder = buildOrder;
        return this;
    }

    public ExecutorInfo setBuildName(String buildName) {
        this.buildName = buildName;
        return this;
    }

    public ExecutorInfo setBuildUrl(String buildUrl) {
        this.buildUrl = buildUrl;
        return this;
    }

    public ExecutorInfo setReportName(String reportName) {
        this.reportName = reportName;
        return this;
    }

    public ExecutorInfo setReportUrl(String reportUrl) {
        this.reportUrl = reportUrl;
        return this;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof ExecutorInfo)) return false;
        final ExecutorInfo other = (ExecutorInfo) o;
        if (!other.canEqual((Object) this)) return false;
        final Object this$name = this.getName();
        final Object other$name = other.getName();
        if (this$name == null ? other$name != null : !this$name.equals(other$name)) return false;
        final Object this$type = this.getType();
        final Object other$type = other.getType();
        if (this$type == null ? other$type != null : !this$type.equals(other$type)) return false;
        final Object this$url = this.getUrl();
        final Object other$url = other.getUrl();
        if (this$url == null ? other$url != null : !this$url.equals(other$url)) return false;
        final Object this$buildOrder = this.getBuildOrder();
        final Object other$buildOrder = other.getBuildOrder();
        if (this$buildOrder == null ? other$buildOrder != null : !this$buildOrder.equals(other$buildOrder))
            return false;
        final Object this$buildName = this.getBuildName();
        final Object other$buildName = other.getBuildName();
        if (this$buildName == null ? other$buildName != null : !this$buildName.equals(other$buildName)) return false;
        final Object this$buildUrl = this.getBuildUrl();
        final Object other$buildUrl = other.getBuildUrl();
        if (this$buildUrl == null ? other$buildUrl != null : !this$buildUrl.equals(other$buildUrl)) return false;
        final Object this$reportName = this.getReportName();
        final Object other$reportName = other.getReportName();
        if (this$reportName == null ? other$reportName != null : !this$reportName.equals(other$reportName))
            return false;
        final Object this$reportUrl = this.getReportUrl();
        final Object other$reportUrl = other.getReportUrl();
        if (this$reportUrl == null ? other$reportUrl != null : !this$reportUrl.equals(other$reportUrl)) return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof ExecutorInfo;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $name = this.getName();
        result = result * PRIME + ($name == null ? 43 : $name.hashCode());
        final Object $type = this.getType();
        result = result * PRIME + ($type == null ? 43 : $type.hashCode());
        final Object $url = this.getUrl();
        result = result * PRIME + ($url == null ? 43 : $url.hashCode());
        final Object $buildOrder = this.getBuildOrder();
        result = result * PRIME + ($buildOrder == null ? 43 : $buildOrder.hashCode());
        final Object $buildName = this.getBuildName();
        result = result * PRIME + ($buildName == null ? 43 : $buildName.hashCode());
        final Object $buildUrl = this.getBuildUrl();
        result = result * PRIME + ($buildUrl == null ? 43 : $buildUrl.hashCode());
        final Object $reportName = this.getReportName();
        result = result * PRIME + ($reportName == null ? 43 : $reportName.hashCode());
        final Object $reportUrl = this.getReportUrl();
        result = result * PRIME + ($reportUrl == null ? 43 : $reportUrl.hashCode());
        return result;
    }

    public String toString() {
        return "ExecutorInfo(name=" + this.getName() + ", type=" + this.getType() + ", url=" + this.getUrl() + ", buildOrder=" + this.getBuildOrder() + ", buildName=" + this.getBuildName() + ", buildUrl=" + this.getBuildUrl() + ", reportName=" + this.getReportName() + ", reportUrl=" + this.getReportUrl() + ")";
    }
}
