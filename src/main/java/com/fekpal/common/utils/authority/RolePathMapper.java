package com.fekpal.common.utils.authority;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by APone on 2017/9/14.
 */
class RolePathMapper {

    /**
     * 无效的
     */
    private static final int UNAVAIABLE = 0;

    /**
     * 有效的
     */
    private static final int AVAILABLE = 1;


    private Map<Integer, Map<String, Integer>> roleMap = new HashMap<>();

    /**
     * 添加新的权限路径合集
     *
     * @param authority int
     * @param urls      List
     */
    void insertPaths(int authority, List<String> urls) {
        Map<String, Integer> map = new HashMap<>();

        //将角色的所有路径全部注入到map里，有效性为有效
        for (String url : urls) {
            map.put(url, AVAILABLE);
        }

        roleMap.put(authority, map);
    }

    /**
     * 根据权限类型获得路径合集
     *
     * @param authority int
     * @return Map
     */
    private Map<String, Integer> getPaths(int authority) {
        return roleMap.get(authority);
    }

    /**
     * 检查相应权限的路径是否合法
     *
     * @param authority int
     * @param url       String
     * @return boolean
     */
    boolean checkUrl(int authority, String url) {
        Map<String, Integer> map = getPaths(authority);
        //权限类型正确且含有正确的路径则返回真
        return map != null && map.containsKey(url);
    }
}
