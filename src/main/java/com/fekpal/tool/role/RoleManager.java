package com.fekpal.tool.role;

import com.fekpal.cons.SystemRole;
import com.fekpal.domain.User;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by APone on 2017/9/14.
 * 基于权限映射访问路径的请求地址拦截器
 */
public class RoleManager {

    //角色路径
    private static RoleUrlMap roleMap;

    /**
     * 添加新的权限路径映射
     *
     * @param authority int 权限类型
     * @param urls      List 路径合集
     */
    public static void addUrls(int authority, List<String> urls) {

        //将权限id和权限路径注入到权限管理
        getInstance().addUrls(authority, urls);
    }

    /**
     * 获取路径权限入口
     *
     * @return RoleUrlMap
     */
    private static RoleUrlMap getInstance() {
        if (roleMap == null) {
            roleMap = new RoleUrlMap();
        }
        return roleMap;
    }

    /**
     * 检查当前路径是否与权限相符合
     *
     * @param user User 角色
     * @param url  String 允许访问路径
     * @return boolean
     */
    public static boolean haveAuthority(User user, String url) {
        return getInstance().checkUrl(user.getAuthority(), url);
    }

    /**
     * 检测公共访问路径权限
     *
     * @param url String
     * @return boolean
     */
    public static boolean checkPublicAuthority(String url) {
        return getInstance().checkUrl(SystemRole.PUBLIC, url);
    }
}

