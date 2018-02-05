package com.fekpal.api;

import com.fekpal.dao.model.Role;

import java.util.List;

/**
 * Created by APone on 2017/9/5.
 * RoleService接口
 */
public interface RoleService {

    /**
     * 加载所有权限的资源
     *
     * @return List
     */
    List<Role> loadAll();
}
