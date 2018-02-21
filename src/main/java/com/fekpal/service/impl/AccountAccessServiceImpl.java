package com.fekpal.service.impl;

import com.fekpal.api.AccountAccessService;
import com.fekpal.common.base.BaseServiceImpl;
import com.fekpal.common.base.ExampleWrapper;
import com.fekpal.common.constant.AvailableState;
import com.fekpal.common.constant.Operation;
import com.fekpal.common.session.SessionContent;
import com.fekpal.common.session.SessionLocal;
import com.fekpal.common.utils.MD5Util;
import com.fekpal.common.utils.TimeUtil;
import com.fekpal.common.utils.captcha.Captcha;
import com.fekpal.common.utils.captcha.CaptchaUtil;
import com.fekpal.dao.mapper.UserMapper;
import com.fekpal.dao.model.User;
import com.fekpal.service.model.domain.AccountRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.OutputStream;

/**
 * Created by APone on 2018/2/19 21:15.
 */
@Service
public class AccountAccessServiceImpl extends BaseServiceImpl<UserMapper, User> implements AccountAccessService {

    @Autowired
    private HttpSession session;

    /**
     * 登录常量
     */
    private final static String LOGIN = "login";

    @Override
    public int login(AccountRecord record) {

        //验证登录验证码是否正确
        SessionContent.Captcha captcha = SessionContent.createCaptcha();
        captcha.setCode(record.getCode());
        captcha.setCurrentTime(record.getCurrentTime());
        if (!isValidCaptcha(captcha, LOGIN)) {
            return Operation.CAPTCHA_INCORRECT;
        }

        //开始获取用户的存在
        ExampleWrapper<User> example = new ExampleWrapper<>();
        example.eq("user_name", record.getUserName());
        User user = mapper.selectFirstByExample(example);
        //拥有此用户信息且允许用户登录状态
        if (user == null || user.getUserState() == AvailableState.UNAVAIABLE) {
            return Operation.FAILED;
        }

        String password = MD5Util.md5(record.getPassword() + user.getUserKey());
        if (user.getPassword().equals(password)) {
            SessionContent.UserIdentity userIdentity = SessionContent.createUID();
            userIdentity.setId(user.getUserId());
            userIdentity.setAuthority(user.getAuthority());
            userIdentity.setName(user.getUserName());
            SessionLocal.local(session).createUserIdentity(userIdentity);
            return Operation.SUCCESSFULLY;
        } else {
            return Operation.FAILED;
        }
    }

    /**
     * 验证验证码
     *
     * @param captcha 验证码
     * @return 是否正确
     */
    private boolean isValidCaptcha(SessionContent.Captcha captcha, final String type) {
        SessionLocal sessionLocal = SessionLocal.local(session);
        boolean isValid = sessionLocal.isValidCaptcha(captcha, type);
        //无论输入的验证最终结果是否正确，验证信息缓存全部清除
        sessionLocal.clear(type);
        return isValid;
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
            return true;
        }
        return false;
    }

    @Override
    public void sendLoginCaptchaImage(OutputStream out) {
        try {
            Captcha captchaImg = CaptchaUtil.create();

            SessionContent.Captcha captcha = SessionContent.createCaptcha();
            captcha.setCode(captchaImg.getCode());
            captcha.setCreateTime(TimeUtil.currentTime());
            captcha.setActiveTime(1000 * 60 * 2);
            //先数据存储到session，再图片流发送到客户端，否则将引起sessionID不一致
            SessionLocal.local(session).createCaptcha(captcha, LOGIN);
            captchaImg.createCaptchaImg(out);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
