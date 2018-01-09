package com.fekpal.dao;

import com.fekpal.domain.User;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by APone on 2017/8/15.
 * userDao接口
 */
@Repository
public interface UserDao {

    /**
     * 根据用户id获得用户
     *
     * @param id int 用户id
     * @return User
     */
    User getUserByUserId(int id);

    /**
     * 根据用户名称获得用户
     *
     * @param userName String 用户名
     * @return User
     */
    User getUserByUserName(String userName);

    /**
     * 根据用户名和密码(用户标识)获得用户
     *
     * @param userName String 用户名
     * @param password String 密码
     * @return User
     */
    User getUserByIdentity(String userName, String password);

    /**
     * 根据邮箱获得用户
     *
     * @param email String 邮件
     * @return User
     */
    User getUserByEmail(String email);

    /**
     * 根据手机号获用户
     *
     * @param phone String 手机号
     * @return User
     */
    User getUserByPhone(String phone);

    /**
     * 添加成员
     *
     * @param user User 用户对象
     */
    void add(User user);

    /**
     * 修改用户部分信息
     *
     * @param user User 用户对象
     */
    void update(User user);

    /**
     * 判断是否存在相同用户名称
     *
     * @param userName String 用户名
     * @return boolean
     */
    boolean isExit(String userName);

    /**
     * 判断是否存在相同的邮箱
     *
     * @param email String 邮件名
     * @return boolean
     */
    boolean exitEmail(String email);

    /**
     * 判断是否存在相同的手机号
     * @param phone String 手机号
     * @return boolean
     */
    boolean exitPhone(String phone);

    /**
     * 获取所有系统用户
     * @param start int 开始
     * @param end int 结束
     * @return List
     */
    List<User> loadAll(int start,int end);
}
