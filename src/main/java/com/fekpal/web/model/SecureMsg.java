package com.fekpal.web.model;

import com.fekpal.common.base.BaseModel;
import org.springframework.stereotype.Component;

/**
 * Created by APone on 2018/2/11 16:10.
 */
@Component
public class SecureMsg extends BaseModel{

    private static final long serialVersionUID = 6607855246362443190L;

    private String oldPassword;

    private String newPassword;

    private String email;

    private String captcha;

    private String newEmail;

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCaptcha() {
        return captcha;
    }

    public void setCaptcha(String captcha) {
        this.captcha = captcha;
    }

    public String getNewEmail() {
        return newEmail;
    }

    public void setNewEmail(String newEmail) {
        this.newEmail = newEmail;
    }
}
