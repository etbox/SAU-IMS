package com.fekpal.service.impl;

import com.fekpal.api.MessageSendService;
import com.fekpal.common.base.BaseServiceImpl;
import com.fekpal.common.base.ExampleWrapper;
import com.fekpal.common.constant.AvailableState;
import com.fekpal.common.constant.Operation;
import com.fekpal.common.session.SessionContent;
import com.fekpal.common.session.SessionLocal;
import com.fekpal.dao.mapper.MessageMapper;
import com.fekpal.dao.mapper.MessageReceiveMapper;
import com.fekpal.dao.model.Message;
import com.fekpal.dao.model.MessageReceive;
import com.fekpal.service.model.domain.SMsgRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

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
    public Message selectByMessageId(int id) {
        int uid = SessionLocal.local(session).getUserIdentity().getId();
        ExampleWrapper<Message> example = new ExampleWrapper<>();
        example.eq("message_id", id)
                .and().eq("user_id", uid)
                .and().eq("message_state", AvailableState.AVAILABLE);
        return mapper.selectFirstByExample(example);
    }

    @Override
    public List<Message> queryByMessageTitle(String title, int offset, int limit) {
        int uid = SessionLocal.local(session).getUserIdentity().getId();
        ExampleWrapper<Message> example = new ExampleWrapper<>();
        example.eq("user_id", uid)
                .and().like("message_title", title)
                .and().eq("message_state", AvailableState.AVAILABLE)
                .orderBy("release_time", false);
        return mapper.selectByExample(example, offset, limit);
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED, rollbackFor = {Exception.class})
    public int deleteByMessageId(SMsgRecord record) {
        int uid = SessionLocal.local(session).getUserIdentity().getId();
        ExampleWrapper<Message> sendExample = new ExampleWrapper<>();
        sendExample.in("message_id", record.getIds())
                .and().eq("user_id", uid)
                .and().eq("message_state", AvailableState.AVAILABLE);
        int row = mapper.deleteByExample(sendExample);

        ExampleWrapper<MessageReceive> receiveExample = new ExampleWrapper<>();
        receiveExample.in("message_id", record.getIds())
                .and().eq("available", AvailableState.AVAILABLE);
        messageReceiveMapper.deleteByExample(receiveExample);
        return row >= record.getIds().size() ? Operation.SUCCESSFULLY : Operation.FAILED;
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED, rollbackFor = {Exception.class})
    public int sendSauMessage(SMsgRecord record) {
        return 0;
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED, rollbackFor = {Exception.class})
    public int sendClubMessage(SMsgRecord record) {
        return 0;
    }

    /**
     * 添加附件文件
     *
     * @param annex 附件文件
     * @return 添加状态
     */
    private boolean addAnnex(MultipartFile annex) {
        return true;
    }

    @Override
    public void getMessageAnnexByMessageId(int id, OutputStream outputStream) {
        ExampleWrapper<Message> example = new ExampleWrapper<>();
        example.eq("message_id", id).and().eq("message_state", AvailableState.AVAILABLE);
        Message message = mapper.selectFirstByExample(example);
        String annexFileName = message.getMessageAnnex();
    }

    @Override
    public List<Message> loadAllMessage(int offset, int limit) {
        int uid = SessionLocal.local(session).getUserIdentity().getId();
        ExampleWrapper<Message> example = new ExampleWrapper<>();
        example.eq("user_id", uid)
                .and().eq("message_state", AvailableState.AVAILABLE)
                .orderBy("release_time", false);
        return mapper.selectByExample(example, offset, limit);
    }
}
