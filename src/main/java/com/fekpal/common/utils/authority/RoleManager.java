package com.fekpal.common.utils.authority;

import java.util.List;

/**
 * Created by APone on 2017/9/14.
 * 基于权限映射访问路径的请求地址拦截器
 */
public class RoleManager {

    //角色路径
    private static RolePathMapper roleMap;

    /**
     * 添加新的权限路径映射
     *
     * @param authority int 权限类型
     * @param urls      List 路径合集
     */
    public static void addpath(int authority, List<String> urls) {

        //将权限id和权限路径注入到权限管理
        getInstance().insertPaths(authority, urls);
    }

    /**
     * 获取路径权限入口
     *
     * @return RoleUrlMap
     */
    private static RolePathMapper getInstance() {
        if (roleMap == null) {
            roleMap = new RolePathMapper();
        }
        return roleMap;
    }

    /**
     * 检查当前路径是否与权限相符合
     *
     * @param authority int 权限标识
     * @param url  String 允许访问路径
     * @return boolean
     */
    public static boolean haveAuthority(int authority, String url) {
        return getInstance().checkUrl(authority, url);
    }
}

