package com.fekpal.web.controller.member;

import com.fekpal.api.MemberOrgService;
import com.fekpal.api.PersonService;
import com.fekpal.common.constant.Operation;
import com.fekpal.common.constant.ResponseCode;
import com.fekpal.common.json.JsonResult;
import com.fekpal.dao.model.MemberOrg;
import com.fekpal.dao.model.Person;
import com.fekpal.service.model.domain.PersonMsg;
import com.fekpal.web.model.MemberOrgDetail;
import com.fekpal.web.model.PageList;
import com.fekpal.web.model.PersonDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

/**
 * 普通用户和社团成员端中心的控制类
 * Created by hasee on 2017/8/19.
 */
@Controller
public class MemberCenterController {

    @Autowired
    private PersonService personService;

    @Autowired
    private MemberOrgService memberOrgService;

    /**
     * 得到普通成员和社团成员中心的信息的方法
     *
     * @return 普通成员或者社团成员的一些基本信息
     */
    @ResponseBody
    @RequestMapping(value = "/member/center/info", method = RequestMethod.GET)
    public JsonResult<PersonDetail> getMemberCenterMsg() {
        Person person = personService.selectByPrimaryId();
        PersonDetail detail = new PersonDetail();
        detail.setAddress(person.getAddress());
        detail.setBirthday(person.getBirthday());
        detail.setDepartment(person.getDepartment());
        detail.setDescription(person.getDescription());
        detail.setEnrollmentYear(person.getEnrollmentYear());
        detail.setGender(person.getGender());
        detail.setLogo(person.getLogo());
        detail.setMajor(person.getMajor());
        detail.setNickname(person.getNickname());
        detail.setRealName(person.getRealName());
        detail.setStudentId(person.getStudentId());

        JsonResult<PersonDetail> result = new JsonResult<>();
        result.setStateCode(ResponseCode.RESPONSE_SUCCESS, "加载成功");
        result.setData(detail);
        return result;
    }

    /**
     * 上传成员个人头像的方法
     *
     * @param msg 普通用户信息封装
     * @return 图片文件名
     */
    @ResponseBody
    @RequestMapping(value = "/member/center/info/head", method = RequestMethod.PUT)
    public JsonResult<String> uploadLogo(PersonMsg msg) {
        String logoName = personService.updateLogo(msg);

        JsonResult<String> result = new JsonResult<>();
        result.setStateCode(ResponseCode.RESPONSE_SUCCESS, "修改头像成功");
        result.setData(logoName);
        return result;
    }

    /**
     * 普通成员用来提交修改个人中心的信息
     *
     * @param msg 普通用户信息封装
     * @return 是否提交成功
     */
    @ResponseBody
    @RequestMapping(value = "/member/center/info", method = RequestMethod.PUT)
    public JsonResult<String> subNewCenterMsg(PersonMsg msg) {
        int state = personService.updatePersonInfo(msg);

        JsonResult<String> result = new JsonResult<>();
        if (state == Operation.SUCCESSFULLY) {
            result.setStateCode(ResponseCode.RESPONSE_SUCCESS, "修改个人信息成功");
        } else if (state == Operation.FAILED) {
            result.setStateCode(ResponseCode.RESPONSE_ERROR, "修改个人信息失败");
        } else if (state == Operation.INPUT_INCORRECT) {
            result.setStateCode(ResponseCode.RESPONSE_ERROR, "昵称已被使用");
        }
        return result;
    }

    /**
     * 获取加入组织的成员状态信息
     *
     * @param page 分页
     * @return 成员组织信息封装
     */
    @ResponseBody
    @RequestMapping(value = "/member/center/org", method = RequestMethod.GET)
    public JsonResult getOrgPersonJoin(PageList page) {
        List<MemberOrg> list = memberOrgService.loadAllOrg(page.getOffset(), page.getLimit());
        List<MemberOrgDetail> details = new ArrayList<>();
        for (MemberOrg memberOrg : list) {
            MemberOrgDetail detail = new MemberOrgDetail();
            detail.setOrgId(memberOrg.getOrgId());
            detail.setOrgName(memberOrg.getOrgName());
            detail.setOrgDepartment(memberOrg.getOrgDepartment());
            detail.setJoinTime(memberOrg.getJoinTime());
            detail.setLeaveTime(memberOrg.getLeaveTime());
            detail.setLogo(memberOrg.getOrgLogo());
            detail.setMemberDuty(memberOrg.getMemberDuty());
            detail.setMemberState(memberOrg.getMemberState());
            details.add(detail);
        }

        JsonResult<List<MemberOrgDetail>> result = new JsonResult<>();
        result.setStateCode(ResponseCode.RESPONSE_SUCCESS, "加载成功");
        result.setData(details);
        return result;
    }
}












