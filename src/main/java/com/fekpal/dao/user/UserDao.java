package com.fekpal.dao.user;

import com.fekpal.dao.BaseDao;
import com.fekpal.domain.ExampleWrapper;
import com.fekpal.domain.pojo.User;
import org.springframework.stereotype.Repository;

/**
 * Created by APone on 2017/8/15.
 * UserDao接口
 * 主要用于对基础用户（所有角色的基础）的增删改查
 */
@Repository
public interface UserDao extends BaseDao<User, ExampleWrapper<User>> {
    
}
