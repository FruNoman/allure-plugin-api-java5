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

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.Comparator;

import java8.util.Comparators;
import java8.util.Objects;
import java8.util.function.Function;

/**
 * @author charlie (Dmitry Baev).
 */
public class Statistic implements Serializable {

    private static final long serialVersionUID = 1L;

    protected long failed;
    protected long broken;
    protected long skipped;
    protected long passed;
    protected long unknown;

    public Statistic() {
    }

    @JsonProperty
    public long getTotal() {
        return getFailed() + getBroken() + getPassed() + getSkipped() + getUnknown();
    }

    /**
     * To ignore total property during deserialization. Should not be used manually.
     *
     * @deprecated Do not use it manually.
     */
    @Deprecated
    @JsonProperty
    public void setTotal(final long total) {
        //do nothing
    }

    public long get(final Status status) {
        switch (status) {
            case FAILED:
                return getFailed();
            case BROKEN:
                return getBroken();
            case PASSED:
                return getPassed();
            case SKIPPED:
                return getSkipped();
            default:
                return getUnknown();
        }
    }

    @JsonIgnore
    public Status getStatus() {
        for (final Status status : Status.values()) {
            if (get(status) > 0) {
                return status;
            }
        }
        return Status.UNKNOWN;
    }

    public void update(final Statusable statusable) {
        if (Objects.isNull(statusable)) {
            return;
        }
        update(statusable.getStatus());
    }

    public void update(final Status status) {
        if (Objects.isNull(status)) {
            return;
        }
        switch (status) {
            case FAILED:
                setFailed(getFailed() + 1);
                break;
            case BROKEN:
                setBroken(getBroken() + 1);
                break;
            case PASSED:
                setPassed(getPassed() + 1);
                break;
            case SKIPPED:
                setSkipped(getSkipped() + 1);
                break;
            default:
                setUnknown(getUnknown() + 1);
                break;
        }
    }

    public void merge(final Statistic other) {
        if (Objects.isNull(other)) {
            return;
        }
        setFailed(getFailed() + other.getFailed());
        setBroken(getBroken() + other.getBroken());
        setPassed(getPassed() + other.getPassed());
        setSkipped(getSkipped() + other.getSkipped());
        setUnknown(getUnknown() + other.getUnknown());
    }

    public static Comparator<Statistic> comparator() {
        Comparator<Statistic> faileds = Comparators.comparing(new Function<Statistic, Long>() {
            @Override
            public Long apply(Statistic statistic) {
                return statistic.getFailed();
            }
        });
        Comparator<Statistic> brokens = Comparators.thenComparing(faileds,new Function<Statistic, Long>() {
            @Override
            public Long apply(Statistic statistic) {
                return statistic.getBroken();
            }
        });
        Comparator<Statistic> passed = Comparators.thenComparing(brokens,new Function<Statistic, Long>() {
            @Override
            public Long apply(Statistic statistic) {
                return statistic.getPassed();
            }
        });
        Comparator<Statistic> skipped = Comparators.thenComparing(passed,new Function<Statistic, Long>() {
            @Override
            public Long apply(Statistic statistic) {
                return statistic.getSkipped();
            }
        });
        Comparator<Statistic> unknown = Comparators.thenComparing(skipped,new Function<Statistic, Long>() {
            @Override
            public Long apply(Statistic statistic) {
                return statistic.getUnknown();
            }
        });
        return unknown;
    }

    public long getFailed() {
        return this.failed;
    }

    public long getBroken() {
        return this.broken;
    }

    public long getSkipped() {
        return this.skipped;
    }

    public long getPassed() {
        return this.passed;
    }

    public long getUnknown() {
        return this.unknown;
    }

    public Statistic setFailed(long failed) {
        this.failed = failed;
        return this;
    }

    public Statistic setBroken(long broken) {
        this.broken = broken;
        return this;
    }

    public Statistic setSkipped(long skipped) {
        this.skipped = skipped;
        return this;
    }

    public Statistic setPassed(long passed) {
        this.passed = passed;
        return this;
    }

    public Statistic setUnknown(long unknown) {
        this.unknown = unknown;
        return this;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof Statistic)) return false;
        final Statistic other = (Statistic) o;
        if (!other.canEqual((Object) this)) return false;
        if (this.getFailed() != other.getFailed()) return false;
        if (this.getBroken() != other.getBroken()) return false;
        if (this.getSkipped() != other.getSkipped()) return false;
        if (this.getPassed() != other.getPassed()) return false;
        if (this.getUnknown() != other.getUnknown()) return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof Statistic;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final long $failed = this.getFailed();
        result = result * PRIME + (int) ($failed >>> 32 ^ $failed);
        final long $broken = this.getBroken();
        result = result * PRIME + (int) ($broken >>> 32 ^ $broken);
        final long $skipped = this.getSkipped();
        result = result * PRIME + (int) ($skipped >>> 32 ^ $skipped);
        final long $passed = this.getPassed();
        result = result * PRIME + (int) ($passed >>> 32 ^ $passed);
        final long $unknown = this.getUnknown();
        result = result * PRIME + (int) ($unknown >>> 32 ^ $unknown);
        return result;
    }

    public String toString() {
        return "Statistic(failed=" + this.getFailed() + ", broken=" + this.getBroken() + ", skipped=" + this.getSkipped() + ", passed=" + this.getPassed() + ", unknown=" + this.getUnknown() + ")";
    }
}
