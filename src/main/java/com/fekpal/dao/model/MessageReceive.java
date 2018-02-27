package com.fekpal.dao.model;

import java.util.List;

public class MessageReceive extends Message {

    private static final long serialVersionUID = 7974631739775373072L;

    private int id;

    private int receiveId;

    private int available;

    private int readFlag;

    private List<Integer> receives;

    public List<Integer> getReceives() {
        return receives;
    }

    public void setReceives(List<Integer> receives) {
        this.receives = receives;
    }

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
