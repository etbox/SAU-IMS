package com.fekpal.common.utils.msg.email;

import org.apache.commons.mail.EmailAttachment;

import java.util.List;

/**
 * 邮箱信息封装
 */
public class EmailMsg {

    /**
     * 发送人
     */
    private String from;

    /**
     * 名称
     */
    private String name;

    /**
     * 接收人
     */
    private String to;

    /**
     * 主题
     */
    private String subject;

    /**
     * 内容
     */
    private String msg;

    /**
     * 接收人列表，群发
     */
    private List<String> recList;

    /**
     * 附件
     */
    private EmailAttachment attachment;

    public List<String> getRecList() {
        return recList;
    }

    public void setRecList(List<String> recList) {
        this.recList = recList;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public EmailAttachment getAttachment() {
        return attachment;
    }

    public void setAttachment(EmailAttachment attachment) {
        this.attachment = attachment;
    }
}
