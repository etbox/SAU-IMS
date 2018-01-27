package com.fekpal.dao.user;

import com.fekpal.dao.BaseDao;

public interface BaseUserDao<T> extends BaseDao<T> {

    /**
     * 根据主键id获取常用信息，不包括父类
     * @param id 主键id
     * @return T
     */
    T getInfo(int id);

    /**
     * 根据基础用户主键id获取常用信息，不包括父类
     * @param id 基础用户主键id
     * @return T
     */
    T getInfoByUserId(int id);
}
