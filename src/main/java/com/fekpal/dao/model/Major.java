package com.fekpal.dao.model;

import com.fekpal.common.base.BaseModel;

public class Major extends BaseModel {

    private static final long serialVersionUID = -5708233887438984705L;

    private int majorId;

    private String majorName;

    private int majorAvailable;

    public int getMajorId() {
        return majorId;
    }

    public void setMajorId(int majorId) {
        this.majorId = majorId;
    }

    public String getMajorName() {
        return majorName;
    }

    public void setMajorName(String majorName) {
        this.majorName = majorName;
    }

    public int getMajorAvailable() {
        return majorAvailable;
    }

    public void setMajorAvailable(int majorAvailable) {
        this.majorAvailable = majorAvailable;
    }

}
