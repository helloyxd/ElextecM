package com.elextec.bi.entity;

import java.util.Date;

/**
 * Created by js_gg on 2018/1/8.
 */
public class BiReportColConf {

    private String id;

    private String title;

    private String reportName;

    private String colConf;

    private Date createTime;

    private String creater;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

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

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getCreater() {
        return creater;
    }

    public void setCreater(String creater) {
        this.creater = creater;
    }
}
