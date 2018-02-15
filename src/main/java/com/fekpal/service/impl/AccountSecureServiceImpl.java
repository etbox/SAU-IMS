package com.fekpal.service.impl;

import com.fekpal.api.AccountSecureService;
import com.fekpal.common.base.BaseServiceImpl;
import com.fekpal.common.base.CRUDException;
import com.fekpal.common.base.ExampleWrapper;
import com.fekpal.common.constant.AvailableState;
import com.fekpal.common.constant.Operation;
import com.fekpal.common.utils.MD5Util;
import com.fekpal.common.utils.TimeUtil;
import com.fekpal.common.utils.captcha.Captcha;
import com.fekpal.common.utils.captcha.CaptchaUtil;
import com.fekpal.common.utils.msg.email.EmailMsg;
import com.fekpal.common.utils.msg.email.EmailSender;
import com.fekpal.dao.mapper.UserMapper;
import com.fekpal.dao.model.User;
import com.fekpal.service.model.domain.AccountRecord;
import com.fekpal.common.session.SessionContent;
import com.fekpal.common.session.SessionLocal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpSession;

import java.io.IOException;
import java.io.OutputStream;

/**
 * Created by APone on 2018/2/7 2:06.
 */
@Service
public class AccountSecureServiceImpl extends BaseServiceImpl<UserMapper, User> implements AccountSecureService {

    @Autowired
    HttpSession session;

    @Autowired
    EmailSender emailSender;

    /**
     * 登录常量
     */
    private final static String LOGIN = "login";

    /**
     * 忘记密码
     */
    private final static String RESET = "reset";

    /**
     * 更新
     */
    private final static String UPDATE = "update";

    @Override
    public int login(AccountRecord record) {

        //验证登录验证码是否正确
        SessionLocal sessionLocal = SessionLocal.local(session);
        SessionContent.Captcha captcha = SessionContent.createCaptcha();
        captcha.setCode(record.getCode());
        captcha.setCurrentTime(record.getCurrentTime());
        if (!sessionLocal.isValidCaptcha(captcha, LOGIN)) {
            return Operation.CAPTCHA_INCORRECT;
        }

        //开始获取用户的存在
        ExampleWrapper<User> example = new ExampleWrapper<>();
        example.eq("user_name", record.getUserName());
        User user = mapper.selectFirstByExample(example);
        //拥有此用户信息且允许用户登录状态
        if (user == null || user.getUserState() == AvailableState.AVAILABLE) {
            return Operation.FAILED;
        }

        String password = MD5Util.md5(record.getPassword() + user.getUserKey());
        if (user.getPassword().equals(password)) {
            SessionContent.UserIdentity userIdentity = SessionContent.createUID();
            userIdentity.setId(user.getUserId());
            userIdentity.setAuthority(user.getAuthority());
            userIdentity.setName(user.getUserName());
            sessionLocal.createUserIdentity(userIdentity);
            return Operation.SUCCESSFULLY;
        }
        return Operation.CAPTCHA_INCORRECT;
    }

    @Override
    public boolean isLogin() {

        SessionLocal sessionLocal = SessionLocal.local(session);
        return sessionLocal.isExitUserIdentity();

    }

    @Override
    public boolean logout() {
        if (isLogin()) {
            session.invalidate();
        }
        return true;
    }

    @Override
    public void sendLoginCaptchaImage(OutputStream out) {
        try {
            Captcha captchaImg = CaptchaUtil.create();

            SessionContent.Captcha captcha = SessionContent.createCaptcha();
            captcha.setCode(captchaImg.getCode());
            captcha.setCreateTime(System.currentTimeMillis());
            captcha.setActiveTime(1000 * 60 * 5);
            //先数据存储到session，再图片流发送到客户端，否则将引起sessionID不一致
            SessionLocal.local(session).createCaptcha(captcha, LOGIN);
            captchaImg.createCaptchaImg(out);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED, rollbackFor = {Throwable.class})
    public int resetPwd(AccountRecord record) {
        SessionContent.Captcha captcha = SessionContent.createCaptcha();
        captcha.setCode(record.getCode());
        captcha.setCurrentTime(record.getCurrentTime());
        SessionLocal sessionLocal = SessionLocal.local(session);
        if (!sessionLocal.isValidCaptcha(captcha, RESET)) {
            return Operation.CAPTCHA_INCORRECT;
        }

        String email = sessionLocal.getCaptcha(RESET).getAuthorize();

        ExampleWrapper<User> example = new ExampleWrapper<>();
        example.eq("email", email);
        User user = new User();
        user.setPassword(record.getNewPassword());
        int row = mapper.updateByExample(user, example);
        if (row > 1) {
            throw new CRUDException();
        }
        return row == 1 ? Operation.SUCCESSFULLY : Operation.FAILED;
    }

    @Override
    public int forgetPwdByEmail(AccountRecord record) {
        ExampleWrapper<User> example = new ExampleWrapper<>();
        example.eq("email", record.getEmail());
        int row = mapper.countByExample(example);
        if (row == 1) {

            String code = CaptchaUtil.create().getCode();
            SessionContent.Captcha captcha = SessionContent.createCaptcha();
            captcha.setCode(code);
            captcha.setCreateTime(TimeUtil.currentTime());
            captcha.setActiveTime(10 * 60 * 1000);
            captcha.setAuthorize(record.getEmail());
            SessionLocal.local(session).createCaptcha(captcha, LOGIN);

            EmailMsg msg = new EmailMsg();
            msg.setTo(record.getEmail());
            msg.setSubject("忘记密码邮箱验证");
            msg.setText("您获取的验证码为：" + code + " 有效期为10分钟，请勿泄露。如果此请求不是由您发出，请尽快修密码");
            emailSender.send(msg);

            return Operation.SUCCESSFULLY;
        }
        return Operation.FAILED;
    }

    @Override
    public int forgetPwdByPhone(AccountRecord record) {

        ExampleWrapper<User> example = new ExampleWrapper<>();
        example.eq("phone", record.getPhone());
        int row = mapper.countByExample(example);
        if (row == 1) {

            String code = CaptchaUtil.create().getCode();
            SessionContent.Captcha captcha = SessionContent.createCaptcha();
            captcha.setCode(code);
            captcha.setCreateTime(TimeUtil.currentTime());
            captcha.setActiveTime(10 * 60 * 1000);
            captcha.setAuthorize(record.getPhone());
            SessionLocal.local(session).createCaptcha(captcha, LOGIN);
            //此处添加手机发送工具

            return Operation.SUCCESSFULLY;
        }
        return Operation.FAILED;
    }

    @Override
    public boolean confirmUpdatePwd() {

        return false;
    }

    @Override
    public int updatePwd(AccountRecord record) {
        return 0;
    }

    @Override
    public boolean confirmUpdateEmail() {
        return false;
    }

    @Override
    public int updateEmail(AccountRecord record) {
        return 0;
    }

    @Override
    public boolean confirmUpdatePhone() {
        return false;
    }

    @Override
    public int updatePhone(AccountRecord record) {
        return 0;
    }
}
