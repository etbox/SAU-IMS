package com.fekpal.web.controller.clubAdmin;

import com.fekpal.api.*;
import com.fekpal.common.constant.Operation;
import com.fekpal.common.constant.ResponseCode;
import com.fekpal.common.json.JsonResult;
import com.fekpal.common.utils.TimeUtil;
import com.fekpal.dao.model.Message;
import com.fekpal.dao.model.Org;
import com.fekpal.dao.model.Person;
import com.fekpal.service.model.domain.SRMsgRecord;
import com.fekpal.web.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.*;

/**
 * 社团的消息发布的控制类
 * @author kanlon
 * @time 2018/4/8
 */
@Controller
public class ClubSendPublishMsgController {

    @Autowired
    private MessageSendService messageSendService;

    @Autowired
    private OrgMemberService orgMemberService;

    @Autowired
    private ClubService clubService;

    @Autowired
    private UserService userService;

    @Autowired
    PersonService personService;


    /**
     * 根据发布信息类型选择接收人的范围
     *
     * @return 发布对象即社团对象
     */
    @ResponseBody
    @RequestMapping(value = "/club/members", method = RequestMethod.GET)
    public JsonResult<List<ClubCustomSendObj>> getMassageType(@RequestParam int messageType) {
        JsonResult<List<ClubCustomSendObj>> result = new JsonResult<>();
        List<ClubCustomSendObj> objList = new ArrayList<>();
        List<Person> personList = clubService.getClubAllMemberPersonMsg();
        if(personList == null || personList.size()==0){result.setStateCode(ResponseCode.REQUEST_ERROR,"无结果");return  result;}

        for(Person person : personList){
            ClubCustomSendObj obj = new ClubCustomSendObj();
            obj.setUserId(person.getUserId());
            obj.setRealName(person.getRealName());
            objList.add(obj);
        }
        result.setCode(ResponseCode.RESPONSE_SUCCESS);
        result.setData(objList);
        return result;
    }


    /**
     * 根据用户id返回该用户之前发布消息的详细内容
     *
     * @return 历史发布消息信息
     */
    @ResponseBody
    @RequestMapping(value = "/club/msg/old", method = RequestMethod.GET)
    public JsonResult<List<OldPublishMsg>> getAllOldMsg(PageList page) {

        //将前端发送过来的页码offset，转化为跳过数offset
        if(page!=null){page.setOffset((page.getOffset()-1)*page.getLimit());}

        List<Message> messageList = messageSendService.loadAllMessage(page.getOffset(),page.getLimit());
        JsonResult<List<OldPublishMsg>> result = new JsonResult<>();
        List<OldPublishMsg> oldPublishMsgList = new ArrayList<>();
        if (messageList == null || messageList.size() == 0) {
            result.setStateCode(ResponseCode.RESPONSE_ERROR, "无结果");
            return result;
        }
        for(Message message:messageList){
            OldPublishMsg oldPublishMsg = new OldPublishMsg();
            oldPublishMsg.setMessageId(message.getMessageId());
            oldPublishMsg.setMessageTitle(message.getMessageTitle());
            oldPublishMsg.setMessageType(message.getMessageType());
            oldPublishMsg.setSendTime(message.getReleaseTime());
            oldPublishMsgList.add(oldPublishMsg);
        }
        result.setCode(ResponseCode.RESPONSE_SUCCESS);
        result.setData(oldPublishMsgList);
        return result;
    }

