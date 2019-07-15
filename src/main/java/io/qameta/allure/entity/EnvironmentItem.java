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

/**
 * @author charlie (Dmitry Baev).
 */

public class EnvironmentItem implements Serializable {

    private static final long serialVersionUID = 1L;

    protected List<String> values = new ArrayList<>();
    protected String name;

    public EnvironmentItem() {
    }

    public List<String> getValues() {
        return this.values;
    }

    public String getName() {
        return this.name;
    }

    public EnvironmentItem setValues(List<String> values) {
        this.values = values;
        return this;
    }

    public EnvironmentItem setName(String name) {
        this.name = name;
        return this;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof EnvironmentItem)) return false;
        final EnvironmentItem other = (EnvironmentItem) o;
        if (!other.canEqual((Object) this)) return false;
        final Object this$values = this.getValues();
        final Object other$values = other.getValues();
        if (this$values == null ? other$values != null : !this$values.equals(other$values)) return false;
        final Object this$name = this.getName();
        final Object other$name = other.getName();
        if (this$name == null ? other$name != null : !this$name.equals(other$name)) return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof EnvironmentItem;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $values = this.getValues();
        result = result * PRIME + ($values == null ? 43 : $values.hashCode());
        final Object $name = this.getName();
        result = result * PRIME + ($name == null ? 43 : $name.hashCode());
        return result;
    }

    public String toString() {
        return "EnvironmentItem(values=" + this.getValues() + ", name=" + this.getName() + ")";
    }
}
