package com.fekpal.domain.json;

import com.fekpal.domain.BasePOJO;
import org.springframework.stereotype.Component;

/**
 * 用来封装登陆发送的数据的用户登陆类
 * Created by hasee on 2017/8/16.
 */
@Component
public class UserLogin extends BasePOJO{

    private String userName;

    private String password;

    private String captcha;

    public UserLogin() {
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

    public String getCaptcha() {
        return captcha;
    }

    public void setCaptcha(String captcha) {
        this.captcha = captcha;
    }
}
