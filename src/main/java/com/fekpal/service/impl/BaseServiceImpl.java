package com.fekpal.service.impl;

import com.fekpal.service.BaseService;
import org.apache.log4j.Logger;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

/**
 * Created by APone on 2018/1/31 15:59.
 * 实现服务层通用操作接口的抽象类
 */
public abstract class BaseServiceImpl<Mapper, Record> implements BaseService<Record> {

    protected Logger logger = Logger.getLogger(this.getClass());

    public Mapper mapper;

    @Override
    public Record selectInfoByPrimaryId(Integer id) {
        try {
            Method selectByPrimaryId = mapper.getClass().getDeclaredMethod("selectByPrimaryKey", id.getClass());
            Object object = selectByPrimaryId.invoke(mapper, id);
            return (Record) object;
        } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public int updateInfoByPrimaryId(Record record) {
        try {
            Method updateInfoByPrimaryId = mapper.getClass().getDeclaredMethod("updateByPrimaryKey", record.getClass());
            Object object = updateInfoByPrimaryId.invoke(mapper, record);
            return Integer.parseInt(String.valueOf(object));
        } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public int updateInfoByPrimaryIdSelective(Record record) {
        try {
            Method updateInfoByPrimaryIdSelective = mapper.getClass().getDeclaredMethod("updateByPrimaryKeySelective", record.getClass());
            Object object = updateInfoByPrimaryIdSelective.invoke(mapper, record);
            return Integer.parseInt(String.valueOf(object));
        } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public int deleteInfoByPrimaryId(Integer id) {
        try {
            Method deleteByPrimaryId = mapper.getClass().getDeclaredMethod("deleteByPrimaryKey", id.getClass());
            Object object = deleteByPrimaryId.invoke(mapper, id);
            return Integer.parseInt(String.valueOf(object));
        } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public int insertInfo(Record record) {
        try {
            Method insertInfo = mapper.getClass().getDeclaredMethod("insert", record.getClass());
            Object object = insertInfo.invoke(mapper, record);
            return Integer.parseInt(String.valueOf(object));
        } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public int insertInfoList(List<Record> records) {
        try {
            Method insertInfoList = mapper.getClass().getDeclaredMethod("insertRecords", records.getClass());
            Object object = insertInfoList.invoke(mapper, records);
            return Integer.parseInt(String.valueOf(object));
        } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return 0;
    }
}
