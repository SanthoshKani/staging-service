/*
 * Copyright 2015-2018 Micro Focus or one of its affiliates.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.github.cafdataprocessing.services.staging;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "staging")
public class StagingProperties {

    private String basePath;
    private int subbatchSize;
    private String storagePath;
    private int fieldValueSizeThreshold;

    public String getBasePath() {
        return basePath;
    }

    public void setBasePath(String basePath) {
        this.basePath = basePath;
    }

    public int getSubbatchSize() {
        return subbatchSize;
    }

    public void setSubbatchSize(int subbatchSize) {
        this.subbatchSize = subbatchSize;
    }

    public String getStoragePath() {
        return storagePath;
    }

    public void setStoragePath(String storagePath) {
        this.storagePath = storagePath;
    }

    public int getFieldValueSizeThreshold() {
        return fieldValueSizeThreshold;
    }

    public void setFieldValueSizeThreshold(int fieldValueSizeThreshold) {
        this.fieldValueSizeThreshold = fieldValueSizeThreshold;
    }
}
