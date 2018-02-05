package com.fekpal.api;

import com.fekpal.dao.model.ClubAudit;

import java.util.List;

/**
 * Created by APone on 2017/9/5.
 * ClubAuditService接口
 */
public interface ClubAuditService {

    /**
     * 根据社团id获得社团审核
     *
     * @param clubId int
     * @return List
     */
    List<ClubAudit> getClubAuditByClubId(int clubId);

    /**
     * 根据审核id获得社团审核
     *
     * @param id int
     * @return ClubAudit
     */
    ClubAudit getClubAuditById(int id);

    /**
     * 根据社团名称获得社团审核
     *
     * @param clubName String
     * @return List
     */
    List<ClubAudit> findClubAuditByClubName(String clubName);

    /**
     * 添加社团审核
     *
     * @param clubAudit ClubAudit
     */
    void addNewClubAudit(ClubAudit clubAudit);

    /**
     * 更新社团审核
     *
     * @param clubAudit ClubAudit
     */
    void updateClubAudit(ClubAudit clubAudit);

    /**
     * 获得所有审核
     *
     * @param start int
     * @param count int
     * @return LIst
     */
    List<ClubAudit> loadAllCLubAudit(int start, int count);
}
