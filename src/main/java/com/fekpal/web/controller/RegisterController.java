package com.fekpal.web.controller;

import com.fekpal.api.RegisterService;
import com.fekpal.common.constant.Operation;
import com.fekpal.common.constant.ResponseCode;
import com.fekpal.common.json.JsonResult;
import com.fekpal.common.utils.IPUtil;
import com.fekpal.common.utils.TimeUtil;
import com.fekpal.service.model.domain.ClubReg;
import com.fekpal.service.model.domain.PersonReg;
import com.fekpal.web.model.CaptchaMsg;
import com.fekpal.web.model.ClubRegisterMsg;
import com.fekpal.web.model.PersonRegisterMsg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;
import java.util.*;

/**
 * 注册的控制类
 * Created by hasee on 2017/8/17.
 */
@Controller
public class RegisterController {

    @Autowired
    private RegisterService registerService;

    private static final String CLUB = "club";

    private static final String PERSON = "person";

    /**
     * 按照注册类型发送邮箱验证码
     *
     * @param msg 验证来源封装
     * @return json数据封装
     */
    @ResponseBody
    @RequestMapping(value = "/reg/{regType}/captcha", method = RequestMethod.POST)
    public JsonResult<List> sendEmailCaptcha(@RequestBody CaptchaMsg msg, @PathVariable String regType) {
        JsonResult<List> result = new JsonResult<>();
        int state = Operation.FAILED;

        if (regType.equals(CLUB)) {
            state = registerService.sendClubEmailCaptcha(msg.getEmail());
        } else if (regType.equals(PERSON)) {
            state = registerService.sendPersonEmailCaptcha(msg.getEmail());
        }
        if (state == Operation.SUCCESSFULLY) {
            result.setStateCode(ResponseCode.RESPONSE_SUCCESS, "验证码发送成功");
        } else if (state == Operation.FAILED) {
            result.setStateCode(ResponseCode.RESPONSE_ERROR, "验证码发送失败");
        }
        return result;
    }

    /**
     * 社团注册的方法
     *
     * @param clubRegisterMsg 社团用户注册信息封装
     * @return 标准json数据
     */
    @ResponseBody
    @RequestMapping(value = "/reg/club", method = RequestMethod.POST)
    public JsonResult<List> clubRegister(@ModelAttribute ClubRegisterMsg clubRegisterMsg,
                                         HttpServletRequest request) {
        long time = TimeUtil.currentTime();
        String ip = IPUtil.getUserIP(request);

        ClubReg reg = new ClubReg();
        reg.setUserName(clubRegisterMsg.getUserName());
        reg.setPassword(clubRegisterMsg.getPassword());
        reg.setEmail(clubRegisterMsg.getEmail());
        reg.setPhone(clubRegisterMsg.getPhone());
        reg.setCaptcha(clubRegisterMsg.getCaptcha());
        reg.setCurrentTime(time);
        reg.setLoginIp(ip);
        reg.setLoginTime(new Timestamp(time));
        reg.setRegisterIp(ip);
        reg.setRegisterTime(new Timestamp(time));

        reg.setClubName(clubRegisterMsg.getClubName());
        reg.setAdminName(clubRegisterMsg.getRealName());
        reg.setClubType(clubRegisterMsg.getClubType());
        reg.setDescription(clubRegisterMsg.getDescription());
        reg.setAuditFile(clubRegisterMsg.getFile());

        JsonResult<List> result = new JsonResult<>();
        int state = registerService.insertClubReg(reg);
        setRegHintMsg(state, result);
        return result;
    }

    /**
     * 个人用户注册的方法
     *
     * @param personRegisterMsg 个人登录信息
     * @return 标准json数据
     */
    @ResponseBody
    @RequestMapping(value = "/reg/person", method = RequestMethod.POST)
    public JsonResult<List> personRegister(@RequestBody PersonRegisterMsg personRegisterMsg,
                                           HttpServletRequest request) {
        long time = TimeUtil.currentTime();
        Timestamp timestamp = new Timestamp(time);
        String ip = IPUtil.getUserIP(request);

        PersonReg reg = new PersonReg();
        reg.setUserName(personRegisterMsg.getUserName());
        reg.setPassword(personRegisterMsg.getPassword());
        reg.setEmail(personRegisterMsg.getUserName());
        reg.setCaptcha(personRegisterMsg.getCaptcha());
        reg.setCurrentTime(time);
        reg.setLoginIp(ip);
        reg.setLoginTime(timestamp);
        reg.setRegisterIp(ip);
        reg.setRegisterTime(timestamp);

        JsonResult<List> result = new JsonResult<>();
        int state = registerService.insertPersonReg(reg);
        setRegHintMsg(state, result);
        return result;
    }

    /**
     * 操作结果
     *
     * @param state  操作状态
     * @param result json封装
     */
    private static void setRegHintMsg(int state, JsonResult<List> result) {
        if (state == Operation.SUCCESSFULLY) {
            result.setStateCode(ResponseCode.RESPONSE_SUCCESS, "注册成功");
        } else if (state == Operation.FAILED) {
            result.setStateCode(ResponseCode.RESPONSE_ERROR, "注册失败");
        } else if (state == Operation.CAPTCHA_INCORRECT) {
            result.setStateCode(ResponseCode.RESPONSE_ERROR, "验证码错误");
        }
    }
}
