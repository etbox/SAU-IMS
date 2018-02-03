package com.fekpal.web.controller.clubAdmin;

import com.fekpal.domain.pojo.Club;
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

        return null;
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

        return null;
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

        return null;
    }

}
