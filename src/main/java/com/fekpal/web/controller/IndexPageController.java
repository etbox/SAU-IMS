package com.fekpal.web.controller;

import com.fekpal.api.ClubService;
import com.fekpal.api.OrgService;
import com.fekpal.common.constant.ResponseCode;
import com.fekpal.common.json.JsonResult;
import com.fekpal.dao.model.Org;
import com.fekpal.web.model.OrgDetail;
import com.fekpal.web.model.OrgListMsg;
import com.fekpal.web.model.PageList;
import org.apache.log4j.Logger;
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

    private static Logger logger = Logger.getLogger(IndexPageController.class);


    @Autowired
    private ClubService clubService;

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
        //前端传的offset是页码，转化为跳过的条数
        if(page!=null){page.setOffset((page.getOffset()-1)*page.getLimit());}

        List<Org> clubList = orgService.loadAllOrg(page.getOffset(),page.getLimit());
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
                clubs.setLogo(club.getOrgLogo());
                String[] descriptions = club.getDescription().split("。");
                clubs.setDescription(descriptions[0]);
                clubs.setLikeClick(club.getLikeClick());
                clubs.setMembers(club.getMembers());
                results.add(clubs);
            }
            result.setStateCode(ResponseCode.RESPONSE_SUCCESS, "加载成功");
            result.setData(results);
        }
        logger.info("执行了获取社团列表信息");
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
        Org club = clubService.selectByPrimaryKey(id);
        JsonResult<OrgDetail> result = new JsonResult<>();

        if (club == null) {
            result.setStateCode(ResponseCode.RESPONSE_ERROR, "无结果");
        } else {
            OrgDetail record = new OrgDetail();
            record.setOrgId(club.getOrgId());
            record.setAdminName(club.getAdminName());
            record.setLogo(club.getOrgLogo());
            record.setView(club.getOrgView());
            record.setOrgName(club.getOrgName());
            record.setDescription(club.getDescription());
            record.setEmail(club.getContactEmail());
            record.setFoundTime(club.getFoundTime());
            record.setMembers(club.getMembers());
            record.setOrgType(club.getOrgType());
            record.setPhone(club.getContactNumber());
            record.setLikeClick(club.getLikeClick());

            result.setStateCode(ResponseCode.RESPONSE_SUCCESS, "加载成功");
            result.setData(record);
        }
        logger.info("执行了获取某社团信息");
        return result;
    }
}

