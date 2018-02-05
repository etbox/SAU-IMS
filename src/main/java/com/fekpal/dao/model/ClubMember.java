package com.fekpal.dao.model;

import com.fekpal.common.base.BaseModel;

import java.sql.Timestamp;

public class ClubMember extends BaseModel {

    private static final long serialVersionUID = -3557278531392834590L;

    private int id;

    private int clubId;

    private int personId;

    private int memberDuty;

    private int userState;

    private Timestamp joinTime;

    private Timestamp leaveTime;

    private String clubDepartment;

    private int available;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getClubId() {
        return clubId;
    }

    public void setClubId(int clubId) {
        this.clubId = clubId;
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

    public int getUserState() {
        return userState;
    }

    public void setUserState(int userState) {
        this.userState = userState;
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

    public String getClubDepartment() {
        return clubDepartment;
    }

    public void setClubDepartment(String clubDepartment) {
        this.clubDepartment = clubDepartment;
    }

    public int getAvailable() {
        return available;
    }

    public void setAvailable(int available) {
        this.available = available;
    }
}
