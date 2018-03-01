package com.fekpal.service.impl;

import com.fekpal.common.base.BaseServiceImpl;
import com.fekpal.common.base.CRUDException;
import com.fekpal.common.constant.FIleDefaultPath;
import com.fekpal.common.constant.Operation;
import com.fekpal.common.constant.SystemRole;
import com.fekpal.common.session.SessionLocal;
import com.fekpal.common.utils.ImageFileUtil;
import com.fekpal.common.base.ExampleWrapper;
import com.fekpal.api.SauService;
import com.fekpal.dao.mapper.OrgMapper;
import com.fekpal.dao.model.Org;
import com.fekpal.service.model.domain.SauMsg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by APone on 2017/9/5.
 * SauService实现类
 */
@Service
public class SauServiceImpl extends BaseServiceImpl<OrgMapper, Org> implements SauService {

    @Autowired
    private HttpSession session;

    @Override
    public boolean isExitSauName(String name) {
        ExampleWrapper<Org> example = new ExampleWrapper<>();
        example.eq("org_name", name);
        int row = mapper.countByExample(example);
        return row >= 1;
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED, rollbackFor = {Exception.class})
    public int updateSauInfo(SauMsg msg) {
        ExampleWrapper<Org> example = new ExampleWrapper<>();
        example.eq("org_name", msg.getSauName());
        int row = mapper.countByExample(example);
        if (row != 0) return Operation.FAILED;

        int uid = SessionLocal.local(session).getUserIdentity().getUid();
        Org org = new Org();
        org.setOrgId(uid);
        org.setOrgName(msg.getSauName());
        org.setDescription(msg.getDescription());
        org.setAdminName(msg.getAdminName());
        org.setFoundTime(msg.getFoundTime());
        row = mapper.updateByPrimaryKeySelective(org);

        if (row != 1) throw new CRUDException("更新校社联信息失败：" + row);
        return Operation.SUCCESSFULLY;
    }

    @Override
    public String updateLogo(SauMsg sauMsg) {
        try {
            int uid = SessionLocal.local(session).getUserIdentity().getUid();
            Org org = mapper.selectByPrimaryKey(uid);
            return ImageFileUtil.handle(sauMsg.getLogo(), FIleDefaultPath.SAU_LOGO_FILE, org.getOrgLogo());
        } catch (Exception e) {
            throw new CRUDException(e.getMessage());
        }
    }

    @Override
    public List<Org> loadAllSau(int offset, int limit) {
        ExampleWrapper<Org> example = new ExampleWrapper<>();
        example.eq("org_auth", SystemRole.SAU);
        return mapper.selectByExample(example, offset, limit);
    }
}


