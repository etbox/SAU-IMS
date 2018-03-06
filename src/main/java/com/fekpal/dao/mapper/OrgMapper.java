package com.fekpal.dao.mapper;

import com.fekpal.common.base.BaseMapper;
import com.fekpal.dao.model.Org;
import com.fekpal.dao.model.PersonOrgView;
import org.springframework.stereotype.Repository;

/**
 * Created by APone on 2018/2/27 19:47.
 */
@Repository
public interface OrgMapper extends BaseMapper<Org> {

    PersonOrgView selectByPrimaryIdForPerson(Integer id);
}
