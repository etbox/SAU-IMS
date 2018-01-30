package com.fekpal.domain;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.io.Serializable;

/**
 * 条件构造器标准，用于数据库查询的标准条件
 */
public class ExampleWrapper<Record> extends EntityWrapper<Record> implements Serializable {

    //参数别名常量，用于mybatis
    public static final String PARAM_NAME = "ex";

    public ExampleWrapper(Record entity) {
        super(entity);
        super.setParamAlias(PARAM_NAME);
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
