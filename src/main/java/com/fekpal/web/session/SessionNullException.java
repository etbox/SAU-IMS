package com.fekpal.web.session;

/**
 * Created by APone on 2018/1/31 2:20.
 * session空指针异常
 */
public class SessionNullException extends Exception {

    private static final long serialVersionUID = -7935423530859886862L;

    public SessionNullException() {
        super("session不能为空! (session can not be null!)");
    }

    public SessionNullException(String message) {
        super(message);
    }
}
