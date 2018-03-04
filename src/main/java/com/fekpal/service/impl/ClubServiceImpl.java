package com.fekpal.service.impl;

import com.fekpal.common.base.BaseServiceImpl;
import com.fekpal.common.base.CRUDException;
import com.fekpal.common.base.ExampleWrapper;
import com.fekpal.api.ClubService;
import com.fekpal.common.constant.AvailableState;
import com.fekpal.common.constant.FIleDefaultPath;
import com.fekpal.common.constant.Operation;
import com.fekpal.common.constant.SystemRole;
import com.fekpal.common.session.SessionLocal;
import com.fekpal.common.utils.ImageFileUtil;
import com.fekpal.dao.mapper.OrgMapper;
import com.fekpal.dao.model.Org;
import com.fekpal.service.model.domain.ClubMsg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by APone on 2017/9/5.
 * ClubService实现类
 */
@Service
public class ClubServiceImpl extends BaseServiceImpl<OrgMapper, Org> implements ClubService {

    @Autowired
    private HttpSession session;

    @Override
    public String updateLogo(ClubMsg msg) {
        try {
            int uid = SessionLocal.local(session).getUserIdentity().getUid();
            Org org = mapper.selectByPrimaryKey(uid);
            return ImageFileUtil.handle(msg.getLogo(), FIleDefaultPath.CLUB_LOGO_FILE, org.getOrgLogo());
        } catch (Exception e) {
            throw new CRUDException(e.getMessage());
        }
    }

    @Override
    public String updateView(ClubMsg msg) {
        try {
            int uid = SessionLocal.local(session).getUserIdentity().getUid();
            Org org = mapper.selectByPrimaryKey(uid);
            return ImageFileUtil.handle(msg.getView(), FIleDefaultPath.CLUB_VIEW_FILE, org.getOrgView());
        } catch (Exception e) {
            throw new CRUDException(e.getMessage());
        }
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED, rollbackFor = {Exception.class})
    public int updateClubInfo(ClubMsg msg) {
        ExampleWrapper<Org> example = new ExampleWrapper<>();
        example.eq("org_name", msg.getClubName());
        int row = mapper.countByExample(example);
        if (row != 0) return Operation.INPUT_INCORRECT;

        int uid = SessionLocal.local(session).getUserIdentity().getUid();
        Org org = new Org();
        org.setOrgId(uid);
        org.setAdminName(msg.getAdminName());
        org.setOrgName(msg.getClubName());
        org.setFoundTime(msg.getFoundTime());
        org.setDescription(msg.getDescription());
        row = mapper.updateByPrimaryKeySelective(org);

        if (row > 1) throw new CRUDException("社团信息更新异常:" + row);
        return row == 0 ? Operation.FAILED : Operation.SUCCESSFULLY;
    }

    @Override
    public Org selectByClubName(String name) {
        ExampleWrapper<Org> example = new ExampleWrapper<>();
        example.eq("org_name", name)
                .and().eq("org_auth", SystemRole.CLUB)
                .and().eq("org_state", AvailableState.AVAILABLE);
        return mapper.selectFirstByExample(example);
    }

    @Override
    public List<Org> queryByClubName(String name, int offset, int limit) {
        ExampleWrapper<Org> example = new ExampleWrapper<>();
        example.like("org_name", name)
                .and().eq("org_auth", SystemRole.CLUB)
                .and().eq("org_state", AvailableState.AVAILABLE);
        return mapper.selectByExample(example, offset, limit);
    }

    @Override
    public boolean isExitClubName(String name) {
        ExampleWrapper<Org> example = new ExampleWrapper<>();
        example.eq("org_name", name);
        int row = mapper.countByExample(example);
        return row >= 1;
    }

    @Override
    public List<Org> loadAllClub(int offset, int limit) {
        ExampleWrapper<Org> example = new ExampleWrapper<>();
        example.eq("org_auth", SystemRole.CLUB).and().eq("org_state", AvailableState.AVAILABLE);
        return mapper.selectByExample(example, offset, limit);
    }
}
