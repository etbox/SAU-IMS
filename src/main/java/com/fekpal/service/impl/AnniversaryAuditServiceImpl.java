package com.fekpal.service.impl;

import com.fekpal.dao.mapper.AnniversaryAuditMapper;
import com.fekpal.dao.model.AnniversaryAudit;
import com.fekpal.api.AnniversaryAuditService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by APone on 2017/9/5.
 */
@Service
public class AnniversaryAuditServiceImpl implements AnniversaryAuditService {

    @Autowired
    private AnniversaryAuditMapper auditDao;

    @Override
    public AnniversaryAudit getAnnByAnnId(int annId) {
        return auditDao.getAnnByAnnId(annId);
    }

    @Override
    public List<AnniversaryAudit> getAnnByClubId(int clubId, int start, int count) {
        return auditDao.getAnnByClubId(clubId, start, count);
    }

    @Override
    public AnniversaryAudit getAnnAuditingByClubId(int clubId) {
        return auditDao.getAnnAuditingByClubId(clubId);
    }

    @Override
    public List<AnniversaryAudit> findAnnByClubName(String clubName, int start, int count) {
        return auditDao.findAnnByClubName(clubName, start, count);
    }

    @Override
    public void addNewAnniversaryAudit(AnniversaryAudit anniversaryAudit) {
        auditDao.addAnniversaryAudit(anniversaryAudit);
    }

    @Override
    public void updateAnniversaryAudit(AnniversaryAudit anniversaryAudit) {
        AnniversaryAudit ann=auditDao.getAnnByAnnId(anniversaryAudit.getId());



        auditDao.updateAnniversaryAudit(anniversaryAudit);
    }

    @Override
    public List<AnniversaryAudit> loadAllAnniversaryAudit(int start, int count) {
        return auditDao.loadAllAnniversaryAudit(start,count);
    }

    @Override
    public List<AnniversaryAudit> getAnniversaryAuditByState(int state, int start, int count) {
        return auditDao.getAnniversaryAuditByState(state,start,count);
    }
}
