package com.fekpal.web.controller;

import com.fekpal.service.UserService;
import com.fekpal.tool.JsonObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * 登陆相关的方法
 * Created by hasee on 2017/8/14.
 */
@Controller
public class LoginController {

    @Autowired
    private UserService userService;

    @Autowired
    private JsonObject returnData;

    /**
     * 用户登录提交方法
     *
     * @return 返回用户信息
     */
    @RequestMapping("/login/go")
    @ResponseBody
    public Map<String, Object> login() {
        return null;
    }

    /**
     * 用户的登出方法
     *
     * @return Map
     */
    @RequestMapping("/logout")
    @ResponseBody
    public Map<String, Object> logout() {
        return null;
    }

    /**
     * 生成登陆验证码，并向浏览器输出
     */
    @RequestMapping(value = "/login/code", method = RequestMethod.GET)
    public void captcha() {
    }
}
