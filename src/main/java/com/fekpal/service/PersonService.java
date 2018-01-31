package com.fekpal.service;

import com.fekpal.domain.pojo.Person;
import com.fekpal.domain.pojo.User;

import java.util.List;

/**
 * Created by APone on 2017/9/5.
 * PersonService接口
 * 该接口主要提功增删查改普通信息
 */
public interface PersonService extends BaseService<Person> {

    /**
     * 根据昵称获得个人
     *
     * @param nickName String
     * @return Person
     */
    Person selectByNickName(String nickName);

    /**
     * 根据用户id获得个人
     *
     * @param id int
     * @return Person
     */
    Person selectByUserId(int id);

    /**
     * 插入新的普通用户信息以及用户身份信息
     * 只有插入数等于2才成功，其他一律错误
     * @param user 用户身份信息
     * @param person 普通户信息
     * @return 插入数量
     */
    int insertInfo(User user, Person person);

    /**
     * 是否有相同的昵称
     *
     * @param nickName String
     * @return boolean
     */
    boolean isExitNickName(String nickName);

    /**
     * 添加喜爱社团
     *
     * @param personId int
     * @param clubId   int
     */
    void addLikeClub(int personId, int clubId);

    /**
     * 获得所有喜爱的社团id
     *
     * @param id int
     * @return List
     */
    List<Integer> loadAllLikeByPersonId(int id);

    /**
     * 获得所有个人
     *
     * @param start int
     * @param count int
     * @return List
     */
    List<Person> loadAllPerson(int start, int count);
}
