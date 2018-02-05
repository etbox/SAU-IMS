package com.fekpal.service.impl;

import com.fekpal.dao.mapper.DepartmentMapper;
import com.fekpal.dao.model.Department;
import com.fekpal.api.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by APone on 2017/9/17.
 */
@Service
public class DepartmentServiceImpl implements DepartmentService {

    @Autowired
    private DepartmentMapper departmentMapper;

    @Override
    public Department getDepartmentByDepartmentId(int departmentId) {
        return departmentMapper.getDepartmentByDepartmentId(departmentId);
    }

    @Override
    public List<Department> loadAllDepartment() {
        return departmentMapper.loadAllDepartment();
    }
}
