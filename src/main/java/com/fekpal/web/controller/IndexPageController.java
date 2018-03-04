package com.fekpal.web.controller;

import com.fekpal.api.OrgService;
import com.fekpal.common.constant.ResponseCode;
import com.fekpal.common.json.JsonResult;
import com.fekpal.dao.model.Org;
import com.fekpal.web.model.OrgDetail;
import com.fekpal.web.model.OrgListMsg;
import com.fekpal.web.model.PageList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


/**
 * 首页的控制类
 * Created by hasee on 2017/8/15.
 */
@Controller
public class IndexPageController {

    @Autowired
    private OrgService orgService;

    /**
     * 得到社团列表信息
     *
     * @return 社团列表信息json
     */
    @ResponseBody
    @RequestMapping(value = "/index/club", method = RequestMethod.GET)
    public JsonResult<List<OrgListMsg>> getClubList(PageList page) {
        List<Org> clubList = orgService.loadAllOrg(page.getOffset(), page.getLimit());
        JsonResult<List<OrgListMsg>> result = new JsonResult<>();

        if (clubList == null || clubList.size() == 0) {
            result.setStateCode(ResponseCode.RESPONSE_ERROR, "无结果");
        } else {
            List<OrgListMsg> results = new ArrayList<>();
            for (Org club : clubList) {
                OrgListMsg clubs = new OrgListMsg();
                clubs.setOrgId(club.getOrgId());
                clubs.setOrgName(club.getOrgName());
                clubs.setView(club.getOrgView());
                clubs.setDescription(club.getDescription());
                clubs.setLikeClick(club.getLikeClick());
                clubs.setMembers(club.getMembers());
                results.add(clubs);
            }
            result.setStateCode(ResponseCode.RESPONSE_SUCCESS, "加载成功");
            result.setData(results);
        }
        return result;
    }

    /**
     * 获取某社团信息
     *
     * @param id 组织标识
     * @return 社团信息封装
     */
    @ResponseBody
    @RequestMapping(value = "/index/club/{id}", method = RequestMethod.GET)
    public JsonResult<OrgDetail> getClubDetail(@PathVariable int id) {
        Org club = orgService.selectByPrimaryKey(id);
        JsonResult<OrgDetail> result = new JsonResult<>();

        if (club == null) {
            result.setStateCode(ResponseCode.RESPONSE_ERROR, "无结果");
        } else {
            OrgDetail record = new OrgDetail();
            record.setOrgId(club.getOrgId());
            record.setAdminName(club.getAdminName());
            record.setLogo(club.getOrgLogo());
            record.setOrgName(club.getOrgName());
            record.setDescription(club.getDescription());
            record.setEmail(club.getContactEmail());
            record.setFoundTime(club.getFoundTime());
            record.setMembers(club.getMembers());

            result.setStateCode(ResponseCode.RESPONSE_SUCCESS, "加载成功");
            result.setData(record);
        }
        return result;
    }

    /**
     * 获取校社联信息
     *
     * @return 社团信息封装
     */
    @ResponseBody
    @RequestMapping(value = "/index/sau", method = RequestMethod.GET)
    public JsonResult<OrgDetail> getSauDetail() {
        JsonResult<OrgDetail> result = new JsonResult<>();
        result.setStateCode(ResponseCode.RESPONSE_SUCCESS, "加载成功");
        return result;
    }
}

