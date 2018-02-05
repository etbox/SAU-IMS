package com.fekpal.common.base;

import java.util.List;

/**
 * Created by APone on 2018/1/31 15:40.
 * 基础服务接口,定义了各个服务的通用操作
 */
public interface BaseService<Record> {

    /**
     * 通用获取，获取范围为本表
     *
     * @param id 主键id
     * @return 记录集
     */
    Record selectByPrimaryKey(Integer id);

    /**
     * 通用根据主键id更新记录
     *
     * @param record 记录
     * @return 更新数量
     */
    int updateByPrimaryKey(Record record);

    /**
     * 通用根据主键id更新记录有效字段
     *
     * @param record 记录
     * @return 更新数量
     */
    int updateByPrimaryKeySelective(Record record);

    /**
     * 通用根据主键id删除记录
     *
     * @param id 主键id
     * @return 更新数量
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * 通用插入记录，并记录获得生成的主键id
     * 插入数量等于1条才为成功，其他为错误
     *
     * @param record 记录
     * @return 插入数量
     */
    int insert(Record record);

    /**
     * 通用批量插入记录，并所有记录获得生成的主键id
     * 插入数量等于列表数条才为成功，其他为错误
     *
     * @param records 记录集
     * @return 插入数量
     */
    int insertLoop(List<Record> records);
}
