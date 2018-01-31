package com.fekpal.service;

import com.fekpal.domain.pojo.Sau;
import com.fekpal.domain.pojo.User;

import java.util.List;

/**
 * Created by APone on 2017/9/5.
 * SauService接口
 * 该接口主要提功增删查改校社联信息
 */
public interface SauService extends BaseService<Sau> {

    /**
     * 根据校社联用户的用户身份id获取校社联记录信息
     *
     * @param id int 用户身份id
     * @return 校社联信息记录
     */
    Sau selectByUserId(int id);

    /**
     * 插入新的校社联用户信息以及用户身份信息
     * 只有插入数等于2才成功，其他一律错误
     * @param user 用户身份信息
     * @param sau 校社联用户信息
     * @return 插入数量
     */
    int insertInfo(User user, Sau sau);

    /**
     * 获得所有的校社联记录，分页获取
     *
     * @param offset 跳过读数
     * @param limit  读取数
     * @return 校社联信息记录集
     */
    List<Sau> loadAllSau(int offset, int limit);
}
