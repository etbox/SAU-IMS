package com.fekpal.dao.model;

import com.fekpal.common.base.BaseModel;

public class Resource extends BaseModel {

    private static final long serialVersionUID = -1114661595168784095L;

    private int resourceId;

    private String resourceURL;

    private String resourceName;

    private int resourceAvailable;

    public Resource() {
    }

    public int getResourceId() {
        return resourceId;
    }

    public void setResourceId(int resourceId) {
        this.resourceId = resourceId;
    }

    public String getResourceURL() {
        return resourceURL;
    }

    public void setResourceURL(String resource) {
        this.resourceURL = resource;
    }

    public String getResourceName() {
        return resourceName;
    }

    public void setResourceName(String resourceName) {
        this.resourceName = resourceName;
    }

    public int getResourceAvailable() {
        return resourceAvailable;
    }

    public void setResourceAvailable(int resourceAvailable) {
        this.resourceAvailable = resourceAvailable;
    }

}
