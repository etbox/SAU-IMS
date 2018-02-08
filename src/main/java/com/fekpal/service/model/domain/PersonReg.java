package com.fekpal.service.model.domain;

import com.fekpal.common.base.BaseModel;

import java.sql.Timestamp;

/**
 * Created by APone on 2018/2/7 16:53.
 * 普通用户注册消息封装
 */
public class PersonReg extends BaseModel {

    private static final long serialVersionUID = 632101732232330617L;

    private String userName;

    private String password;

    private String email;

    private String code;

    private long currentTime;

    private Timestamp loginTIme;

    private Timestamp registerTime;

    private String loginIp;

    private String registerIp;

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
}
