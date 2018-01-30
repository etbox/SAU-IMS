package com.fekpal.dao.user;

import com.fekpal.dao.BaseDao;
import com.fekpal.domain.ExampleWrapper;
import com.fekpal.domain.pojo.Sau;
import org.springframework.stereotype.Repository;

/**
 * Created by APone on 2017/8/15.
 * SauDao的接口
 * 主要用于对校社联用户的增删查改
 */
@Repository
public interface SauDao extends BaseDao<Sau, ExampleWrapper<Sau>> {

}
