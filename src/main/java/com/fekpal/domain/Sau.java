package com.fekpal.domain;

import java.sql.Timestamp;

public class Sau extends User {

    private int sauId;

    private String sauName;

    private Timestamp foundTime;

    private String description;

    private String adminName;

    private int members;

    public int getSauId() {
        return sauId;
    }

    public void setSauId(int sauId) {
        this.sauId = sauId;
    }

    public String getSauName() {
        return sauName;
    }

    public void setSauName(String sauName) {
        this.sauName = sauName;
    }

    public Timestamp getFoundTime() {
        return foundTime;
    }

    public void setFoundTime(Timestamp foundTime) {
        this.foundTime = foundTime;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAdminName() {
        return adminName;
    }

    public void setAdminName(String adminName) {
        this.adminName = adminName;
    }

    public int getMembers() {
        return members;
    }

    public void setMembers(int members) {
        this.members = members;
    }
}
