package com.fekpal.service;

import com.fekpal.domain.pojo.Club;
import com.fekpal.domain.pojo.User;

import java.util.List;

/**
 * Created by APone on 2017/9/5.
 * 该接口主要提功增删查改社团信息
 */
public interface ClubService extends BaseService<Club> {

    /**
     * 根据社团名称获得社团信息记录
     *
     * @param clubName String 社团名称
     * @return 社团信息记录
     */
    Club selectByClubName(String clubName);

    /**
     * 根据社团用户的用户身份id获得社团信息记录
     *
     * @param id int 用户身份id
     * @return 社团信息记录
     */
    Club selectByUserId(int id);

    /**
     * 插入新的社团用户信息以及用户身份信息
     * 只有插入数等于2才成功，其他一律错误
     *
     * @param user User 用户身份信息
     * @param club Club 社团用户信息
     * @return 插入数量
     */
    int insertInfo(User user, Club club);

    /**
     * 根据社团名称进行模糊搜索社团信息，分页读取
     *
     * @param clubName String 社团名称
     * @param offset   int 跳过读数
     * @param limit    int 读取数
     * @return 社团信息记录集
     */
    List<Club> queryByClubName(String clubName, int offset, int limit);

    /**
     * 是否有相同的社团名称的社团
     *
     * @param clubName String 社团名称
     * @return 是否存在
     */
    boolean isExitClubName(String clubName);


    /**
     * 获得所有的社团信息记录,分页读取
     *
     * @param offset int 跳过读数
     * @param limit  int 读取数
     * @return 社团信息记录集
     */
    List<Club> loadAllClub(int offset, int limit);
}
