package com.fekpal.service.impl;

import com.fekpal.api.ClubService;
import com.fekpal.api.MessageSendService;
import com.fekpal.api.SauService;
import com.fekpal.common.base.BaseServiceImpl;
import com.fekpal.common.base.CRUDException;
import com.fekpal.common.base.ExampleWrapper;
import com.fekpal.common.constant.*;
import com.fekpal.common.session.SessionContent;
import com.fekpal.common.session.SessionLocal;
import com.fekpal.common.utils.FileUtil;
import com.fekpal.dao.mapper.MessageMapper;
import com.fekpal.dao.mapper.MessageReceiveMapper;
import com.fekpal.dao.mapper.OrgMapper;
import com.fekpal.dao.model.Message;
import com.fekpal.dao.model.MessageReceive;
import com.fekpal.dao.model.Org;
import com.fekpal.service.model.domain.SRMsgRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

/**
 * Created by APone on 2017/9/17.
 */
@Service
public class MessageSendServiceImpl extends BaseServiceImpl<MessageMapper, Message> implements MessageSendService {

    @Autowired
    private OrgMapper orgMapper;

    @Autowired
    private MessageReceiveMapper receiveMapper;

    @Autowired
    private HttpSession session;

    /**
     * 一次循环最大数目
     */
    private int maxItem = 50;

    @Override
    public Message selectByMessageId(int id) {
        int uid = SessionLocal.local(session).getUserIdentity().getUid();
        ExampleWrapper<Message> example = new ExampleWrapper<>();
        example.eq("message_id", id)
                .and().eq("user_id", uid)
                .and().eq("message_state", AvailableState.AVAILABLE);
        return mapper.selectFirstByExample(example);
    }

    @Override
    public List<Message> queryByMessageTitle(String title, int offset, int limit) {
        int uid = SessionLocal.local(session).getUserIdentity().getUid();
        ExampleWrapper<Message> example = new ExampleWrapper<>();
        example.eq("user_id", uid)
                .and().like("message_title", title)
                .and().eq("message_state", AvailableState.AVAILABLE)
                .orderBy("release_time", false);
        return mapper.selectByExample(example, offset, limit);
    }

