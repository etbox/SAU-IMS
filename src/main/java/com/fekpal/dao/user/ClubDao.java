package com.fekpal.dao.user;

import com.fekpal.dao.BaseDao;
import com.fekpal.domain.ExampleWrapper;
import com.fekpal.domain.pojo.Club;
import org.springframework.stereotype.Repository;


/**
 * Created by APone on 2017/8/15.
 * ClubDao的接口
 * 主要用于对社团用户的增删查改
 */
@Repository
public interface ClubDao extends BaseDao<Club, ExampleWrapper<Club>> {

}
