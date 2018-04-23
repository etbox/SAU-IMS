package com.fekpal.service.impl;

import com.fekpal.api.PersonService;
import com.fekpal.common.base.BaseServiceImpl;
import com.fekpal.common.base.CRUDException;
import com.fekpal.common.base.ExampleWrapper;
import com.fekpal.api.ClubService;
import com.fekpal.common.constant.*;
import com.fekpal.common.session.SessionLocal;
import com.fekpal.common.utils.ImageFileUtil;
import com.fekpal.dao.mapper.MemberMapper;
import com.fekpal.dao.mapper.OrgMapper;
import com.fekpal.dao.mapper.PersonMapper;
import com.fekpal.dao.model.Member;
import com.fekpal.dao.model.Org;
import com.fekpal.dao.model.Person;
import com.fekpal.dao.model.PersonOrgView;
import com.fekpal.service.model.domain.ClubMsg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by APone on 2017/9/5.
 * ClubService实现类
 */
@Service
public class ClubServiceImpl extends BaseServiceImpl<OrgMapper, Org> implements ClubService {

    @Autowired
    private HttpSession session;

    @Autowired
    MemberMapper memberMapper;

    @Autowired
    PersonMapper personMapper;

    @Override
    public Org selectByPrimaryId() {
        int uid = SessionLocal.local(session).getUserIdentity().getUid();
        return mapper.selectByPrimaryKey(uid);
    }

    @Override
    public String updateLogo(ClubMsg msg) {
        try {
            int uid = SessionLocal.local(session).getUserIdentity().getUid();
            Org org = mapper.selectByPrimaryKey(uid);
            //存入数据库的是带后缀的，进行存储的时候是不能带后缀的，要以上传文件的后缀为后缀
            String[] orgLogos = org.getOrgLogo().split("\\.");
            String logo = "";
            if( org.getOrgLogo().equalsIgnoreCase(DefaultField.DEFAULT_LOGO)) {
                logo = ImageFileUtil.handle(msg.getLogo(), FIleDefaultPath.PERSON_LOGO_FILE);
            }else{
                logo = ImageFileUtil.handle(msg.getLogo(), FIleDefaultPath.PERSON_LOGO_FILE,orgLogos[0]);
            }
            org.setOrgLogo(logo);
            mapper.updateByPrimaryKey(org);
            return logo;
        } catch (Exception e) {
            e.printStackTrace();
            throw new CRUDException(e.getMessage());
        }
    }

    @Override
    public String updateView(ClubMsg msg) {
        try {
            int uid = SessionLocal.local(session).getUserIdentity().getUid();
            Org org = mapper.selectByPrimaryKey(uid);
            //数据库存储的orgView是带后缀的，但是存储文件的时候不能带后缀，要以上传的文件后缀为准备
            String[] orgViews = org.getOrgView().split("\\.");
            String orgView = "";
            if(org.getOrgView().equalsIgnoreCase(DefaultField.DEFAULT_CLUB_OVERVIEW) ){
                orgView = ImageFileUtil.handle(msg.getView(), FIleDefaultPath.CLUB_VIEW_FILE);
            }else {
                orgView = ImageFileUtil.handle(msg.getView(), FIleDefaultPath.CLUB_VIEW_FILE, orgViews[0]);
            }
            org.setOrgView(orgView);
            mapper.updateByPrimaryKey(org);
            return orgView;
        } catch (Exception e) {
            e.printStackTrace();
            throw new CRUDException(e.getMessage());
        }
    }

    @Override
    public int updateClubInfo(ClubMsg msg) {
        int orgId = SessionLocal.local(session).getUserIdentity().getUid();
        ExampleWrapper<Org> example = new ExampleWrapper<>();
        example.eq("org_name", msg.getClubName()).and().ne("org_id",orgId);
        int row = mapper.countByExample(example);
        if (row != 0) {return Operation.INPUT_INCORRECT;}

        int uid = SessionLocal.local(session).getUserIdentity().getUid();
        Org org = new Org();
        org.setOrgId(uid);
        org.setAdminName(msg.getAdminName());
        org.setOrgName(msg.getClubName());
        org.setFoundTime(msg.getFoundTime());
        org.setDescription(msg.getDescription());
        row = mapper.updateByPrimaryKeySelective(org);

        if (row > 1) {throw new CRUDException("社团信息更新异常:" + row);}
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

    /**
     * 只用于社团用户和普通用户用，返回不括校社联的信息
     * 根据社团id返回社团详细信息
     *
     * @param orgId 社团id
     * @return 社团详细信息
     */
    @Override
    public PersonOrgView selectByIdForPerson(int orgId) {
        PersonOrgView org = mapper.selectByPrimaryIdForPerson(orgId);
        if(org == null ){return  null;}
        if(org.getOrgAuth() == SystemRole.SAU){return null;}
        int uid = SessionLocal.local(session).getUserIdentity().getUid();
        ExampleWrapper<Member> example = new ExampleWrapper<>();
        example.eq("person_id", uid).and().eq("org_id", orgId);
        int exit = memberMapper.countByExample(example);
        org.setJoinState(exit == 1 ? AvailableState.AVAILABLE : AvailableState.UNAVAILABLE);
        return org;
    }

    /**
     * 得到本社团所有人的个人信息，仅仅社团用户用
     *
     * @return 本社团社员个人信息记录集
     */
    @Override
    public List<Person> getClubAllMemberPersonMsg() {
        int orgId = SessionLocal.local(session).getUserIdentity().getUid();
        List<Person> personList = new ArrayList<>();
        ExampleWrapper<Member> memberExample = new ExampleWrapper<>();
        memberExample.eq("org_id",orgId).and().eq("member_state", AuditState.PASS);
        List<Member> memberList  =memberMapper.selectByExample(memberExample,0,100000);
        if(memberList==null || memberList.size()==0){return null;}
        for(Member member : memberList){
            Person person = personMapper.selectByPrimaryKey(member.getPersonId());
            personList.add(person);
        }
        if(personList == null || personList.size()==0){return null;}
        return personList;
    }

    /**
     * 根据计算社团内部男生的数量
     *
     * @return 社团内男生的人数
     */
    @Override
    public int countClubManNum(){
        int orgId = SessionLocal.local(session).getUserIdentity().getUid();
        int manNum = memberMapper.countOrgManNum(orgId);
        return manNum;
    }

    /**
     * 计算社团内部女生的数量
     *
     * @return 社团内女生的人数
     */
    @Override
    public int countClubWomanNum() {
        int orgId = SessionLocal.local(session).getUserIdentity().getUid();
        int womanNum = memberMapper.countOrgWomanNum(orgId);
        return womanNum;
    }

    /**
     * 根据年级数计算社团内部年级的数量
     *
     * @param grade 年级 如1,2,3,4,
     * @return 社团内各个年级的人数
     */
    @Override
    public int countClubGradeNum(int grade) {
        int orgId = SessionLocal.local(session).getUserIdentity().getUid();
        Calendar date = Calendar.getInstance();
        int year = date.get(Calendar.YEAR);
        int yearBack2 = year%100;
        String realGrade = "15";
        int month = date.get(Calendar.MONDAY);
        //根据当前年份加上年级数（1,2,3,4），得到临时年级（15,16,17,18）
        int tempGrade = yearBack2-grade+1;
        if(month<9){
            realGrade = String.valueOf(tempGrade-1);
        }else{
            realGrade = String.valueOf(tempGrade);
        }
        int gradeNum = memberMapper.countOrgGradeNum(orgId,realGrade+"%");
        return gradeNum;
    }


}
