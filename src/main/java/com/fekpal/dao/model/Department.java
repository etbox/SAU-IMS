package com.fekpal.dao.model;


import com.fekpal.common.base.BaseModel;

import java.util.List;

public class Department extends BaseModel {

    private static final long serialVersionUID = -8672030467667468233L;

    private int departmentId;

    private String departmentName;

    private int departmentAvailable;

    private List<Major> majorList;

    public int getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(int departmentId) {
        this.departmentId = departmentId;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public int getDepartmentAvailable() {
        return departmentAvailable;
    }

    public void setDepartmentAvailable(int departmentAvailable) {
        this.departmentAvailable = departmentAvailable;
    }

    public List<Major> getMajorList() {
        return majorList;
    }

    public void setMajorList(List<Major> majorList) {
        this.majorList = majorList;
    }

}
