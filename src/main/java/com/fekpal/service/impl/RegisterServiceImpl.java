package com.fekpal.service.impl;

import com.fekpal.api.RegisterService;
import com.fekpal.common.base.BaseServiceImpl;
import com.fekpal.common.base.CRUDException;
import com.fekpal.common.constant.*;
import com.fekpal.common.utils.FileUploadUtil;
import com.fekpal.common.utils.MD5Util;
import com.fekpal.common.utils.RandomUtil;
import com.fekpal.common.utils.TimeUtil;
import com.fekpal.common.utils.captcha.Captcha;
import com.fekpal.common.utils.msg.email.EmailMsg;
import com.fekpal.common.utils.msg.email.EmailSender;
import com.fekpal.dao.mapper.*;
import com.fekpal.dao.model.*;
import com.fekpal.service.model.domain.ClubReg;
import com.fekpal.service.model.domain.PersonReg;
import com.fekpal.service.model.domain.SauReg;
import com.fekpal.common.session.SessionContent;
import com.fekpal.common.session.SessionLocal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpSession;

/**
 * Created by APone on 2018/2/9 0:42.
 */
@Service
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
    private ClubAuditMapper clubAuditMapper;

    @Autowired
    private EmailSender emailSender;

    /**
     * 社团注册
     */
    private static final String CLUB_REG = "club_reg";

    /**
     * 普通注册
     */
    private static final String PERSON_REG = "person_reg";

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED, rollbackFor = {Exception.class})
    public int insertPersonReg(PersonReg reg) {
        SessionContent.Captcha captcha = SessionContent.createCaptcha();
        captcha.setAuthorize(reg.getEmail());
        captcha.setCode(reg.getCaptcha());
        captcha.setCurrentTime(reg.getCurrentTime());
        if (!isValidCaptcha(captcha, PERSON_REG)) {
            return Operation.CAPTCHA_INCORRECT;
        }

        String salt = RandomUtil.createSalt();
        User user = new User();
        user.setUserName(reg.getUserName());
        user.setPassword(MD5Util.md5(reg.getPassword() + salt));
        user.setEmail(reg.getEmail());
        user.setLoginIp(reg.getLoginIp());
        user.setLoginTime(reg.getRegisterTime());
        user.setRegisterIp(reg.getRegisterIp());
        user.setRegisterTime(reg.getRegisterTime());
        user.setPhone(DefaultField.DEFAULT_PHONE);
        user.setAuthority(SystemRole.PERSON);
        user.setUserState(AvailableState.AVAILABLE);
        user.setUserKey(salt);

        Person person = new Person();
        person.setPersonState(AvailableState.AVAILABLE);
        person.setNickname(DefaultField.DEFAULT_NICKNAME + user.getUserId());
        person.setLogo(DefaultField.DEFAULT_LOGO);
        person.setGender(DefaultField.DEFAULT_GENDER);
        person.setBirthday(DefaultField.DEFAULT_TIME);

        try {
            int row = mapper.insert(user);
            person.setUserId(user.getUserId());
            row += personMapper.insert(person);
            if (row != 2) throw new CRUDException("普通用户注册数据插入数量异常，数量：" + row);
        } catch (Exception e) {
            throw new CRUDException(e.getMessage());
        }

        //注册成功后直接存储注册用户身份会话信息，以便直接自动登录
        SessionContent.UserIdentity identity = SessionContent.createUID();
        identity.setId(user.getUserId());
        identity.setName(user.getUserName());
        identity.setAuthority(user.getAuthority());
        SessionLocal.local(session).createUserIdentity(identity);
        return Operation.SUCCESSFULLY;
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED, rollbackFor = {Exception.class})
    public int insertSauReg(SauReg reg) {

        String salt = RandomUtil.createSalt();
        User user = new User();
        user.setUserName(reg.getUserName());
        user.setPassword(MD5Util.md5(reg.getPassword() + salt));
        user.setEmail(reg.getEmail());
        user.setLoginIp(reg.getLoginIp());
        user.setLoginTime(reg.getRegisterTime());
        user.setRegisterIp(reg.getRegisterIp());
        user.setRegisterTime(reg.getRegisterTime());
        user.setPhone(reg.getPhone());
        user.setAuthority(SystemRole.SAU);
        user.setUserState(AvailableState.AVAILABLE);
        user.setUserKey(salt);

        Sau sau = new Sau();
        sau.setSauName(reg.getSauName());
        sau.setAdminName(reg.getAdminName());
        sau.setFoundTime(reg.getRegisterTime());
        sau.setMembers(DefaultField.DEFAULT_MEMBERS);
        sau.setLogo(DefaultField.DEFAULT_LOGO);
        sau.setSauState(AvailableState.AVAILABLE);

        try {
            int row = mapper.insert(user);
            sau.setUserId(user.getUserId());
            row += sauMapper.insert(sau);
            if (row != 2) throw new CRUDException("校社联用户注册数据插入数量异常，数量：" + row);
        } catch (Exception e) {
            throw new CRUDException(e.getMessage());
        }

        return Operation.SUCCESSFULLY;
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED, rollbackFor = {Exception.class})
    public int insertClubReg(ClubReg reg) {
        SessionContent.Captcha captcha = SessionContent.createCaptcha();
        captcha.setAuthorize(reg.getEmail());
        captcha.setCode(reg.getCaptcha());
        captcha.setCurrentTime(reg.getCurrentTime());
        if (!isValidCaptcha(captcha, CLUB_REG)) {
            return Operation.CAPTCHA_INCORRECT;
        }

        String salt = RandomUtil.createSalt();
        User user = new User();
        user.setUserName(reg.getUserName());
        user.setPassword(MD5Util.md5(reg.getPassword() + salt));
        user.setEmail(reg.getEmail());
        user.setLoginIp(reg.getLoginIp());
        user.setLoginTime(reg.getRegisterTime());
        user.setRegisterIp(reg.getRegisterIp());
        user.setRegisterTime(reg.getRegisterTime());
        user.setPhone(reg.getPhone());
        user.setAuthority(SystemRole.CLUB);
        user.setUserState(AvailableState.AUDITING);
        user.setUserKey(salt);

        Club club = new Club();
        club.setClubName(reg.getClubName());
        club.setAdminName(reg.getAdminName());
        club.setFoundTime(reg.getRegisterTime());
        club.setDescription(reg.getDescription());
        club.setClubType(reg.getClubType());
        club.setLogo(DefaultField.DEFAULT_LOGO);
        club.setClubState(AvailableState.AUDITING);
        club.setClubView(DefaultField.DEFAULT_CLUB_OVERVIEW);
        club.setMembers(DefaultField.DEFAULT_MEMBERS);
        club.setLikeNumber(DefaultField.DEFAULT_MEMBERS);

        ClubAudit clubAudit = new ClubAudit();
        clubAudit.setRegisterTime(reg.getRegisterTime());
        clubAudit.setApplyName(reg.getAdminName());
        clubAudit.setAuditResult(AuditState.AUDITING);
        clubAudit.setAuditTitle(reg.getClubName() + " 注册申请审核");
        clubAudit.setAuditTime(reg.getRegisterTime());
        clubAudit.setAuditDescription(DefaultField.EMPTY);
        clubAudit.setAuditState(AvailableState.AUDITING);

        try {
            int row = mapper.insert(user);
            club.setUserId(user.getUserId());
            row += clubMapper.insert(club);
            String auditFileName = FileUploadUtil.fileHandle(reg.getAuditFile(), FIleDefaultPath.CLUB_AUDIT_FILE);
            clubAudit.setClubId(club.getClubId());
            clubAudit.setFile(auditFileName);
            row += clubAuditMapper.insert(clubAudit);
            if (row != 3) throw new CRUDException("社团用户注册数据插入数量异常，数量：" + row);
        } catch (Exception e) {
            throw new CRUDException(e.getMessage());
        }

        return Operation.SUCCESSFULLY;
    }

    /**
     * 验证验证码
     *
     * @param captcha 验证码
     * @return 是否正确
     */
    private boolean isValidCaptcha(SessionContent.Captcha captcha, final String type) {
        SessionLocal sessionLocal = SessionLocal.local(session);
        boolean isValid = sessionLocal.isValidCaptchaWithAuth(captcha, type);
        //正确则清除原有验证信息
        if (isValid) {
            sessionLocal.clear(type);
        }
        return isValid;
    }

    /**
     * 发送验证码邮件通用操作
     *
     * @param email  邮箱地址
     * @param type   注册种类
     * @param common 对象类型
     */
    private void sendRegCaptchaByEmail(String email, final String type, String common) {
        String code = new Captcha().getCode();
        SessionContent.Captcha captcha = SessionContent.createCaptcha();
        captcha.setCode(code);
        captcha.setActiveTime(1000 * 60 * 10);
        captcha.setCreateTime(TimeUtil.currentTime());
        captcha.setAuthorize(email);
        SessionLocal.local(session).createCaptcha(captcha, type);

        EmailMsg msg = new EmailMsg();
        msg.setSubject("校社联管理系统" + common + "用户注册验证码");
        msg.setText("您获取的验证码为：" + code + "\n10分钟内有效，如果不是您提出的注册申请，请留意");
        msg.setTo(email);
        emailSender.send(msg);
    }

    @Override
    public void sendClubEmailCaptcha(String email) {
        sendRegCaptchaByEmail(email, CLUB_REG, "社团");
    }

    @Override
    public void sendPersonEmailCaptcha(String email) {
        sendRegCaptchaByEmail(email, PERSON_REG, "普通");
    }
}
