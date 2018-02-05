package com.fekpal.dao.mapper;

import com.fekpal.dao.model.Department;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by APone on 2017/8/22.
 * DepartmentDao 接口
 */
@Repository
public interface DepartmentMapper {

    /**
     * 根据系别id获得系部
     *
     * @param departmentId int
     * @return Department
     */
    Department getDepartmentByDepartmentId(int departmentId);

    /**
     * 获取所有的系别
     *
     * @return List
     */
    List<Department> loadAllDepartment();
}
