package com.fekpal.web.controller.sauAdmin;

import com.fekpal.web.model.SauPublishedNewMsg;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/**
 * 消息发布的控制类
 * Created by hasee on 2017/8/26.
 */
@Controller
public class SauMsgPublicController {

    /**
     * 根据用户id和消息类型，返回发布对象
     * @param messageType 消息类型
     * @return 发布对象即社团对象
     */
    @ResponseBody
    @RequestMapping(value = "/sau/clubs",method = RequestMethod.GET)
    public Map<String,Object> getMassageType(@RequestParam("messageType")int messageType){
        return null;
    }


    /**
     * 根据用户id返回该用户之前发布的消息
     * @return 历史发布消息信息
     */
    @ResponseBody
    @RequestMapping(value = "/sau/msg/old",method = RequestMethod.GET)
    public Map<String,Object> getAllOldMsg(){
        return null;
    }

    /**
     * 根据历史发布消息id返回消息详细内容的方法
     * @param messageId 消息id
     * @return 某条历史发布消息的详细信息
     */
    @ResponseBody
    @RequestMapping(value = "/sau/msg/old/{messageId}",method = RequestMethod.GET)
    public Map<String,Object> getOneOldMsg (@PathVariable int messageId){
        return null;
    }

    /**
     * 校社联发布消息给全部成员
     * @param newMsg 消息对象
     * @return 是否发送成功
     */
    @ResponseBody
    @RequestMapping(value = "/sau/msg/new/all",method = RequestMethod.POST)
    public Map<String,Object> sendMsgToAll (@RequestBody SauPublishedNewMsg newMsg) {
        return null;
    }

    /**
     * 发送消息个给本社团内的人
     * @param newMsg 消息对象
     * @return 是否成功
     */
    @ResponseBody
    @RequestMapping(value = "/sau/msg/new/group",method = RequestMethod.POST)
    public Map<String,Object> sendMsgToGroup (@RequestBody SauPublishedNewMsg newMsg) {
        return null;
    }

    /**
     * 发送消息给自定义对象
     * @param newMsg 消息对象
     * @return 是否成功
     */
    @ResponseBody
    @RequestMapping(value = "/sau/msg/new/person",method = RequestMethod.POST)
    public Map<String,Object> sendMsgToPerson (@RequestBody SauPublishedNewMsg newMsg) {
        return null;
    }

    /**
     * 根据消息id删除历史发布消息
     * @param deleteMsgIdMap 要删除的消息的id
     * @return 是否成功
     */
    @ResponseBody
    @RequestMapping(value = "/sau/msg/old",method = RequestMethod.DELETE)
    public Map<String,Object> deleteMsgs (@RequestParam Map<String,Object> deleteMsgIdMap) {
        return null;
    }

    /**
     *根据查找内容查找消息
     * @param findContent 查找内容
     * @return 返回消息列表
     */
    @ResponseBody
    @RequestMapping(value = "/sau/msg/old/search",method = RequestMethod.GET)
    public Map<String,Object> searchMsg (@RequestParam String findContent) {
        return null;
    }
}
