package com.fekpal.service.impl;

import com.fekpal.dao.DepartmentDao;
import com.fekpal.domain.pojo.Department;
import com.fekpal.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by APone on 2017/9/17.
 */
@Service
public class DepartmentServiceImpl implements DepartmentService {

    @Autowired
    private DepartmentDao departmentDao;

    @Override
    public Department getDepartmentByDepartmentId(int departmentId) {
        return departmentDao.getDepartmentByDepartmentId(departmentId);
    }

    @Override
    public List<Department> loadAllDepartment() {
        return departmentDao.loadAllDepartment();
    }
}
