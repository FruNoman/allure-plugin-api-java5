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

import static java.util.Objects.isNull;

public class Time implements Serializable {

    private static final long serialVersionUID = 1L;

    protected Long start;
    protected Long stop;
    protected Long duration;

    public Time() {
    }

    public static Time create(final Long duration) {
        return new Time().setDuration(duration);
    }

    public static Time create(final Long start, final Long stop) {
        return new Time()
                .setStart(start)
                .setStop(stop)
                .setDuration(isNull(start) || isNull(stop) ? null : stop - start);
    }

    public Long getStart() {
        return this.start;
    }

    public Long getStop() {
        return this.stop;
    }

    public Long getDuration() {
        return this.duration;
    }

    public Time setStart(Long start) {
        this.start = start;
        return this;
    }

    public Time setStop(Long stop) {
        this.stop = stop;
        return this;
    }

    public Time setDuration(Long duration) {
        this.duration = duration;
        return this;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof Time)) return false;
        final Time other = (Time) o;
        if (!other.canEqual((Object) this)) return false;
        final Object this$start = this.getStart();
        final Object other$start = other.getStart();
        if (this$start == null ? other$start != null : !this$start.equals(other$start)) return false;
        final Object this$stop = this.getStop();
        final Object other$stop = other.getStop();
        if (this$stop == null ? other$stop != null : !this$stop.equals(other$stop)) return false;
        final Object this$duration = this.getDuration();
        final Object other$duration = other.getDuration();
        if (this$duration == null ? other$duration != null : !this$duration.equals(other$duration)) return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof Time;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $start = this.getStart();
        result = result * PRIME + ($start == null ? 43 : $start.hashCode());
        final Object $stop = this.getStop();
        result = result * PRIME + ($stop == null ? 43 : $stop.hashCode());
        final Object $duration = this.getDuration();
        result = result * PRIME + ($duration == null ? 43 : $duration.hashCode());
        return result;
    }

    public String toString() {
        return "Time(start=" + this.getStart() + ", stop=" + this.getStop() + ", duration=" + this.getDuration() + ")";
    }
}
