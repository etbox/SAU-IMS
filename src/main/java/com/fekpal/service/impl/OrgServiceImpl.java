package com.fekpal.service.impl;

import com.fekpal.api.OrgService;
import com.fekpal.common.base.BaseServiceImpl;
import com.fekpal.dao.mapper.OrgMapper;
import com.fekpal.dao.model.Org;
import com.fekpal.dao.model.PersonOrgView;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by APone on 2018/3/4 15:44.
 */
@Service
public class OrgServiceImpl extends BaseServiceImpl<OrgMapper, Org> implements OrgService {

    @Override
    public PersonOrgView selectByOrgName(String name, int offset, int limit) {
        return null;
    }

    @Override
    public PersonOrgView selectByIdInPerson(int id) {
        return null;
    }

    @Override
    public Org selectByIdInOrg(int id) {
        return null;
    }

    @Override
    public List<Org> loadAllOrg(int offset, int limit) {
        return null;
    }
}
