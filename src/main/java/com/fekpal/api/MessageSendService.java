package com.fekpal.api;

import com.fekpal.common.base.BaseService;
import com.fekpal.dao.model.Message;
import com.fekpal.dao.model.MessageReceive;
import com.fekpal.service.model.domain.SMsgRecord;

import java.io.OutputStream;
import java.util.List;

/**
 * Created by APone on 2017/8/25.
 * 信息发送信息接口
 * 该接口提供信息发送者用于发送，删除，查看信息的操作
 */
public interface MessageSendService extends BaseService<Message> {

    Message selectByUserId(int id);

    List<Message> queryByMessageTitle(SMsgRecord msg, int offset, int limit);

    List<Message> queryByReleaseName(SMsgRecord msg, int offset, int limit);

    int deleteByMessageId(int id);

    int addNewMessage(SMsgRecord record);

    void getMessageAnnex(OutputStream outputStream);

    List<Message> loadAllMessage(SMsgRecord msg, int offset, int limit);
}
