package com.fekpal.web.model;


import java.sql.Timestamp;

/**
 * 消息列表的中的消息对象
 * Created by hasee on 2017/8/22.
 */
public class NewMsgListDomain {
    private int messageId;
    private String messageTitle;
    private Timestamp sendTime;
    private String sendName;
    private int readFlag;

    public NewMsgListDomain(){}
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

    public Timestamp getSendTime() {
        return sendTime;
    }

    public void setSendTime(Timestamp sendTime) {
        this.sendTime = sendTime;
    }

    public String getSendName() {
        return sendName;
    }

    public void setSendName(String sendName) {
        this.sendName = sendName;
    }

    public int getReadFlag() {
        return readFlag;
    }

    public void setReadFlag(int readFlag) {
        this.readFlag = readFlag;
    }

    @Override
    public String toString() {
        return "NewMsgListDomain{" +
                "messageId=" + messageId +
                ", messageTitle='" + messageTitle + '\'' +
                ", sendTime='" + sendTime + '\'' +
                ", sendName='" + sendName + '\'' +
                ", readFlag=" + readFlag +
                '}';
    }
}
