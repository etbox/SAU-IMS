package com.fekpal.dao.user;

import com.fekpal.domain.Person;
import org.springframework.stereotype.Repository;

/**
 * Created by APone on 2017/8/15.
 * PersonDao的接口
 * 主要用于对普通用户的增删查改
 */
@Repository
public interface PersonDao extends BaseUserDao<Person> {

    /**
     * 根据昵称获得个人
     *
     * @param nickname String 普通用户昵称
     * @return Person
     */
    Person getByNickname(String nickname);

    /**
     * 是否有相同的昵称
     *
     * @param nickName String 普通用户昵称
     * @return boolean
     */
    boolean isExit(String nickName);
}
