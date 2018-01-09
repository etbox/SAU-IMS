package com.fekpal.web.controller;

import com.fekpal.domain.Club;
import com.fekpal.domain.json.ClubDetail;
import com.fekpal.domain.json.ClubListMsg;
import com.fekpal.service.ClubService;
import com.fekpal.tool.JsonObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;


/**
 * 首页的控制类
 * Created by hasee on 2017/8/15.
 */
@Controller
@RequestMapping("/index")
public class IndexPageController {

    @Autowired
    private ClubService clubService;

    @Autowired
    private JsonObject returnData;

    /**
     * 得到社团列表信息
     *
     * @return 社团列表信息（json数据）
     */
    @ResponseBody
    @RequestMapping(value = "/club")
    public Map<String, Object> getClubList() {

        // 创建封装社团列表信息的list集合
        List<ClubListMsg> list = new ArrayList<>();

        //从service中得到对象，获取对象属性，放入对应中
        List<Club> clubList = clubService.loadAllClub(0, 50);

        for (Club club : clubList) {
            ClubListMsg clubs = new ClubListMsg();
            clubs.setClubId(club.getClubId());
            clubs.setClubName(club.getClubName());
            clubs.setClubView(club.getClubView());
            clubs.setDescription(club.getDescription());
            clubs.setLikeNumber(club.getLikeNumber());
            clubs.setMembers(club.getMembers());
            list.add(clubs);
        }
        //将list加入到数据中
        returnData.setData(list);

        return returnData.getMap();
    }

    /**
     * 发送某个社团的详细信息
     *
     * @param clubId 接受社团ID
     * @return 返回社团详细信息json
     */
    @ResponseBody
    @RequestMapping(value = "/club/{clubId}")
    public Map<String, Object> getClubDetail(@PathVariable("clubId") Integer clubId) {

        Club club = clubService.getClubByClubId(clubId);
        ClubDetail club1 = new ClubDetail();

        club1.setClubId(club.getClubId());
        club1.setAdminName(club.getAdminName());
        club1.setClubLogo(club.getLogo());
        club1.setClubName(club.getClubName());
        club1.setDescription(club.getDescription());
        club1.setEmail(club.getEmail());
        club1.setFoundTime(new Date(club.getFoundTime().getTime()));
        club1.setMembers(club.getMembers());
        returnData.setData(club);

        return returnData.getMap();
    }
}

