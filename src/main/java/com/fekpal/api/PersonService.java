package com.fekpal.api;

import com.fekpal.common.base.BaseService;
import com.fekpal.dao.model.Person;

import java.util.List;

/**
 * Created by APone on 2017/9/5.
 * 普通用户信息接口
 * 该接口主要提功普通用户信息增删查改操作
 */
public interface PersonService extends BaseService<Person> {

    /**
     * 根据昵称获得个人
     *
     * @param name 昵称
     * @return 普通用户记录
     */
    Person selectByNickname(String name);

    /**
     * 根据用户身份标识获得个人
     *
     * @param id 用户身份标识
     * @return 普通用户记录
     */
    Person selectByUserId(int id);

    /**
     * 根据昵称模糊搜索普通用户信息记录，分页读取
     *
     * @param name   昵称
     * @param offset 跳过读数
     * @param limit  读取数
     * @return 普通用户信息记录集
     */
    List<Person> queryByNickname(String name, int offset, int limit);

    /**
     * 是否有相同的昵称
     *
     * @param name 昵称
     * @return 是否存在
     */
    boolean isExitNickname(String name);

    /**
     * 获得所有普通用户信息记录集
     *
     * @param offset 跳过读数
     * @param limit  读取数
     * @return 普通用户记录集
     */
    List<Person> loadAllPerson(int offset, int limit);
}
