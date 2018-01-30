package com.fekpal.web.controller;

import com.fekpal.cons.ResponseCode;
import com.fekpal.domain.pojo.User;
import com.fekpal.service.UserService;
import com.fekpal.tool.*;
import com.fekpal.tool.captcha.Captcha;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.Map;

import static java.lang.System.out;

/**
 * 安全功能的控制类，包含忘记密码，发送邮箱验证
 * Created by hasee on 2017/8/29.
 */
@Controller
public class SecurityController {

/*
    @Autowired
    private MailHtmlTool mailHtmlTool;//邮箱发送工具
*/
    @Autowired
    private UserService userService;

    @Autowired
    private JsonObject returnData;

    /**
     * 发送重置密码的邮箱验证码
     *
     * @param email   邮箱地址
     * @param session 用户session会话
     * @return 标准json数据
     */
    @ResponseBody
    @RequestMapping(value = "/security/resetpwd/code", method = RequestMethod.GET)
    public Map<String, Object> sendEmailCaptcha(@RequestParam String email, HttpSession session) {

        email = email.trim();

        //判断用户是否登录
        if (session.getAttribute("userCode") != null) {
            returnData.setStateCode(1, "你已登录，请到个人中心修改密码");
            return returnData.getMap();
        }

        if (!userService.isExitEmail(email)) {
            returnData.setStateCode(1, "邮箱不存在");
            return returnData.getMap();
        }

        //从工具类中得到邮箱验证码
        String captcha = new Captcha().getCode();
        //把验证码，邮箱，时间发入到session中去
        session.setAttribute("emailCaptcha", captcha);
        session.setAttribute("email", email);
        session.setAttribute("time", TimeTool.getTime());
/*
        try {
            mailHtmlTool.sendHtml(email, "校社联管理系统发给您重置密码的验证码", "您重置密码的验证码是：<br/>"
                    + captcha + "<br/><br/>" + "验证码十分钟内有效");
            out.println("已经发送了验证码：" + captcha);
        } catch (MessagingException e) {
            returnData.setStateCode(1, "邮件发送失败，请重新发送或稍后再试。");
        }
*/
        return returnData.getMap();

    }

    /**
     * 重置密码的方法
     *
     * @param newPassword 新密码
     * @param captcha     验证码
     * @param session     session
     * @return 标准json数据
     */
    @ResponseBody
    @RequestMapping(value = "/security/resetpwd", method = RequestMethod.PUT)
    public Map<String, Object> resetPassword(@RequestParam(value = "newPassword") String newPassword, @RequestParam(value = "captcha") String captcha, HttpSession session) {

        out.println("发送过来的数据为：newPassword=" + newPassword + "    captcha=" + captcha);

        //检查验证码和密码是否为空
        if (StringUtils.isEmpty(newPassword) || StringUtils.isEmpty(captcha)) {
            returnData.setStateCode(1, "验证码或者密码为空，请重新输入");
            return returnData.getMap();
        } else {
            //将验证码空格去掉
            captcha = captcha.trim();
            newPassword=newPassword.trim();
        }

        if (session.getAttribute("emailCaptcha") != null) {
            //比较验证码是否正确和时间是否在有效期内
            if (session.getAttribute("emailCaptcha").toString().trim().toLowerCase().equals(captcha.toLowerCase())
                    && TimeTool.cmpTime(TimeTool.getTime())) {

                //清除session
                session.invalidate();
                //获取邮箱
                String email = session.getAttribute("email").toString();
                //获取用户原信息并修改密码，更新数据库
                User user = userService.getByEmail(email);
                user.setPassword(newPassword);
                userService.updateUserInfo(user);
                session.setAttribute("userCode", user);

                returnData.setStateCode(0, "密码修改成功");
            } else {
                returnData.setStateCode(1, "验证码错误，请重新输入");
            }
        } else {
            returnData.setStateCode(1, "未发送验证码，请点击发送");
        }

        return returnData.getMap();
    }

    /**
     * 发送修改邮箱的邮箱验证码
     *
     * @param session 用户session会话
     * @param email   邮箱地址
     * @return 是否成功
     */
    @ResponseBody
    @RequestMapping(value = "/security/email/code", method = RequestMethod.GET)
    public Map<String, Object> sendEmailCaptcha(HttpSession session, @RequestParam(value = "email") String email) {

        User user = (User) session.getAttribute("userCode");
        //获取旧邮箱
        String oldEmail = user.getEmail();
        //将邮件地址去空格
        email = email.trim();

        //根据用户id链接数据库判断邮箱是否跟原来邮箱相同
        //如果原来邮箱与发过来的邮箱相同，则返回
        if (email.equals(oldEmail)) {
            returnData.setStateCode(ResponseCode.REQUEST_ERROR, "新邮箱与旧邮箱相同");
            return returnData.getMap();
        }

        //判断用户是否登录
        if (session.getAttribute("userCode") == null) {
            returnData.setStateCode(1, "你还没有登陆，请登陆后在发送邮箱验证码。");
            return returnData.getMap();
        }

        //从工具类中得到邮箱验证码
        String captcha = new Captcha().getCode();
        //把验证码，邮箱，时间发入到session中去
        session.setAttribute("emailCaptcha", captcha);
        session.setAttribute("email", email);
        session.setAttribute("time", TimeTool.getTime());
/*
        try {
            mailHtmlTool.sendHtml(email, "校社联管理系统发给您的验证码", "您的邮箱验证码是：<br/>"
                    + captcha + "<br/><br/>" + "验证码十分钟内有效");
            out.println("已经发送了验证码：" + captcha);
        } catch (MessagingException e) {
            returnData.setStateCode(1, "邮件发送失败，请点击重新发送。如果多次点击发送后，依然不成功，请稍后再试。");
        }
*/
        return returnData.getMap();
    }

    /**
     * 修改邮箱
     * @param newEmail 新邮箱
     * @param captcha 验证码
     * @param session session
     * @return 返回提示信息
     */
    @ResponseBody
    @RequestMapping(value = "/security/email", method = RequestMethod.PUT)
    public Map<String, Object> resetEmail(@RequestParam(value = "newEmail") String newEmail, @RequestParam(value = "captcha") String captcha, HttpSession session) {

        //检查验证码和密码是否为空
        if (StringUtils.isEmpty(newEmail) || StringUtils.isEmpty(captcha)) {
            returnData.setStateCode(1, "验证码或者邮箱为空，请重新输入");
            return returnData.getMap();
        } else {
            //将验证码空格去掉
            captcha = captcha.trim();
            newEmail=newEmail.trim();
        }

        if (session.getAttribute("emailCaptcha") != null) {
            //比较验证码是否正确和时间是否在有效期内
            if (session.getAttribute("emailCaptcha").toString().trim().toLowerCase().equals(captcha.toLowerCase())
                    && TimeTool.cmpTime(TimeTool.getTime())) {

                //清除session
                session.invalidate();

                //获取用户原信息并修改密码，更新数据库
                User user = (User) session.getAttribute("userCode");
                user.setEmail(newEmail);
                userService.updateUserInfo(user);
                session.setAttribute("userCode", user);

                returnData.setStateCode(0, "邮箱修改成功");
            } else {
                returnData.setStateCode(1, "验证码错误，请重新输入");
            }
        } else {
            returnData.setStateCode(1, "未发送验证码，请点击发送");
        }

        return returnData.getMap();
    }

}
