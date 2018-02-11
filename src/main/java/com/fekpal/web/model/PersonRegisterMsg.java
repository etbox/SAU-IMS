package com.fekpal.web.model;

import com.fekpal.common.base.BaseModel;
import org.springframework.stereotype.Component;

/**
 * Created by hasee on 2017/8/18.
 */
@Component
public class PersonRegisterMsg extends BaseModel{

    private static final long serialVersionUID = 7346679216150174894L;

    private String userName;

    private String password;

    private String captcha;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCaptcha() {
        return captcha;
    }

    public void setCaptcha(String captcha) {
        this.captcha = captcha;
    }
}
