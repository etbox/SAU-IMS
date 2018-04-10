package com.fekpal.dao.model;

import com.fekpal.common.base.BaseModel;

import java.sql.Timestamp;

public class Message extends BaseModel {

    private static final long serialVersionUID = 7490442249324832127L;

    private Integer messageId;

    private Integer orgId;

    private String messageTitle;

    private String messageContent;

    private Timestamp releaseTime;

    private String releaseName;

    private Integer messageType;

    /**
     * 消息附件
     */
    private String messageAnnex;

    private Integer messageState;

    public String getReleaseName() {
        return releaseName;
    }

    public void setReleaseName(String releaseName) {
        this.releaseName = releaseName;
    }

    public Integer getOrgId() {
        return orgId;
    }

    public void setOrgId(Integer orgId) {
        this.orgId = orgId;
    }

    public Integer getMessageId() {
        return messageId;
    }

    public void setMessageId(Integer messageId) {
        this.messageId = messageId;
    }

    public String getMessageTitle() {
        return messageTitle;
    }

    public void setMessageTitle(String messageTitle) {
        this.messageTitle = messageTitle;
    }

    public String getMessageContent() {
        return messageContent;
    }

    public void setMessageContent(String messageContent) {
        this.messageContent = messageContent;
    }

    public Timestamp getReleaseTime() {
        return releaseTime;
    }

    public void setReleaseTime(Timestamp releaseTime) {
        this.releaseTime = releaseTime;
    }

    public Integer getMessageType() {
        return messageType;
    }

    public void setMessageType(Integer messageType) {
        this.messageType = messageType;
    }

    public String getMessageAnnex() {
        return messageAnnex;
    }

    public void setMessageAnnex(String messageAnnex) {
        this.messageAnnex = messageAnnex;
    }

    public Integer getMessageState() {
        return messageState;
    }

    public void setMessageState(Integer messageState) {
        this.messageState = messageState;
    }
}
