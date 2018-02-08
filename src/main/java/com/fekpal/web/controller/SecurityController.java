package com.fekpal.web.controller;

import com.fekpal.api.UserService;
import com.fekpal.common.json.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

/**
 * 安全功能的控制类，包含忘记密码，发送邮箱验证
 * Created by hasee on 2017/8/29.
 */
@Controller
public class SecurityController {

    @Autowired
    private UserService userService;

    @Autowired
    private JsonResult returnData;

    /**
     * 发送重置密码的邮箱验证码
     *
     * @param email 邮箱地址
     * @return 标准json数据
     */
    @ResponseBody
    @RequestMapping(value = "/security/resetpwd/code", method = RequestMethod.GET)
    public Map<String, Object> sendResetPwdCaptcha(@RequestParam String email) {
        return null;

    }

    /**
     * 重置密码的方法
     *
     * @param newPassword 新密码
     * @param captcha     验证码
     * @return 标准json数据
     */
    @ResponseBody
    @RequestMapping(value = "/security/resetpwd", method = RequestMethod.PUT)
    public Map<String, Object> resetPassword(@RequestParam(value = "newPassword") String newPassword, @RequestParam(value = "captcha") String captcha) {
        return null;
    }

    /**
     * 发送修改邮箱的邮箱验证码
     *
     * @param email 邮箱地址
     * @return 是否成功
     */
    @ResponseBody
    @RequestMapping(value = "/security/email/code", method = RequestMethod.GET)
    public Map<String, Object> sendResetEmailCaptcha(@RequestParam(value = "email") String email) {
        return null;
    }

    /**
     * 修改邮箱
     *
     * @param newEmail 新邮箱
     * @param captcha  验证码
     * @return 返回提示信息
     */
    @ResponseBody
    @RequestMapping(value = "/security/email", method = RequestMethod.PUT)
    public Map<String, Object> resetEmail(@RequestParam(value = "newEmail") String newEmail, @RequestParam(value = "captcha") String captcha) {
        return null;
    }

}
