package com.fekpal.service.model.domain;

import com.fekpal.common.base.BaseModel;
import org.springframework.web.multipart.MultipartFile;

import java.sql.Timestamp;

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

    private Timestamp loginTIme;

    private Timestamp registerTime;

    private String loginIp;

    private String registerIp;

    private String code;

    private long currentTime;

    private MultipartFile auditFile;

    public Timestamp getLoginTIme() {
        return loginTIme;
    }

    public void setLoginTIme(Timestamp loginTIme) {
        this.loginTIme = loginTIme;
    }

    public Timestamp getRegisterTime() {
        return registerTime;
    }

    public void setRegisterTime(Timestamp registerTime) {
        this.registerTime = registerTime;
    }

    public String getLoginIp() {
        return loginIp;
    }

    public void setLoginIp(String loginIp) {
        this.loginIp = loginIp;
    }

    public String getRegisterIp() {
        return registerIp;
    }

    public void setRegisterIp(String registerIp) {
        this.registerIp = registerIp;
    }

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

    public MultipartFile getAuditFile() {
        return auditFile;
    }

    public void setAuditFile(MultipartFile auditFile) {
        this.auditFile = auditFile;
    }
}
