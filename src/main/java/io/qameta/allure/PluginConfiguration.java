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
package io.qameta.allure;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author charlie (Dmitry Baev).
 */
public class PluginConfiguration implements Serializable {

    private static final long serialVersionUID = 1L;

    protected String id;
    protected String name;
    protected String description;
    protected List<String> extensions = new ArrayList<>();
    protected List<String> jsFiles = new ArrayList<>();
    protected List<String> cssFiles = new ArrayList<>();

    public PluginConfiguration() {
    }

    public String getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public String getDescription() {
        return this.description;
    }

    public List<String> getExtensions() {
        return this.extensions;
    }

    public List<String> getJsFiles() {
        return this.jsFiles;
    }

    public List<String> getCssFiles() {
        return this.cssFiles;
    }

    public PluginConfiguration setId(String id) {
        this.id = id;
        return this;
    }

    public PluginConfiguration setName(String name) {
        this.name = name;
        return this;
    }

    public PluginConfiguration setDescription(String description) {
        this.description = description;
        return this;
    }

    public PluginConfiguration setExtensions(List<String> extensions) {
        this.extensions = extensions;
        return this;
    }

    public PluginConfiguration setJsFiles(List<String> jsFiles) {
        this.jsFiles = jsFiles;
        return this;
    }

    public PluginConfiguration setCssFiles(List<String> cssFiles) {
        this.cssFiles = cssFiles;
        return this;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof PluginConfiguration)) return false;
        final PluginConfiguration other = (PluginConfiguration) o;
        if (!other.canEqual((Object) this)) return false;
        final Object this$id = this.getId();
        final Object other$id = other.getId();
        if (this$id == null ? other$id != null : !this$id.equals(other$id)) return false;
        final Object this$name = this.getName();
        final Object other$name = other.getName();
        if (this$name == null ? other$name != null : !this$name.equals(other$name)) return false;
        final Object this$description = this.getDescription();
        final Object other$description = other.getDescription();
        if (this$description == null ? other$description != null : !this$description.equals(other$description))
            return false;
        final Object this$extensions = this.getExtensions();
        final Object other$extensions = other.getExtensions();
        if (this$extensions == null ? other$extensions != null : !this$extensions.equals(other$extensions))
            return false;
        final Object this$jsFiles = this.getJsFiles();
        final Object other$jsFiles = other.getJsFiles();
        if (this$jsFiles == null ? other$jsFiles != null : !this$jsFiles.equals(other$jsFiles)) return false;
        final Object this$cssFiles = this.getCssFiles();
        final Object other$cssFiles = other.getCssFiles();
        if (this$cssFiles == null ? other$cssFiles != null : !this$cssFiles.equals(other$cssFiles)) return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof PluginConfiguration;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $id = this.getId();
        result = result * PRIME + ($id == null ? 43 : $id.hashCode());
        final Object $name = this.getName();
        result = result * PRIME + ($name == null ? 43 : $name.hashCode());
        final Object $description = this.getDescription();
        result = result * PRIME + ($description == null ? 43 : $description.hashCode());
        final Object $extensions = this.getExtensions();
        result = result * PRIME + ($extensions == null ? 43 : $extensions.hashCode());
        final Object $jsFiles = this.getJsFiles();
        result = result * PRIME + ($jsFiles == null ? 43 : $jsFiles.hashCode());
        final Object $cssFiles = this.getCssFiles();
        result = result * PRIME + ($cssFiles == null ? 43 : $cssFiles.hashCode());
        return result;
    }

    public String toString() {
        return "PluginConfiguration(id=" + this.getId() + ", name=" + this.getName() + ", description=" + this.getDescription() + ", extensions=" + this.getExtensions() + ", jsFiles=" + this.getJsFiles() + ", cssFiles=" + this.getCssFiles() + ")";
    }
}
