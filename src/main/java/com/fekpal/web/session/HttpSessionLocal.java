package com.fekpal.web.session;

import com.fekpal.domain.pojo.User;

import javax.servlet.http.HttpSession;

/**
 * Created by APone on 2018/1/30 13:07.
 * 对session进行封装，提供通用操作，例如验证码，身份等
 */
public class HttpSessionLocal {

    //身份信息
    private static final String USER_IDENTITY = "userIdentity";

    //手机验证信息
    private static final String PHONE_CAPTCHA = "phoneCaptcha";

    //邮箱验证信息
    private static final String EMAIL_CAPTCHA = "emailCaptcha";

    //登录验证信息
    private static final String LOGIN_CAPTCHA = "loginCaptcha";

    //路径验证信息
    private static final String URL_IDENTITY = "urlIdentity";

    //会话对象
    private HttpSession session;

    /**
     * 对session进行通用操作
     *
     * @param session 会话对象
     * @return 会话操作对象
     */
    public static HttpSessionLocal local(HttpSession session) {
        return new HttpSessionLocal(session);
    }

    /**
     * 操作构造函数
     *
     * @param session 会话对象
     */
    private HttpSessionLocal(HttpSession session) {
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
    private SessionContent.Captcha createCaptcha(final String type) throws SessionNullException {
        if (session == null) {
            throw new SessionNullException();
        }
        SessionContent.Captcha captcha = new SessionContent.Captcha();
        session.setAttribute(type, captcha);
        return captcha;
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
    public SessionContent.Captcha createPhoneCaptcha() throws SessionNullException {
        return createCaptcha(PHONE_CAPTCHA);
    }

    /**
     * 获取手机验证信息
     *
     * @return 手机验证
     * @throws SessionNullException 空session异常
     */
    public SessionContent.Captcha getPhoneCaptcha() throws SessionNullException {
        return getCaptcha(PHONE_CAPTCHA);
    }

    /**
     * 清除手机验证信息
     */
    public void clearPhoneCaptcha() {
        clear(PHONE_CAPTCHA);
    }

    /**
     * 创建邮箱验证
     *
     * @return 验证信息
     * @throws SessionNullException 空session异常
     */
    public SessionContent.Captcha createEmailCaptcha() throws SessionNullException {
        return createCaptcha(EMAIL_CAPTCHA);
    }

    /**
     * 获取邮箱验证信息
     *
     * @return 邮箱验证
     * @throws SessionNullException 空session异常
     */
    public SessionContent.Captcha getEmailCaptcha() throws SessionNullException {
        return getCaptcha(EMAIL_CAPTCHA);
    }

    /**
     * 清除邮箱验证信息
     */
    public void clearEmailCaptcha() {
        clear(EMAIL_CAPTCHA);
    }

    /**
     * 创建登录验证
     *
     * @return 验证信息
     * @throws SessionNullException 空session异常
     */
    public SessionContent.Captcha createLoginCaptcha() throws SessionNullException {
        return createCaptcha(LOGIN_CAPTCHA);
    }

    /**
     * 获取登录验证信息
     *
     * @return 登录验证
     * @throws SessionNullException 空session异常
     */
    public SessionContent.Captcha getLoginCaptcha() throws SessionNullException {
        return getCaptcha(LOGIN_CAPTCHA);
    }

    /**
     * 清除登录验证信息
     */
    public void clearLoginCaptcha() {
        clear(LOGIN_CAPTCHA);
    }

    /**
     * 创建路径验证
     *
     * @return 验证信息
     * @throws SessionNullException 空session异常
     */
    public SessionContent.Captcha createUrlIdentity() throws SessionNullException {
        return createCaptcha(URL_IDENTITY);
    }

    /**
     * 获取路径验证信息
     *
     * @return 登录验证
     * @throws SessionNullException 空session异常
     */
    public SessionContent.Captcha getUrlIdentity() throws SessionNullException {
        return getCaptcha(URL_IDENTITY);
    }

    /**
     * 清除路径验证信息
     */
    public void clearUrlIdentity() {
        clear(URL_IDENTITY);
    }

    /**
     * 验证当前验证信息是否符合预先储存的信息
     *
     * @param code        验证码
     * @param currentTime 当前时间
     * @param type        验证种类
     * @return 判断
     */
    private boolean isValidCaptcha(final String code, final long currentTime, final String type) {
        //获得验证信息
        try {
            SessionContent.Captcha captcha = getCaptcha(type);
            if (captcha == null) {
                return false;
            }
            //是否超时
            boolean timeOut = (currentTime - captcha.getCreateTime()) > captcha.getActiveTime();
            //如果验证码正确且无超时则返回真
            return (code.equalsIgnoreCase(captcha.getCode()) && !timeOut);
        } catch (SessionNullException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 验证当前邮箱验证信息是否符合预先储存的信息
     *
     * @param code        验证码
     * @param currentTime 当前时间
     * @return 判断
     */
    public boolean isValidEmailCaptcha(String code, long currentTime) {
        return isValidCaptcha(code, currentTime, EMAIL_CAPTCHA);
    }

    /**
     * 验证当前手机验证信息是否符合预先储存的信息
     *
     * @param code        验证码
     * @param currentTime 当前时间
     * @return 判断
     */
    public boolean isValidPhoneCaptcha(String code, long currentTime) {
        return isValidCaptcha(code, currentTime, PHONE_CAPTCHA);
    }

    /**
     * 验证当前登录验证信息是否符合预先储存的信息
     *
     * @param code        验证码
     * @param currentTime 当前时间
     * @return 判断
     */
    public boolean isValidLoginCaptcha(String code, long currentTime) {
        return isValidCaptcha(code, currentTime, LOGIN_CAPTCHA);
    }

    /**
     * 验证当前路径验证信息是否符合预先储存的信息
     *
     * @param code        验证码
     * @param hashCode    哈希值
     * @param currentTime 当前时间
     * @return 判断
     */
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