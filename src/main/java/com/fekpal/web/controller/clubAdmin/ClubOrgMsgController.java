package com.fekpal.web.controller.clubAdmin;

import com.fekpal.api.ClubService;
import com.fekpal.api.OrgService;
import com.fekpal.common.constant.ResponseCode;
import com.fekpal.common.json.JsonResult;
import com.fekpal.dao.model.Org;
import com.fekpal.dao.model.PersonOrgView;
import com.fekpal.web.model.OrgDetail;
import com.fekpal.web.model.OrgListMsg;
import com.fekpal.web.model.PageList;
import com.fekpal.web.model.SearchPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.*;


/**
 * 社团管理员的的社团信息的控制类
 * @author kanlon
 * @time 2018/4/8
 */
@Controller
public class ClubOrgMsgController {

    @Autowired
    private ClubService clubService;

    @Autowired
    private OrgService orgService;

    /**
     * 返回社团信息列表的方法
     *
     * @return 社团信息列表
     */
    @ResponseBody
    @RequestMapping(value = "/club/other", method = RequestMethod.GET)
    public JsonResult<List<OrgListMsg>> getAllClubMsg(PageList page) {
        List<Org> orgList = clubService.loadAllClub(page.getOffset(), page.getLimit());
        JsonResult<List<OrgListMsg>> result = new JsonResult<>();

        if (orgList == null || orgList.size() == 0) {
            result.setStateCode(ResponseCode.REQUEST_ERROR, "无结果");
        } else {
            List<OrgListMsg> lists = new ArrayList<>();
            for (Org org : orgList) {
                OrgListMsg msg = new OrgListMsg();
                msg.setOrgId(org.getOrgId());
                msg.setOrgName(org.getOrgName());
                msg.setLogo(org.getOrgLogo());
                msg.setMembers(org.getMembers());
                msg.setLikeClick(org.getLikeClick());
                lists.add(msg);
            }
            result.setStateCode(ResponseCode.RESPONSE_SUCCESS, "加载成功");
            result.setData(lists);
        }
        return result;
    }


    /**
     * 得到某个社团的详细信息
     *
     * @param clubId 社团ID
     * @return 社团详细信息
     */
    @ResponseBody
    @RequestMapping(value = "/club/other/{clubId}", method = RequestMethod.GET)
    public JsonResult<OrgDetail> getOneClubMsg(@PathVariable int clubId) {
        PersonOrgView org = clubService.selectByIdForPerson(clubId);
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
     * 根据查找内容查找某个社团
     *
     * @param page 查找内容及查找条数信息
     * @return 查找的社团的列表对象信息
     */
    @ResponseBody
    @RequestMapping(value = "/club/other/search", method = RequestMethod.GET)
    public JsonResult<List<OrgListMsg>> searchMsg(SearchPage page) {
        List<Org> orgList = clubService.queryByClubName(page.getKey(), page.getOffset(), page.getLimit());
        JsonResult<List<OrgListMsg>> result = new JsonResult<>();
        if (orgList == null || orgList.size() == 0) {
            result.setStateCode(ResponseCode.RESPONSE_ERROR, "无结果");
            return result;
        }

        List<OrgListMsg> lists = new ArrayList<>();
        for (Org org : orgList) {
            OrgListMsg msg = new OrgListMsg();
            msg.setOrgId(org.getOrgId());
            msg.setOrgName(org.getOrgName());
            msg.setMembers(org.getMembers());
            msg.setLogo(org.getOrgLogo());
            msg.setLikeClick(org.getLikeClick());
            lists.add(msg);
        }
        result.setStateCode(ResponseCode.RESPONSE_SUCCESS, "加载成功");
        result.setData(lists);
        return result;
    }


}
