package com.fekpal.api;

import com.fekpal.common.base.BaseService;
import com.fekpal.dao.model.ClubAudit;

import java.util.List;

/**
 * Created by APone on 2017/9/5.
 * ClubAuditService接口
 * 该接口用于校社联审核关社团注册申请的审核等操作
 */
public interface ClubAuditService extends BaseService<ClubAudit> {

    /**
     * 根据社团用户标识获得社团注册审核记录
     *
     * @param clubId 社团用户标识
     * @return 社团注册审核记录
     */
    ClubAudit selectByClubId(int clubId);

    /**
     * 根据社团名称模糊搜索获得社团注册审核记录，分页获取
     *
     * @param clubName 社团名称
     * @param offset   跳过读数
     * @param limit    读取数
     * @return 社团注册审核记录集
     */
    List<ClubAudit> queryByClubName(String clubName, int offset, int limit);

    /**
     * 获得所有审核
     *
     * @param offset 跳过读数
     * @param limit  读取数
     * @return 社团注册审核记录集
     */
    List<ClubAudit> loadAllCLubAudit(int offset, int limit);
}
