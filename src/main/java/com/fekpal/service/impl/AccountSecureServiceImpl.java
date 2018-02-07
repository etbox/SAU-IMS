package com.fekpal.service.impl;

import com.fekpal.api.AccountSecureService;
import com.fekpal.common.base.BaseServiceImpl;
import com.fekpal.common.base.ExampleWrapper;
import com.fekpal.common.utils.MD5Utils;
import com.fekpal.common.utils.msg.email.EmailSender;
import com.fekpal.dao.mapper.UserMapper;
import com.fekpal.dao.model.User;
import com.fekpal.service.model.domain.AccountRecord;
import com.fekpal.web.session.SessionContent;
import com.fekpal.web.session.SessionLocal;
import com.fekpal.web.session.SessionNullException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;

import static test.Model.user;

/**
 * Created by APone on 2018/2/7 2:06.
 */
@Service
public class AccountSecureServiceImpl extends BaseServiceImpl<UserMapper, User> implements AccountSecureService {

    @Autowired
    HttpSession session;

    @Autowired
    EmailSender emailSender;

    @Override
    public boolean login(AccountRecord record) {
        try {
            //验证登录验证码是否正确
            SessionLocal sessionLocal = SessionLocal.local(session);
            SessionContent.Captcha captcha = new SessionContent.Captcha();
            captcha.setCode(record.getCode());
            captcha.setCurrentTime(record.getCurrentTime());
            if (!sessionLocal.isValidLoginCaptcha(captcha)) {
                return false;
            }

            //开始获取用户的存在
            ExampleWrapper<User> example = new ExampleWrapper<>();
            example.eq("user_name", record.getUserName());
            User userIdentity = mapper.selectFirstByExample(example);
            if (userIdentity == null) {
                return false;
            }
            String password = MD5Utils.md5(userIdentity.getPassword() + userIdentity.getUserKey());
            if (user.getPassword().equals(password)) {
                sessionLocal.createUserIdentity(userIdentity);
                return true;
            }
        } catch (SessionNullException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean isLogin() {
        try {
            SessionLocal sessionLocal = SessionLocal.local(session);
            return sessionLocal.isExitUserIdentity();
        } catch (SessionNullException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean logout() {
        session.invalidate();
        return true;
    }

    @Override
    public int updatePwd(AccountRecord record) {
        SessionLocal sessionLocal = SessionLocal.local(session);
        return 0;
    }

    @Override
    public int resetPwd(AccountRecord record) {
        return 0;
    }

    @Override
    public void sendLoginCaptchaImage() {

    }

    @Override
    public int forgetPwdByEmail(AccountRecord record) {
        return 0;
    }

    @Override
    public int forgetPwdByPhone(AccountRecord record) {
        return 0;
    }

    @Override
    public boolean confirmUpdatePwd() {
        return false;
    }

    @Override
    public boolean confirmUpdateEmail() {
        return false;
    }

    @Override
    public boolean confirmUpdatePhone() {
        return false;
    }

    private void emailSend(String object, String email) {

    }

    @Override
    public int updateEmail(AccountRecord record) {
        return 0;
    }

    @Override
    public int updatePhone(AccountRecord record) {
        return 0;
    }
}
