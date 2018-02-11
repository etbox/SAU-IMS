package com.fekpal.service.model.domain;

import com.fekpal.common.base.BaseModel;
import org.springframework.web.multipart.MultipartFile;

import java.sql.Timestamp;

/**
 * Created by APone on 2018/2/7 16:54.
 * 社团用户注册消息封装
 */
public class ClubReg extends BaseModel {

    private static final long serialVersionUID = -4433414181950693858L;

    private String userName;

    private String password;

    private String email;

    private String phone;

    private String clubName;

    private String adminName;

    private String clubType;

    private String description;

    private Timestamp loginTime;

    private Timestamp registerTime;

    private String loginIp;

    private String registerIp;

    private String captcha;

    private long currentTime;

    private MultipartFile auditFile;

    public MultipartFile getAuditFile() {
        return auditFile;
    }

    public void setAuditFile(MultipartFile auditFile) {
        this.auditFile = auditFile;
    }

    public Timestamp getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(Timestamp loginTime) {
        this.loginTime = loginTime;
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

    public String getClubName() {
        return clubName;
    }

    public void setClubName(String clubName) {
        this.clubName = clubName;
    }

    public String getAdminName() {
        return adminName;
    }

    public void setAdminName(String adminName) {
        this.adminName = adminName;
    }

    public String getClubType() {
        return clubType;
    }

    public void setClubType(String clubType) {
        this.clubType = clubType;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCaptcha() {
        return captcha;
    }

    public void setCaptcha(String captcha) {
        this.captcha = captcha;
    }

    public long getCurrentTime() {
        return currentTime;
    }

    public void setCurrentTime(long currentTime) {
        this.currentTime = currentTime;
    }
}
