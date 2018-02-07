package com.fekpal.service.model.domain;

import com.fekpal.common.base.BaseModel;

/**
 * Created by APone on 2018/2/7 16:54.
 * 校社联用户注册消息封装
 */
public class SauReg extends BaseModel {

    private static final long serialVersionUID = 6746125712813345382L;

    private String userName;

    private String password;

    private String email;

    private String phone;

    private String sauName;

    private String adminName;

    private String description;

    private String code;

    private long currentTime;

    private String auditFileName;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public String getSauName() {
        return sauName;
    }

    public void setSauName(String sauName) {
        this.sauName = sauName;
    }

    public String getAdminName() {
        return adminName;
    }

    public void setAdminName(String adminName) {
        this.adminName = adminName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public long getCurrentTime() {
        return currentTime;
    }

    public void setCurrentTime(long currentTime) {
        this.currentTime = currentTime;
    }

    public String getAuditFileName() {
        return auditFileName;
    }

    public void setAuditFileName(String auditFileName) {
        this.auditFileName = auditFileName;
    }
}
