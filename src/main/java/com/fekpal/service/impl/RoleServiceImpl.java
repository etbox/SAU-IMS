package com.fekpal.service.impl;

import com.fekpal.dao.RoleDao;
import com.fekpal.domain.Role;
import com.fekpal.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by APone on 2017/9/17.
 */
@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleDao roleDao;

    @Override
    public List<Role> loadAll() {
        return roleDao.loadAll();
    }
}
