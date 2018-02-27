package com.fekpal.dao.model;


import java.sql.Timestamp;

/**
 * Created by APone on 2018/2/26 20:27.
 */
public class MemberOrg extends Org {

    private static final long serialVersionUID = 6300840844736636054L;

    private int id;

    private int personId;

    private int memberDuty;

    private int memberState;

    private Timestamp joinTime;

    private Timestamp leaveTime;

    private String orgDepartment;

    private int available;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPersonId() {
        return personId;
    }

    public void setPersonId(int personId) {
        this.personId = personId;
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
