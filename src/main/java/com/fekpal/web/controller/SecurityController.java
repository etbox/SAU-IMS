package com.fekpal.web.controller;

import com.fekpal.api.AccountSecureService;
import com.fekpal.common.constant.Operation;
import com.fekpal.common.constant.ResponseCode;
import com.fekpal.common.json.JsonResult;
import com.fekpal.common.utils.TimeUtil;
import com.fekpal.service.model.domain.AccountRecord;
import com.fekpal.web.model.SecureMsg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.awt.*;


/**
 * 安全功能的控制类，包含忘记密码，发送邮箱验证
 * Created by hasee on 2017/8/29.
 */
@Controller
public class SecurityController {

    @Autowired
    private AccountSecureService accountSecureService;

    /**
     * 发送重置密码的邮箱验证码
     *
     * @param msg 安全信息封装
     * @return 标准json数据
     */
    @ResponseBody
    @RequestMapping(value = "/security/resetpwd/code", method = RequestMethod.GET)
    public JsonResult<List> sendResetPwdCaptcha(@RequestBody SecureMsg msg) {
        AccountRecord record = new AccountRecord();
        record.setEmail(msg.getEmail());

        JsonResult<List> result = new JsonResult<>();
        int state = accountSecureService.forgetPwdByEmail(record);
        if (state == Operation.SUCCESSFULLY) {
            result.setStateCode(ResponseCode.RESPONSE_SUCCESS, "发送验证码成功");
        } else if (state == Operation.FAILED) {
            result.setStateCode(ResponseCode.RESPONSE_ERROR, "发送验证码失败");
        }
        return result;
    }

    /**
     * 重置密码
     *
     * @param msg 安全信息封装
     * @return 标准json数据
     */
    @ResponseBody
    @RequestMapping(value = "/security/resetpwd", method = RequestMethod.PUT)
    public JsonResult<List> resetPassword(@RequestBody SecureMsg msg) {
        AccountRecord record = new AccountRecord();
        record.setNewPassword(msg.getNewPassword());
        record.setCode(msg.getCaptcha());
        record.setCurrentTime(TimeUtil.currentTime());

        JsonResult<List> result = new JsonResult<>();
        int state = accountSecureService.resetPwd(record);
        if (state == Operation.CAPTCHA_INCORRECT) {
            result.setStateCode(ResponseCode.RESPONSE_ERROR, "验证码错误");
        } else if (state == Operation.FAILED) {
            result.setStateCode(ResponseCode.RESPONSE_ERROR, "重置密码失败");
        } else if (state == Operation.SUCCESSFULLY) {
            result.setStateCode(ResponseCode.RESPONSE_SUCCESS, "重置密码成功");
        }
        return result;
    }

    /**
     * 发送修改邮箱的邮箱验证码
     *
     * @return 是否成功
     */
    @ResponseBody
    @RequestMapping(value = "/security/email/code", method = RequestMethod.GET)
    public JsonResult<List> sendModifyEmailCaptcha() {
        int state = accountSecureService.sendModifyEmailCaptcha();
        JsonResult<List> result = new JsonResult<>();
        if (state == Operation.SUCCESSFULLY) {
            result.setStateCode(ResponseCode.RESPONSE_SUCCESS, "发送验证码成功");
        } else if (state == Operation.FAILED) {
            result.setStateCode(ResponseCode.RESPONSE_ERROR, "发送验证码失败");
        }
        return result;
    }

    /**
     * 修改邮箱
     *
     * @return 返回提示信息
     */
    @ResponseBody
    @RequestMapping(value = "/security/email", method = RequestMethod.PUT)
    public JsonResult<List> modifyEmail(@RequestBody SecureMsg msg) {
        AccountRecord record = new AccountRecord();
        record.setCode(msg.getCaptcha());
        record.setNewEmail(msg.getNewEmail());
        record.setCurrentTime(TimeUtil.currentTime());

        int state = accountSecureService.modifyEmail(record);
        JsonResult<List> result = new JsonResult<>();
        if (state == Operation.SUCCESSFULLY) {
            result.setStateCode(ResponseCode.RESPONSE_SUCCESS, "修改邮箱成功");
        } else if (state == Operation.FAILED) {
            result.setStateCode(ResponseCode.RESPONSE_ERROR, "修改邮箱失败");
        } else if (state == Operation.CAPTCHA_INCORRECT) {
            result.setStateCode(ResponseCode.REQUEST_ERROR, "验证码错误");
        }
        return result;
    }

}
