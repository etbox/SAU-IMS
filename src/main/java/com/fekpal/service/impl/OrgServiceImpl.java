package com.fekpal.service.impl;

import com.fekpal.api.LikeOrgService;
import com.fekpal.api.OrgService;
import com.fekpal.common.base.BaseServiceImpl;
import com.fekpal.common.base.ExampleWrapper;
import com.fekpal.common.constant.AvailableState;
import com.fekpal.common.constant.MessageType;
import com.fekpal.common.constant.Operation;
import com.fekpal.common.constant.ResponseCode;
import com.fekpal.common.session.SessionLocal;
import com.fekpal.dao.mapper.MemberMapper;
import com.fekpal.dao.mapper.OrgMapper;
import com.fekpal.dao.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpSession;
import java.util.Calendar;
import java.util.List;

/**
 * Created by APone on 2018/3/4 15:44.
 */
@Service
public class OrgServiceImpl extends BaseServiceImpl<OrgMapper, Org> implements OrgService {

    @Autowired
    private MemberMapper memberMapper;

    @Autowired
    private HttpSession session;

    @Override
    public List<Org> selectByOrgName(String name, int offset, int limit) {
        ExampleWrapper<Org> example = new ExampleWrapper<>();
        example.like("org_name", name).and().eq("org_state", AvailableState.AVAILABLE);
        return mapper.selectByExample(example, offset, limit);
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED, rollbackFor = {Exception.class})
    public PersonOrgView selectByIdForPerson(int id) {
        PersonOrgView org = mapper.selectByPrimaryIdForPerson(id);
        if(org == null ){return  null;}
        int uid = SessionLocal.local(session).getUserIdentity().getUid();
        ExampleWrapper<Member> example = new ExampleWrapper<>();
        example.eq("person_id", uid).and().eq("org_id", id);
        int exit = memberMapper.countByExample(example);
        org.setJoinState(exit == 1 ? AvailableState.AVAILABLE : AvailableState.UNAVAILABLE);
        return org;
    }

    @Override
    public Org selectByIdForOrg(int id) {
        Org org = mapper.selectByPrimaryKey(id);
        if (org != null && org.getOrgState() == AvailableState.AVAILABLE) {
            return org;
        }
        return null;
    }

    @Override
    public int likeByOrgId(int orgId) {
        LikeOrgService likeOrgService = new LikeOrgServiceImpl();
        LikeOrg likeOrg = null;
        likeOrg = likeOrgService.selectByOrgId(orgId);
        //如果根据社团id获取不到，点赞状态则，返回错误
        if(likeOrg == null){
            return Operation.FAILED;
        }
        int likeState = likeOrg.getAvailable();
        //如果之前状态为未点赞，则执行点赞
        if(likeState == 0){
            likeOrgService.likeById(orgId);
            return Operation.SUCCESSFULLY;
        }else if(likeState == 1){
            //如果之前状态为点赞，则执行取消点赞
            likeOrgService.cancelLikeById(orgId);
            return Operation.CANCEL;
        }
        return Operation.FAILED;
    }

    @Override
    public List<Org> loadAllOrg(int offset, int limit) {
        ExampleWrapper<Org> example = new ExampleWrapper<>();
        example.eq("org_state", AvailableState.AVAILABLE);
        return mapper.selectByExample(example, offset, limit);
    }

    /**
     * 根据社团id计算社团内部男生的数量
     *
     * @param orgId 组织id
     * @return 组织内男生的人数
     */
    @Override
    public int countOrgManNumByOrgId(int orgId) {
        int manNum = memberMapper.countOrgManNum(orgId);
        return manNum;
    }

    /**
     * 根据组织id计算社团内部女生的数量
     *
     * @param orgId 组织id
     * @return 组织内女生的人数
     */
    @Override
    public int countOrgWomanNumByOrgId(int orgId) {
        int womanNum = memberMapper.countOrgWomanNum(orgId);
        return womanNum;
    }

    /**
     * 根据组织id计算年级数计算社团内部年级的数量
     *
     * @param grade 年级 如1,2,3,4,
     * @param orgId 组织id
     * @return 组织内各个年级的人数
     */
    @Override
    public int countOrgGradeNumByOrgId(int grade, int orgId) {
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
