package com.fekpal.dao;

import com.fekpal.domain.Person;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by APone on 2017/8/15.
 * PersonDao的接口,个人普通用户信息，校社联用户和社团以此类推
 */
@Repository
public interface PersonDao {

    /**
     * 根据普通用户id获得个人
     *
     * @param id int 普通用户id
     * @return Person
     */
    Person getPersonByPersonId(int id);

    /**
     * 根据昵称获得个人
     *
     * @param nickName String 普通用户昵称
     * @return Person
     */
    Person getPersonByNickName(String nickName);

    /**
     * 根据用户id获得个人
     *
     * @param id int 用户id
     * @return Person
     */
    Person getPersonByUserId(int id);

    /**
     * 是否有相同的昵称
     *
     * @param nickName String 普通用户昵称
     * @return boolean
     */
    boolean exitNickName(String nickName);

    /**
     * 添加个人
     *
     * @param person Person 普通用户对象
     */
    void add(Person person);

    /**
     * 更新个人
     *
     * @param person Person 普通用户对象
     */
    void update(Person person);


    /**
     * 获得所有个人
     *
     * @param start int 开始
     * @param count int 结束
     * @return List
     */
    List<Person> loadAll(int start, int count);
}
