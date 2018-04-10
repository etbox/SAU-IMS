package com.fekpal.service.impl;

import com.fekpal.api.MemberOrgService;
import com.fekpal.common.base.BaseServiceImpl;
import com.fekpal.common.base.CRUDException;
import com.fekpal.common.base.ExampleWrapper;
import com.fekpal.common.constant.AvailableState;
import com.fekpal.common.constant.ClubRole;
import com.fekpal.common.constant.Operation;
import com.fekpal.common.session.SessionLocal;
import com.fekpal.common.utils.TimeUtil;
import com.fekpal.dao.mapper.MemberOrgMapper;
import com.fekpal.dao.mapper.PersonMapper;
import com.fekpal.dao.model.MemberOrg;
import com.fekpal.dao.model.Person;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpSession;
import java.sql.Timestamp;
import java.util.List;

/**
 * Created by APone on 2018/2/28 3:01.
 */
@Service
public class MemberOrgServiceImpl extends BaseServiceImpl<MemberOrgMapper, MemberOrg> implements MemberOrgService {

    Logger logger = Logger.getLogger(MemberOrgService.class);

    @Autowired
    private HttpSession session;

    @Autowired
    private PersonMapper personMapper;

    @Override
    public MemberOrg selectByOrgId(int id) {
        int uid = SessionLocal.local(session).getUserIdentity().getUid();
        ExampleWrapper<MemberOrg> example = new ExampleWrapper<>();
        example.eq("org_id", id)
                .and().eq("person_id", uid)
                .and().eq("available", AvailableState.AVAILABLE);
        return mapper.selectFirstByExample(example);
    }

    @Override
    public MemberOrg selectById(int id) {
        int uid = SessionLocal.local(session).getUserIdentity().getUid();
        ExampleWrapper<MemberOrg> example = new ExampleWrapper<>();
        example.eq("id", id)
                .and().eq("person_id", uid)
                .and().eq("available", AvailableState.AVAILABLE);
        return mapper.selectFirstByExample(example);
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED, rollbackFor = {Exception.class})
    public int joinOrganizationByOrgId(int id) {
        int uid = SessionLocal.local(session).getUserIdentity().getUid();
        Person person = personMapper.selectByPrimaryKey(uid);
        if (!isValidInfo(person)) return Operation.INPUT_INCORRECT;

        MemberOrg memberOrg = new MemberOrg();
        memberOrg.setPersonId(uid);
        memberOrg.setOrgId(id);
        memberOrg.setMemberDuty(ClubRole.MEMBER);
        memberOrg.setJoinTime(new Timestamp(TimeUtil.currentTime()));
        memberOrg.setAvailable(AvailableState.AUDITING);
        int row = mapper.insert(memberOrg);
        if (row > 1) throw new CRUDException("加入组织操作异常：" + row);
        return row == 0 ? Operation.FAILED : Operation.SUCCESSFULLY;
    }

    /**
     * 检查加入组织的用户是否完成必要信息的填写
     *
     * @param person 普通用户
     * @return 是否符合
     */
    private boolean isValidInfo(Person person) {
        return (person.getRealName() != null && person.getDepartment() != null &&
                person.getMajor() != null && person.getStudentId() != null &&
                person.getEnrollmentYear() > 0);
    }

    @Override
    public List<MemberOrg> loadAllOrg(int offset, int limit) {
        int uid = SessionLocal.local(session).getUserIdentity().getUid();
        logger.info("执行了查询某个人加入的所有社团，他的uid为"+uid);
        ExampleWrapper<MemberOrg> example = new ExampleWrapper<>();
        example.eq("person_id", uid).and().eq("available", AvailableState.AVAILABLE);
        return mapper.selectByExample(example, offset, limit);
    }
}
