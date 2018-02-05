package com.fekpal.api;

import com.fekpal.common.base.BaseService;
import com.fekpal.dao.model.User;
import com.fekpal.web.session.SessionContent;

import java.util.List;

/**
 * Created by APone on 2017/8/25.
 * UserService接口
 * 该接口主要提功增删查改所有角色的基本身份信息
 */
public interface UserService extends BaseService<User> {


    /**
     * 根据用户名称获得用户
     *
     * @param userName String 用户名
     * @return User
     */
    User selectByUserName(String userName);

    /**
     * 根据用户名和密码获得用户
     *
     * @param userName String 用户名
     * @param password String 密码
     * @return User
     */
    User selectByUserNameAndPwd(String userName, String password);

    /**
     * 根据邮箱获得用户
     *
     * @param email String 邮箱
     * @return User
     */
    User selectByEmail(String email);

    /**
     * 判断是否存在相同用户名称
     *
     * @param userName String 用户名
     * @return boolean
     */
    boolean isExitAccount(String userName);

    /**
     * 判断是否存在相同的邮箱
     *
     * @param email String 邮箱
     * @return boolean
     */
    boolean isExitEmail(String email);

    /**
     * 获取所有系统用户
     *
     * @param offset Integer 跳过读数
     * @param limit  Integer 读取数
     * @return List
     */
    List<User> loadAllUser(Integer offset, Integer limit);

    /**
     * 用户身份信息进行用户登录
     *
     * @param user    用户身份记录 传入参数：用户名userName, 密码password
     * @param captcha 验证码信息 传入参数 验证码code, 当前时间currentTime
     * @return 是否登录成功
     */
    boolean userLogin(User user, SessionContent.Captcha captcha);

    /**
     * 用户是否仍然处于登录状态
     *
     * @return 是否处于登录状态
     */
    boolean isLogin();

    /**
     * 用户登出系统
     *
     * @return 是否登出成功
     */
    boolean userLogout();
}
