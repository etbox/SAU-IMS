package com.fekpal.service.impl;

import com.fekpal.api.MessageSendService;
import com.fekpal.common.base.BaseServiceImpl;
import com.fekpal.dao.mapper.MessageMapper;
import com.fekpal.dao.model.Message;
import org.springframework.stereotype.Service;

/**
 * Created by APone on 2017/9/17.
 */
@Service
public class MessageSendServiceImpl extends BaseServiceImpl<MessageMapper, Message> implements MessageSendService {
}
