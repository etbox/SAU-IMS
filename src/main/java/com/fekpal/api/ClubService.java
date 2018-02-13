package com.fekpal.api;

import com.fekpal.common.base.BaseService;
import com.fekpal.dao.model.Club;

import java.util.List;

/**
 * Created by APone on 2017/9/5.
 * 该接口主要提功增删查改社团信息
 */
public interface ClubService extends BaseService<Club> {

    /**
     * 根据社团名称获得社团信息记录
     *
     * @param name 社团名称
     * @return 社团信息记录
     */
    Club selectByClubName(String name);

    /**
     * 根据用户身份标识获得社团信息记录
     *
     * @param id 用户身份标识
     * @return 社团信息记录
     */
    Club selectByUserId(int id);

    /**
     * 根据社团名称进行模糊搜索社团信息记录，分页读取
     *
     * @param name 社团名称
     * @param offset   跳过读数
     * @param limit    读取数
     * @return 社团信息记录集
     */
    List<Club> queryByClubName(String name, int offset, int limit);

    /**
     * 是否有相同的社团名称的社团
     *
     * @param name 社团名称
     * @return 是否存在
     */
    boolean isExitClubName(String name);

    /**
     * 获得所有的社团信息记录,分页读取
     *
     * @param offset 跳过读数
     * @param limit  读取数
     * @return 社团信息记录集
     */
    List<Club> loadAllClub(int offset, int limit);
}
