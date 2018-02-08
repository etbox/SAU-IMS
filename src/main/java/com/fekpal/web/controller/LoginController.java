package com.fekpal.web.controller;

import com.fekpal.api.AccountSecureService;

import com.fekpal.common.Result;
import com.fekpal.common.json.JsonResult;
import com.fekpal.dao.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;

/**
 * 登陆相关的方法
 * Created by hasee on 2017/8/14.
 */
@Controller
public class LoginController {

    @Autowired
    private AccountSecureService accountSecureService;

    @Autowired
    private JsonResult result;


    /**
     * 用户登录提交方法
     *
     * @return 返回用户信息
     */
    @RequestMapping("/login/go")
    @ResponseBody
    public JsonResult login() {

        return result;
    }

    /**
     * 用户的登出方法
     *
     * @return Map
     */
    @ResponseBody
    @RequestMapping("/logout")
    public Result logout() {
        Result result=new Result();
        result.put(new User());
        return result;
    }

    /**
     * 生成登陆验证码，并向浏览器输出
     */
    @RequestMapping(value = "/login/code", method = RequestMethod.GET)
    public void captcha(HttpServletResponse response) {
        try {
            OutputStream outputStream = response.getOutputStream();
            accountSecureService.sendLoginCaptchaImage(outputStream);
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
