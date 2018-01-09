package com.fekpal.web.controller.clubAdmin;

import com.fekpal.cons.ResponseCode;
import com.fekpal.domain.Club;
import com.fekpal.domain.ClubMember;
import com.fekpal.domain.User;
import com.fekpal.service.ClubMemberService;
import com.fekpal.service.ClubService;
import com.fekpal.tool.JsonObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.*;

/**
 * 注册审核的控制类
 * Created by hasee on 2017/8/27.
 */
@Controller
public class ClubAuditRegController {

    @Autowired
    private ClubMemberService clubMemberService;

    @Autowired
    private ClubService clubService;

    @Autowired
    private JsonObject returnData;

    /**
     * 查看全部审核的信息的方法
     *
     * @param session 会话
     * @return 审核信息列表
     */
    @ResponseBody
    @RequestMapping(value = "/club/audit/join", method = RequestMethod.GET)
    public Map<String, Object> getAllAuditMsg(HttpSession session) {

        User user = (User) session.getAttribute("userCode");
        Club club = clubService.getClubByUserId(user.getUserId());

        //根据用户id得到社团成员注册信息的对象
        List<ClubMember> lists = clubMemberService.getAllAuditingByClubId(club.getClubId());
        //创建放全部审核信息的list集合，并将它放入返回数据
        List<Map<String, Object>> auditMsgList = new ArrayList<>();

        for (ClubMember member : lists) {
            //将得到的数据放入每个map集合中
            Map<String, Object> auditMsgListMap = new LinkedHashMap<>();
            auditMsgListMap.put("auditMsgId", member.getId());
            auditMsgListMap.put("auditTitle", member.getPerson().getRealName());
            auditMsgListMap.put("registerTime", new Date(member.getJoinTime().getTime()));
            auditMsgListMap.put("auditState", member.getAvailable());
            auditMsgList.add(auditMsgListMap);
        }

        returnData.setData(auditMsgList);
        return returnData.getMap();
    }

    /**
     * 根据某个审核消息id查看审核信息的具体内容
     *
     * @param auditMsgId 审核信息的id
     * @param session    会话
     * @return 审核信息列表
     */
    @ResponseBody
    @RequestMapping(value = "/club/audit/join/{auditMsgId}", method = RequestMethod.GET)
    public Map<String, Object> getAuditMsgDetail(@PathVariable("auditMsgId") int auditMsgId, HttpSession session) {

        if (auditMsgId > 0) {
            //根据审核id和用户id,和role值从dao中得到审核消息内容


            //审核社员注册信息
            Map<String, Object> auditMsgDetailMap = new LinkedHashMap<>();
            auditMsgDetailMap.put("auditMsgId", auditMsgId);
            auditMsgDetailMap.put("userName", "s19961234@126.com");
            auditMsgDetailMap.put("registerTime", new Date());
            auditMsgDetailMap.put("personLogo", "a.jpg");
            auditMsgDetailMap.put("realName", "张三");
            auditMsgDetailMap.put("studentId", "151612220");
            auditMsgDetailMap.put("gender", "男");
            auditMsgDetailMap.put("birthday", new Date());
            auditMsgDetailMap.put("phone", "183168218383");
            auditMsgDetailMap.put("email", "s19961234@126.com");
            auditMsgDetailMap.put("departmentId", "金融学院与统计学院");
            auditMsgDetailMap.put("majorName", "信息与计算科学");
            auditMsgDetailMap.put("address", "8#110");

            //将map集合数据放入到返回数据中，返回
            returnData.setData(auditMsgDetailMap);

        } else {
            returnData.setStateCode(ResponseCode.REQUEST_ERROR, "要查询的审核消息的id不符合条件，请重新查询");
            return returnData.getMap();
        }
        return returnData.getMap();
    }

    /**
     * 发送审核结果，得到审核结果
     *
     * @param auditMsgId 审核消息id
     * @param resultMap  结果的map集合
     * @param session    会话
     * @return 是否成功
     */
    @ResponseBody
    @RequestMapping(value = "/club/audit/join/{auditMsgId}", method = RequestMethod.PUT)
    public Map<String, Object> sendAuditMsgResult(@PathVariable("auditMsgId") int auditMsgId, @RequestParam Map<String, Object> resultMap, HttpSession session) {


        //从map中得到审核结果和审核状态
        int auditState = Integer.parseInt(resultMap.get("auditState").toString());
        String auditResult = (String) resultMap.get("auditResult");

        //对审核状态码进行判断
        if (auditState >= 2 || auditState < 0) {
            returnData.setStateCode(ResponseCode.REQUEST_ERROR, "审核状态码不正确，请重新输入。");
            return returnData.getMap();
        }

        if (auditMsgId > 0) {
            //根据用户id，审核消息id，通过dao将审核结果和状态存入数据库

        } else {
            returnData.setStateCode(ResponseCode.REQUEST_ERROR, "审核消息的id不正确，请重新执行操作");
            return returnData.getMap();
        }
        return returnData.getMap();
    }

    /**
     * 根据查找内容查找审核消息
     *
     * @param findContent 查找内容
     * @param session     会话
     * @return 返回审核消息列表
     */
    @ResponseBody
    @RequestMapping(value = "/club/audit/join/search", method = RequestMethod.GET)
    public Map<String, Object> searchAuditMsg(@RequestParam String findContent, HttpSession session) {


        //如果要查找的消息的是空的话，返回全部审核消息
        if (StringUtils.isEmpty(findContent)) {
            return getAllAuditMsg(session);
        }

        //根据用户id和查找内容，通过dao从数据库中查找相应的审核信息

        //将得到的数据放入每个map集合中
        Map<String, Object> auditMsgListMap = new LinkedHashMap<String, Object>();
        auditMsgListMap.put("auditMsgId", 234);
        auditMsgListMap.put("auditTitle", "张三" + "。查找的内容是：" + findContent);
        auditMsgListMap.put("registerTime", new Date());
        auditMsgListMap.put("auditState", 2);

        //创建放全部审核信息的list集合，并将它放入返回数据
        List<Map<String, Object>> auditMsgList = new ArrayList<Map<String, Object>>();
        auditMsgList.add(auditMsgListMap);

        returnData.setData(auditMsgList);
        return returnData.getMap();
    }
}
