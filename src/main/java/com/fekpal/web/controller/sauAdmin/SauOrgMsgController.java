package com.fekpal.web.controller.sauAdmin;

import com.fekpal.api.MemberOrgService;
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

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * 校社联的社团信息的控制类
 * @author kanlon
 */
@Controller
public class SauOrgMsgController {

    @Autowired
    private OrgService orgService;

    /**
     * 返回社团信息列表的方法
     * @return 社团信息列表
     */
    @ResponseBody
    @RequestMapping(value = "/sau/club",method = RequestMethod.GET)
    public JsonResult<List<OrgListMsg>> getAllClubMsg(PageList page){
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
                lists.add(msg);
            }
            result.setStateCode(ResponseCode.RESPONSE_SUCCESS, "加载成功");
            result.setData(lists);
        }
        return result;
    }


    /**
     * 得到某个社团的详细信息
     * @param id 社团ID
     * @return 社团详细信息
     */
    @ResponseBody
    @RequestMapping(value = "/sau/club/{id}",method = RequestMethod.GET)
    public JsonResult<OrgDetail> getOneClubMsg(@PathVariable int id){
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
     * 根据查找内容查找某个社团
     * @param page 请求开始和条数
     * @return 查找的社团的列表对象信息
     */
    @ResponseBody
    @RequestMapping(value = "/sau/club/search",method = RequestMethod.GET)
    public JsonResult<List<OrgListMsg>> searchMsg(SearchPage page){
        List<Org> orgList = orgService.selectByOrgName(page.getKey(), page.getOffset(), page.getLimit());
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
                lists.add(msg);
            }
            result.setStateCode(ResponseCode.RESPONSE_SUCCESS, "加载成功");
            result.setData(lists);
        }
        return result;

    }
}
