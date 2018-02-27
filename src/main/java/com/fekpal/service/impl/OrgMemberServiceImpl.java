package com.fekpal.service.impl;

import com.fekpal.api.OrgMemberService;
import com.fekpal.common.base.BaseServiceImpl;
import com.fekpal.common.base.CRUDException;
import com.fekpal.common.base.ExampleWrapper;
import com.fekpal.common.constant.AvailableState;
import com.fekpal.common.constant.MemberState;
import com.fekpal.common.constant.Operation;
import com.fekpal.common.session.SessionLocal;
import com.fekpal.common.utils.TimeUtil;
import com.fekpal.dao.mapper.OrgMemberMapper;
import com.fekpal.dao.model.OrgMember;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpSession;
import java.sql.Timestamp;
import java.util.List;

/**
 * Created by APone on 2017/9/17.
 */
@Service
public class OrgMemberServiceImpl extends BaseServiceImpl<OrgMemberMapper, OrgMember> implements OrgMemberService {

    @Autowired
    private HttpSession session;

    @Override
    public OrgMember selectByPersonId(int id) {
        int uid = SessionLocal.local(session).getUserIdentity().getUid();
        ExampleWrapper<OrgMember> example = new ExampleWrapper<>();
        example.eq("org_id", uid)
                .and().eq("person_id", id)
                .and().eq("available", AvailableState.AVAILABLE);
        return mapper.selectFirstByExample(example);
    }

    @Override
    public OrgMember selectById(int id) {
        int uid = SessionLocal.local(session).getUserIdentity().getUid();
        ExampleWrapper<OrgMember> example = new ExampleWrapper<>();
        example.eq("id", id)
                .and().eq("org_id", uid)
                .and().eq("available", AvailableState.AVAILABLE);
        return mapper.selectFirstByExample(example);
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED, rollbackFor = {Exception.class})
    public int cancelMemberById(int id) {
        int uid = SessionLocal.local(session).getUserIdentity().getUid();
        ExampleWrapper<OrgMember> example = new ExampleWrapper<>();
        example.eq("id", id)
                .and().eq("org_id", uid)
                .and().eq("member_state", MemberState.STILL_BEING)
                .and().eq("available", AvailableState.AVAILABLE);
        Timestamp time = new Timestamp(TimeUtil.currentTime());
        OrgMember orgMember = new OrgMember();
        orgMember.setLeaveTime(time);
        orgMember.setMemberState(MemberState.LEAVE);
        int row = mapper.updateByExampleSelective(orgMember, example);
        if (row > 1) throw new CRUDException("撤销组织成员关系异常：" + row);
        return row == 0 ? Operation.FAILED : Operation.SUCCESSFULLY;
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED, rollbackFor = {Exception.class})
    public int deleteMemberById(int id) {
        int uid = SessionLocal.local(session).getUserIdentity().getUid();
        ExampleWrapper<OrgMember> example = new ExampleWrapper<>();
        example.eq("id", id)
                .and().eq("org_id", uid)
                .and().eq("available", AvailableState.AVAILABLE);
        int row = mapper.deleteByExample(example);
        if (row > 1) throw new CRUDException("删除组织成员关系异常：" + row);
        return row == 0 ? Operation.FAILED : Operation.SUCCESSFULLY;
    }

    @Override
    public List<OrgMember> loadAllMember(int offset, int limit) {
        int uid = SessionLocal.local(session).getUserIdentity().getUid();
        ExampleWrapper<OrgMember> example = new ExampleWrapper<>();
        example.eq("org_id", uid)
                .and().eq("member_state", MemberState.STILL_BEING)
                .and().eq("available", AvailableState.AVAILABLE);
        return mapper.selectByExample(example, offset, limit);
    }
}
