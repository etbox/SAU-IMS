package com.fekpal.api;

import com.fekpal.common.base.BaseService;
import com.fekpal.dao.model.Org;
import com.fekpal.dao.model.PersonOrgView;

import java.util.List;

/**
 * Created by APone on 2018/3/4 15:42.
 * 组织接口
 * 该接口提供组织（社团，校社联）的查询，修改等操作
 */
public interface OrgService extends BaseService<Org> {

    /**
     * 根据组织标识获取组织记录详细，用于普通用户
     *
     * @param id 组织标识
     * @return 普通用户范围组织记录
     */
    PersonOrgView selectByIdInPerson(int id);

    /**
     * 根据组织名称进行模糊搜索获取组织列表，按分页获取，用于普通用户
     *
     * @param name   组织名称
     * @param offset 跳过读数
     * @param limit  读取数
     * @return 组织记录
     */
    PersonOrgView selectByOrgName(String name, int offset, int limit);

    /**
     * 根据组织标识获取组织记录详细，用于校社联和社团用户
     *
     * @param id 组织标识
     * @return 普通用户范围组织记录
     */
    Org selectByIdInOrg(int id);

    /**
     * 加载所有组织记录，按分页获取
     *
     * @param offset 跳过读数
     * @param limit  读取数
     * @return 组织记录集
     */
    List<Org> loadAllOrg(int offset, int limit);
}