    @Override
    public int deleteByMessageId(SRMsgRecord record) {
        int uid = SessionLocal.local(session).getUserIdentity().getUid();
        ExampleWrapper<Message> sendExample = new ExampleWrapper<>();
        sendExample.in("message_id", record.getIds())
                .and().eq("user_id", uid)
                .and().eq("message_state", AvailableState.AVAILABLE);
        int row = mapper.deleteByExample(sendExample);
        return row == 1 ? Operation.SUCCESSFULLY : Operation.FAILED;
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED, rollbackFor = {Exception.class})
    public int sendGlobalMessage(SRMsgRecord record) {
        Message message = new Message();
        try {
            String fileName = addAnnex(record);
            message.setMessageAnnex(fileName);
        } catch (Exception e) {
            return Operation.FAILED;
        }
        SessionContent.UserIdentity userIdentity = SessionLocal.local(session).getUserIdentity();
        String releaseName = getReleaseNameByUserId(userIdentity);
        if (releaseName == null) return Operation.FAILED;

        int uid = userIdentity.getUid();
        message.setOrgId(uid);
        message.setMessageTitle(record.getMessageTitle());
        message.setMessageContent(record.getMessageContent());
        message.setReleaseName(releaseName);
        message.setReleaseTime(record.getReleaseTime());
        message.setMessageType(MessageType.ALL);
        message.setMessageState(AvailableState.AVAILABLE);
        int row = mapper.insert(message);
        if (row != 1) throw new CRUDException("全体信息插入异常：" + row);
        return Operation.SUCCESSFULLY;
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED, rollbackFor = {Exception.class})
    public int sendCustomMessage(SRMsgRecord record) {
        Message message = new Message();
        try {
            String fileName = addAnnex(record);
            message.setMessageAnnex(fileName);
        } catch (Exception e) {
            return Operation.FAILED;
        }

        SessionContent.UserIdentity userIdentity = SessionLocal.local(session).getUserIdentity();
        String releaseName = getReleaseNameByUserId(userIdentity);
        int uid = userIdentity.getUid();

        message.setOrgId(uid);
        message.setMessageTitle(record.getMessageTitle());
        message.setMessageContent(record.getMessageContent());
        message.setReleaseName(releaseName);
        message.setReleaseTime(record.getReleaseTime());
        message.setMessageType(MessageType.ALL);
        message.setMessageState(AvailableState.AVAILABLE);
        int row = mapper.insert(message);

        MessageReceive receive = new MessageReceive();
        receive.setMessageId(message.getMessageId());
        receive.setReadFlag(MessageType.UN_READ);
        receive.setAvailable(AvailableState.AVAILABLE);
        List<Integer> receives = record.getReceives();

        //批量一定长度插入
        int size = receives.size(), loopCount = (size % maxItem == 0) ? (size / maxItem) : (size / maxItem + 1);
        for (int i = 0; i < loopCount; i++) {
            int from = i * maxItem, to = (size > from + maxItem) ? from + maxItem : size;
            receive.setReceives(receives.subList(from, to));
            row += receiveMapper.insertLoopOnlyWithReceiveId(receive);
        }
        if (row != size + 1) throw new CRUDException("插入接收人信息出错，发送人和接收人数总数：" + size + " 实际插入：" + row);
        return Operation.SUCCESSFULLY;
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED, rollbackFor = {Exception.class})
    public int sendOrgMessage(SRMsgRecord record) {
        Message message = new Message();
        try {
            String fileName = addAnnex(record);
            message.setMessageAnnex(fileName);
        } catch (Exception e) {
            return Operation.FAILED;
        }

        SessionContent.UserIdentity userIdentity = SessionLocal.local(session).getUserIdentity();
        String releaseName = getReleaseNameByUserId(userIdentity);
        int uid = userIdentity.getUid();

        message.setOrgId(uid);
        message.setMessageTitle(record.getMessageTitle());
        message.setMessageContent(record.getMessageContent());
        message.setReleaseName(releaseName);
        message.setReleaseTime(record.getReleaseTime());
        message.setMessageType(MessageType.ALL);
        message.setMessageState(AvailableState.AVAILABLE);
        int row = mapper.insert(message);

        MessageReceive receive = new MessageReceive();
        receive.setMessageId(message.getMessageId());
        receive.setReadFlag(MessageType.UN_READ);
        receive.setAvailable(AvailableState.AVAILABLE);

        //获取该用户所在的组织所有仍未离开组织的社员
        List<Integer> receives = record.getReceives();
        //批量一定长度插入
        int size = receives.size(), loopCount = (size % maxItem == 0) ? (size / maxItem) : (size / maxItem + 1);
        for (int i = 0; i < loopCount; i++) {
            int from = i * maxItem, to = (size > from + maxItem) ? from + maxItem : size;
            receive.setReceives(receives.subList(from, to));
            row += receiveMapper.insertLoopOnlyWithReceiveId(receive);
        }

        return Operation.SUCCESSFULLY;
    }

    /**
     * 添加附件文件
     *
     * @param record 发送信息封装
     * @return 添加状态
     */
    private String addAnnex(SRMsgRecord record) throws IOException {
        MultipartFile annex = record.getMessageAnnex();
        if (annex != null) {
            return FileUtil.fileHandle(annex, FIleDefaultPath.MESSAGE_ANNEX_FILE);
        }
        return null;
    }

    /**
     * 获取发送人名称
     *
     * @param identity 用户身份
     * @return 发送人名称
     */
    private String getReleaseNameByUserId(SessionContent.UserIdentity identity) {
        Org org = orgMapper.selectByPrimaryKey(identity.getUid());
        return org != null ? org.getOrgName() : null;
    }

    @Override
    public void getAnnexByMessageId(int id, OutputStream outputStream) {
        ExampleWrapper<Message> example = new ExampleWrapper<>();
        example.eq("message_id", id).and().eq("message_state", AvailableState.AVAILABLE);
        Message message = mapper.selectFirstByExample(example);
        String annexFileName = message.getMessageAnnex();
    }

    @Override
    public List<Message> loadAllMessage(int offset, int limit) {
        int uid = SessionLocal.local(session).getUserIdentity().getUid();
        ExampleWrapper<Message> example = new ExampleWrapper<>();
        example.eq("user_id", uid)
                .and().eq("message_state", AvailableState.AVAILABLE)
                .orderBy("release_time", false);
        return mapper.selectByExample(example, offset, limit);
    }
}
