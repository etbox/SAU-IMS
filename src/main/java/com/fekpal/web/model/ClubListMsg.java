package com.fekpal.web.model;


import com.fekpal.common.base.BaseModel;
import org.springframework.stereotype.Component;

/**
 * 用来发送给前端的社团列表信息实体类
 * Created by hasee on 2017/8/15.
 */
@Component
public class ClubListMsg extends BaseModel {

    private static final long serialVersionUID = 4994884684828642007L;

    private String clubName;

    private int clubId;

    private String clubView;

    private String description;

    private int members;

    private int likeNumber;

    public void setClubId(int clubId) {
        this.clubId = clubId;
    }

    public void setMembers(int members) {
        this.members = members;
    }

    public void setLikeNumber(int likeNumber) {
        this.likeNumber = likeNumber;
    }

    public String getClubName() {
        return clubName;
    }

    public void setClubName(String clubName) {
        this.clubName = clubName;
    }

    public ClubListMsg() {
    }

    public Integer getClubId() {
        return clubId;
    }

    public void setClubId(Integer clubId) {
        this.clubId = clubId;
    }

    public String getClubView() {
        return clubView;
    }

    public void setClubView(String clubView) {
        this.clubView = clubView;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getMembers() {
        return members;
    }

    public void setMembers(Integer members) {
        this.members = members;
    }

    public Integer getLikeNumber() {
        return likeNumber;
    }

    public void setLikeNumber(Integer likeNumber) {
        this.likeNumber = likeNumber;
    }
}
