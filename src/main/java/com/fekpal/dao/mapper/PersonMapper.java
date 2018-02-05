package com.fekpal.dao.mapper;

import com.fekpal.common.base.BaseMapper;
import com.fekpal.common.base.ExampleWrapper;
import com.fekpal.dao.model.Person;
import org.springframework.stereotype.Repository;

/**
 * Created by APone on 2017/8/15.
 * PersonDao的接口
 * 主要用于对普通用户的增删查改
 */
@Repository
public interface PersonMapper extends BaseMapper<Person> {

}
