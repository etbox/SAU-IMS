package com.fekpal.dao.model;

import java.sql.Timestamp;

public class OrgMember extends Person {

    private static final long serialVersionUID = -3557278531392834590L;

    private int id;

    private int orgId;

    private int memberDuty;

    private int memberState;

    private Timestamp joinTime;

    private Timestamp leaveTime;

    private String orgDepartment;

    private int available;

    public int getOrgId() {
        return orgId;
    }

    public void setOrgId(int orgId) {
        this.orgId = orgId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMemberDuty() {
        return memberDuty;
    }

    public void setMemberDuty(int memberDuty) {
        this.memberDuty = memberDuty;
    }

    public int getMemberState() {
        return memberState;
    }

    public void setMemberState(int memberState) {
        this.memberState = memberState;
    }

    public Timestamp getJoinTime() {
        return joinTime;
    }

    public void setJoinTime(Timestamp joinTime) {
        this.joinTime = joinTime;
    }

    public Timestamp getLeaveTime() {
        return leaveTime;
    }

    public void setLeaveTime(Timestamp leaveTime) {
        this.leaveTime = leaveTime;
    }

    public String getOrgDepartment() {
        return orgDepartment;
    }

    public void setOrgDepartment(String orgDepartment) {
        this.orgDepartment = orgDepartment;
    }

    public int getAvailable() {
        return available;
    }

    public void setAvailable(int available) {
        this.available = available;
    }
}
