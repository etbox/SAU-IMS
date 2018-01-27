package com.fekpal.dao.user;

import com.fekpal.domain.Club;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by APone on 2017/8/15.
 * ClubDao的接口
 * 主要用于对社团用户的增删查改
 */
@Repository
public interface ClubDao extends BaseUserDao<Club> {

    /**
     * 根据名称获得社团
     *
     * @param clubName String 社团名称
     * @return Club
     */
    Club getByClubName(String clubName);


    /**
     * 根据社团名称模糊搜索
     *
     * @param clubName String 社团名称
     * @return CLub
     */
    List<Club> findByClubName(String clubName, int start, int count);

    /**
     * 是否有相同的社团名称
     *
     * @param clubName String 社团名称
     * @return boolean
     */
    boolean isExit(String clubName);

    /**
     * 更新社团赞人数（目前为加1）
     *
     * @param id int 社团用户id
     */
    void updateLikeClick(int id);
}
