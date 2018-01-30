package com.fekpal.web.session;

import javax.servlet.http.HttpSession;

/**
 * Created by APone on 2018/1/30 13:07.
 */
public abstract class HttpSessionLocal implements HttpSession {

    private static final String SESSION_CONTENT = "sessionContent";

    private SessionContent sessionContent = null;

    /**
     * 获取当前用户会话
     *
     * @return 会话
     */
    SessionContent getUserSession() {
        return (SessionContent) this.getAttribute(SESSION_CONTENT);
    }

    /**
     * 设置当前用户会话
     *
     * @param sessionContent 当前会话
     */
    void setUserSession(SessionContent sessionContent) {
        this.setAttribute(SESSION_CONTENT, sessionContent);
    }

    /**
     * 清除当前会话内容
     */
    public void clear() {
        this.removeAttribute(SESSION_CONTENT);
    }


}
