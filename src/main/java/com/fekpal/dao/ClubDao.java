package com.fekpal.dao;

import com.fekpal.domain.Club;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by APone on 2017/8/15.
 * ClubDao的接口
 */
@Repository
public interface ClubDao {

    /**
     * 根据社团id获得社团
     *
     * @param id int 社团用户id
     * @return Club
     */
    Club getClubByClubId(int id);

    /**
     * 根据名称获得社团
     *
     * @param clubName String 社团名称
     * @return Club
     */
    Club getClubByClubName(String clubName);

    /**
     * 根据社团用户id获得社团
     *
     * @param id int 用户id
     * @return Club
     */
    Club getClubByUserId(int id);

    /**
     * 根据社团名称模糊搜索
     *
     * @param clubName String 社团名称
     * @return CLub
     */
    List<Club> findClubByClubName(String clubName, int start, int count);

    /**
     * 是否有相同的社团名称
     *
     * @param clubName String 社团名称
     * @return boolean
     */
    boolean exitClubName(String clubName);

    /**
     * 添加社团
     *
     * @param club Club 社团对象
     */
    void add(Club club);

    /**
     * 更新社团信息
     *
     * @param club Club 社团对象
     */
    void update(Club club);


    /**
     * 更新社团赞人数（目前为加1）
     *
     * @param clubId int 社团用户id
     */
    void update(int clubId);

    /**
     * 获得所有的社团
     *
     * @param start int 开始
     * @param count int 结束
     * @return List
     */
    List<Club> loadAll(int start, int count);
}
