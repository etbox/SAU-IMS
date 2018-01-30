package com.fekpal.web.controller;

import com.fekpal.cons.AvailableState;
import com.fekpal.cons.ResponseCode;
import com.fekpal.domain.pojo.Message;
import com.fekpal.domain.pojo.MessageRelease;
import com.fekpal.domain.pojo.User;
import com.fekpal.domain.json.NewMsgListDomain;
import com.fekpal.service.MessageService;
import com.fekpal.tool.JsonObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.*;

import static java.lang.System.out;

/**
 * 新消息的控制类
 * Created by hasee on 2017/8/22.
 */
@Controller
public class NewMsgController {

    @Autowired
    private MessageService messageService;

    @Autowired
    private JsonObject returnData;

    /**
     * 根据用户id返回全部消息或者删除某些消息的方法
     *
     * @param session  会话
     * @param request  请求
     * @param msgIdMap 消息的要删除的消息idMap集合
     * @return 消息列表内容或者删除成功的标记
     */
    @ResponseBody
    @RequestMapping(value = "/msg", method = {RequestMethod.GET, RequestMethod.DELETE})
    public Map<String, Object> getAllMsg(HttpSession session, HttpServletRequest request, @RequestParam(required = false) Map<String, Object> msgIdMap) {

        //获取用户信息
        User user = (User) session.getAttribute("userCode");
        //获取用户id
        int userId = user.getUserId();

        //如果请求是get，获得全部消息
        if ("GET".equals(request.getMethod())) {

            returnData = new JsonObject();
            //初始化用户id和返回的消息列表对象
            List<NewMsgListDomain> list = new ArrayList<>();

            out.println("从数据库中获取得到所有消息，和用户id是；" + userId);
            List<MessageRelease> messages = messageService.getRvcMessagesByRcvId(userId, 0, 50);
            //如果得到的消息列表为空，直接返回
            if (messages.isEmpty()) {
                return returnData.getMap();
            }

            NewMsgListDomain newMsgListDomain;
            //模拟将消息对象中的某些属性提取出来，放入返回数据的list集合中
            for (MessageRelease release : messages) {
                newMsgListDomain = new NewMsgListDomain();
                newMsgListDomain.setMessageId(release.getMessage().getMessageId());
                newMsgListDomain.setMessageTitle(release.getMessage().getMessageTitle());
                newMsgListDomain.setReadFlag(release.getReadFlag());
                newMsgListDomain.setSendName(release.getMessage().getReleaseName());
                newMsgListDomain.setSendTime(new Date(release.getMessage().getReleaseTime().getTime()));
                list.add(newMsgListDomain);
            }

            returnData.setData(list);
            return returnData.getMap();

            //如果请求是delete，删除某些消息

        } else if ("DELETE".equals(request.getMethod())) {
            //初始化装要删除消息id的list集合
            List<Integer> messages = new ArrayList<>();

            for (int i = 0; i < msgIdMap.size() - 1; i++) {
                //组装map集合的键
                String keyId = "deleteMsgIds" + "[" + i + "]" + "[" + "deleteMsgId" + "]";
                //将得到的msgId值放入list集合
                messages.add(Integer.parseInt(msgIdMap.get(keyId).toString()));
            }
            //根据userId和消息id在数据库中做相应删除操作
            MessageRelease release = new MessageRelease();
            release.setReceiveId(userId);
            release.setAvailable(AvailableState.UNAVAIABLE);
            messageService.updateRcvMessage(release, messages);
            out.println("用户id为：" + userId + "。要删除的id是:(list)" + messages);
        }

        return returnData.getMap();
    }

    /**
     * 根据用户ID和消息id返回某条消息的详细内容信息
     *
     * @param messageId 消息id
     * @param session   会话
     * @return 某条消息的详细内容
     */
    @ResponseBody
    @RequestMapping(value = "/msg/{messageId}", method = RequestMethod.GET)
    public Map<String, Object> getMsgById(@PathVariable int messageId, HttpSession session) {

        //获取用户信息
        User user = (User) session.getAttribute("userCode");

        //用来装具体消息细节的map集合
        Map<String, Object> msgDetailMap = new LinkedHashMap<>();

        //获取用户id
        int userId = user.getUserId();

        if (messageId <= 0) {
            returnData.setStateCode(ResponseCode.REQUEST_ERROR, "消息id不合法");
            return returnData.getMap();
        }

        //根据用户id和消息id从从dao中得到用户消息对象
        out.println("根据用户id和消息id从从dao中得到用户消息对象" + "用户id：" + userId + "消息id：" + messageId);
        MessageRelease release = messageService.getRcvMessageByReleaseId(messageId);
        //将消息对象的某些属性提前出来放到返回的消息对象中
        msgDetailMap.put("messagedId", release.getId());
        msgDetailMap.put("messageTitle", release.getMessage().getMessageTitle());
        msgDetailMap.put("senderName", release.getMessage().getReleaseName());
        msgDetailMap.put("sendTime", new Date(release.getMessage().getReleaseTime().getTime()));
        msgDetailMap.put("messageContent", release.getMessage().getMessageContent());
        returnData.setData(msgDetailMap);

        return returnData.getMap();
    }

    /**
     * 根据查找内容从数据库中查找相关消息
     *
     * @param request     请求
     * @param session     会话
     * @param findContent 查找内容
     * @return 消息列表
     */
    @ResponseBody
    @RequestMapping(value = "/msg/search", method = RequestMethod.GET)
    public Map<String, Object> searchMsg(HttpServletRequest request, HttpSession session, @RequestParam String findContent) {

        User user = (User) session.getAttribute("userCode");

        //如果查找内容为空的话，则查询全部消息
        if (StringUtils.isEmpty(findContent.trim())) {
            return getAllMsg(session, request, null);
        }

        int userId = user.getUserId();
        //根据用户id和查找内容，从数据库中查找相关消息，只要查找标题就可以了,得到相关消息的对象
        out.println("用户id" + userId + "。要查找的内容：" + findContent);

        //初始化消息列表对象
        List<NewMsgListDomain> list = new ArrayList<>();
        MessageRelease release = new MessageRelease();
        Message message = new Message();
        release.setReceiveId(userId);
        message.setMessageTitle(findContent);
        release.setMessage(message);

        List<MessageRelease> messages = messageService.findRcvMessageByTitle(release, 0, 50);

        //模拟从对象中获取得到相关消息列表对象
        NewMsgListDomain newMsgListDomain;
        for (MessageRelease mess : messages) {
            newMsgListDomain = new NewMsgListDomain();
            newMsgListDomain.setMessageId(mess.getId());
            newMsgListDomain.setMessageTitle(mess.getMessage().getMessageTitle());
            newMsgListDomain.setReadFlag(mess.getReadFlag());
            newMsgListDomain.setSendName(mess.getMessage().getReleaseName());
            newMsgListDomain.setSendTime(new Date(mess.getMessage().getReleaseTime().getTime()));
            list.add(newMsgListDomain);
        }
        returnData.setData(list);

        return returnData.getMap();
    }
}
