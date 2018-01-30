package com.fekpal.web.controller;

import com.fekpal.cons.SystemRole;
import com.fekpal.domain.pojo.User;
import com.fekpal.domain.json.UserLogin;
import com.fekpal.service.UserService;
import com.fekpal.tool.JsonObject;

import com.fekpal.tool.MD5Tool;
import com.fekpal.tool.captcha.Captcha;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.OutputStream;
import java.util.LinkedHashMap;
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
     * @param session   HttpSession
     * @param userLogin UserLogin
     * @return 返回用户信息
     */
    @RequestMapping("/login/go")
    @ResponseBody
    public Map<String, Object> login(@RequestBody UserLogin userLogin, HttpSession session) {

        //判断用户是否已经登陆
        if (session.getAttribute("userCode") != null) {
            returnData.setStateCode(1, "你已经登陆了，不能重复登陆");
            //用于前端回显数据
            returnData.setData(userLogin);
            return returnData.getMap();
        }

        String code = userLogin.getCaptcha();
        String userName = userLogin.getUserName();

        //打印出验证码
        if (session.getAttribute("code") != null) {
            //从session域获取系统生成的验证码
            String sCode = (String) session.getAttribute("code");

            //判断验证码是否相等
            if (!code.toLowerCase().equals(sCode.toLowerCase())) {
                returnData.setStateCode(1, "验证码不正确");
                returnData.setData(userLogin);
                return returnData.getMap();
            }

        } else {
            returnData.setStateCode(1, "验证码无效，请重新获得验证码");
            returnData.setData(userLogin);
            return returnData.getMap();
        }


        //如果得到的用户为空的话，表示找不到该用户
        if (!userService.isExitAccount(userName)) {
            returnData.setStateCode(1, "该用户找不到，请重新输入");
            return returnData.getMap();
        }

        User user = userService.getByUserNameAndPwd(userName, MD5Tool.md5(userLogin.getPassword()));

        if (user != null) {

            //清除当前session的验证码
            session.removeAttribute("code");

            //登陆成功,从service中得到用户信息对象
            user = userService.getByUserId(user.getUserId());

            //把用户信息放到一个map集合中去，然后返回
            Map<String, Object> userMap = new LinkedHashMap<>();
            userMap.put("role", SystemRole.ROLE_NAME[user.getAuthority()]);
            userMap.put("userId", user.getUserId());
            userMap.put("userName", user.getUserName());
            userMap.put("userLogo", user.getLogo());
            returnData.setData(userMap);

            //如果Service校验通过，将用户身份记录到session
            session.setAttribute("userCode", user);
        } else {
            returnData.setStateCode(1, "用户名或密码有误，请重新输入！");
            returnData.setData(userLogin);
        }

        return returnData.getMap();
    }

    /**
     * 用户的登出方法
     *
     * @param session HttpSession
     * @return Map
     */
    @RequestMapping("/logout")
    @ResponseBody
    public Map<String, Object> logout(HttpSession session) {

        session.invalidate();//注销session
        return returnData.getMap();
    }

    /**
     * 生成登陆验证码，并向浏览器输出
     *
     * @param response HttpServletResponse
     * @param session  HttpSession
     */
    @RequestMapping(value = "/login/code", method = RequestMethod.GET)
    public void captcha(HttpServletResponse response, HttpSession session) {
        try {
            //生成一张随机验证码图片，并写出到浏览器
            OutputStream out = response.getOutputStream();
            Captcha captcha = new Captcha();
            captcha.createCaptchaImg(out);
            String code = captcha.getCode();
            //把sCode共享给用户登录时使用
            session.setAttribute("code", code);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
