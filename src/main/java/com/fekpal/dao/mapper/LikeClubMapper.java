package com.fekpal.dao.mapper;

import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by APone on 2017/9/5.
 * LikeClubDao接口
 */
@Repository
public interface LikeClubMapper {

    /**
     * 添加喜爱社团
     *
     * @param personId int
     * @param clubId int
     */
    void addLikeClub(int personId, int clubId);

    /**
     * 获得所有喜爱的社团id
     *
     * @param personId int
     * @return List
     */
    List<Integer> loadAllLikeByPersonId(int personId);

}
