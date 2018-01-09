package com.fekpal.web.controller.member;

import com.fekpal.domain.ClubMember;
import com.fekpal.domain.Person;
import com.fekpal.domain.User;
import com.fekpal.service.ClubMemberService;
import com.fekpal.service.PersonService;
import com.fekpal.tool.JsonObject;
import com.fekpal.tool.ImagesUploadTool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.Timestamp;
import java.util.*;

/**
 * 普通用户和社团成员端中心的控制类
 * Created by hasee on 2017/8/19.
 */
@Controller
public class MemberCenterController {

    @Autowired
    private PersonService personService;

    @Autowired
    private ClubMemberService clubMemberService;

    /**
     * 得到普通成员和社团成员中心的信息的方法
     *
     * @param session 用户session
     * @return 普通成员或者社团成员的一些基本信息
     */
    @ResponseBody
    @RequestMapping(value = "/member/center/info", method = RequestMethod.GET)
    public Map<String, Object> getMemberCenterMsg(HttpSession session) {

        JsonObject returnData = new JsonObject();
        User user = (User) session.getAttribute("userCode");

        //创建链表map集合存放普通成员中心信息
        Map<String, Object> memberCenterMsg = new LinkedHashMap<>();

        //通过用户ID得到数据
        Person person = personService.getPersonAllInfoByUserId(user.getUserId());

        memberCenterMsg.put("userName", person.getUserName());
        memberCenterMsg.put("realName", person.getRealName());
        memberCenterMsg.put("personLogo", person.getLogo());
        memberCenterMsg.put("studentID", person.getStudentId());
        memberCenterMsg.put("gender", person.getGender());
        memberCenterMsg.put("birthday", new Date(person.getBirthday().getTime()));
        memberCenterMsg.put("phone", person.getPhone());
        memberCenterMsg.put("departmentName", person.getDepartment());
        memberCenterMsg.put("majorName", person.getMajor());
        memberCenterMsg.put("address", person.getAddress());

        //创建社团成员所属社团的list集合
        List<Map<String, Object>> clubsList = new ArrayList<>();
        //获取用户所有参加的社团的信息
        List<ClubMember> list = clubMemberService.getClubMemberByPersonId(person.getPersonId());

        for (ClubMember clubMember : list) {
            Map<String, Object> clubsMap = new LinkedHashMap<>();
            clubsMap.put("clubName", clubMember.getClub().getClubName());
            clubsMap.put("clubDuty", clubMember.getMemberDuty());
            clubsMap.put("userState", clubMember.getUserState());
            clubsList.add(clubsMap);
        }
        memberCenterMsg.put("clubs", clubsList);

        //把用户数据添加到返回数据模板中
        returnData.setData(memberCenterMsg);

        return returnData.getMap();
    }

    /**
     * 上传成员个人头像的方法
     *
     * @param files   文件对象，用from-data表单
     * @param request 请求
     * @return 图片文件名
     */
    @ResponseBody
    @RequestMapping(value = "/member/center/info/edit/head", method = RequestMethod.POST)
    public Map<String, Object> uploadLogo(@RequestParam("file") MultipartFile[] files, HttpServletRequest request, HttpSession session) {

        Map<String, Object> returnData = ImagesUploadTool.uploadImage(files, request, "club//logo");

        if (returnData.get("code").toString().equals("0")) {

            Map<String, String> memberLogoNameMap = (Map<String, String>) returnData.get("data");
            User user = (User) session.getAttribute("userCode");
            Person person = personService.getPersonByUserId(user.getUserId());

            String memberLogoName = memberLogoNameMap.get("clubLogo");
            person.setLogo(memberLogoName);
            personService.updatePerson(person);
        }

        return returnData;
    }

    /**
     * 普通成员或者社团成员用来提交修改个人中心的信息
     *
     * @param memberCenterMsg 个人中心信息
     * @param session         会话
     * @return 是否提交成功
     */
    @ResponseBody
    @RequestMapping(value = "/member/center/info/edit", method = RequestMethod.PUT)
    public Map<String, Object> subNewCenterMsg(@RequestParam Map<String, Object> memberCenterMsg, HttpSession session) {

        String realName = memberCenterMsg.get("realName").toString();
        String studentId = memberCenterMsg.get("studentId").toString();
        int gender = Integer.parseInt(memberCenterMsg.get("gender").toString());
        Date birthday = (Date) memberCenterMsg.get("birthday");
        String phone = memberCenterMsg.get("phone").toString();
        String department = memberCenterMsg.get("departmentName").toString();
        String major = memberCenterMsg.get("major").toString();
        String address = memberCenterMsg.get("address").toString();

        User user = (User) session.getAttribute("userCode");
        Person person = personService.getPersonByUserId(user.getUserId());
        person.setRealName(realName);
        person.setStudentId(studentId);
        person.setGender(gender);
        person.setPhone(phone);
        person.setStudentId(studentId);
        person.setBirthday(new Timestamp(birthday.getTime()));
        person.setMajor(major);
        person.setDepartment(department);
        person.setAddress(address);

        personService.updatePerson(person);
        return new JsonObject().getMap();
    }
}












