package com.fekpal.dao;

import java.util.List;

/**
 * dao基础增删查改操作，所有dao的必须继承,按需求实现方法
 *
 * @param <T>
 */
public interface BaseDao<T> {

    /**
     * 根据主键id获取,包括父类
     *
     * @param id 主键id
     * @return T
     */
    T get(int id);

    /**
     * 根据基础用户主键id获取，包括父类
     *
     * @param id 基础用户主键id
     * @return T
     */
    T getByUserId(int id);

    /**
     * 更新
     *
     * @param obj 实体类
     */
    void update(T obj);

    /**
     * 逻辑删除
     *
     * @param id 主键id
     */
    void delete(int id);

    /**
     * 添加
     *
     * @param obj 实体类
     */
    void add(T obj);

    /**
     * 获取所有
     *
     * @param start 开始序号
     * @param end   结束序号
     */
    List<T> loadAll(int start, int end);
}
