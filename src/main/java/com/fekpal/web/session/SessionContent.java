package com.fekpal.web.session;

/**
 * Created by APone on 2018/1/30 13:05.
 */
public class SessionContent {

    private int id;

    private int authority;

    private class Captcha{

        private String code;

        private String hashCode;

    }

    private class EmailCaptcha extends Captcha{

    }
}
