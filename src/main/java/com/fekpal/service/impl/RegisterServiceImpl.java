package com.fekpal.service.impl;

import com.fekpal.api.RegisterService;
import com.fekpal.common.base.BaseServiceImpl;
import com.fekpal.common.base.CRUDException;
import com.fekpal.common.constant.*;
import com.fekpal.common.utils.FileUploadUtil;
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
import com.fekpal.common.session.SessionNullException;
import org.apache.commons.mail.EmailException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpSession;
import java.io.IOException;

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

    /**
     * 社团注册
     */
    private static final String CLUB_REG = "club_reg";

    /**
     * 社团注册
     */
    private static final String PERSON_REG = "person_reg";

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED, rollbackFor = {Throwable.class})
    public int insertPersonReg(PersonReg reg) {
        SessionContent.Captcha captcha = new SessionContent.Captcha();
        captcha.setCode(reg.getCaptcha());
        captcha.setCurrentTime(reg.getCurrentTime());
        if (!isValidCaptcha(captcha, PERSON_REG)) {
            return Operation.CAPTCHA_INCORRECT;
        }

        User user = new User();
        user.setUserName(reg.getUserName());
        user.setPassword(reg.getPassword());
        user.setEmail(reg.getEmail());
        user.setLoginIp(reg.getLoginIp());
        user.setLoginTime(reg.getRegisterTime());
        user.setRegisterIp(reg.getRegisterIp());
        user.setRegisterTime(reg.getRegisterTime());
        user.setPhone(DefaultField.DEFAULT_PHONE);
        user.setAuthority(SystemRole.PERSON);
        user.setUserState(AvailableState.AUDITING);
        user.setUserKey(RandomUtil.createSalt());
        int row = mapper.insert(user);

        Person person = new Person();
        person.setUserId(user.getUserId());
        person.setPersonState(AvailableState.AUDITING);
        person.setNickname(DefaultField.DEFAULT_NICKNAME + user.getUserId());
        person.setLogo(DefaultField.DEFAULT_LOGO);
        person.setGender(DefaultField.DEFAULT_GENDER);
        row += personMapper.insert(person);

        return row == 2 ? Operation.SUCCESSFULLY : Operation.FAILED;
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED, rollbackFor = {Throwable.class})
    public int insertSauReg(SauReg reg) {

        User user = new User();
        user.setUserName(reg.getUserName());
        user.setPassword(reg.getPassword());
        user.setEmail(reg.getEmail());
        user.setLoginIp(reg.getLoginIp());
        user.setLoginTime(reg.getRegisterTime());
        user.setRegisterIp(reg.getRegisterIp());
        user.setRegisterTime(reg.getRegisterTime());
        user.setPhone(reg.getPhone());
        user.setAuthority(SystemRole.PERSON);
        user.setUserState(AvailableState.AUDITING);
        user.setUserKey(RandomUtil.createSalt());
        int row = mapper.insert(user);

        Sau sau = new Sau();
        sau.setUserId(user.getUserId());
        sau.setSauName(reg.getSauName());
        sau.setAdminName(reg.getAdminName());
        sau.setFoundTime(reg.getRegisterTime());
        sau.setMembers(DefaultField.DEFAULT_MEMBERS);
        sau.setLogo(DefaultField.DEFAULT_LOGO);
        sau.setSauState(AvailableState.AVAILABLE);
        row += sauMapper.insert(sau);

        return row == 2 ? Operation.SUCCESSFULLY : Operation.FAILED;
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED, rollbackFor = {Throwable.class})
    public int insertClubReg(ClubReg reg) {
        SessionContent.Captcha captcha = new SessionContent.Captcha();
        captcha.setCode(reg.getCaptcha());
        captcha.setCurrentTime(reg.getCurrentTime());
        if (!isValidCaptcha(captcha, CLUB_REG)) {
            return Operation.CAPTCHA_INCORRECT;
        }

        User user = new User();
        user.setUserName(reg.getUserName());
        user.setPassword(reg.getPassword());
        user.setEmail(reg.getEmail());
        user.setLoginIp(reg.getLoginIp());
        user.setLoginTime(reg.getRegisterTime());
        user.setRegisterIp(reg.getRegisterIp());
        user.setRegisterTime(reg.getRegisterTime());
        user.setPhone(reg.getPhone());
        user.setAuthority(SystemRole.PERSON);
        user.setUserState(AvailableState.AUDITING);
        user.setUserKey(RandomUtil.createSalt());
        int row = mapper.insert(user);

        Club club = new Club();
        club.setUserId(user.getUserId());
        club.setAdminName(reg.getAdminName());
        club.setFoundTime(reg.getRegisterTime());
        club.setDescription(reg.getDescription());
        club.setClubType(reg.getClubType());
        club.setLogo(DefaultField.DEFAULT_LOGO);
        club.setClubState(AvailableState.AUDITING);
        club.setClubView(DefaultField.DEFAULT_CLUB_OVERVIEW);
        club.setMembers(DefaultField.DEFAULT_MEMBERS);
        club.setLikeNumber(DefaultField.DEFAULT_MEMBERS);
        row += clubMapper.insert(club);

        String auditFileName;

        try {
            auditFileName = FileUploadUtil.fileHandle(reg.getAuditFile(), FIleDefaultPath.CLUB_AUDIT_FILE);
        } catch (IOException e) {
            e.printStackTrace();
            throw new CRUDException();
        }
        ClubAudit clubAudit = new ClubAudit();
        clubAudit.setClubId(club.getClubId());
        clubAudit.setRegisterTime(reg.getRegisterTime());
        clubAudit.setFile(auditFileName);
        clubAudit.setApplyName(reg.getAdminName());
        clubAudit.setAuditResult(AuditState.AUDITING);
        clubAudit.setAuditTitle(reg.getClubName() + " 注册申请审核");
        clubAudit.setAuditTime(reg.getRegisterTime());
        clubAudit.setAuditDescription(DefaultField.EMPTY);
        clubAudit.setAuditState(AvailableState.AUDITING);
        row += clubAuditMapper.insert(clubAudit);

        return row == 3 ? Operation.SUCCESSFULLY : Operation.FAILED;
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
        sessionLocal.clearCaptcha(type);
        return isValid;
    }

    /**
     * 发送验证码邮件通用操作
     *
     * @param email 邮箱地址
     * @param type  注册种类
     * @return 发送状态
     */
    private int sendRegCaptchaByEmail(String email, final String type) {
        try {
            String code = new Captcha().getCode();
            SessionContent.Captcha captcha = new SessionContent.Captcha();
            captcha.setCode(code);
            captcha.setActiveTime(1000 * 60 * 10);
            captcha.setCreateTime(TimeUtil.currentTime());
            SessionLocal.local(session).createCaptcha(type, captcha);

            EmailMsg msg = new EmailMsg();
            msg.setSubject("校社联管理系统用户注册验证码");
            msg.setMsg("您获取的验证码为：" + code + "，10分钟内有效");
            msg.setTo(email);
            EmailSender sender = new EmailSender();
            sender.sendMsg(msg);

            return Operation.SUCCESSFULLY;
        } catch (EmailException | SessionNullException e) {
            e.printStackTrace();
        }
        return Operation.FAILED;
    }

    @Override
    public int sendClubEmailCaptcha(String email) {
        return sendRegCaptchaByEmail(email, CLUB_REG);
    }

    @Override
    public int sendPersonEmailCaptcha(String email) {
        return sendRegCaptchaByEmail(email, PERSON_REG);
    }
}
