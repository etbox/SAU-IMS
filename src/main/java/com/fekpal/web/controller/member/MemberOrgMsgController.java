package com.fekpal.web.controller.member;

import com.fekpal.common.json.JsonResult;
import com.fekpal.web.model.SearchPage;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


/**
 * 普通成员社团信息的控制类
 * Created by hasee on 2017/8/23.
 */
@Controller
public class MemberOrgMsgController {

    /**
     * 返回社团信息列表的方法
     *
     * @return 组织信息列表
     */
    @ResponseBody
    @RequestMapping(value = "/member/org", method = RequestMethod.GET)
    public JsonResult getAllClubMsg() {

        return null;
    }


    /**
     * 得到某个组织的详细信息
     *
     * @param id 组织id
     * @return 社团详细信息
     */
    @ResponseBody
    @RequestMapping(value = "/member/org/{id}", method = RequestMethod.GET)
    public JsonResult getOneClubMsg(@PathVariable int id) {
        return null;
    }

    /**
     * 用户加入组织
     *
     * @param id 组织id
     * @return 返回时候加入成功，等待审核
     */
    @ResponseBody
    @RequestMapping(value = "/member/org/{id}/join", method = RequestMethod.POST)
    public JsonResult joinClub(@PathVariable int id) {

        return null;
    }

    /**
     * 用户喜爱组织
     *
     * @param id 要喜爱的组织id
     * @return 返回时候是否喜爱成功
     */
    @ResponseBody
    @RequestMapping(value = "/member/org/{id}/star", method = RequestMethod.GET)
    public JsonResult likeClub(@PathVariable int id) {
        return null;
    }

    /**
     * 根据查找内容查找组织
     *
     * @param page 查找封装
     * @return 查找的组织的列表对象信息
     */
    @ResponseBody
    @RequestMapping(value = "/member/org/search", method = RequestMethod.GET)
    public JsonResult searchMsg(SearchPage page) {
        return null;
    }
}
