package com.fekpal.api;

import com.fekpal.common.base.BaseService;
import com.fekpal.dao.model.MessageReceive;
import com.fekpal.service.model.domain.RMsgRecord;

import java.io.OutputStream;
import java.util.List;

/**
 * Created by APone on 2018/2/13 19:26.
 * 信息接收服务接口
 * 该接口提供信息接收者用于接收，删除，查看信息的操作
 */
public interface MessageReceiveService extends BaseService<MessageReceive> {

    MessageReceive selectByMessageId(int id);

    MessageReceive selectByUserId(int id);

    List<MessageReceive> queryByMessageTitle(String title, int offset, int limit);

    List<MessageReceive> queryByReleaseName(String name, int offset, int limit);

    void getReceiveMessageAnnex(OutputStream outputStream);

    List<MessageReceive> loadAllReceiveMessage(int offset, int limit);
}