    /**
     * 根据历史发布消息id返回消息详细内容的方法
     *
     * @param messageId 消息id
     * @return 某条历史发布消息的详细信息
     */
    @ResponseBody
    @RequestMapping(value = "/club/msg/old/{messageId}", method = RequestMethod.GET)
    public JsonResult<SauMsgPublishDetail> getOneOldMsg(@PathVariable int messageId) {
        Message message =  messageSendService.selectByMessageId(messageId);
        JsonResult<SauMsgPublishDetail> result = new JsonResult<>();
        if (message == null) {
            result.setStateCode(ResponseCode.RESPONSE_ERROR, "无结果");
            return result;
        }
        SauMsgPublishDetail sauMsgPublishDetail = new SauMsgPublishDetail();
        sauMsgPublishDetail.setMessageId(message.getMessageId());
        sauMsgPublishDetail.setMessageContent(message.getMessageContent());
        sauMsgPublishDetail.setMessageTitle(message.getMessageTitle());
        sauMsgPublishDetail.setMessageType(message.getMessageType());
        //暂时看不了发送了给那些对象
        sauMsgPublishDetail.setPublishedObject(null);
        sauMsgPublishDetail.setSendTime(message.getReleaseTime());

        result.setCode(ResponseCode.RESPONSE_SUCCESS);
        result.setData(sauMsgPublishDetail);
        return result;
    }

    /**
     * 社团发布消息给全部成员
     *
     * @param newMsg 消息对象
     * @return 是否发送成功
     */
    @ResponseBody
    @RequestMapping(value = "/club/msg/new/all", method = RequestMethod.POST)
    public JsonResult<String> sendMsgToAll(@RequestBody ClubPublishedNewMsg newMsg) {
        JsonResult<String> result = new JsonResult<>();
        SRMsgRecord sRMsgRecord = new SRMsgRecord();
        sRMsgRecord.setMessageTitle(newMsg.getMessageTitle());
        sRMsgRecord.setMessageContent(newMsg.getMessageContent());
        sRMsgRecord.setReleaseTime(new Timestamp(TimeUtil.currentTime()));
        int state = messageSendService.sendGlobalMessage(sRMsgRecord);
        if(state == Operation.SUCCESSFULLY){
            result.setStateCode(ResponseCode.RESPONSE_SUCCESS,"发送成功");
        }else if(state == Operation.FAILED){
            result.setStateCode(ResponseCode.RESPONSE_ERROR,"发送失败");
        }
        return result;
    }

    /**
     * 发送消息个给本社团内的人
     *
     * @param newMsg  消息对象
     * @return 是否成功
     */
    @ResponseBody
    @RequestMapping(value = "/club/msg/new/group", method = RequestMethod.POST)
    public JsonResult<String> sendMsgToGroup(@RequestBody ClubPublishedNewMsg newMsg) {
        JsonResult<String> result = new JsonResult<>();
        SRMsgRecord sRMsgRecord = new SRMsgRecord();
        sRMsgRecord.setMessageTitle(newMsg.getMessageTitle());
        sRMsgRecord.setMessageContent(newMsg.getMessageContent());
        sRMsgRecord.setReleaseTime(new Timestamp(TimeUtil.currentTime()));
        int state = messageSendService.sendOrgMessage(sRMsgRecord);
        if(state == Operation.SUCCESSFULLY){
            result.setStateCode(ResponseCode.RESPONSE_SUCCESS,"发送成功");
        }else if(state == Operation.FAILED){
            result.setStateCode(ResponseCode.RESPONSE_ERROR,"发送失败");
        }
        return result;
    }

