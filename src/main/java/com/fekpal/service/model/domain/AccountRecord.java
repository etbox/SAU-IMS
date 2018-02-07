package com.fekpal.service.model.domain;

import com.fekpal.common.base.BaseModel;
import test.Model;

/**
 * Created by APone on 2018/2/7 1:55.
 * 领域对象，用于controller层与service层的交互
 * 账号信息记录
 */
public class AccountRecord extends BaseModel{

    private static final long serialVersionUID = -9141383817817859890L;

    //用户名
    public String userName;

    //新密码
    public String oldPassword;

    //旧密码
    public String newPassword;

    //手机号码
    public String phone;

    //邮箱地址
    public String email;

    //验证码
    public String code;

    //当前时间
    public long currentTime;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
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
