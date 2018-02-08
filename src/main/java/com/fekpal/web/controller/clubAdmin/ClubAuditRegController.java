package com.fekpal.web.controller.clubAdmin;

import com.fekpal.api.ClubMemberService;
import com.fekpal.api.ClubService;
import com.fekpal.common.json.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/**
 * 注册审核的控制类
 * Created by hasee on 2017/8/27.
 */
@Controller
public class ClubAuditRegController {

    @Autowired
    private ClubMemberService clubMemberService;

    @Autowired
    private ClubService clubService;

    @Autowired
    private JsonResult returnData;

    /**
     * 查看全部审核的信息的方法
     *
     * @return 审核信息列表
     */
    @ResponseBody
    @RequestMapping(value = "/club/audit/join", method = RequestMethod.GET)
    public Map<String, Object> getAllAuditMsg() {
        return null;
    }

    /**
     * 根据某个审核消息id查看审核信息的具体内容
     *
     * @param auditMsgId 审核信息的id
     * @return 审核信息列表
     */
    @ResponseBody
    @RequestMapping(value = "/club/audit/join/{auditMsgId}", method = RequestMethod.GET)
    public Map<String, Object> getAuditMsgDetail(@PathVariable("auditMsgId") int auditMsgId) {
        return null;
    }

    /**
     * 发送审核结果，得到审核结果
     *
     * @param auditMsgId 审核消息id
     * @param resultMap  结果的map集合
     * @return 是否成功
     */
    @ResponseBody
    @RequestMapping(value = "/club/audit/join/{auditMsgId}", method = RequestMethod.PUT)
    public Map<String, Object> sendAuditMsgResult(@PathVariable("auditMsgId") Integer auditMsgId, @RequestParam Map<String, Object> resultMap) {
        return null;
    }

    /**
     * 根据查找内容查找审核消息
     *
     * @param findContent 查找内容
     * @return 返回审核消息列表
     */
    @ResponseBody
    @RequestMapping(value = "/club/audit/join/search", method = RequestMethod.GET)
    public Map<String, Object> searchAuditMsg(@RequestParam String findContent) {
        return null;
    }
}