    /**
     * 发送消息给自定义对象
     *
     * @param newMsg  消息对象
     * @return 是否成功
     */
    @ResponseBody
    @RequestMapping(value = "/club/msg/new/person", method = RequestMethod.POST)
    public JsonResult<String> sendMsgToPerson(@RequestBody ClubPublishedNewMsg newMsg) {
        JsonResult<String> result = new JsonResult<>();
        SRMsgRecord sRMsgRecord = new SRMsgRecord();
        sRMsgRecord.setMessageTitle(newMsg.getMessageTitle());
        sRMsgRecord.setMessageContent(newMsg.getMessageContent());
        sRMsgRecord.setReleaseTime(new Timestamp(TimeUtil.currentTime()));
        List<Integer> orgIdsList = new ArrayList<>();
        String msgPublishedObject = newMsg.getPublishedObject();
        if(msgPublishedObject== null){
            result.setStateCode(ResponseCode.RESPONSE_ERROR,"要发送的对象为空");
            return result;
        }
        //获取发布对象中的用户id
        String[] msgPublishObjectStrs = msgPublishedObject.split(",");
        for(int i=0;i<msgPublishObjectStrs.length;i++){
            orgIdsList.add(Integer.parseInt(msgPublishObjectStrs[i]));
        }
        sRMsgRecord.setReceives(orgIdsList);
        int state = messageSendService.sendCustomMessage(sRMsgRecord);
        if(state == Operation.SUCCESSFULLY){
            result.setStateCode(ResponseCode.RESPONSE_SUCCESS,"发送成功");
        }else if(state == Operation.FAILED){
            result.setStateCode(ResponseCode.RESPONSE_ERROR,"发送失败");
        }
        return result;
    }

    /**
     * 根据消息id删除历史发布消息
     *
     * @param deleteMsgIdsModel 要删除的消息的id
     * @return 是否成功
     */
    @ResponseBody
    @RequestMapping(value = "/club/msg/old", method = RequestMethod.DELETE)
    public JsonResult<String> deleteMsg(@RequestBody DeleteMsgIdsModel deleteMsgIdsModel) {
        JsonResult<String> result = new JsonResult<>();
        if(deleteMsgIdsModel.getDeleteMsgIds()==null){
            result.setStateCode(ResponseCode.REQUEST_ERROR,"要删除的消息为空，不能执行");
            return result;
        }
        String[] deleteMsgIdsStr = deleteMsgIdsModel.getDeleteMsgIds().split(",");
        List<Integer> deleteMsgIdsList = new ArrayList<>();
        for(int i=0;i<deleteMsgIdsStr.length;i++){
            deleteMsgIdsList.add(Integer.parseInt(deleteMsgIdsStr[i]));
        }
        SRMsgRecord srMsgRecord = new SRMsgRecord();
        srMsgRecord.setIds(deleteMsgIdsList);
        int state = messageSendService.deleteByMessageId(srMsgRecord);
        if(state == Operation.SUCCESSFULLY ){
            result.setStateCode(ResponseCode.RESPONSE_SUCCESS,"删除成功");
        }else{
            result.setStateCode(ResponseCode.REQUEST_ERROR,"删除失败");
        }
        return result;
    }

    /**
     * 根据查找内容查找历史发布消息
     *
     * @param page 查找内容
     * @return 返回消息列表
     */
    @ResponseBody
    @RequestMapping(value = "/club/msg/old/search", method = RequestMethod.GET)
    public JsonResult<List<OldPublishMsg>> searchMsg(SearchPage page) {
        //将前端发送的页码offset，转化为跳过条数offset
        if(page!=null){page.setOffset((page.getOffset()-1)*page.getLimit());}

        List<Message> messageList = messageSendService.queryByMessageTitle(page.getFindContent(),page.getOffset(),page.getLimit());
        JsonResult<List<OldPublishMsg>> result = new JsonResult<>();
        List<OldPublishMsg> oldPublishMsgList = new ArrayList<>();
        if (messageList == null || messageList.size() == 0) {
            result.setStateCode(ResponseCode.RESPONSE_ERROR, "无结果");
            return result;
        }
        for(Message message:messageList){
            OldPublishMsg oldPublishMsg = new OldPublishMsg();
            oldPublishMsg.setMessageId(message.getMessageId());
            oldPublishMsg.setMessageTitle(message.getMessageTitle());
            oldPublishMsg.setMessageType(message.getMessageType());
            oldPublishMsg.setSendTime(message.getReleaseTime());
            oldPublishMsgList.add(oldPublishMsg);
        }
        result.setCode(ResponseCode.RESPONSE_SUCCESS);
        result.setData(oldPublishMsgList);
        return result;
    }
}
