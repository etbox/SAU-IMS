package com.fekpal.web.model;

import com.fekpal.common.base.BaseModel;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;

/**
 * Created by APone on 2018/3/4 14:24.
 */
@Component
public class OrgDetail extends BaseModel {

    private static final long serialVersionUID = -8999216384274349374L;

    private int orgId;

    private String orgName;

    private Timestamp foundTime;

    private String description;

    private String adminName;

    private String orgType;

    private Integer likeClick;

    private Integer members;

    private String view;

    private String logo;

    private String email;

    private String phone;

    private int joinState;

    public String getView() {
        return view;
    }

    public void setView(String view) {
        this.view = view;
    }

    public int getOrgId() {
        return orgId;
    }

    public void setOrgId(int orgId) {
        this.orgId = orgId;
    }

    public int getJoinState() {
        return joinState;
    }

    public void setJoinState(int joinState) {
        this.joinState = joinState;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
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

    public String getOrgType() {
        return orgType;
    }

    public void setOrgType(String orgType) {
        this.orgType = orgType;
    }

    public Integer getLikeClick() {
        return likeClick;
    }

    public void setLikeClick(Integer likeClick) {
        this.likeClick = likeClick;
    }

    public Integer getMembers() {
        return members;
    }

    public void setMembers(Integer members) {
        this.members = members;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
