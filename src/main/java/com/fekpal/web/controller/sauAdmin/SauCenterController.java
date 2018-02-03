package com.fekpal.web.controller.sauAdmin;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * 校社联中心信息的控制类
 * Created by hasee on 2017/8/19.
 */
@Controller
public class SauCenterController {

    /**
     * 上传校社联头像的方法
     * @param files 文件对象，用from-data表单
     * @param request 请求
     * @return 图片文件名
     */
    @ResponseBody
    @RequestMapping(value = "/sau/center/info/edit/head",method = RequestMethod.POST)
    public Map<String,Object> uploadLogo(@RequestParam("file") MultipartFile[] files,HttpServletRequest request){
        return null;
    }

    /**
     * 得到校社联中心的信息的方法
     * @return 校社联的一些基本信息
     */
    @ResponseBody
    @RequestMapping(value = "/sau/center/info",method = RequestMethod.GET)
    public Map<String,Object> getSauCenterMsg(){
        return null;
    }

    /**
     * 校社联用来提交修改校社联中心的信息
     * @param sauCenterMsg 校社联中心信息
     * @param request 请求
     * @return 是否提交成功
     */
    @ResponseBody
    @RequestMapping(value = "/sau/center/info/edit",method = RequestMethod.PUT)
    public Map<String,Object> subNewCenterMsg(@RequestParam Map<String,Object> sauCenterMsg,HttpServletRequest request){
        return null;
    }
}
