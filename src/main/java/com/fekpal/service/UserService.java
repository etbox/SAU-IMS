package com.fekpal.service;

import com.fekpal.domain.pojo.Club;
import com.fekpal.domain.pojo.Person;
import com.fekpal.domain.pojo.Sau;
import com.fekpal.domain.pojo.User;

import java.util.List;

/**
 * Created by APone on 2017/8/25.
 * UserService接口
 */
public interface UserService {


    /**
     * 根据用户id获得用户
     *
     * @param userId int
     * @return User
     */
    User getByUserId(int userId);

    /**
     * 根据用户名称获得用户
     *
     * @param userName String
     * @return User
     */
    User getByUserName(String userName);

    /**
     * 根据用户名和密码获得用户
     *
     * @param userName String
     * @param password String
     * @return User
     */
    User getByUserNameAndPwd(String userName, String password);

    /**
     * 根据邮箱获得用户
     *
     * @param email String
     * @return User
     */
    User getByEmail(String email);

    /**
     * 添加普通成员
     *
     * @param person Person
     */
    void addNewPerson(Person person);

    /**
     * 添加社团成员
     *
     * @param club Club
     */
    void addNewClub(Club club);

    /**
     * 添加校社联成员
     *
     * @param sau  Sau
     */
    void addNewSau(Sau sau);

    /**
     * 修改用户认证信息
     *
     * @param user User
     */
    void updateUserInfo(User user);

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
     * 获取所有系统用户
     * @return List
     */
    List<User> loadAllUser();
}
