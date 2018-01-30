package com.fekpal.service.impl;

import com.fekpal.dao.ClubAuditDao;
import com.fekpal.domain.pojo.ClubAudit;
import com.fekpal.service.ClubAuditService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by APone on 2017/9/17.
 */
@Service
public class ClubAuditServiceImpl implements ClubAuditService {

    @Autowired
    private ClubAuditDao clubAuditDao;

    @Override
    public List<ClubAudit> getClubAuditByClubId(int clubId) {
        return clubAuditDao.getClubAuditByClubId(clubId);
    }

    @Override
    public ClubAudit getClubAuditById(int id) {
        return clubAuditDao.getClubAuditById(id);
    }

    @Override
    public List<ClubAudit> findClubAuditByClubName(String clubName) {
        return clubAuditDao.findClubAuditByClubName(clubName);
    }

    @Override
    public void addNewClubAudit(ClubAudit clubAudit) {
        clubAuditDao.addClubAudit(clubAudit);
    }

    @Override
    public void updateClubAudit(ClubAudit clubAudit) {

        clubAuditDao.updateClubAudit(clubAudit);
    }

    @Override
    public List<ClubAudit> loadAllCLubAudit(int start, int count) {
        return clubAuditDao.loadAllCLubAudit(start, count);
    }
}
