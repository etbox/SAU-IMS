package com.fekpal.dao.model;

import com.fekpal.common.base.BaseModel;

public class MessageRelease extends BaseModel {

    private static final long serialVersionUID = 7974631739775373072L;

    private int id;

    private Message message;

    private int receiveId;

    private int available;

    private int readFlag;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getReceiveId() {
        return receiveId;
    }

    public void setReceiveId(int receiveId) {
        this.receiveId = receiveId;
    }

    public Message getMessage() {
        return message;
    }

    public void setMessage(Message message) {
        this.message = message;
    }

    public int getAvailable() {
        return available;
    }

    public void setAvailable(int available) {
        this.available = available;
    }

    public int getReadFlag() {
        return readFlag;
    }

    public void setReadFlag(int readFlag) {
        this.readFlag = readFlag;
    }
}
