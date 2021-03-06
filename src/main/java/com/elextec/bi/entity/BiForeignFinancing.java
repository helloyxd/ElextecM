package com.elextec.bi.entity;


import com.elextec.bi.utils.TimeUtil;
import com.elextec.mdm.common.entity.BasicEntity;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 对外融资情况表实体类
 * Created by js_gg on 2017/12/19.
 */
public class BiForeignFinancing {
    private String id;
    private String orgId;
    private String orgName;
    private String type;
    private String regionComName;
    private String bankComName;
    private BigDecimal loanAmount;
    private String loanDate;
    private String appointmentRepaymentDate;
    private BigDecimal yearComprehensiveCost;
    private String costPayType;
    private String interestPayTime;
    private String pressInfo;
    private String creditTerm;
    private String pressTerm;
    private String remark;
    private Date createTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getRegionComName() {
        return regionComName;
    }

    public void setRegionComName(String regionComName) {
        this.regionComName = regionComName;
    }

    public String getBankComName() {
        return bankComName;
    }

    public void setBankComName(String bankComName) {
        this.bankComName = bankComName;
    }

    public BigDecimal getLoanAmount() {
        return loanAmount;
    }

    public void setLoanAmount(BigDecimal loanAmount) {
        this.loanAmount = loanAmount;
    }

    public String getLoanDate() {
        return loanDate;
    }

    public void setLoanDate(Date loanDate) {
        this.loanDate = TimeUtil.formatDateByFormat(loanDate,TimeUtil.DEFAULT_PATTERN);
    }

    public String getAppointmentRepaymentDate() {
        return appointmentRepaymentDate;
    }

    public void setAppointmentRepaymentDate(Date appointmentRepaymentDate) {
        this.appointmentRepaymentDate = TimeUtil.formatDateByFormat(appointmentRepaymentDate,TimeUtil.DEFAULT_PATTERN);
    }

    public BigDecimal getYearComprehensiveCost() {
        return yearComprehensiveCost;
    }

    public void setYearComprehensiveCost(BigDecimal yearComprehensiveCost) {
        this.yearComprehensiveCost = yearComprehensiveCost;
    }

    public String getCostPayType() {
        return costPayType;
    }

    public void setCostPayType(String costPayType) {
        this.costPayType = costPayType;
    }

    public String getInterestPayTime() {
        return interestPayTime;
    }

    public void setInterestPayTime(Date interestPayTime) {
        this.interestPayTime = TimeUtil.formatDateByFormat(interestPayTime,TimeUtil.DEFAULT_PATTERN);
    }

    public String getPressInfo() {
        return pressInfo;
    }

    public void setPressInfo(String pressInfo) {
        this.pressInfo = pressInfo;
    }

    public String getCreditTerm() {
        return creditTerm;
    }

    public void setCreditTerm(String creditTerm) {
        this.creditTerm = creditTerm;
    }

    public String getPressTerm() {
        return pressTerm;
    }

    public void setPressTerm(String pressTerm) {
        this.pressTerm = pressTerm;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
