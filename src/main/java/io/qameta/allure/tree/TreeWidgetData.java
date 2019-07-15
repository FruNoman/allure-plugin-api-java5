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
package io.qameta.allure.tree;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author charlie (Dmitry Baev).
 */
public class TreeWidgetData implements Serializable {

    private static final long serialVersionUID = 1L;

    protected long total;
    protected List<TreeWidgetItem> items = new ArrayList<>();

    public TreeWidgetData() {
    }

    public long getTotal() {
        return this.total;
    }

    public List<TreeWidgetItem> getItems() {
        return this.items;
    }

    public TreeWidgetData setTotal(long total) {
        this.total = total;
        return this;
    }

    public TreeWidgetData setItems(List<TreeWidgetItem> items) {
        this.items = items;
        return this;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof TreeWidgetData)) return false;
        final TreeWidgetData other = (TreeWidgetData) o;
        if (!other.canEqual((Object) this)) return false;
        if (this.getTotal() != other.getTotal()) return false;
        final Object this$items = this.getItems();
        final Object other$items = other.getItems();
        if (this$items == null ? other$items != null : !this$items.equals(other$items)) return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof TreeWidgetData;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final long $total = this.getTotal();
        result = result * PRIME + (int) ($total >>> 32 ^ $total);
        final Object $items = this.getItems();
        result = result * PRIME + ($items == null ? 43 : $items.hashCode());
        return result;
    }

    public String toString() {
        return "TreeWidgetData(total=" + this.getTotal() + ", items=" + this.getItems() + ")";
    }
}
