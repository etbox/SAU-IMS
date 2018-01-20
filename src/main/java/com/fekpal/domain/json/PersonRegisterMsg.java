package com.fekpal.domain.json;

import com.fekpal.domain.BasePOJO;

/**
 * Created by hasee on 2017/8/18.
 */
public class PersonRegisterMsg extends BasePOJO{

    private String userName;

    private String password;
    
    private String captcha;

    public PersonRegisterMsg(){}

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
