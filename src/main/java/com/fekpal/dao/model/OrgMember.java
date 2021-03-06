package com.fekpal.dao.model;

import java.sql.Timestamp;

public class OrgMember extends Person {

    private static final long serialVersionUID = -3557278531392834590L;

    private Integer id;

    private Integer orgId;

    private Integer memberDuty;

    /**
     * 默认状态是存在的
     */
    private Integer memberState=1;

    private Timestamp joinTime;

    private Timestamp leaveTime;

    private String orgDepartment;

    private Integer available;

    public Integer getOrgId() {
        return orgId;
    }

    public void setOrgId(Integer orgId) {
        this.orgId = orgId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getMemberDuty() {
        return memberDuty;
    }

    public void setMemberDuty(Integer memberDuty) {
        this.memberDuty = memberDuty;
    }

    public Integer getMemberState() {
        return memberState;
    }

    public void setMemberState(Integer memberState) {
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

    public Integer getAvailable() {
        return available;
    }

    public void setAvailable(Integer available) {
        this.available = available;
    }
}
