package com.fekpal.web.session;

import com.fekpal.dao.model.User;

import javax.servlet.http.HttpSession;

/**
 * Created by APone on 2018/1/30 13:07.
 * 对session进行封装，提供通用操作，例如验证码，身份等
 */
public class SessionLocal {

    /**
     * 身份信息
     */
    private static final String USER_IDENTITY = "userIdentity";

    /**
     * 手机验证信息
     */
    private static final String PHONE_CAPTCHA = "phoneCaptcha";

    /**
     * 邮箱验证信息
     */
    private static final String EMAIL_CAPTCHA = "emailCaptcha";

    /**
     * 登录验证信息
     */
    private static final String LOGIN_CAPTCHA = "loginCaptcha";

    /**
     * 路径验证信息
     */
    private static final String URL_IDENTITY = "urlIdentity";

    /**
     * 验证码常量
     */
    private static final String CAPTCHA = "captcha";

    /**
     * 会话对象
     */
    private HttpSession session;

    /**
     * 对session进行通用操作
     *
     * @param session 会话对象
     * @return 会话操作对象
     */
    public static SessionLocal local(HttpSession session) {
        return new SessionLocal(session);
    }

    /**
     * 操作构造函数
     *
     * @param session 会话对象
     */
    private SessionLocal(HttpSession session) {
        this.session = session;
    }

    /**
     * 获取验证信息
     *
     * @param type 种类
     * @return 验证信息
     * @throws SessionNullException 空session异常
     */
    private SessionContent.Captcha getCaptcha(final String type) throws SessionNullException {
        if (session == null) {
            throw new SessionNullException();
        }
        return (SessionContent.Captcha) session.getAttribute(type);
    }

    /**
     * 判断session是否为空
     *
     * @return 是否为空
     */
    public boolean isEmptySession() {
        return session == null;
    }

    /**
     * 是否存在用户身份信息的会话
     *
     * @return 是否存在
     * @throws SessionNullException 空session异常
     */
    public boolean isExitUserIdentity() throws SessionNullException {
        SessionContent.UserIdentity userIdentity = getUserIdentity();
        return userIdentity != null;
    }

    /**
     * 清除信息
     *
     * @param type 种类
     */
    private void clear(final String type) {
        if (session == null) {
            return;
        }
        session.removeAttribute(type);
    }

    /**
     * 创建验证信息
     *
     * @param type 验证种类
     * @return 验证信息
     * @throws SessionNullException 空session异常
     */
    private SessionContent.Captcha createCaptcha(final String type, final SessionContent.Captcha captcha) throws SessionNullException {
        if (session == null) {
            throw new SessionNullException();
        }
        session.setAttribute(type, captcha);
        return captcha;
    }

    /**
     * 创建验证码信息
     *
     * @param captcha 验证信息
     * @throws SessionNullException 空session异常
     */
    public void createCaptcha(final SessionContent.Captcha captcha) throws SessionNullException {
        createCaptcha(CAPTCHA, captcha);
    }

    /**
     * 获得验证码信息
     *
     * @return 验证码信息
     * @throws SessionNullException 空session异常
     */
    public SessionContent.Captcha getCaptcha() throws SessionNullException {
        return getCaptcha(CAPTCHA);
    }

    /**
     * 刷新验证码信息
     *
     * @param captcha 验证信息
     * @throws SessionNullException 空session异常
     */
    @Deprecated
    public void freshCaptcha(final SessionContent.Captcha captcha) throws SessionNullException {
        createCaptcha(captcha);
    }

    /**
     * 清除验证信息
     */
    public void clearCaptcha() {
        clear(CAPTCHA);
    }

    /**
     * 刷新用户身份信息
     *
     * @param userIdentity 用户身份信息
     * @throws SessionNullException 空session异常
     */
    public void freshUserIdentity(final SessionContent.UserIdentity userIdentity) throws SessionNullException {
        session.setAttribute(USER_IDENTITY, userIdentity);
    }

