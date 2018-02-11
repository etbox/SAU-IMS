package com.fekpal.web.controller;

import com.fekpal.api.AccountSecureService;

import com.fekpal.common.constant.Operation;
import com.fekpal.common.constant.ResponseCode;
import com.fekpal.common.json.JsonResult;
import com.fekpal.common.utils.TimeUtils;
import com.fekpal.service.model.domain.AccountRecord;
import com.fekpal.web.model.UserLogin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

/**
 * 登陆相关的方法
 * Created by hasee on 2017/8/14.
 */
@Controller
public class LoginController {

    @Autowired
    private AccountSecureService accountSecureService;

    @Autowired
    private JsonResult<List> result;

    /**
     * 用户登录提交方法
     *
     * @return 返回用户信息
     */
    @RequestMapping("/login/go")
    @ResponseBody
    public JsonResult<List> login(UserLogin login) {
        AccountRecord record = new AccountRecord();
        record.setUserName(login.getUserName());
        record.setPassword(login.getPassword());
        record.setCode(login.getCaptcha());
        record.setCurrentTime(TimeUtils.currentTime());

        int state = accountSecureService.login(record);

        if (state == Operation.CAPTCHA_INCORRECT) {
            result.setStateCode(ResponseCode.RESPONSE_ERROR, "验证码错误");
        } else if (state == Operation.FAILED) {
            result.setStateCode(ResponseCode.RESPONSE_ERROR, "用户名或密码错误");
        } else if (state == Operation.SUCCESSFULLY) {
            result.setStateCode(ResponseCode.RESPONSE_SUCCESS, "登录成功");
        }
        return result;
    }

    /**
     * 用户的登出方法
     *
     * @return Map
     */
    @ResponseBody
    @RequestMapping("/logout")
    public JsonResult<List> logout() {

        if (accountSecureService.logout()) {
            result.setStateCode(ResponseCode.RESPONSE_SUCCESS, "登出成功");
        } else {
            result.setStateCode(ResponseCode.RESPONSE_ERROR, "登出失败");
        }
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
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
