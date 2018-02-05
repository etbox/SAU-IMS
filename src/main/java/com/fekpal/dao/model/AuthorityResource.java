package com.fekpal.dao.model;

import com.fekpal.common.base.BaseModel;

/**
 * Created by APone on 2018/2/5 19:25.
 */
public class AuthorityResource extends BaseModel {

    private static final long serialVersionUID = 2568330860865523087L;

    private int id;

    private int authorityId;

    private int resourceId;

    private int available;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAuthorityId() {
        return authorityId;
    }

    public void setAuthorityId(int authorityId) {
        this.authorityId = authorityId;
    }

    public int getResourceId() {
        return resourceId;
    }

    public void setResourceId(int resourceId) {
        this.resourceId = resourceId;
    }

    public int getAvailable() {
        return available;
    }

    public void setAvailable(int available) {
        this.available = available;
    }
}
