package com.elextec.bi.entity;

import com.elextec.bi.common.entity.BasicEntity;

/**
 * Created by js_gg on 2018/1/15.
 */
public class BiDataSource extends BasicEntity {
    private String sourceName;
    private String sourceType;
    private String config;

    public String getSourceName() {
        return sourceName;
    }

    public void setSourceName(String sourceName) {
        this.sourceName = sourceName;
    }

    public String getSourceType() {
        return sourceType;
    }

    public void setSourceType(String sourceType) {
        this.sourceType = sourceType;
    }

    public String getConfig() {
        return config;
    }

    public void setConfig(String config) {
        this.config = config;
    }
}
