package com.fekpal.tool.email;

import org.apache.commons.mail.*;

/**
 * 邮件工具
 */
public class EmailTool {

    private static Email email;

    private static HtmlEmail htmlEmail;

    public static void sendSimpleTextEmail()throws EmailException{

        Email email = new SimpleEmail();
      email.setHostName("smtp.qq.com");
        email.setSmtpPort(465);
          // 用户名和密码为邮箱的账号和密码（不需要进行base64编码）
            email.setAuthenticator(new DefaultAuthenticator("756279336@qq.com", "wfancrubhontbedc"));
              email.setSSLOnConnect(true);
             email.setFrom("756279336@qq.com");
             email.setSubject("TestMail");
               email.setMsg("This is a test mail ... :-)");
            email.addTo("aponework@outlook.com");
            email.send();
    }

    public static void sendEmailWithAttachment(EmailMsg msg){

    }

    public static void sendHtmlEmail(){

    }

    public static void sendHtmlEmailWithEmbededImage(){

    }
}
