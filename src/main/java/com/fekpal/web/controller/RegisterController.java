package com.fekpal.web.controller;

import com.fekpal.cons.AvailableState;
import com.fekpal.cons.SystemRole;
import com.fekpal.domain.pojo.Club;
import com.fekpal.domain.pojo.ClubAudit;
import com.fekpal.domain.pojo.Person;
import com.fekpal.domain.json.PersonRegisterMsg;
import com.fekpal.service.ClubAuditService;
import com.fekpal.service.UserService;
import com.fekpal.tool.*;
import com.fekpal.tool.captcha.Captcha;
import com.fekpal.tool.msg.email.EmailSender;
import com.fekpal.web.controller.clubAdmin.ClubAnnRegisterController;
import org.apache.commons.mail.EmailException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.Timestamp;
import java.util.*;

/**
 * 注册的控制类
 * Created by hasee on 2017/8/17.
 */
@Controller
public class RegisterController {

    @Autowired
    private EmailSender emailSender;

    @Autowired
    private UserService userService;

    @Autowired
    private ClubAuditService clubAuditService;

    @Autowired
    private JsonObject returnData;

    /**
     * 发送邮箱验证码
     *
     * @param email 发送过来的邮箱
     * @return 返回标准json数据
     */
    @ResponseBody
    @RequestMapping(value = "/reg/code", method = RequestMethod.GET)
    public Map<String, Object> sendEmailCaptcha(@RequestParam(value = "email") String email) {
        return null;
    }

    /**
     * 社团注册的方法
     *
     * @param file    上传的文件
     * @param request 请求
     * @return 标准json数据
     */
    @ResponseBody
    @RequestMapping(value = "/reg/club", method = RequestMethod.POST)
    public Map<String, Object> clubRegister(@RequestParam MultipartFile[] file,
                                            HttpServletRequest request) {
        return null;
    }

    /**
     * 个人用户注册的方法
     *
     * @param personRegisterMsg 个人登录信息
     * @return 标准json数据
     */
    @ResponseBody
    @RequestMapping("/reg/person")
    public Map<String, Object> personRegister(@RequestBody PersonRegisterMsg personRegisterMsg,
                                              HttpServletRequest request) {
        return null;
    }
}
