package com.fekpal.service;

import com.fekpal.domain.pojo.User;

import java.util.List;

/**
 * Created by APone on 2017/8/25.
 * UserService接口
 * 该接口主要提功增删查改所有角色的基本身份信息
 */
public interface UserService extends BaseService<User>{

    /**
     * 根据用户名称获得用户
     *
     * @param userName String
     * @return User
     */
    User selectByUserName(String userName);

    /**
     * 根据用户名和密码获得用户
     *
     * @param userName String
     * @param password String
     * @return User
     */
    User selectByUserNameAndPwd(String userName, String password);

    /**
     * 根据邮箱获得用户
     *
     * @param email String
     * @return User
     */
    User selectByEmail(String email);

    /**
     * 判断是否存在相同用户名称
     *
     * @param userName String
     * @return boolean
     */
    boolean isExitAccount(String userName);

    /**
     * 判断是否存在相同的邮箱
     *
     * @param email String
     * @return boolean
     */
    boolean isExitEmail(String email);

    /**
     * 用户身份信息登录
     * @param userName String 用户名
     * @param password String 密码
     * @return 是否登录正确
     */
    boolean login(String userName,String password);

    /**
     * 获取所有系统用户
     *
     * @return List
     */
    List<User> loadAllUser(Integer offset, Integer limit);
}
