package com.fekpal.service.impl;

import com.fekpal.api.MessageSendService;
import com.fekpal.common.base.BaseServiceImpl;
import com.fekpal.dao.mapper.MessageMapper;
import com.fekpal.dao.mapper.MessageReceiveMapper;
import com.fekpal.dao.model.Message;
import com.fekpal.service.model.domain.SMsgRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.io.OutputStream;
import java.util.List;

/**
 * Created by APone on 2017/9/17.
 */
@Service
public class MessageSendServiceImpl extends BaseServiceImpl<MessageMapper, Message> implements MessageSendService {

    @Autowired
    private HttpSession session;

    @Autowired
    private MessageReceiveMapper messageReceiveMapper;

    @Override
    public Message selectByUserId(int id) {
        return null;
    }

    @Override
    public List<Message> queryByMessageTitle(SMsgRecord msg, int offset, int limit) {
        return null;
    }

    @Override
    public List<Message> queryByReleaseName(SMsgRecord msg, int offset, int limit) {
        return null;
    }

    @Override
    public int deleteByMessageId(int id) {
        return 0;
    }

    @Override
    public int addNewMessage(SMsgRecord record) {
        return 0;
    }

    @Override
    public void getMessageAnnex(OutputStream outputStream) {

    }

    @Override
    public List<Message> loadAllMessage(SMsgRecord msg, int offset, int limit) {
        return null;
    }
}
