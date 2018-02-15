package com.fekpal.web.controller;

import com.fekpal.api.MessageSendService;
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
     * 根据用户id返回全部消息或者删除某些消息的方法
     *
     * @return 消息列表内容或者删除成功的标记
     */
    @ResponseBody
    @RequestMapping(value = "/msg", method = {RequestMethod.GET, RequestMethod.DELETE})
    public Map<String, Object> getAllMsg() {
        return null;
    }

    /**
     * 根据用户ID和消息id返回某条消息的详细内容信息
     *
     * @return 某条消息的详细内容
     */
    @ResponseBody
    @RequestMapping(value = "/msg/{messageId}", method = RequestMethod.GET)
    public Map<String, Object> getMsgById(@PathVariable Integer messageId) {
        return null;
    }

    /**
     * 根据查找内容从数据库中查找相关消息
     *
     * @param request     请求
     * @param findContent 查找内容
     * @return 消息列表
     */
    @ResponseBody
    @RequestMapping(value = "/msg/search", method = RequestMethod.GET)
    public Map<String, Object> searchMsg(HttpServletRequest request, @RequestParam String findContent) {
        return null;
    }
}
