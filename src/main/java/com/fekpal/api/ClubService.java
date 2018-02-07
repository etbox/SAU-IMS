package com.fekpal.api;

import com.fekpal.common.base.BaseService;
import com.fekpal.common.utils.msg.email.EmailMsg;
import com.fekpal.dao.model.Club;
import com.fekpal.dao.model.User;
import com.fekpal.service.model.domain.ClubReg;

import java.util.List;

/**
 * Created by APone on 2017/9/5.
 * 该接口主要提功增删查改社团信息
 */
public interface ClubService extends BaseService<Club> {

    /**
     * 根据社团名称获得社团信息记录
     *
     * @param name 社团名称
     * @return 社团信息记录
     */
    Club selectByClubName(String name);

    /**
     * 根据用户身份标识获得社团信息记录
     *
     * @param id 用户身份标识
     * @return 社团信息记录
     */
    Club selectByUserId(int id);

    /**
     * 注册新的社团用户
     *
     * @param reg 社团用户注册信息封装
     *            传入参数：用户名userName，密码password，社长adminName，邮箱地址email，手机号码phone，
     *            社团名称clubName，社团类型clubType，描述description，验证码code，当前时间currentTime
     *            社团注册审核文件名auditFileName
     * @return 是否插入成功
     * 参考参数：Operation.SUCCESSFULLY 成功 Operation.FAILED 失败
     */
    int insertAccount(ClubReg reg);

    /**
     * 储存社团注册审核文件
     *
     * @return 是否处理成功
     * 参考参数：Operation.SUCCESSFULLY 成功 Operation.FAILED 失败
     */
    int saveRegAuditFile();

    /**
     * 用邮箱发送注册验证码
     *
     * @param email 邮箱地址
     */
    void sendRegCaptchaByEmail(String email);

    /**
     * 根据社团名称进行模糊搜索社团信息记录，分页读取
     *
     * @param name 社团名称
     * @param offset   跳过读数
     * @param limit    读取数
     * @return 社团信息记录集
     */
    List<Club> queryByClubName(String name, int offset, int limit);

    /**
     * 是否有相同的社团名称的社团
     *
     * @param name 社团名称
     * @return 是否存在
     */
    boolean isExitClubName(String name);


    /**
     * 获得所有的社团信息记录,分页读取
     *
     * @param offset 跳过读数
     * @param limit  读取数
     * @return 社团信息记录集
     */
    List<Club> loadAllClub(int offset, int limit);
}
