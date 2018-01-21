package com.fekpal.web.controller;

import com.fekpal.cons.AvailableState;
import com.fekpal.cons.SystemRole;
import com.fekpal.domain.Club;
import com.fekpal.domain.ClubAudit;
import com.fekpal.domain.Person;
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
     * @param session session
     * @param email   发送过来的邮箱
     * @return 返回标准json数据
     */
    @ResponseBody
    @RequestMapping(value = "/reg/code", method = RequestMethod.GET)
    public Map<String, Object> sendEmailCaptcha(HttpSession session, @RequestParam(value = "email") String email) {

        //链接数据库判断邮箱是否已经存在
        if (userService.checkSameEmail(email)) {
            returnData.setStateCode(1, "邮箱已被绑定，请重新输入");
            return returnData.getMap();
        }

        //将邮件地址去空格
        email = email.trim();
        //判断用户是否登录
        if (session.getAttribute("userCode") != null) {
            returnData.setStateCode(1, "你已经登录，请退出后再注册。");
            return returnData.getMap();
        }

        //从工具类中得到邮箱验证码
        String captcha = new Captcha().getCode();
        //把验证码，邮箱，时间发入到session中去
        session.setAttribute("emailCaptcha", captcha);
        session.setAttribute("email", email);
        session.setAttribute("time", TimeTool.getTime());

        try {
            String msg = "您的邮箱验证码是：\n\t" + captcha + "\n验证码十分钟内有效";
            emailSender.sendMsg("校社联管理系统发给您的验证码", msg, email);
            returnData.setStateCode(1, "验证码发送成功!");

        } catch (EmailException e) {
            returnData.setStateCode(1, "验证码发送失败，请重新点击或稍后再试。");
        }
        return returnData.getMap();
    }

    /**
     * 社团注册的方法
     *
     * @param file       上传的文件
     * @param clubMsgMap 社团信息
     * @param request    请求
     * @param session    session会话
     * @return 标准json数据
     */
    @ResponseBody
    @RequestMapping(value = "/reg/club", method = RequestMethod.POST)
    public Map<String, Object> clubRegister(@RequestParam MultipartFile[] file,
                                            @RequestParam(required = false) Map<String, Object> clubMsgMap,
                                            HttpServletRequest request,
                                            HttpSession session) {

        //判断用户是否登录
        if (session.getAttribute("userCode") != null) {
            returnData.setStateCode(1, "你已经登录，请退出后在注册。");
            return returnData.getMap();
        }

        //从前端传传过来的社团信息中获取相应内容
        String userName = clubMsgMap.get("userName").toString().trim();
        String realName = clubMsgMap.get("realName").toString().trim();
        String password = MD5Tool.md5(clubMsgMap.get("password").toString()).trim();
        String phone = clubMsgMap.get("phone").toString().trim();
        String clubName = clubMsgMap.get("clubName").toString().trim();
        String clubType = clubMsgMap.get("clubType").toString().trim();
        String email = clubMsgMap.get("email").toString().trim();
        String captcha = clubMsgMap.get("captcha").toString().trim();

        //如果上传的文件不符合符合条件
        if ((Integer) (ClubAnnRegisterController.handleFile(file).get("code")) != 0) {
            //如果上传的文件不符合条件，返回相应内容
            return ClubAnnRegisterController.handleFile(file);
        }

        //如果session域中验证码非空
        if (session.getAttribute("emailCaptcha") != null) {
            //从session域中得到验证码和邮箱
            String sessionCaptcha = (String) session.getAttribute("emailCaptcha");
            String sessionEmail = (String) session.getAttribute("email");

            //校验验证码和邮箱是否相等和时间是否过了10分钟
            if (sessionCaptcha.trim().toLowerCase().equals(captcha.trim().toLowerCase())
                    && sessionEmail.trim().toLowerCase().equals(email.trim().toLowerCase())
                    && TimeTool.cmpTime((String) session.getAttribute("time"))) {

                //清除session
                session.invalidate();

                //检验用户名是否重复
                if (userService.checkSameAccount(userName)) {
                    returnData.setStateCode(1, "用户名已存在");
                    return returnData.getMap();
                }

                //调用service层检验将社团信息存入数据库
                Club club = new Club();
                Timestamp time = new Timestamp(new Date().getTime());
                String ip = IPUtils.getUserIP(request);

                club.setUserName(userName);
                club.setPassword(password);
                club.setPhone(phone);
                club.setEmail(email);
                club.setUserKey(MD5Tool.md5(time.toString()));
                club.setRegisterIp(ip);
                club.setRegisterTime(time);
                club.setLoginIp(ip);
                club.setLoginTime(time);
                club.setAuthority(SystemRole.CLUB);
                club.setUserState(AvailableState.AUDITING);

                club.setAdminName(realName);
                club.setClubName(clubName);
                club.setClubType(clubType);
                club.setFoundTime(time);

                userService.addNewClub(club);

                //将文件存入服务器中的与本项目同目录的//MySAUImages/clubRegister文件夹中
                List<String> files = FileUploadTool.fileHandle(file, request, "clubRegister");
                ClubAudit audit = new ClubAudit();
                audit.setClub(club);
                audit.setSendTime(time);
                audit.setFile(files.get(0));
                clubAuditService.addNewClubAudit(audit);

                returnData.setStateCode(0, "社团成员注册成功，请稍后留意审核信息");
            } else {
                returnData.setStateCode(1, "验证码不正确，请重新输入");
            }

        } else {
            returnData.setStateCode(1, "未发送验证码，请重新点击发送");
        }

        return returnData.getMap();
    }

    /**
     * 个人用户注册的方法
     *
     * @param personRegisterMsg 个人登录信息
     * @param session           session会话
     * @return 标准json数据
     */
    @ResponseBody
    @RequestMapping("/reg/person")
    public Map<String, Object> personRegister(@RequestBody PersonRegisterMsg personRegisterMsg,
                                              HttpSession session,
                                              HttpServletRequest request) {

        //判断是否登录
        if (session.getAttribute("userCode") != null) {
            returnData.setStateCode(1, "你已经登录，请退出后在注册。");
            return returnData.getMap();
        }

        //如果session域中验证码非空
        if (session.getAttribute("emailCaptcha") != null) {

            //从session域中得到验证码和邮箱
            String sessionCaptcha = (String) session.getAttribute("emailCaptcha");
            String sessionEmail = (String) session.getAttribute("email");

            //校验验证码,邮箱和时间是否过了10分钟是否相等
            if (sessionCaptcha.trim().toLowerCase().equals(personRegisterMsg.getCaptcha().trim().toLowerCase())
                    && sessionEmail.trim().toLowerCase().equals(personRegisterMsg.getUserName().trim().toLowerCase())
                    && TimeTool.cmpTime((String) session.getAttribute("time"))) {

                //清除session
                session.invalidate();

                //检验用户名是否重复
                if (userService.checkSameEmail(personRegisterMsg.getUserName())) {
                    returnData.setStateCode(1, "邮箱已存在");
                    return returnData.getMap();
                }

                //调用service层检验将社团信息存入数据库
                Person person = new Person();
                Timestamp time = new Timestamp(new Date().getTime());
                String ip = IPUtils.getUserIP(request);

                person.setUserName(personRegisterMsg.getUserName());
                person.setPassword(MD5Tool.md5(personRegisterMsg.getPassword()));
                person.setEmail(personRegisterMsg.getUserName());
                person.setPhone("123456708");
                person.setUserKey(MD5Tool.md5(time.toString()));
                person.setRegisterIp(ip);
                person.setRegisterTime(time);
                person.setLoginIp(ip);
                person.setLoginTime(time);
                person.setUserState(AvailableState.AVAILABLE);
                person.setAuthority(SystemRole.PERSON);

                person.setNickname(personRegisterMsg.getUserName());

                userService.addNewPerson(person);
                returnData.setStateCode(0, "普通成员注册成功");

            } else {
                returnData.setStateCode(1, "验证码不正确，请重新输入");
            }
        } else {
            returnData.setStateCode(1, "未发送验证码，请重新点击发送");
        }

        return returnData.getMap();
    }
}
