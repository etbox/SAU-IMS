package com.fekpal.dao.user;

import com.fekpal.dao.BaseDao;
import com.fekpal.domain.ExampleWrapper;
import com.fekpal.domain.pojo.Person;
import org.springframework.stereotype.Repository;

/**
 * Created by APone on 2017/8/15.
 * PersonDao的接口
 * 主要用于对普通用户的增删查改
 */
@Repository
public interface PersonDao extends BaseDao<Person, ExampleWrapper<Person>> {

}
