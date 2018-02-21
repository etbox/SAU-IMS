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
     * 忘记密码
     */
    private final static String RESET = "reset";

    /**
     * 更新
     */
    private final static String UPDATE = "update";

    /**
     * 验证验证码
     *
     * @param captcha 验证码
     * @return 是否正确
     */
    private boolean isValidCaptcha(SessionContent.Captcha captcha, final String type) {
        SessionLocal sessionLocal = SessionLocal.local(session);
        boolean isValid = sessionLocal.isValidCaptcha(captcha, type);
        //正确则清除原有验证信息
        if (isValid) {
            sessionLocal.clear(type);
        }
        return isValid;
    }


    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED, rollbackFor = {Exception.class})
    public int resetPwd(AccountRecord record) {
        SessionContent.Captcha captcha = SessionContent.createCaptcha();
        captcha.setCode(record.getCode());
        captcha.setCurrentTime(record.getCurrentTime());
        if (!isValidCaptcha(captcha, RESET)) {
            return Operation.CAPTCHA_INCORRECT;
        }

        String email = SessionLocal.local(session).getCaptcha(RESET).getAuthorize();

        ExampleWrapper<User> example = new ExampleWrapper<>();
        example.eq("email", email);
        User user = new User();
        user.setPassword(record.getNewPassword());
        try {
            int row = mapper.updateByExample(user, example);
            if (row != 1) throw new CRUDException("更新数量异常，数量" + row);
        } catch (Exception e) {
            throw new CRUDException(e.getMessage());
        }

        return Operation.SUCCESSFULLY;
    }

    @Override
    public int forgetPwdByEmail(AccountRecord record) {
        ExampleWrapper<User> example = new ExampleWrapper<>();
        example.eq("email", record.getEmail());
        int row = mapper.countByExample(example);
        if (row != 1)  return Operation.FAILED;

        String code = CaptchaUtil.create().getCode();
        SessionContent.Captcha captcha = SessionContent.createCaptcha();
        captcha.setCode(code);
        captcha.setCreateTime(TimeUtil.currentTime());
        captcha.setActiveTime(10 * 60 * 1000);
        captcha.setAuthorize(record.getEmail());
        SessionLocal.local(session).createCaptcha(captcha, RESET);

        EmailMsg msg = new EmailMsg();
        msg.setTo(record.getEmail());
        msg.setSubject("忘记密码邮箱验证");
        msg.setText("您获取的验证码为：" + code + "\n有效期为10分钟，请勿泄露。如果此请求不是由您发出，请尽快修密码");
        emailSender.send(msg);
        return Operation.SUCCESSFULLY;
    }

    @Override
    public int forgetPwdByPhone(AccountRecord record) {

        ExampleWrapper<User> example = new ExampleWrapper<>();
        example.eq("phone", record.getPhone());
        int row = mapper.countByExample(example);
        if (row != 1) return Operation.FAILED;

        String code = CaptchaUtil.create().getCode();
        SessionContent.Captcha captcha = SessionContent.createCaptcha();
        captcha.setCode(code);
        captcha.setCreateTime(TimeUtil.currentTime());
        captcha.setActiveTime(10 * 60 * 1000);
        captcha.setAuthorize(record.getPhone());
        SessionLocal.local(session).createCaptcha(captcha, RESET);
        //此处添加手机发送工具
        return Operation.SUCCESSFULLY;
    }

    @Override
    public int sendModifyPwdCaptcha() {
        return 0;
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED, rollbackFor = {Exception.class})
    public int modifyPwd(AccountRecord record) {
        return 0;
    }

    @Override
    public int sendModifyEmailCaptcha() {
        int uid = SessionLocal.local(session).getUserIdentity().getId();
        User user = mapper.selectByPrimaryKey(uid);
        if (user == null) return Operation.FAILED;

        String code = new Captcha().getCode();
        SessionContent.Captcha captcha = SessionContent.createCaptcha();
        captcha.setCode(code);
        captcha.setCreateTime(TimeUtil.currentTime());
        captcha.setActiveTime(1000 * 60 * 10);
        SessionLocal.local(session).createCaptcha(captcha, UPDATE);

        EmailMsg msg = new EmailMsg();
        msg.setTo(user.getEmail());
        msg.setSubject("修改邮箱安全验证码");
        msg.setText("您所申请修改邮箱安全验证码为：" + code + "\n有效期为10分钟，请勿泄露。如果此请求不是由您发出，请留意您的账户安全");
        emailSender.send(msg);
        return Operation.SUCCESSFULLY;
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED, rollbackFor = {Exception.class})
    public int modifyEmail(AccountRecord record) {
        SessionContent.Captcha captcha = SessionContent.createCaptcha();
        captcha.setCode(record.getCode());
        captcha.setCurrentTime(record.getCurrentTime());
        if (!isValidCaptcha(captcha, UPDATE)) {
            return Operation.CAPTCHA_INCORRECT;
        }

        int uid = SessionLocal.local(session).getUserIdentity().getId();
        User user = new User();
        user.setUserId(uid);
        user.setEmail(record.getNewEmail());
        try {
            int row = mapper.updateByPrimaryKeySelective(user);
            if (row != 1) throw new CRUDException("更新数量异常，数量：" + row);
        } catch (Exception e) {
            throw new CRUDException(e.getMessage());
        }

        return Operation.SUCCESSFULLY;
    }

    @Override
    public int sendModifyPhoneCaptcha() {
        return 0;
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED, rollbackFor = {Exception.class})
    public int modifyPhone(AccountRecord record) {
        return 0;
    }
}
