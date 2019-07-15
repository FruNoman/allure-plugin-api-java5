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

public class Attachment implements Serializable {

    private static final long serialVersionUID = 1L;
    protected String uid;
    protected String name;
    protected String source;
    protected String type;
    protected Long size;

    public Attachment() {
    }

    public String getUid() {
        return this.uid;
    }

    public String getName() {
        return this.name;
    }

    public String getSource() {
        return this.source;
    }

    public String getType() {
        return this.type;
    }

    public Long getSize() {
        return this.size;
    }

    public Attachment setUid(String uid) {
        this.uid = uid;
        return this;
    }

    public Attachment setName(String name) {
        this.name = name;
        return this;
    }

    public Attachment setSource(String source) {
        this.source = source;
        return this;
    }

    public Attachment setType(String type) {
        this.type = type;
        return this;
    }

    public Attachment setSize(Long size) {
        this.size = size;
        return this;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof Attachment)) return false;
        final Attachment other = (Attachment) o;
        if (!other.canEqual((Object) this)) return false;
        final Object this$uid = this.getUid();
        final Object other$uid = other.getUid();
        if (this$uid == null ? other$uid != null : !this$uid.equals(other$uid)) return false;
        final Object this$name = this.getName();
        final Object other$name = other.getName();
        if (this$name == null ? other$name != null : !this$name.equals(other$name)) return false;
        final Object this$source = this.getSource();
        final Object other$source = other.getSource();
        if (this$source == null ? other$source != null : !this$source.equals(other$source)) return false;
        final Object this$type = this.getType();
        final Object other$type = other.getType();
        if (this$type == null ? other$type != null : !this$type.equals(other$type)) return false;
        final Object this$size = this.getSize();
        final Object other$size = other.getSize();
        if (this$size == null ? other$size != null : !this$size.equals(other$size)) return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof Attachment;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $uid = this.getUid();
        result = result * PRIME + ($uid == null ? 43 : $uid.hashCode());
        final Object $name = this.getName();
        result = result * PRIME + ($name == null ? 43 : $name.hashCode());
        final Object $source = this.getSource();
        result = result * PRIME + ($source == null ? 43 : $source.hashCode());
        final Object $type = this.getType();
        result = result * PRIME + ($type == null ? 43 : $type.hashCode());
        final Object $size = this.getSize();
        result = result * PRIME + ($size == null ? 43 : $size.hashCode());
        return result;
    }

    public String toString() {
        return "Attachment(uid=" + this.getUid() + ", name=" + this.getName() + ", source=" + this.getSource() + ", type=" + this.getType() + ", size=" + this.getSize() + ")";
    }
}
