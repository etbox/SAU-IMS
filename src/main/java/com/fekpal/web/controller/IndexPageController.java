package com.fekpal.web.controller;

import com.fekpal.common.constant.ResponseCode;
import com.fekpal.common.json.JsonResult;
import com.fekpal.dao.model.Org;
import com.fekpal.web.model.ClubDetail;
import com.fekpal.web.model.ClubListMsg;
import com.fekpal.api.ClubService;
import com.fekpal.web.model.SauDetail;
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
    private ClubService clubService;

    /**
     * 得到社团列表信息
     *
     * @return 社团列表信息json
     */
    @ResponseBody
    @RequestMapping(value = "/index/club", method = RequestMethod.GET)
    public JsonResult<List<ClubListMsg>> getClubList() {
        List<Org> clubList = clubService.loadAllClub(0, 50);
        JsonResult<List<ClubListMsg>> result = new JsonResult<>();

        if (clubList == null || clubList.size() == 0) {
            result.setStateCode(ResponseCode.RESPONSE_ERROR, "无结果");
        } else {
            List<ClubListMsg> results = new ArrayList<>();
            for (Org club : clubList) {
                ClubListMsg clubs = new ClubListMsg();
                clubs.setClubId(club.getOrgId());
                clubs.setClubName(club.getOrgName());
                clubs.setClubView(club.getOrgView());
                clubs.setDescription(club.getDescription());
                clubs.setLikeNumber(club.getLikeClick());
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
     * @param clubId 接受社团标识
     * @return 社团信息封装
     */
    @ResponseBody
    @RequestMapping(value = "/index/club/{id}", method = RequestMethod.GET)
    public JsonResult<ClubDetail> getClubDetail(@PathVariable("id") int clubId) {
        Org club = clubService.selectByPrimaryKey(clubId);
        JsonResult<ClubDetail> result = new JsonResult<>();

        if (club == null) {
            result.setStateCode(ResponseCode.RESPONSE_ERROR, "无结果");
        } else {
            ClubDetail record = new ClubDetail();
            record.setClubId(club.getOrgId());
            record.setAdminName(club.getAdminName());
            record.setLogo(club.getOrgLogo());
            record.setClubName(club.getOrgName());
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
    public JsonResult<SauDetail> getSauDetail() {
        JsonResult<SauDetail> result = new JsonResult<>();
        result.setStateCode(ResponseCode.RESPONSE_SUCCESS, "加载成功");
        return result;
    }
}

