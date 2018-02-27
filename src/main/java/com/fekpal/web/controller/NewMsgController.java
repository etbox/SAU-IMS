package com.fekpal.web.controller;

import com.fekpal.api.MessageSendService;
import com.fekpal.common.json.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * 新消息的控制类
 * Created by hasee on 2017/8/22.
 */
@Controller
public class NewMsgController {

    @Autowired
    private MessageSendService messageSendService;

    /**
     * 根据用户id返回全部有效消息
     *
     * @return json
     */
    @ResponseBody
    @RequestMapping(value = "/msg", method = RequestMethod.GET)
    public JsonResult<List> getAllMsg() {

        return null;
    }

    /**
     * 删除该用户的所发的信息
     *
     * @return json
     */
    @ResponseBody
    @RequestMapping(value = "/msg", method = RequestMethod.DELETE)
    public JsonResult<String> deleteMsg() {
        return null;
    }

    /**
     * 根据用户ID和消息id返回某条消息的详细内容信息
     *
     * @return json
     */
    @ResponseBody
    @RequestMapping(value = "/msg/{messageId}", method = RequestMethod.GET)
    public JsonResult<String> getMsgById(@PathVariable Integer messageId) {
        return null;
    }

    /**
     * 根据查找内容从查找相关消息
     *
     * @return json
     */
    @ResponseBody
    @RequestMapping(value = "/msg/search", method = RequestMethod.POST)
    public JsonResult<String> searchMsg() {
        return null;
    }
}
