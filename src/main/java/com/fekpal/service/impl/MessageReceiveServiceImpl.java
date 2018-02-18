package com.fekpal.service.impl;

import com.fekpal.api.MessageReceiveService;
import com.fekpal.common.base.BaseServiceImpl;
import com.fekpal.dao.mapper.MessageMapper;
import com.fekpal.dao.mapper.MessageReceiveMapper;
import com.fekpal.dao.model.MessageReceive;
import com.fekpal.service.model.domain.RMsgRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.io.OutputStream;
import java.util.List;

/**
 * Created by APone on 2018/2/13 19:26.
 */
@Service
public class MessageReceiveServiceImpl extends BaseServiceImpl<MessageReceiveMapper, MessageReceive> implements MessageReceiveService {

    @Autowired
    private HttpSession session;

    @Autowired
    private MessageMapper messageMapper;

    @Override
    public MessageReceive selectByMessageId(int id) {
        return null;
    }

    @Override
    public MessageReceive selectByUserId(int id) {
        return null;
    }

    @Override
    public List<MessageReceive> queryByMessageTitle(String title, int offset, int limit) {
        return null;
    }

    @Override
    public List<MessageReceive> queryByReleaseName(String name, int offset, int limit) {
        return null;
    }

    @Override
    public List<MessageReceive> loadAllReceiveMessage(int offset, int limit) {
        return null;
    }

    @Override
    public void getReceiveMessageAnnex(OutputStream outputStream) {

    }

}
