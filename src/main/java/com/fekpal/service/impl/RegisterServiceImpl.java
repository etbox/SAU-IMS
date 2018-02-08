package com.fekpal.service.impl;

import com.fekpal.api.RegisterService;
import com.fekpal.common.base.BaseServiceImpl;
import com.fekpal.common.constant.Operation;
import com.fekpal.common.utils.TimeUtils;
import com.fekpal.common.utils.captcha.Captcha;
import com.fekpal.common.utils.msg.email.EmailMsg;
import com.fekpal.common.utils.msg.email.EmailSender;
import com.fekpal.dao.mapper.ClubMapper;
import com.fekpal.dao.mapper.PersonMapper;
import com.fekpal.dao.mapper.SauMapper;
import com.fekpal.dao.mapper.UserMapper;
import com.fekpal.dao.model.User;
import com.fekpal.service.model.domain.ClubReg;
import com.fekpal.service.model.domain.PersonReg;
import com.fekpal.service.model.domain.SauReg;
import com.fekpal.web.session.SessionContent;
import com.fekpal.web.session.SessionLocal;
import com.fekpal.web.session.SessionNullException;
import org.apache.commons.mail.EmailException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpSession;

/**
 * Created by APone on 2018/2/9 0:42.
 */
public class RegisterServiceImpl extends BaseServiceImpl<UserMapper, User> implements RegisterService {

    @Autowired
    HttpSession session;

    @Autowired
    private SauMapper sauMapper;

    @Autowired
    private ClubMapper clubMapper;

    @Autowired
    private PersonMapper personMapper;

    @Autowired
    private EmailSender emailSender;

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED, rollbackFor = {Throwable.class})
    public int insertPersonReg(PersonReg reg) {
        SessionContent.Captcha captcha = new SessionContent.Captcha();
        captcha.setCode(reg.getCode());
        captcha.setCurrentTime(reg.getCurrentTime());

        if (!isValidCaptcha(captcha)) {
            return Operation.CAPTCHA_INCORRECT;
        }

        User user = new User();
        user.setUserName(reg.getUserName());
        user.setPassword(reg.getPassword());
        user.setEmail(reg.getEmail());
        user.setLoginIp(reg.getLoginIp());
        user.setLoginTime(reg.getRegisterTime());
        user.setLoginIp(reg.getRegisterIp());
        user.setRegisterTime(reg.getRegisterTime());

        int row = mapper.insert(user);


        return 0;
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED, rollbackFor = {Throwable.class})
    public int insertSauReg(SauReg reg) {
        return 0;
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED, rollbackFor = {Throwable.class})
    public int insertClubReg(ClubReg reg) {
        return 0;
    }

    private boolean isValidCaptcha(SessionContent.Captcha captcha) {
        SessionLocal sessionLocal = SessionLocal.local(session);
        return sessionLocal.isValidCaptcha(captcha);
    }

    @Override
    public int sendRegCaptchaByEmail(String email) {
        try {
            String code = new Captcha().getCode();
            SessionContent.Captcha captcha = new SessionContent.Captcha();
            captcha.setCode(code);
            captcha.setActiveTime(1000 * 60 * 10);
            captcha.setCreateTime(TimeUtils.currentTime());
            SessionLocal.local(session).createCaptcha(captcha);

            EmailMsg msg = new EmailMsg();
            msg.setSubject("校社联管理系统用户注册验证码");
            msg.setMsg("您获取的验证码为：" + code + "，10分钟内有效");
            msg.setTo(email);

            emailSender.sendMsg(msg);
            return Operation.SUCCESSFULLY;
        } catch (EmailException | SessionNullException e) {
            e.printStackTrace();
        }
        return Operation.FAILED;
    }

    @Override
    public int sendRegCaptchaByPhone(String phone) {
        return Operation.SUCCESSFULLY;
    }
}
