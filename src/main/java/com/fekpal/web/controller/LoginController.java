package com.fekpal.web.controller;

import com.fekpal.api.AccountAccessService;

import com.fekpal.common.constant.Operation;
import com.fekpal.common.constant.ResponseCode;
import com.fekpal.common.constant.SystemRole;
import com.fekpal.common.json.JsonResult;
import com.fekpal.common.utils.TimeUtil;
import com.fekpal.service.model.domain.SecureMsg;
import com.fekpal.service.model.domain.LoginResult;
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
    private AccountAccessService accountAccessService;

    /**
     * 用户登录
     *
     * @return 返回用户信息
     */
    @ResponseBody
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public JsonResult<String> login(@RequestBody SecureMsg msg) {
        msg.setCurrentTime(TimeUtil.currentTime());
        JsonResult<String> result = new JsonResult<>();

        LoginResult state = accountAccessService.login(msg);
        if (state.getResultState() == Operation.CAPTCHA_INCORRECT) {
            result.setStateCode(ResponseCode.RESPONSE_ERROR, "验证码错误");
        } else if (state.getResultState() == Operation.FAILED) {
            result.setStateCode(ResponseCode.RESPONSE_ERROR, "用户名或密码错误");
        } else if (state.getResultState() == Operation.SUCCESSFULLY) {
            result.setStateCode(ResponseCode.RESPONSE_SUCCESS, "登录成功");

            if (state.getAuthority() == SystemRole.CLUB) {
                result.setUrl("club.html");
            } else if (state.getAuthority() == SystemRole.PERSON) {
                result.setUrl("person.html");
            } else if (state.getAuthority() == SystemRole.SAU) {
                result.setUrl("sau.html");
            }
        }
        return result;
    }

    /**
     * 生成登陆验证码
     */
    @RequestMapping(value = "/login/captcha", method = RequestMethod.GET)
    public void captcha(HttpServletResponse response) {
        try {
            response.setContentType("image/png");
            OutputStream outputStream = response.getOutputStream();
            accountAccessService.sendLoginCaptchaImage(outputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 用户的登出
     *
     * @return json数据
     */
    @ResponseBody
    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public JsonResult<String> logout() {
        JsonResult<String> result = new JsonResult<>();
        if (accountAccessService.logout()) {
            result.setStateCode(ResponseCode.RESPONSE_SUCCESS, "登出成功");
        } else {
            result.setStateCode(ResponseCode.RESPONSE_ERROR, "登出失败");
        }
        return result;
    }
}
