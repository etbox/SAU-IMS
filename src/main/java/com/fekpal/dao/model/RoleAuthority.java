package com.fekpal.dao.model;

import com.fekpal.common.base.BaseModel;

/**
 * Created by APone on 2018/2/5 19:22.
 */
public class RoleAuthority extends BaseModel {

    private static final long serialVersionUID = 7234116940225512421L;

    private int id;

    private int roleId;

    private int authorityId;

    private int available;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public int getAuthorityId() {
        return authorityId;
    }

    public void setAuthorityId(int authorityId) {
        this.authorityId = authorityId;
    }

    public int getAvailable() {
        return available;
    }

    public void setAvailable(int available) {
        this.available = available;
    }
}
