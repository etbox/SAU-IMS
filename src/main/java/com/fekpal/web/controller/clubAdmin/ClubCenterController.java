package com.fekpal.web.controller.clubAdmin;

import com.fekpal.api.ClubService;
import com.fekpal.common.json.JsonObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
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
     * @return 社团的一些基本信息
     */
    @ResponseBody
    @RequestMapping("/club/center/info")
    public Map<String, Object> getClubsCenterMsg() {
       return null;
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
    public Map<String, Object> uploadLogo(@RequestParam("file") MultipartFile[] files, HttpServletRequest request) {
        return null;
    }

    /**
     * 上传社团展示图片的方法
     *
     * @param files   文件对象，用from-data表单
     * @return 图片文件名
     */
    @ResponseBody
    @RequestMapping(value = "/club/center/info/edit/view", method = RequestMethod.POST)
    public Map<String, Object> uploadView(@RequestParam("file") MultipartFile[] files, HttpServletRequest request) {
        return null;
    }

    /**
     * 社团用来提交修改社团中心的信息
     *
     * @param clubCenterMsg 社团中心信息
     * @return 是否提交成功
     */
    @ResponseBody
    @RequestMapping(value = "/club/center/info/edit", method = RequestMethod.PUT)
    public Map<String, Object> subNewCenterMsg(@RequestParam Map<String, Object> clubCenterMsg, HttpSession session) {
        return null;
    }
}
