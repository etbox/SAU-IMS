package com.fekpal.dao.user;

import com.fekpal.domain.User;
import org.springframework.stereotype.Repository;

/**
 * Created by APone on 2017/8/15.
 * UserDao接口
 * 主要用于对基础用户（所有角色的基础）的增删改查
 */
@Repository
public interface UserDao extends BaseUserDao<User> {

    /**
     * 根据用户名称获得用户
     *
     * @param userName String 用户名
     * @return User
     */
    User getByUserName(String userName);

    /**
     * 根据用户名和密码(用户标识)获得用户
     *
     * @param userName String 用户名
     * @param password String 密码
     * @return User
     */
    User getByIdentity(String userName, String password);

    /**
     * 根据邮箱获得用户
     *
     * @param email String 邮件
     * @return User
     */
    User getByEmail(String email);

    /**
     * 根据手机号获用户
     *
     * @param phone String 手机号
     * @return User
     */
    User getByPhone(String phone);

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
     *
     * @param phone String 手机号
     * @return boolean
     */
    boolean exitPhone(String phone);
}
