package com.fekpal.service.impl;

import com.fekpal.common.base.BaseServiceImpl;
import com.fekpal.common.base.ExampleWrapper;
import com.fekpal.dao.mapper.ClubAuditMapper;
import com.fekpal.dao.model.ClubAudit;
import com.fekpal.api.ClubAuditService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by APone on 2017/9/17.
 */
@Service
public class ClubAuditServiceImpl extends BaseServiceImpl<ClubAuditMapper, ClubAudit> implements ClubAuditService {

    @Override
    public ClubAudit selectByClubId(int clubId) {
        ExampleWrapper<ClubAudit> example = new ExampleWrapper<>();
        example.eq("club_id", clubId);
        return mapper.selectFirstByExample(example);
    }

    @Override
    public List<ClubAudit> queryByClubName(String clubName, int offset, int limit) {
        ExampleWrapper<ClubAudit> example = new ExampleWrapper<>();
        example.like("club_name", clubName);
        return mapper.selectByExample(example, offset, limit);
    }

    @Override
    public List<ClubAudit> loadAllCLubAudit(int offset, int limit) {
        ExampleWrapper<ClubAudit> example = new ExampleWrapper<>();
        return mapper.selectByExample(example, offset, limit);
    }
}
