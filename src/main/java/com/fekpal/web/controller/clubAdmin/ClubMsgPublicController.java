package com.fekpal.web.controller.clubAdmin;

import com.fekpal.api.ClubMemberService;
import com.fekpal.api.ClubService;
import com.fekpal.api.MessageService;
import com.fekpal.api.UserService;
import com.fekpal.web.model.ClubPublishedNewMsg;
import com.fekpal.common.json.JsonObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/**
 * 社团的消息发布的控制类
 * Created by hasee on 2017/8/26.
 */
@Controller
public class ClubMsgPublicController {

    @Autowired
    private MessageService messageService;

    @Autowired
    private ClubMemberService clubMemberService;

    @Autowired
    private ClubService clubService;

    @Autowired
    private UserService userService;

    @Autowired
    private JsonObject returnData;


    /**
     * 根据发布信息类型选择接收人的范围
     *
     * @return 发布对象即社团对象
     */
    @ResponseBody
    @RequestMapping(value = "/club/members", method = RequestMethod.GET)
    public Map<String, Object> getMassageType() {

        return null;
    }


    /**
     * 根据用户id返回该用户之前发布的消息
     *
     * @return 历史发布消息信息
     */
    @ResponseBody
    @RequestMapping(value = "/club/msg/old", method = RequestMethod.GET)
    public Map<String, Object> getAllOldMsg() {

        return null;
    }

    /**
     * 根据历史发布消息id返回消息详细内容的方法
     *
     * @param messageId 消息id
     * @return 某条历史发布消息的详细信息
     */
    @ResponseBody
    @RequestMapping(value = "/club/msg/old/{messageId}", method = RequestMethod.GET)
    public Map<String, Object> getOneOldMsg(@PathVariable int messageId) {

        return null;
    }

    /**
     * 社团发布消息给全部成员
     *
     * @param newMsg 消息对象
     * @return 是否发送成功
     */
    @ResponseBody
    @RequestMapping(value = "/club/msg/new/all", method = RequestMethod.POST)
    public Map<String, Object> sendMsgToAll(@RequestBody ClubPublishedNewMsg newMsg) {

        return null;
    }

    /**
     * 发送消息个给本社团内的人
     *
     * @param newMsg  消息对象
     * @return 是否成功
     */
    @ResponseBody
    @RequestMapping(value = "/club/msg/new/group", method = RequestMethod.POST)
    public Map<String, Object> sendMsgToGroup(@RequestBody ClubPublishedNewMsg newMsg) {

        return null;
    }

    /**
     * 发送消息给自定义对象
     *
     * @param newMsg  消息对象
     * @return 是否成功
     */
    @ResponseBody
    @RequestMapping(value = "/club/msg/new/person", method = RequestMethod.POST)
    public Map<String, Object> sendMsgToPerson(@RequestBody ClubPublishedNewMsg newMsg) {

        return null;
    }

    /**
     * 根据消息id删除历史发布消息
     *
     * @param deleteMsgIdMap 要删除的消息的id
     * @return 是否成功
     */
    @ResponseBody
    @RequestMapping(value = "/club/msg/old", method = RequestMethod.DELETE)
    public Map<String, Object> deleteMsg(@RequestParam Map<String, Object> deleteMsgIdMap) {

        return null;
    }

    /**
     * 根据查找内容查找历史发布消息
     *
     * @param findContent 查找内容
     * @return 返回消息列表
     */
    @ResponseBody
    @RequestMapping(value = "/club/msg/old/search", method = RequestMethod.GET)
    public Map<String, Object> searchMsg(@RequestParam String findContent) {

        return null;
    }
}
