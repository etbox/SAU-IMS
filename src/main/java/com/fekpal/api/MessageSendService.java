package com.fekpal.api;

import com.fekpal.common.base.BaseService;
import com.fekpal.dao.model.Message;
import com.fekpal.dao.model.MessageReceive;

import java.util.List;

/**
 * Created by APone on 2017/8/25.
 * 信息发送信息接口
 * 该接口提供信息发送者用于发送，删除，查看信息的操作
 */
public interface MessageSendService extends BaseService<Message> {
}
