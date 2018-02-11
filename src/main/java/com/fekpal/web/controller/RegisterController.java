package com.fekpal.web.controller;

import com.fekpal.common.json.JsonResult;
import com.fekpal.web.model.ClubRegisterMsg;
import com.fekpal.web.model.PersonRegisterMsg;
import com.fekpal.api.ClubAuditService;
import com.fekpal.api.UserService;
import com.fekpal.common.utils.msg.email.EmailSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * 注册的控制类
 * Created by hasee on 2017/8/17.
 */
@Controller
public class RegisterController {

    /**
     * 发送邮箱验证码
     *
     * @param email 发送过来的邮箱
     * @return 返回标准json数据
     */
    @ResponseBody
    @RequestMapping(value = "/reg/code", method = RequestMethod.GET)
    public JsonResult<List> sendEmailCaptcha(@RequestParam(value = "email") String email) {
        return null;
    }

    /**
     * 社团注册的方法
     *
     * @param file 上传的文件
     * @return 标准json数据
     */
    @ResponseBody
    @RequestMapping(value = "/reg/club", method = RequestMethod.POST)
    public JsonResult<List> clubRegister(@RequestParam MultipartFile[] file,
                                         @RequestBody ClubRegisterMsg clubRegisterMsg) {
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
    public JsonResult<List> personRegister(@RequestBody PersonRegisterMsg personRegisterMsg) {
        return null;
    }
}
