package com.fekpal.web.controller.clubAdmin;

import com.fekpal.domain.Club;
import com.fekpal.domain.json.ClubDetail;
import com.fekpal.service.ClubService;
import com.fekpal.tool.JsonObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.*;


/**
 * 社团管理员的的社团信息的控制类
 * Created by hasee on 2017/8/23.
 */
@Controller
public class ClubMsgController {

    @Autowired
    private ClubService clubService;

    @Autowired
    private JsonObject returnData;

    /**
     * 返回社团信息列表的方法
     *
     * @return 社团信息列表
     */
    @ResponseBody
    @RequestMapping(value = "/club/other", method = RequestMethod.GET)
    public Map<String, Object> getAllClubMsg() {

        List<Club> clubs = clubService.loadAllClub(0, 50);

        //将得到的对象，从里面循环获取值赋值到返回前端的社团列表对象中
        List<Map<String, Object>> clubMsgList = new ArrayList<>();
        for (Club club : clubs) {
            Map<String, Object> clubMsgMap = new LinkedHashMap<>();
            clubMsgMap.put("clubId", club.getClubId());
            clubMsgMap.put("clubName", club.getClubName());
            clubMsgMap.put("members", club.getMembers());
            clubMsgMap.put("likeNumber", club.getLikeNumber());
            clubMsgList.add(clubMsgMap);
        }

        returnData.setData(clubMsgList);
        return returnData.getMap();
    }


    /**
     * 得到某个社团的详细信息
     *
     * @param clubId 社团ID
     * @return 社团详细信息
     */
    @ResponseBody
    @RequestMapping(value = "/club/other/{clubId}", method = RequestMethod.GET)
    public Map<String, Object> getOneClubMsg(@PathVariable int clubId) {

        Club club = clubService.getClubByClubId(clubId);

        //将对象的属性赋值到显示给前端的clubDetail对象中
        ClubDetail clubDetail = new ClubDetail();
        clubDetail.setClubId(club.getClubId());
        clubDetail.setAdminName(club.getAdminName());
        clubDetail.setClubLogo(club.getLogo());
        clubDetail.setClubName(club.getClubName());
        clubDetail.setDescription(club.getDescription());
        clubDetail.setEmail(club.getEmail());
        clubDetail.setPhone(club.getPhone());
        clubDetail.setFoundTime(new Date(club.getFoundTime().getTime()));
        clubDetail.setMembers(club.getMembers());

        returnData.setData(clubDetail);
        return returnData.getMap();
    }

    /**
     * 根据查找内容查找某个社团
     *
     * @param findContent 查找内容
     * @return 查找的社团的列表对象信息
     */
    @ResponseBody
    @RequestMapping(value = "/club/other/search", method = RequestMethod.GET)
    public Map<String, Object> searchMsg(@RequestParam String findContent) {

        List<Club> clubs = clubService.findClubByClubName(findContent, 0, 50);

        List<Map<String, Object>> clubMsgList = new ArrayList<>();
        for (Club club : clubs) {
            Map<String, Object> clubMsgMap = new LinkedHashMap<>();
            clubMsgMap.put("members", club.getMembers());
            clubMsgMap.put("likeNumber", club.getLikeNumber());
            clubMsgMap.put("clubId", club.getClubId());
            clubMsgMap.put("clubName", club.getClubName());
            clubMsgList.add(clubMsgMap);
        }

        returnData.setData(clubMsgList);
        return returnData.getMap();
    }

}
