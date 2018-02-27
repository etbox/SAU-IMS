package com.fekpal.service.model.domain;

import com.fekpal.common.base.BaseModel;

/**
 * Created by APone on 2018/2/22 13:55.
 * 登录结果封装
 */
public class LoginResult extends BaseModel{

    private static final long serialVersionUID = -3640349510026355851L;

    /**
     * 登录结果
     */
    private int resultState;

    /**
     * 用户权限
     */
    private int authority;

    public int getResultState() {
        return resultState;
    }

    public void setResultState(int resultState) {
        this.resultState = resultState;
    }

    public int getAuthority() {
        return authority;
    }

    public void setAuthority(int authority) {
        this.authority = authority;
    }
}
