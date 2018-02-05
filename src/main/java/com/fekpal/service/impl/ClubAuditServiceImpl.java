package com.fekpal.service.impl;

import com.fekpal.dao.mapper.ClubAuditMapper;
import com.fekpal.dao.model.ClubAudit;
import com.fekpal.api.ClubAuditService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by APone on 2017/9/17.
 */
@Service
public class ClubAuditServiceImpl implements ClubAuditService {

    @Autowired
    private ClubAuditMapper clubAuditMapper;

    @Override
    public List<ClubAudit> getClubAuditByClubId(int clubId) {
        return clubAuditMapper.getClubAuditByClubId(clubId);
    }

    @Override
    public ClubAudit getClubAuditById(int id) {
        return clubAuditMapper.getClubAuditById(id);
    }

    @Override
    public List<ClubAudit> findClubAuditByClubName(String clubName) {
        return clubAuditMapper.findClubAuditByClubName(clubName);
    }

    @Override
    public void addNewClubAudit(ClubAudit clubAudit) {
        clubAuditMapper.addClubAudit(clubAudit);
    }

    @Override
    public void updateClubAudit(ClubAudit clubAudit) {

        clubAuditMapper.updateClubAudit(clubAudit);
    }

    @Override
    public List<ClubAudit> loadAllCLubAudit(int start, int count) {
        return clubAuditMapper.loadAllCLubAudit(start, count);
    }
}
