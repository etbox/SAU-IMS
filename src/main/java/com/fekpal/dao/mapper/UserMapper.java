package com.fekpal.dao.mapper;

import com.fekpal.common.base.BaseMapper;
import com.fekpal.common.base.ExampleWrapper;
import com.fekpal.dao.model.User;
import org.springframework.stereotype.Repository;

/**
 * Created by APone on 2017/8/15.
 * UserDao接口
 * 主要用于对基础用户（所有角色的基础）的增删改查
 */
@Repository
public interface UserMapper extends BaseMapper<User> {
}
