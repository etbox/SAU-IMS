package com.fekpal.service.impl;

import com.fekpal.common.base.BaseServiceImpl;
import com.fekpal.common.base.CRUDException;
import com.fekpal.common.constant.DefaultField;
import com.fekpal.common.constant.FIleDefaultPath;
import com.fekpal.common.constant.Operation;
import com.fekpal.common.constant.SystemRole;
import com.fekpal.common.session.SessionLocal;
import com.fekpal.common.utils.ImageFileUtil;
import com.fekpal.common.base.ExampleWrapper;
import com.fekpal.api.SauService;
import com.fekpal.dao.mapper.MemberMapper;
import com.fekpal.dao.mapper.OrgMapper;
import com.fekpal.dao.model.Org;
import com.fekpal.service.model.domain.SauMsg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpSession;
import java.util.Calendar;
import java.util.List;

/**
 * Created by APone on 2017/9/5.
 * SauService实现类
 */
@Service
public class SauServiceImpl extends BaseServiceImpl<OrgMapper, Org> implements SauService {

    @Autowired
    private HttpSession session;

    @Autowired
    private MemberMapper memberMapper;

    @Override
    public Org selectByPrimaryId() {
        int uid = SessionLocal.local(session).getUserIdentity().getUid();
        return mapper.selectByPrimaryKey(uid);
    }

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
        int uid = SessionLocal.local(session).getUserIdentity().getUid();
        ExampleWrapper<Org> example = new ExampleWrapper<>();
        example.eq("org_name", msg.getSauName()).and().ne("org_id",uid);
        int row = mapper.countByExample(example);
        if (row != 0) return Operation.INPUT_INCORRECT;


        Org org = new Org();
        org.setOrgId(uid);
        org.setOrgName(msg.getSauName());
        org.setDescription(msg.getDescription());
        org.setAdminName(msg.getAdminName());
        org.setFoundTime(msg.getFoundTime());
        row = mapper.updateByPrimaryKeySelective(org);

        if (row > 1) throw new CRUDException("更新校社联信息失败：" + row);
        return row == 0 ? Operation.FAILED : Operation.SUCCESSFULLY;
    }

    @Override
    public String updateLogo(SauMsg msg) {
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
            throw new CRUDException(e.getMessage());
        }
    }

    /**
     * 根据校社联用户标识更新校社联展示
     *
     * @param msg 校社联修改信息封装
     *            传入参数：头像文件logo
     * @return 头像名
     */
    @Override
    public String updateView(SauMsg msg) {
        try {
            int uid = SessionLocal.local(session).getUserIdentity().getUid();
            Org org = mapper.selectByPrimaryKey(uid);
            //存入数据库的是带后缀的，进行存储的时候是不能带后缀的，要以上传文件的后缀为后缀
            String[] orgViews = org.getOrgView().split("\\.");
            String view = "";
            if( org.getOrgView().equalsIgnoreCase(DefaultField.DEFAULT_CLUB_OVERVIEW)) {
                view = ImageFileUtil.handle(msg.getLogo(), FIleDefaultPath.SAU_VIEW_FILE);
            }else{
                view = ImageFileUtil.handle(msg.getLogo(), FIleDefaultPath.SAU_VIEW_FILE,orgViews[0]);
            }
            org.setOrgView(view);
            mapper.updateByPrimaryKey(org);
            return view;
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

    /**
     * 根据社团id计算校社联内部男生的数量
     *
     * @return 校社联内男生的人数
     */
    @Override
    public int countSauManNum() {
        int orgId = SessionLocal.local(session).getUserIdentity().getUid();
        int manNum = memberMapper.countOrgManNum(orgId);
        return manNum;
    }

    /**
     * 计算校社联内部女生的数量
     *
     * @return 校社联内女生的人数
     */
    @Override
    public int countSauWomanNum() {
        int orgId = SessionLocal.local(session).getUserIdentity().getUid();
        int womanNum = memberMapper.countOrgWomanNum(orgId);
        return womanNum;
    }

    /**
     * 根据年级数计算校社联内部年级的数量
     *
     * @param grade 年级 如1,2,3,4,
     * @return 校社联内各个年级的人数
     */
    @Override
    public int countSauGradeNum(int grade) {
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


