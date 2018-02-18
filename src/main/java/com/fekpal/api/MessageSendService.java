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

    /**
     * 根据信息标识获取该用户发送的有效信息
     *
     * @param id 信息标识
     * @return 信息记录
     */
    Message selectByMessageId(int id);

    /**
     * 根据标题文字模糊搜索获取该用户发送的时间降序的有效信息记录集分页获取，
     *
     * @param title  信息标题
     * @param offset 跳过读数
     * @param limit  读取数
     * @return 信息记录集
     */
    List<Message> queryByMessageTitle(String title, int offset, int limit);

    /**
     * 根据信息标识逻辑删除该用户所发送的有效信息记录集
     *
     * @param record 信息封装
     *               传入参数：删除集合ids
     * @return 删除状态 Operation.SUCCESSFULLY 成功 Operation.FAILED 失败
     */
    int deleteByMessageId(SMsgRecord record);

    /**
     * 发送校社联用户新的信息
     *
     * @param record 信息封装：信息标题messageTitle，信息内容messageContent，
     *               发送时间releaseTime，消息附件（可为空）messageAnnex
     * @return 添加状态 Operation.SUCCESSFULLY 成功 Operation.FAILED 失败
     */
    int sendSauMessage(SMsgRecord record);

    /**
     * 发送社团用户新的信息
     *
     * @param record 信息封装：信息标题messageTitle，信息内容messageContent，
     *               发送时间releaseTime，消息附件（可为空）messageAnnex
     * @return 添加状态 Operation.SUCCESSFULLY 成功 Operation.FAILED 失败
     */
    int sendClubMessage(SMsgRecord record);

    /**
     * 根据信息标识获取该用户发送的有效信息附件
     *
     * @param outputStream 输出流
     * @param id           信息标识
     */
    void getMessageAnnexByMessageId(int id, OutputStream outputStream);

    /**
     * 加载所有该用户发送的时间降序有效信息,分页获取
     *
     * @param offset 跳过读数
     * @param limit  读取数
     * @return 信息记录集
     */
    List<Message> loadAllMessage(int offset, int limit);
}
