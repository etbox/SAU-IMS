package com.fekpal.dao;

import com.fekpal.domain.Sau;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by APone on 2017/8/15.
 * SauDao的接口
 */
@Repository
public interface SauDao {

    /**
     * 根据校社联用户id获取校社联信息
     *
     * @param id int 用户id
     * @return Sau
     */
    Sau getSauByUserId(int id);


    /**
     * 根据校社联id获取校社联
     *
     * @param id int 校社联id
     * @return Sau
     */
    Sau getSauBySauId(int id);

    /**
     * 添加校社联
     *
     * @param sau Sau 校社联对象
     */
    void add(Sau sau);

    /**
     * 更新校社联信息
     *
     * @param sau Sau 校社联对象
     */
    void update(Sau sau);

    /**
     * 获得所有的校社联
     *
     * @param start int 开始
     * @param end   int 结束
     * @return List
     */
    List<Sau> loadAll(int start, int end);
}
