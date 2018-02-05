package com.fekpal.dao.model;

import com.fekpal.common.base.BaseModel;

/**
 * Created by APone on 2018/2/5 19:16.
 */
public class ClubLike extends BaseModel {

    private static final long serialVersionUID = -497770962000889055L;

    private int id;

    private int personId;

    private int clubId;

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

    public int getClubId() {
        return clubId;
    }

    public void setClubId(int clubId) {
        this.clubId = clubId;
    }

    public int getAvailable() {
        return available;
    }

    public void setAvailable(int available) {
        this.available = available;
    }
}
