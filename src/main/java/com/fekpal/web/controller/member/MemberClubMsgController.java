package com.fekpal.web.controller.member;

import com.fekpal.api.ClubMemberService;
import com.fekpal.api.ClubService;
import com.fekpal.api.PersonService;
import com.fekpal.common.json.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.*;


/**
 * 普通成员社团信息的控制类
 * Created by hasee on 2017/8/23.
 */
@Controller
public class MemberClubMsgController {

    @Autowired
    private ClubMemberService clubMemberService;

    @Autowired
    private PersonService personService;

    @Autowired
    private ClubService clubService;

    @Autowired
    private JsonResult returnData;

    /**
     * 返回社团信息列表的方法
     *
     * @return 社团信息列表
     */
    @ResponseBody
    @RequestMapping(value = "/member/club", method = RequestMethod.GET)
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
    @RequestMapping(value = "/member/club/{clubId}", method = RequestMethod.GET)
    public Map<String, Object> getOneClubMsg(@PathVariable int clubId) {
        return null;
    }

    /**
     * 用户加入社团的方法
     *
     * @param clubId 要加入的社团id
     * @return 返回时候加入成功，等待审核
     */
    @ResponseBody
    @RequestMapping(value = "/member/club/{clubId}/join", method = RequestMethod.POST)
    public Map<String, Object> joinClub(@PathVariable("clubId") int clubId) {

        return null;
    }

    /**
     * 用户喜爱社团的方法
     *
     * @param clubId 要喜爱的社团id
     * @return 返回时候是否喜爱成功
     */
    @ResponseBody
    @RequestMapping(value = "/member/club/{clubId}/star", method = RequestMethod.GET)
    public Map<String, Object> likeClub(@PathVariable("clubId") int clubId, @RequestParam int available) {
        return null;
    }

    /**
     * 根据查找内容查找某个社团
     *
     * @param request     请求
     * @param findContent 查找内容
     * @return 查找的社团的列表对象信息
     */
    @ResponseBody
    @RequestMapping(value = "/member/club/search", method = RequestMethod.GET)
    public Map<String, Object> searchMsg(HttpServletRequest request, @RequestParam String findContent) {
        return null;
    }

}
