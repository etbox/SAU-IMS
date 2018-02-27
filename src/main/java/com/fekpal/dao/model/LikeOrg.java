package com.fekpal.dao.model;

import com.fekpal.common.base.BaseModel;

/**
 * Created by APone on 2018/2/5 19:16.
 */
public class LikeOrg extends BaseModel {

    private static final long serialVersionUID = -497770962000889055L;

    private int id;

    private int personId;

    private int orgId;

    private int available;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPersonId() {
        return personId;
    }

    public void setPersonId(int personId) {
        this.personId = personId;
    }

    public int getOrgId() {
        return orgId;
    }

    public void setOrgId(int orgId) {
        this.orgId = orgId;
    }

    public int getAvailable() {
        return available;
    }

    public void setAvailable(int available) {
        this.available = available;
    }
}
