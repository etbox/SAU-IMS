package com.fekpal.service.impl;

import com.fekpal.dao.mapper.MessageMapper;
import com.fekpal.dao.mapper.MessageReleaseMapper;
import com.fekpal.dao.model.Message;
import com.fekpal.dao.model.MessageRelease;
import com.fekpal.api.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by APone on 2017/9/17.
 */
@Service
public class MessageServiceImpl implements MessageService {

    @Autowired
    private MessageMapper messageMapper;

    @Autowired
    private MessageReleaseMapper releaseDao;

    @Override
    public Message getSendMessageByMessageId(int id) {
        return messageMapper.getMessageByMessageId(id);
    }

    @Override
    public List<Message> getSendMessageByUserId(int id, int start, int count) {
        return messageMapper.getMessagesByUserId(id, start, count);
    }

    @Override
    public List<Message> findSendMessageByTitle(Message message, int start, int count) {
        return messageMapper.findMessageByMessageTitle(message.getMessageTitle(), message.getUserId(), start, count);
    }

    @Override
    public void addNewSendMessage(Message message, List<Integer> rcvIdList) {
        //添加信息
        messageMapper.addMessage(message);

        List<MessageRelease> list = new ArrayList<>();
        MessageRelease release;

        //将信息和收件人进行封装，添加至列表
        for (int rcv : rcvIdList) {
            release = new MessageRelease();
            release.setMessage(message);
            release.setReceiveId(rcv);
            list.add(release);
        }

        //添加到收件人列表
        releaseDao.addMessageRelease(list);
    }

    @Override
    public void updateSendMessage(Message message) {
        messageMapper.updateMessage(message);
    }

    @Override
    public MessageRelease getRcvMessageByReleaseId(int id) {
        return releaseDao.getMessageByMessageReleaseId(id);
    }

    @Override
    public List<MessageRelease> getRvcMessagesByRcvId(int id, int start, int count) {
        return releaseDao.getMessagesByReceiveId(id, start, count);
    }

    @Override
    public List<MessageRelease> findRcvMessageByTitle(MessageRelease release, int start, int count) {
        return releaseDao.findMessageByMessageTitle(release.getMessage().getMessageTitle(), release.getReceiveId(), start, count);
    }

    @Override
    public void updateRcvMessage(MessageRelease release, List<Integer> rcvIdList) {
        releaseDao.updateMessageRelease(release, rcvIdList);
    }
}
