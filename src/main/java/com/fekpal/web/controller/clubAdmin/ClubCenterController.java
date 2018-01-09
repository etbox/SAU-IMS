package com.fekpal.web.controller.clubAdmin;

import com.fekpal.domain.Club;
import com.fekpal.domain.User;
import com.fekpal.service.ClubService;
import com.fekpal.tool.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;


/**
 * 校社联中心信息的控制类
 * Created by hasee on 2017/8/19.
 */
@Controller
public class ClubCenterController {

    @Autowired
    private ClubService clubService;

    @Autowired
    private JsonObject returnData;

    /**
     * 得到社团中心的信息的方法
     *
     * @param session 用户session
     * @return 社团的一些基本信息
     */
    @ResponseBody
    @RequestMapping("/club/center/info")
    public Map<String, Object> getClubsCenterMsg(HttpSession session) {

        User user = (User) session.getAttribute("userCode");

        //创建链表map集合存放社团中心信息
        Map<String, Object> clubCenterMsg = new LinkedHashMap<>();

        //通过用户ID得到数据
        Club club = null;//clubService.getClubAllInfoByUserId(user.getUserId());

        clubCenterMsg.put("clubId", club.getClubId());
        clubCenterMsg.put("clubName", club.getClubName());
        clubCenterMsg.put("clubLogo", club.getLogo());
        clubCenterMsg.put("clubView", club.getClubView());
        clubCenterMsg.put("description", club.getDescription());
        clubCenterMsg.put("adminName", club.getAdminName());
        clubCenterMsg.put("email", club.getEmail());
        clubCenterMsg.put("phone", club.getPhone());
        clubCenterMsg.put("foundTime", new Date(club.getFoundTime().getTime()));
        clubCenterMsg.put("members", club.getMembers());

        //把用户数据添加到返回数据模板中
        returnData.setData(clubCenterMsg);
        return returnData.getMap();
    }

    /**
     * 上传社团头像的方法
     *
     * @param files   文件对象，用from-data表单
     * @param request 请求
     * @return 图片文件名
     */
    @ResponseBody
    @RequestMapping(value = "/club/center/info/edit/head", method = RequestMethod.POST)
    public Map<String, Object> uploadLogo(@RequestParam("file") MultipartFile[] files, HttpServletRequest request, HttpSession session) {

        Map<String, Object> returnData = ImagesUploadTool.uploadImage(files, request, "club//logo");

        if (returnData.get("code").toString().equals("0")) {
            //获取图片的名称
            Map<String, String> clubLogoNameMap = (Map<String, String>) returnData.get("data");
            String clubLogoName = clubLogoNameMap.get("clubLogo");

            User user = (User) session.getAttribute("userCode");
            Club club = clubService.getClubByUserId(user.getUserId());
            club.setLogo(clubLogoName);
            //将logo文件名存入数据库
            clubService.updateClubInfo(club);
        }

        return returnData;
    }

    /**
     * 上传社团展示图片的方法
     *
     * @param files   文件对象，用from-data表单
     * @param request 请求
     * @return 图片文件名
     */
    @ResponseBody
    @RequestMapping(value = "/club/center/info/edit/view", method = RequestMethod.POST)
    public Map<String, Object> uploadView(@RequestParam("file") MultipartFile[] files, HttpServletRequest request,HttpSession session) {

        Map<String, Object> returnData = ImagesUploadTool.uploadImage(files, request, "club//view");

        if (returnData.get("code").toString().equals("0")) {
            //获取图片的名称
            Map<String, String> clubLogoNameMap = (Map<String, String>) returnData.get("data");
            String clubLogoName = clubLogoNameMap.get("clubLogo");

            User user = (User) session.getAttribute("userCode");
            Club club = clubService.getClubByUserId(user.getUserId());
            club.setClubView(clubLogoName);
            //将logo文件名存入数据库
            clubService.updateClubInfo(club);
        }

        return returnData;
    }

    /**
     * 社团用来提交修改社团中心的信息
     *
     * @param clubCenterMsg 社团中心信息
     * @param session       会话
     * @return 是否提交成功
     */
    @ResponseBody
    @RequestMapping(value = "/club/center/info/edit", method = RequestMethod.PUT)
    public Map<String, Object> subNewCenterMsg(@RequestParam Map<String, Object> clubCenterMsg, HttpSession session) {

        //根据用户id从service层得到数据库的用户的实体
        return returnData.getMap();
    }
}
