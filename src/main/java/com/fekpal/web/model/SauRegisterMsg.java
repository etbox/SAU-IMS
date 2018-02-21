package com.fekpal.web.model;

import com.fekpal.common.base.BaseModel;
import org.springframework.stereotype.Component;

/**
 * Created by APone on 2018/2/21 14:34.
 * 校社联注册信息封装
 */
@Component
public class SauRegisterMsg  extends BaseModel {

    private static final long serialVersionUID = 470353737244328429L;

    private String userName;

    private String password;

    private String email;

    private String phone;

    private String sauName;

    private String adminName;

    private String description;

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
}
