package com.fekpal.api;

import com.fekpal.common.base.BaseService;
import com.fekpal.dao.model.Sau;

import java.util.List;

/**
 * Created by APone on 2017/9/5.
 * 校社联用户信息接口
 * 该接口主要提功校社联用户信息增删查改操作
 */
public interface SauService extends BaseService<Sau> {

    /**
     * 根据用户身份标识获取校社联记录信息
     *
     * @param id 用户身份标识
     * @return 校社联信息记录
     */
    Sau selectByUserId(int id);

    /**
     * 获得所有的校社联记录，分页获取
     *
     * @param offset 跳过读数
     * @param limit  读取数
     * @return 校社联信息记录集
     */
    List<Sau> loadAllSau(int offset, int limit);
}
