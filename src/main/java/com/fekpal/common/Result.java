package com.fekpal.common;

import com.fekpal.dao.model.Club;
import com.fekpal.dao.model.User;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 用来产生返回数据的标准工具类
 * Created by hasee on 2017/8/15.
 */
public class Result {

    private int code;

    private String msg;

    private Object data;


    public Result() {
        this.code = 0;
        this.msg = "";
    }


    public void put(Object record) {
        this.data = record;
    }

    public static void main(String[] args) {
        Result result = new Result();
        result.put(new User());
        System.out.println(result);
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
