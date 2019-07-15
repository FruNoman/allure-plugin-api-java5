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
import java8.util.Objects;
import java8.util.function.BiFunction;
import java8.util.function.Consumer;

import static io.qameta.allure.entity.EntityUtils.firstNonNull;
import static java.lang.Long.MAX_VALUE;

public class GroupTime implements Serializable {

    private static final long serialVersionUID = 1L;

    protected Long start;
    protected Long stop;
    protected Long duration;
    protected Long minDuration;
    protected Long maxDuration;
    protected Long sumDuration;

    public GroupTime() {
    }

    public void merge(final GroupTime groupTime) {
        if (Objects.isNull(groupTime)) {
            return;
        }
        update(firstNonNull(getStart(), MAX_VALUE), groupTime.getStart(), Math::min, this::setStart);
        update(firstNonNull(getStop(), 0L), groupTime.getStop(), Math::max, this::setStop);
        update(getStop(), getStart(), (a, b) -> a - b, this::setDuration);
        update(firstNonNull(getMinDuration(), MAX_VALUE), groupTime.getMinDuration(), Math::min, this::setMinDuration);
        update(firstNonNull(getMaxDuration(), 0L), groupTime.getMaxDuration(), Math::max, this::setMaxDuration);
        update(firstNonNull(getSumDuration(), 0L), groupTime.getSumDuration(), (a, b) -> a + b, this::setSumDuration);
    }

    public void update(final Timeable timeable) {
        if (Objects.isNull(timeable)) {
            return;
        }
        update(timeable.getTime());
    }

    public void update(final Time time) {
        if (Objects.isNull(time)) {
            return;
        }
        update(firstNonNull(getStart(), MAX_VALUE), time.getStart(), Math::min, this::setStart);
        update(firstNonNull(getStop(), 0L), time.getStop(), Math::max, this::setStop);
        update(getStop(), getStart(), (a, b) -> a - b, this::setDuration);
        update(firstNonNull(getMinDuration(), MAX_VALUE), time.getDuration(), Math::min, this::setMinDuration);
        update(firstNonNull(getMaxDuration(), 0L), time.getDuration(), Math::max, this::setMaxDuration);
        update(firstNonNull(getSumDuration(), 0L), time.getDuration(), (a, b) -> a + b, this::setSumDuration);
    }

    protected static <T> void update(final T first, final T second,
                                     final BiFunction<T, T, T> merge, final Consumer<T> setter) {
        if (Objects.nonNull(first) && Objects.nonNull(second)) {
            setter.accept(merge.apply(first, second));
        }
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

    public Long getMinDuration() {
        return this.minDuration;
    }

    public Long getMaxDuration() {
        return this.maxDuration;
    }

    public Long getSumDuration() {
        return this.sumDuration;
    }

    public GroupTime setStart(Long start) {
        this.start = start;
        return this;
    }

    public GroupTime setStop(Long stop) {
        this.stop = stop;
        return this;
    }

    public GroupTime setDuration(Long duration) {
        this.duration = duration;
        return this;
    }

    public GroupTime setMinDuration(Long minDuration) {
        this.minDuration = minDuration;
        return this;
    }

    public GroupTime setMaxDuration(Long maxDuration) {
        this.maxDuration = maxDuration;
        return this;
    }

    public GroupTime setSumDuration(Long sumDuration) {
        this.sumDuration = sumDuration;
        return this;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof GroupTime)) return false;
        final GroupTime other = (GroupTime) o;
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
        final Object this$minDuration = this.getMinDuration();
        final Object other$minDuration = other.getMinDuration();
        if (this$minDuration == null ? other$minDuration != null : !this$minDuration.equals(other$minDuration))
            return false;
        final Object this$maxDuration = this.getMaxDuration();
        final Object other$maxDuration = other.getMaxDuration();
        if (this$maxDuration == null ? other$maxDuration != null : !this$maxDuration.equals(other$maxDuration))
            return false;
        final Object this$sumDuration = this.getSumDuration();
        final Object other$sumDuration = other.getSumDuration();
        if (this$sumDuration == null ? other$sumDuration != null : !this$sumDuration.equals(other$sumDuration))
            return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof GroupTime;
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
        final Object $minDuration = this.getMinDuration();
        result = result * PRIME + ($minDuration == null ? 43 : $minDuration.hashCode());
        final Object $maxDuration = this.getMaxDuration();
        result = result * PRIME + ($maxDuration == null ? 43 : $maxDuration.hashCode());
        final Object $sumDuration = this.getSumDuration();
        result = result * PRIME + ($sumDuration == null ? 43 : $sumDuration.hashCode());
        return result;
    }

    public String toString() {
        return "GroupTime(start=" + this.getStart() + ", stop=" + this.getStop() + ", duration=" + this.getDuration() + ", minDuration=" + this.getMinDuration() + ", maxDuration=" + this.getMaxDuration() + ", sumDuration=" + this.getSumDuration() + ")";
    }
}