    /**
     * 创建用户身份信息
     *
     * @param user 用户信息
     * @throws SessionNullException 空session异常
     */
    public void createUserIdentity(User user) throws SessionNullException {
        if (session == null) {
            throw new SessionNullException();
        }
        SessionContent.UserIdentity userIdentity = new SessionContent.UserIdentity(user);
        session.setAttribute(USER_IDENTITY, userIdentity);
    }

    /**
     * 获取当前用户身份
     *
     * @return 会话
     * @throws SessionNullException 空session异常
     */
    public SessionContent.UserIdentity getUserIdentity() throws SessionNullException {
        if (session == null) {
            throw new SessionNullException();
        }
        return (SessionContent.UserIdentity) session.getAttribute(USER_IDENTITY);
    }

    /**
     * 清除当前用户身份
     */
    public void clearUserIdentity() {
        clear(USER_IDENTITY);
    }

    /**
     * 创建手机验证
     *
     * @return 验证信息
     * @throws SessionNullException 空session异常
     */
    @Deprecated
    public SessionContent.Captcha createPhoneCaptcha(final SessionContent.Captcha captcha) throws SessionNullException {
        return createCaptcha(PHONE_CAPTCHA, captcha);
    }

    /**
     * 获取手机验证信息
     *
     * @return 手机验证
     * @throws SessionNullException 空session异常
     */
    @Deprecated
    public SessionContent.Captcha getPhoneCaptcha() throws SessionNullException {
        return getCaptcha(PHONE_CAPTCHA);
    }

    /**
     * 是否存在手机验证信息
     *
     * @return 是否存在
     * @throws SessionNullException 空session异常
     */
    @Deprecated
    public boolean isExitPhoneCaptcha() throws SessionNullException {
        SessionContent.Captcha captcha = getPhoneCaptcha();
        return captcha != null;
    }

    /**
     * 清除手机验证信息
     */
    @Deprecated
    public void clearPhoneCaptcha() {
        clear(PHONE_CAPTCHA);
    }

    /**
     * 创建邮箱验证
     *
     * @return 验证信息
     * @throws SessionNullException 空session异常
     */
    @Deprecated
    public SessionContent.Captcha createEmailCaptcha(final SessionContent.Captcha captcha) throws SessionNullException {
        return createCaptcha(EMAIL_CAPTCHA, captcha);
    }

    /**
     * 获取邮箱验证信息
     *
     * @return 邮箱验证
     * @throws SessionNullException 空session异常
     */
    @Deprecated
    public SessionContent.Captcha getEmailCaptcha() throws SessionNullException {
        return getCaptcha(EMAIL_CAPTCHA);
    }

    /**
     * 是否存在邮箱验证信息
     *
     * @return 是否存在
     * @throws SessionNullException 空session异常
     */
    @Deprecated
    public boolean isExitEmailCaptcha() throws SessionNullException {
        SessionContent.Captcha captcha = getEmailCaptcha();
        return captcha != null;
    }

    /**
     * 清除邮箱验证信息
     */
    @Deprecated
    public void clearEmailCaptcha() {
        clear(EMAIL_CAPTCHA);
    }

    /**
     * 创建登录验证
     *
     * @return 验证信息
     * @throws SessionNullException 空session异常
     */
    @Deprecated
    public SessionContent.Captcha createLoginCaptcha(final SessionContent.Captcha captcha) throws SessionNullException {
        return createCaptcha(LOGIN_CAPTCHA, captcha);
    }

    /**
     * 获取登录验证信息
     *
     * @return 登录验证
     * @throws SessionNullException 空session异常
     */
    @Deprecated
    public SessionContent.Captcha getLoginCaptcha() throws SessionNullException {
        return getCaptcha(LOGIN_CAPTCHA);
    }

    /**
     * 是否存在登录验证信息
     *
     * @return 是否存在
     * @throws SessionNullException 空session异常
     */
    @Deprecated
    public boolean isExitLoginCaptcha() throws SessionNullException {
        SessionContent.Captcha captcha = getLoginCaptcha();
        return captcha != null;
    }

