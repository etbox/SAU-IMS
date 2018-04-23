package com.fekpal.web.controller.member;

import com.fekpal.api.LikeOrgService;
import com.fekpal.api.MemberOrgService;
import com.fekpal.api.OrgService;
import com.fekpal.common.constant.Operation;
import com.fekpal.common.constant.ResponseCode;
import com.fekpal.common.json.JsonResult;
import com.fekpal.dao.model.LikeOrg;
import com.fekpal.dao.model.Org;
import com.fekpal.dao.model.PersonOrgView;
import com.fekpal.web.model.OrgDetail;
import com.fekpal.web.model.OrgListMsg;
import com.fekpal.web.model.PageList;
import com.fekpal.web.model.SearchPage;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


/**
 * 普通成员社团信息的控制类
 * Created by hasee on 2017/8/23.
 */
@Controller
public class MemberOrgMsgController {

    private Logger logger = Logger.getLogger(MemberCenterController.class);

    @Autowired
    private OrgService orgService;

    @Autowired
    private MemberOrgService memberOrgService;

    @Autowired
    private LikeOrgService likeOrgService;

    private LikeOrg likeOrg;

    /**
     * 返回组织信息列表的方法
     *
     * @param page 分页封装
     * @return 组织信息列表
     */
    @ResponseBody
    @RequestMapping(value = "/member/org", method = RequestMethod.GET)
    public JsonResult<List<OrgListMsg>> getAllOrgMsg(PageList page) {
        //将前端发送过来的页码offset，转化为跳过数offset
        if(page!=null){page.setOffset((page.getOffset()-1)*page.getLimit());}

        List<Org> orgList = orgService.loadAllOrg(page.getOffset(), page.getLimit());
        JsonResult<List<OrgListMsg>> result = new JsonResult<>();

        if (orgList == null || orgList.size() == 0) {
            result.setStateCode(ResponseCode.RESPONSE_ERROR, "无结果");
        } else {
            List<OrgListMsg> lists = new ArrayList<>();
            for (Org org : orgList) {
                OrgListMsg msg = new OrgListMsg();
                msg.setOrgId(org.getOrgId());
                msg.setOrgName(org.getOrgName());
                msg.setLogo(org.getOrgLogo());
                msg.setMembers(org.getMembers());
                msg.setLikeClick(org.getLikeClick());
                likeOrg = likeOrgService.selectByOrgId(org.getOrgId());
                msg.setIsClick(likeOrg.getAvailable());
                lists.add(msg);
            }
            result.setStateCode(ResponseCode.RESPONSE_SUCCESS, "加载成功");
            result.setData(lists);
        }
        return result;
    }


    /**
     * 得到某个组织的详细信息
     *
     * @param id 组织id
     * @return 社团详细信息
     */
    @ResponseBody
    @RequestMapping(value = "/member/org/{id}", method = RequestMethod.GET)
    public JsonResult<OrgDetail> getOneOrgMsg(@PathVariable int id) {
        PersonOrgView org = orgService.selectByIdForPerson(id);

        JsonResult<OrgDetail> result = new JsonResult<>();
        if (org == null) {
            result.setStateCode(ResponseCode.RESPONSE_ERROR, "无结果");

        } else {
            OrgDetail detail = new OrgDetail();
            detail.setOrgId(org.getOrgId());
            detail.setOrgName(org.getOrgName());
            detail.setAdminName(org.getAdminName());
            detail.setDescription(org.getDescription());
            detail.setPhone(org.getContactNumber());
            detail.setEmail(org.getContactEmail());
            detail.setFoundTime(org.getFoundTime());
            detail.setLogo(org.getOrgLogo());
            detail.setOrgType(org.getOrgType());
            detail.setMembers(org.getMembers());
            detail.setLikeClick(org.getLikeClick());
            detail.setView(org.getOrgView());
            detail.setJoinState(org.getJoinState());

            result.setStateCode(ResponseCode.RESPONSE_SUCCESS, "加载成功");
            result.setData(detail);
        }
        return result;
    }

    /**
     * 用户加入组织
     *
     * @param id 组织id
     * @return 返回时候加入成功，等待审核
     */
    @ResponseBody
    @RequestMapping(value = "/member/org/{id}/join", method = RequestMethod.POST)
    public JsonResult<String> joinClub(@PathVariable int id) {
        int state = memberOrgService.joinOrganizationByOrgId(id);

        JsonResult<String> result = new JsonResult<>();
        if (state == Operation.SUCCESSFULLY) {
            result.setStateCode(ResponseCode.RESPONSE_SUCCESS, "申请成功，请留意审核通知");
        } else if (state == Operation.FAILED) {
            result.setStateCode(ResponseCode.RESPONSE_ERROR, "申请失败，请稍后再试");
        } else if (state == Operation.INPUT_INCORRECT) {
            result.setStateCode(ResponseCode.RESPONSE_ERROR, "请完善资料再进行申请");
        }
        return result;
    }

    /**
     * 用户喜爱组织
     *
     * @param id 要喜爱的组织id
     * @return 返回时候是否喜爱成功
     */
    @ResponseBody
    @RequestMapping(value = "/member/org/{id}/star", method = RequestMethod.POST)
    public JsonResult<String> likeClub(@PathVariable int id) {
        int state = likeOrgService.likeByOrgIdAndState(id);

        JsonResult<String> result = new JsonResult<>();
        if (state == Operation.SUCCESSFULLY) {
            result.setStateCode(ResponseCode.RESPONSE_SUCCESS, "点赞成功");
        } else if (state == Operation.FAILED) {
            result.setStateCode(ResponseCode.RESPONSE_ERROR, "点赞失败");
        } else if (state == Operation.CANCEL) {
            result.setStateCode(ResponseCode.RESPONSE_SUCCESS, "取消点赞");
        }
        return result;
    }

    /**
     * 根据查找内容查找组织
     *
     * @param page 查找封装
     * @return 查找的组织的列表对象信息
     */
    @ResponseBody
    @RequestMapping(value = "/member/org/search", method = RequestMethod.GET)
    public JsonResult<List<OrgListMsg>> searchMsg(SearchPage page) {
        //将前端发送的页码offset，转化为跳过条数offset
        if(page!=null){page.setOffset((page.getOffset()-1)*page.getLimit());}

        List<Org> orgList = orgService.selectByOrgName(page.getFindContent(), page.getOffset(), page.getLimit());
        JsonResult<List<OrgListMsg>> result = new JsonResult<>();

        if (orgList == null || orgList.size() == 0) {
            result.setStateCode(ResponseCode.RESPONSE_ERROR, "无结果");
        } else {
            List<OrgListMsg> lists = new ArrayList<>();

            for (Org org : orgList) {
                OrgListMsg msg = new OrgListMsg();
                msg.setOrgId(org.getOrgId());
                msg.setOrgName(org.getOrgName());
                msg.setMembers(org.getMembers());
                msg.setLogo(org.getOrgLogo());
                msg.setLikeClick(org.getLikeClick());
                likeOrg = likeOrgService.selectByOrgId(org.getOrgId());
                msg.setIsClick(likeOrg.getAvailable());

                lists.add(msg);
            }
            result.setStateCode(ResponseCode.RESPONSE_SUCCESS, "加载成功");
            result.setData(lists);
        }
        return result;
    }
}
