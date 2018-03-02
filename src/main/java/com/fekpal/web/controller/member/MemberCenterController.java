package com.fekpal.web.controller.member;

import com.fekpal.api.PersonService;
import com.fekpal.common.json.JsonResult;
import com.fekpal.service.model.domain.PersonMsg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

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
    //private OrgMemberService clubMemberService;

    /**
     * 得到普通成员和社团成员中心的信息的方法
     *
     * @return 普通成员或者社团成员的一些基本信息
     */
    @ResponseBody
    @RequestMapping(value = "/member/center/info", method = RequestMethod.GET)
    public JsonResult<List> getMemberCenterMsg() {

        return null;
    }

    /**
     * 上传成员个人头像的方法
     *
     * @param msg 普通用户信息封装
     * @return 图片文件名
     */
    @ResponseBody
    @RequestMapping(value = "/member/center/info/head", method = RequestMethod.POST)
    public JsonResult<String> uploadLogo(PersonMsg msg) {

        return null;
    }

    /**
     * 普通成员或者社团成员用来提交修改个人中心的信息
     *
     * @param msg 普通用户信息封装
     * @return 是否提交成功
     */
    @ResponseBody
    @RequestMapping(value = "/member/center/info", method = RequestMethod.PUT)
    public Map<String, Object> subNewCenterMsg(PersonMsg msg) {

        return null;
    }
}












