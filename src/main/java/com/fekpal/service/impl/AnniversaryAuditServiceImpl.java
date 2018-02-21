package com.fekpal.service.impl;

import com.fekpal.common.base.BaseServiceImpl;
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
public class AnniversaryAuditServiceImpl extends BaseServiceImpl<AnniversaryAuditMapper, AnniversaryAudit> implements AnniversaryAuditService {
}
