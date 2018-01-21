package com.fekpal.web.controller.clubAdmin;

import com.fekpal.cons.MessageType;
import com.fekpal.cons.AvailableState;
import com.fekpal.domain.*;
import com.fekpal.domain.json.ClubPublishedNewMsg;
import com.fekpal.service.*;
import com.fekpal.tool.JsonObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.sql.Timestamp;
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
     * @param session 用户会话
     * @return 发布对象即社团对象
     */
    @ResponseBody
    @RequestMapping(value = "/club/members", method = RequestMethod.GET)
    public Map<String, Object> getMassageType(HttpSession session) {

        User user = (User) session.getAttribute("userCode");
        Club club = clubService.getClubByUserId(user.getUserId());

        List<Person> lists = clubMemberService.getAllMemberByClubId(club.getClubId());

        //创建存放返回发布对象的list集合
        List<Map<String, Object>> clubObjList = new ArrayList<>();

        for (Person person : lists) {
            Map<String, Object> clubObjMap = new LinkedHashMap<>();
            clubObjMap.put("userId", person.getPersonId());
            clubObjMap.put("realName", person.getRealName());
            clubObjList.add(clubObjMap);
        }

        //将社团对象数据放入返回数据中并返回
        returnData.setData(clubObjList);
        return returnData.getMap();
    }


    /**
     * 根据用户id返回该用户之前发布的消息
     *
     * @param session 用户会话
     * @return 历史发布消息信息
     */
    @ResponseBody
    @RequestMapping(value = "/club/msg/old", method = RequestMethod.GET)
    public Map<String, Object> getAllOldMsg(HttpSession session) {

        User user = (User) session.getAttribute("userCode");
        List<Message> lists = messageService.getSendMessageByUserId(user.getUserId(), 0, 50);

        //创建消息列表的list集合，并将消息装入list集合，并返回
        List<Map<String, Object>> msgList = new ArrayList<>();

        for (Message message : lists) {

            if (message.getMessageState() == AvailableState.AVAILABLE) {
                Map<String, Object> msgMap = new LinkedHashMap<>();
                msgMap.put("messageId", message.getMessageId());
                msgMap.put("messageTitle", message.getMessageTitle());
                msgMap.put("sendTime", new Date(message.getReleaseTime().getTime()));
                msgMap.put("messageType", message.getMessageType());
                msgList.add(msgMap);
            }
        }

        returnData.setData(msgList);
        return returnData.getMap();
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

        Message message = messageService.getSendMessageByMessageId(messageId);

        //创建发布对象的map集合
        Map<String, Object> publishedObjMap = new LinkedHashMap<>();
        publishedObjMap.put("userId", message.getUserId());
        publishedObjMap.put("realName", message.getReleaseName());

        //创建发布对象的list集合，并将每个map集合的发布对象放入list中
        List<Map<String, Object>> publishedObjList = new ArrayList<>();
        publishedObjList.add(publishedObjMap);

        //将某条信息的map集合放入返回数据中
        Map<String, Object> msgMap = new LinkedHashMap<>();
        msgMap.put("messageId", message.getMessageId());
        msgMap.put("messageTitle", message.getMessageTitle());
        msgMap.put("messageContent", message.getMessageContent());
        msgMap.put("sendTime", new Date(message.getReleaseTime().getTime()));
        msgMap.put("messageType", message.getMessageType());
        msgMap.put("publishedObject", publishedObjList);
        returnData.setData(msgMap);

        return returnData.getMap();
    }

    /**
     * 社团发布消息给全部成员
     *
     * @param newMsg 消息对象
     * @return 是否发送成功
     */
    @ResponseBody
    @RequestMapping(value = "/club/msg/new/all", method = RequestMethod.POST)
    public Map<String, Object> sendMsgToAll(@RequestBody ClubPublishedNewMsg newMsg, HttpSession session) {

        User user = (User) session.getAttribute("userCode");
        Club club = clubService.getClubByUserId(user.getUserId());

        Message message = new Message();
        message.setMessageTitle(newMsg.getMessageTitle());
        message.setMessageContent(newMsg.getMessageContent());
        message.setReleaseTime(new Timestamp(newMsg.getSendTime().getTime()));
        message.setUserId(user.getUserId());
        message.setReleaseName(club.getClubName());
        message.setMessageType(MessageType.ALL);

        //将所有系统用户视为收件人
        List<User> lists = userService.loadAllUser();
        List<Integer> releases = new ArrayList<>();

        for (User revUser : lists) {
            releases.add(revUser.getUserId());
        }

        //发布信息
        messageService.addNewSendMessage(message, releases);

        return returnData.getMap();
    }

    /**
     * 发送消息个给本社团内的人
     *
     * @param newMsg  消息对象
     * @param session 会话
     * @return 是否成功
     */
    @ResponseBody
    @RequestMapping(value = "/club/msg/new/group", method = RequestMethod.POST)
    public Map<String, Object> sendMsgToGroup(@RequestBody ClubPublishedNewMsg newMsg, HttpSession session) {

        User user = (User) session.getAttribute("userCode");
        Club club = clubService.getClubByUserId(user.getUserId());

        Message message = new Message();
        message.setMessageTitle(newMsg.getMessageTitle());
        message.setMessageContent(newMsg.getMessageContent());
        message.setReleaseTime(new Timestamp(newMsg.getSendTime().getTime()));
        message.setUserId(user.getUserId());
        message.setReleaseName(club.getClubName());
        message.setMessageType(MessageType.ALL);

        //将所有社员视为收件人
        List<Person> lists = clubMemberService.getAllMemberByClubId(club.getClubId());
        List<Integer> releases = new ArrayList<>();

        for (User revUser : lists) {
            releases.add(revUser.getUserId());
        }

        //发布信息
        messageService.addNewSendMessage(message, releases);

        return returnData.getMap();
    }

    /**
     * 发送消息给自定义对象
     *
     * @param newMsg  消息对象
     * @param session 会话
     * @return 是否成功
     */
    @ResponseBody
    @RequestMapping(value = "/club/msg/new/person", method = RequestMethod.POST)
    public Map<String, Object> sendMsgToPerson(@RequestBody ClubPublishedNewMsg newMsg, HttpSession session) {

        User user = (User) session.getAttribute("userCode");
        Club club = clubService.getClubByUserId(user.getUserId());

        Message message = new Message();
        message.setMessageTitle(newMsg.getMessageTitle());
        message.setMessageContent(newMsg.getMessageContent());
        message.setReleaseTime(new Timestamp(newMsg.getSendTime().getTime()));
        message.setUserId(user.getUserId());
        message.setReleaseName(club.getClubName());
        message.setMessageType(MessageType.ALL);

        //创建用来放发布对象的id的list集合
        List<Map<String, Integer>> publishObject = newMsg.getPublishedObject();
        List<Integer> releases = new ArrayList<>();

        //遍历发布id的list集合，从map集合中得到用户id，并将它放入到id的list集合中
        for (Map<String, Integer> userIdMap : publishObject) {
            releases.add(userIdMap.get("userId"));
        }

        //根据用户id将消息发布给相应的用户
        messageService.addNewSendMessage(message, releases);

        return returnData.getMap();
    }

    /**
     * 根据消息id删除历史发布消息
     *
     * @param deleteMsgIdMap 要删除的消息的id
     * @return 是否成功
     */
    @ResponseBody
    @RequestMapping(value = "/club/msg/old", method = RequestMethod.DELETE)
    public Map<String, Object> deleteMsg(@RequestParam Map<String, Object> deleteMsgIdMap, HttpSession session) {

        User user = (User) session.getAttribute("userCode");
        Message message = new Message();
        message.setMessageState(AvailableState.UNAVAIABLE);
        message.setUserId(user.getUserId());

        //从要删除的消息id的map集合中得到要删除的消息id，并将它们放入list集合中

        for (int i = 0; i < deleteMsgIdMap.size(); i++) {
            //组装map集合的键
            String keyId = "deleteMsgIds" + "[" + i + "]" + "[" + "messageId" + "]";

            //根据userId和消息id在数据库中做相应删除操作
            message.setMessageId(Integer.parseInt(deleteMsgIdMap.get(keyId).toString()));
            messageService.updateSendMessage(message);
        }

        return returnData.getMap();
    }

    /**
     * 根据查找内容查找历史发布消息
     *
     * @param findContent 查找内容
     * @return 返回消息列表
     */
    @ResponseBody
    @RequestMapping(value = "/club/msg/old/search", method = RequestMethod.GET)
    public Map<String, Object> searchMsg(@RequestParam String findContent, HttpSession session) {

        User user = (User) session.getAttribute("userCode");

        Message findMessage = new Message();
        findMessage.setMessageTitle(findContent);
        findMessage.setUserId(user.getUserId());

        //根据发布人id和查找关键字来获取消息列表
        List<Message> lists = messageService.findSendMessageByTitle(findMessage, 0, 50);

        //创建消息列表的list集合，并将消息装入list集合，并返回
        List<Map<String, Object>> msgList = new ArrayList<>();
        for (Message message : lists) {
            if (message.getMessageState() == AvailableState.AVAILABLE) {
                Map<String, Object> msgMap = new LinkedHashMap<>();
                msgMap.put("messageId", message.getMessageId());
                msgMap.put("messageTitle", message.getMessageTitle());
                msgMap.put("messageType", message.getMessageType());
                msgMap.put("sendTime", new Date(message.getReleaseTime().getTime()));
                msgList.add(msgMap);
            }
        }

        returnData.setData(msgList);
        return returnData.getMap();
    }
}
