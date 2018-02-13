package com.fekpal.common.session;

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
     * 创建验证信息
     *
     * @param type 验证种类
     * @throws SessionNullException 空session异常
     */
    public void createCaptcha(final SessionContent.Captcha captcha, final String type) throws SessionNullException {
        if (session == null) {
            throw new SessionNullException();
        }
        session.setAttribute(type, captcha);
    }

    /**
     * 创建用户身份信息
     *
     * @param userIdentity 用户身份信息
     * @throws SessionNullException 空session异常
     */
    public void createUserIdentity(SessionContent.UserIdentity userIdentity) throws SessionNullException {
        if (session == null) {
            throw new SessionNullException();
        }
        session.setAttribute(USER_IDENTITY, userIdentity);
    }

    /**
     * 清除信息
     *
     * @param type 种类
     */
    public void clear(final String type) {
        if (session != null) {
            session.removeAttribute(type);
        }
    }

    /**
     * 清除当前用户身份
     */
    public void clearUserIdentity() {
        clear(USER_IDENTITY);
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
     * 验证当前验证信息是否符合预先储存的信息
     *
     * @param captcha 待验证的验证码信息
     * @param type    验证种类
     * @return 判断
     */
    public boolean isValidCaptcha(final SessionContent.Captcha captcha, final String type) {
        //获得验证信息
        try {
            SessionContent.Captcha validCaptcha = getCaptcha(type);
            if (validCaptcha != null) {
                //是否超时
                boolean timeOut = (captcha.getCreateTime() - validCaptcha.getCreateTime()) > validCaptcha.getActiveTime();
                //如果验证码正确且无超时则返回真
                return captcha.getCode().equalsIgnoreCase(validCaptcha.getCode()) &&
                        !timeOut;
            }
        } catch (SessionNullException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 验证当前验证信息是否符合预先储存的信息,且验证码授权对象一致
     *
     * @param captcha 待验证的验证码信息
     * @param type    验证种类
     * @return 判断
     */
    public boolean isValidCaptchaWithAuth(final SessionContent.Captcha captcha, final String type) {
        //获得验证信息
        try {
            SessionContent.Captcha validCaptcha = getCaptcha(type);
            if (validCaptcha != null && validCaptcha.getAuthorize() != null && captcha.getAuthorize() != null) {
                //是否超时
                boolean timeOut = (captcha.getCreateTime() - validCaptcha.getCreateTime()) > validCaptcha.getActiveTime();
                //如果验证码正确,授权对象一致且无超时则返回真
                return captcha.getCode().equalsIgnoreCase(validCaptcha.getCode()) &&
                        captcha.getAuthorize().equals(validCaptcha.getAuthorize()) &&
                        !timeOut;
            }
        } catch (SessionNullException e) {
            e.printStackTrace();
        }
        return false;
    }
}