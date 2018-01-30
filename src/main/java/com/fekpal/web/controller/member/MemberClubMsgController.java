package com.fekpal.web.controller.member;

import com.fekpal.cons.AvailableState;
import com.fekpal.cons.ResponseCode;
import com.fekpal.domain.pojo.Club;
import com.fekpal.domain.pojo.ClubMember;
import com.fekpal.domain.pojo.Person;
import com.fekpal.domain.pojo.User;
import com.fekpal.domain.json.ClubDetail;
import com.fekpal.service.ClubMemberService;
import com.fekpal.service.ClubService;
import com.fekpal.service.PersonService;
import com.fekpal.tool.JsonObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
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
    private JsonObject returnData;

    /**
     * 返回社团信息列表的方法
     *
     * @param session 会话
     * @return 社团信息列表
     */
    @ResponseBody
    @RequestMapping(value = "/member/club", method = RequestMethod.GET)
    public Map<String, Object> getAllClubMsg(HttpSession session) {

        User user = (User) session.getAttribute("userCode");
        Person person = personService.getPersonByUserId(user.getUserId());
        List<Club> clubs = clubService.loadAllClub(0, 50);

        List<ClubMember> lists = clubMemberService.getClubMemberByPersonId(person.getPersonId());
        List<Integer> joinList = new ArrayList<>();

        for (ClubMember member : lists) {
            joinList.add(member.getClub().getClubId());
        }

        //将得到的对象，从里面循环获取值赋值到返回前端的社团列表对象中
        List<Map<String, Object>> clubMsgList = new ArrayList<>();

        for (Club club : clubs) {
            Map<String, Object> clubMsgMap = new LinkedHashMap<>();
            clubMsgMap.put("clubId", club.getClubId());
            clubMsgMap.put("clubName", club.getClubName());
            clubMsgMap.put("members", club.getMembers());
            clubMsgMap.put("likeNumber", club.getLikeNumber());

            if (joinList.contains(club.getClubId())) {
                clubMsgMap.put("like", AvailableState.AVAILABLE);
            } else {
                clubMsgMap.put("like", AvailableState.UNAVAIABLE);
            }

            //将对象放入返回json数据中
            clubMsgList.add(clubMsgMap);
        }

        returnData.setData(clubMsgList);
        return returnData.getMap();
    }


    /**
     * 得到某个社团的详细信息
     *
     * @param session 会话
     * @param clubId  社团ID
     * @return 社团详细信息
     */
    @ResponseBody
    @RequestMapping(value = "/member/club/{clubId}", method = RequestMethod.GET)
    public Map<String, Object> getOneClubMsg(HttpSession session, @PathVariable int clubId) {

        User user = (User) session.getAttribute("userCode");

        //根据用户Id和社团id通过dao从社团表里面返回社团对象

        //将对象的属性赋值到显示给前端的clubDetail对象中
        ClubDetail clubDetail = new ClubDetail();
        clubDetail.setClubId(clubId);
        clubDetail.setAdminName("张三");
        clubDetail.setClubLogo("a.jpg");
        clubDetail.setClubName("乒乓球协会");
        clubDetail.setDescription("我们是一个乒乓球爱好者的集合");
        clubDetail.setEmail("s19961234@126.com");
        clubDetail.setPhone("18316821383");
        clubDetail.setFoundTime(new Date());
        clubDetail.setMembers(100);

        //将对象加入的到返回数据中
        returnData.setData(clubDetail);
        return returnData.getMap();
    }

    /**
     * 用户加入社团的方法
     *
     * @param clubId  要加入的社团id
     * @param session 会话
     * @return 返回时候加入成功，等待审核
     */
    @ResponseBody
    @RequestMapping(value = "/member/club/{clubId}/join", method = RequestMethod.POST)
    public Map<String, Object> joinClub(@PathVariable("clubId") int clubId, HttpSession session) {

        User user = (User) session.getAttribute("userCode");

        //在service层调用方法，查询用户个人信息是否完整了，

        //如果不完整了
        if (false) {
            returnData.setStateCode(ResponseCode.REQUEST_ERROR, "请先完善个人信息，再加入！");
        }

        //如果完整了，直接返回，前端提示等待审核中
        return returnData.getMap();
    }

    /**
     * 用户喜爱社团的方法
     *
     * @param clubId  要喜爱的社团id
     * @param session 会话
     * @return 返回时候是否喜爱成功
     */
    @ResponseBody
    @RequestMapping(value = "/member/club/{clubId}/star", method = RequestMethod.GET)
    public Map<String, Object> likeClub(@PathVariable("clubId") int clubId, @RequestParam int avaliable, HttpSession session) {
        JsonObject returnData = new JsonObject();

        //得到用户id 
        int userId = 0;
        if (session.getAttribute("userCode") != null) {
            userId = (Integer) session.getAttribute("userCode");
        }

        if (avaliable == 0) {
            //调用数据库，把喜爱人数加一，并返回该人数

            //设置返回数据的map集合
            Map<String, Object> likeClubMap = new LinkedHashMap<String, Object>();
            likeClubMap.put("clubId", clubId);
            likeClubMap.put("likeNumber", 101);
            likeClubMap.put("avaliable", 0);
            returnData.setData(likeClubMap);
        } else {
            //调用数据库，把喜爱人数减一，并返回该人数

            //设置返回数据的map集合
            Map<String, Object> likeClubMap = new LinkedHashMap<String, Object>();
            likeClubMap.put("clubId", clubId);
            likeClubMap.put("likeNumber", 99);
            likeClubMap.put("avaliable", 1);
            returnData.setData(likeClubMap);
        }

        return returnData.getMap();
    }

    /**
     * 根据查找内容查找某个社团
     *
     * @param request     请求
     * @param session     会话
     * @param findContent 查找内容
     * @return 查找的社团的列表对象信息
     */
    @ResponseBody
    @RequestMapping(value = "/member/club/search", method = RequestMethod.GET)
    public Map<String, Object> searchMsg(HttpServletRequest request, HttpSession session, @RequestParam String findContent) {
        User user = (User) session.getAttribute("userCode");

        //如果查找内容为空的话，则查询全部社团
        if (StringUtils.isEmpty(findContent.trim())) {
            return getAllClubMsg(session);
        }

        //根据用户id和查找内容，从数据库中查找相关社团，只要查找社团名就可以了,得到相关社团列表的对象

        //将得到的对象，从里面循环获取值赋值到返回前端的社团列表对象中
        //初始化返回对象集合
        List<Map<String, Object>> clubMsgList = new ArrayList<>();

        Map<String, Object> clubMsgMap1 = new LinkedHashMap<>();
        clubMsgMap1.put("clubId", 123);
        clubMsgMap1.put("clubName", "乒乓球协会" + "--查找内容是：" + findContent);
        clubMsgMap1.put("members", 100);
        clubMsgMap1.put("likeNumber", 20);
        clubMsgMap1.put("like", 0);

        //将对象放入返回json数据中
        clubMsgList.add(clubMsgMap1);
        returnData.setData(clubMsgList);

        return returnData.getMap();
    }

}
