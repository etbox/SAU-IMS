package com.fekpal.dao.model;

import com.fekpal.common.base.BaseModel;

import java.sql.Timestamp;

public class Message extends BaseModel {

    private static final long serialVersionUID = 7490442249324832127L;

    private int messageId;

    private int orgId;

    private String messageTitle;

    private String messageContent;

    private Timestamp releaseTime;

    private String releaseName;

    private int messageType;

    private String messageAnnex;

    private int messageState;

    public String getReleaseName() {
        return releaseName;
    }

    public void setReleaseName(String releaseName) {
        this.releaseName = releaseName;
    }

    public int getOrgId() {
        return orgId;
    }

    public void setOrgId(int orgId) {
        this.orgId = orgId;
    }

    public int getMessageId() {
        return messageId;
    }

    public void setMessageId(int messageId) {
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

    public int getMessageType() {
        return messageType;
    }

    public void setMessageType(int messageType) {
        this.messageType = messageType;
    }

    public String getMessageAnnex() {
        return messageAnnex;
    }

    public void setMessageAnnex(String messageAnnex) {
        this.messageAnnex = messageAnnex;
    }

    public int getMessageState() {
        return messageState;
    }

    public void setMessageState(int messageState) {
        this.messageState = messageState;
    }
}
