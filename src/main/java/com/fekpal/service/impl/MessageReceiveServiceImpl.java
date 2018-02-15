package com.fekpal.service.impl;

import com.fekpal.api.MessageReceiveService;
import com.fekpal.common.base.BaseServiceImpl;
import com.fekpal.dao.mapper.MessageReceiveMapper;
import com.fekpal.dao.model.MessageReceive;
import org.springframework.stereotype.Service;

/**
 * Created by APone on 2018/2/13 19:26.
 */
@Service
public class MessageReceiveServiceImpl extends BaseServiceImpl<MessageReceiveMapper, MessageReceive> implements MessageReceiveService {
}
