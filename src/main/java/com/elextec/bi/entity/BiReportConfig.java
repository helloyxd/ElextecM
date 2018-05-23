package com.elextec.bi.entity;

import com.elextec.bi.common.entity.BasicEntity;

/**
 * Created by js_gg on 2018/1/11.
 */
/**
 * 自定义报表配置信息实体类
 *
 */
public class BiReportConfig extends BasicEntity {
    //报表名称
    private String reportName;
    //报表列配置
    private String colConf;
    //报表数据集配置，包括数据权限配置
    private String datasetConfig;
    //表头配置
    private String tableHeadConfig;
    //过滤字段配置
    private String screenParameterConfig;

    public String getReportName() {
        return reportName;
    }

    public void setReportName(String reportName) {
        this.reportName = reportName;
    }

    public String getColConf() {
        return colConf;
    }

    public void setColConf(String colConf) {
        this.colConf = colConf;
    }

    public String getDatasetConfig() {
        return datasetConfig;
    }

    public void setDatasetConfig(String datasetConfig) {
        this.datasetConfig = datasetConfig;
    }

    public String getTableHeadConfig() {
        return tableHeadConfig;
    }

    public void setTableHeadConfig(String tableHeadConfig) {
        this.tableHeadConfig = tableHeadConfig;
    }

    public String getScreenParameterConfig() {
        return screenParameterConfig;
    }

    public void setScreenParameterConfig(String screenParameterConfig) {
        this.screenParameterConfig = screenParameterConfig;
    }
}