    /**
     * 清除登录验证信息
     */
    @Deprecated
    public void clearLoginCaptcha() {
        clear(LOGIN_CAPTCHA);
    }

    /**
     * 创建路径验证
     *
     * @return 验证信息
     * @throws SessionNullException 空session异常
     */
    @Deprecated
    public SessionContent.Captcha createUrlIdentity(final SessionContent.Captcha captcha) throws SessionNullException {
        return createCaptcha(URL_IDENTITY, captcha);
    }

    /**
     * 获取路径验证信息
     *
     * @return 登录验证
     * @throws SessionNullException 空session异常
     */
    @Deprecated
    public SessionContent.Captcha getUrlIdentity() throws SessionNullException {
        return getCaptcha(URL_IDENTITY);
    }

    /**
     * 是否存在路径验证信息
     *
     * @return 是否存在
     * @throws SessionNullException 空session异常
     */
    @Deprecated
    public boolean isExitUrlIdentity() throws SessionNullException {
        SessionContent.Captcha captcha = getUrlIdentity();
        return captcha != null;
    }

    /**
     * 清除路径验证信息
     */
    @Deprecated
    public void clearUrlIdentity() {
        clear(URL_IDENTITY);
    }

    /**
     * 验证当前验证信息是否符合预先储存的信息
     *
     * @param captcha 待验证的验证码信息
     * @param type    验证种类
     * @return 判断
     */
    private boolean isValidCaptcha(final SessionContent.Captcha captcha, final String type) {
        //获得验证信息
        try {
            SessionContent.Captcha valid = getCaptcha(type);
            if (valid == null) {
                return false;
            }
            //是否超时
            boolean timeOut = (captcha.getCreateTime() - valid.getCreateTime()) > valid.getActiveTime();
            //如果验证码正确且无超时则返回真
            return (captcha.getCode().equalsIgnoreCase(valid.getCode()) && !timeOut);
        } catch (SessionNullException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 验证当前验证信息是否符合预先储存的信息
     *
     * @param captcha 待验证的验证码信息
     * @return 判断
     */
    public boolean isValidCaptcha(final SessionContent.Captcha captcha) {
        return isValidCaptcha(captcha, CAPTCHA);
    }


    /**
     * 验证当前邮箱验证信息是否符合预先储存的信息
     *
     * @param captcha 待验证的验证码信息
     * @return 判断
     */
    @Deprecated
    public boolean isValidEmailCaptcha(final SessionContent.Captcha captcha) {
        return isValidCaptcha(captcha, EMAIL_CAPTCHA);
    }

    /**
     * 验证当前手机验证信息是否符合预先储存的信息
     *
     * @param captcha 待验证的验证码信息
     * @return 判断
     */
    @Deprecated
    public boolean isValidPhoneCaptcha(final SessionContent.Captcha captcha) {
        return isValidCaptcha(captcha, PHONE_CAPTCHA);
    }

    /**
     * 验证当前登录验证信息是否符合预先储存的信息
     *
     * @param captcha 待验证的验证码信息
     * @return 判断
     */
    @Deprecated
    public boolean isValidLoginCaptcha(final SessionContent.Captcha captcha) {
        return isValidCaptcha(captcha, LOGIN_CAPTCHA);
    }

    /**
     * 验证当前路径验证信息是否符合预先储存的信息
     *
     * @param code        验证码
     * @param hashCode    哈希值
     * @param currentTime 当前时间
     * @return 判断
     */
    @Deprecated
    public boolean isValidUrlIdentity(String code, String hashCode, long currentTime) {
        try {
            //获得验证信息
            SessionContent.Captcha captcha = getCaptcha(URL_IDENTITY);
            if (captcha == null) {
                return false;
            }
            //是否超时
            boolean timeOut = (currentTime - captcha.getCreateTime()) > captcha.getActiveTime();
            //如果无超时且hash和code值正确则返回真
            return (code.equals(captcha.getCode()) && !timeOut && hashCode.equals(captcha.getHashCode()));
        } catch (SessionNullException e) {
            e.printStackTrace();
            return false;
        }
    }
}