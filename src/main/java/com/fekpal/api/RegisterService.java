package com.fekpal.api;

import com.fekpal.common.base.BaseService;
import com.fekpal.dao.model.User;
import com.fekpal.service.model.domain.ClubReg;
import com.fekpal.service.model.domain.PersonReg;
import com.fekpal.service.model.domain.SauReg;

/**
 * Created by APone on 2018/2/9 0:31.
 * 注册服务接口
 * 该接口用于提供注册普通用户，社团用户，校社联用户，以及发送注册验证码的操作
 */
public interface RegisterService extends BaseService<User> {

    /**
     * 注册普通用户
     *
     * @param reg 普通注册信息封装
     *            传入参数：用户名userName，密码password，邮箱地址email，验证码code，当前时间currentTime，
     *            登录地址loginIp，登录时间loginTime，注册地址registerIp，注册时间registerTime
     * @return 注册状态 Operation.SUCCESSFULLY 成功 Operation.FAILED 失败 Operation.CAPTCHA_INCORRECT 验证码错误
     */
    int insertPersonReg(PersonReg reg);

    /**
     * 注册校社联用户
     *
     * @param reg 校社联注册信息封装
     * @return 注册状态 Operation.SUCCESSFULLY 成功 Operation.FAILED 失败 Operation.CAPTCHA_INCORRECT 验证码错误
     */
    int insertSauReg(SauReg reg);

    /**
     * 注册社团用户
     *
     * @param reg 社团注册信息封装
     * @return 注册状态 Operation.SUCCESSFULLY 成功 Operation.FAILED 失败 Operation.CAPTCHA_INCORRECT 验证码错误
     */
    int insertClubReg(ClubReg reg);

    /**
     * 邮箱发送注册验证码
     *
     * @param email 邮箱地址
     * @return 发送状态 Operation.SUCCESSFULLY 成功 Operation.FAILED 失败
     */
    int sendRegCaptchaByEmail(String email);

    /**
     * 手机发送注册验证码
     *
     * @param phone 手机号码
     * @return 发送状态 Operation.SUCCESSFULLY 成功 Operation.FAILED 失败
     */
    int sendRegCaptchaByPhone(String phone);

}
