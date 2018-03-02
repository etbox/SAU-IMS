package com.fekpal.web.controller.sauAdmin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * 校社联的社团信息的控制类
 * Created by hasee on 2017/8/23.
 */
@Controller
public class SauSendCustomMsgController {

    /**
     * 返回社团信息列表的方法
     * @return 社团信息列表
     */
    @ResponseBody
    @RequestMapping(value = "/sau/club",method = RequestMethod.GET)
    public Map<String,Object> getAllClubMsg(){
        return null;
    }


    /**
     * 得到某个社团的详细信息
     * @param clubId 社团ID
     * @return 社团详细信息
     */
    @ResponseBody
    @RequestMapping(value = "/sau/club/{clubId}",method = RequestMethod.GET)
    public Map<String,Object> getOneClubMsg( @PathVariable int clubId){
        return null;
    }

    /**
     * 根据查找内容查找某个社团
     * @param request 请求
     * @param findContent 查找内容
     * @return 查找的社团的列表对象信息
     */
    @ResponseBody
    @RequestMapping(value = "/sau/club/search",method = RequestMethod.GET)
    public Map<String,Object> searchMsg(HttpServletRequest request, @RequestParam String findContent){
        return null;
    }
}
