package com.fekpal.dao.model;

import com.fekpal.common.base.BaseModel;

import java.sql.Timestamp;

public class ClubAudit extends BaseModel {

    private static final long serialVersionUID = -8822913989759898229L;

    private int id;

    private int orgId;

    private String auditTitle;

    private Timestamp registerTime;

    private Timestamp auditTime;

    private int auditResult;

    private String file;

    private String auditDescription;

    private String applyName;

    private int auditState;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getOrgId() {
        return orgId;
    }

    public void setOrgId(int orgId) {
        this.orgId = orgId;
    }

    public int getAuditState() {
        return auditState;
    }

    public void setAuditState(int auditState) {
        this.auditState = auditState;
    }

    public String getApplyName() {
        return applyName;
    }

    public void setApplyName(String applyName) {
        this.applyName = applyName;
    }

    public String getAuditTitle() {
        return auditTitle;
    }

    public void setAuditTitle(String auditTitle) {
        this.auditTitle = auditTitle;
    }

    public Timestamp getRegisterTime() {
        return registerTime;
    }

    public void setRegisterTime(Timestamp registerTime) {
        this.registerTime = registerTime;
    }

    public Timestamp getAuditTime() {
        return auditTime;
    }

    public void setAuditTime(Timestamp auditTime) {
        this.auditTime = auditTime;
    }

    public int getAuditResult() {
        return auditResult;
    }

    public void setAuditResult(int auditResult) {
        this.auditResult = auditResult;
    }

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }

    public String getAuditDescription() {
        return auditDescription;
    }

    public void setAuditDescription(String auditDescription) {
        this.auditDescription = auditDescription;
    }
}
