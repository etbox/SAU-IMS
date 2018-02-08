package com.fekpal.api;

import com.fekpal.common.base.BaseService;
import com.fekpal.dao.model.Sau;
import com.fekpal.dao.model.User;
import com.fekpal.service.model.domain.SauReg;

import java.util.List;

/**
 * Created by APone on 2017/9/5.
 * SauService接口
 * 该接口主要提功增删查改校社联信息
 */
public interface SauService extends BaseService<Sau> {

    /**
     * 根据用户身份标识获取校社联记录信息
     *
     * @param id 用户身份标识
     * @return 校社联信息记录
     */
    Sau selectByUserId(int id);

    /**
     * 插入新的校社联用户信息以及用户身份信息
     *
     * @param reg 校社联用户注册信息封装
     *            传入参数：用户名userName，密码password，社长adminName，邮箱地址email，手机号码phone，
     *            校社联名称sauName，描述description，验证码code，当前时间currentTime，校社联注册审核文件名auditFileName，
     *            登录地址loginIp，登录时间loginTime，注册地址registerIp，注册时间registerTime
     * @return 是否插入成功
     * 参考参数：Operation.SUCCESSFULLY 成功 Operation.FAILED 失败
     */
    int insertAccount(SauReg reg);

    /**
     * 用邮箱发送注册验证码
     *
     * @param email 邮箱地址
     */
    void sendRegCaptchaByEmail(String email);

    /**
     * 获得所有的校社联记录，分页获取
     *
     * @param offset 跳过读数
     * @param limit  读取数
     * @return 校社联信息记录集
     */
    List<Sau> loadAllSau(int offset, int limit);
}
