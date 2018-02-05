package com.fekpal.web.controller.sauAdmin;

import com.fekpal.api.AnniversaryAuditService;
import com.fekpal.api.ClubService;
import com.fekpal.api.SauService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.*;

/**
 * 年度审核的控制类
 * Created by hasee on 2017/8/27.
 */
@Controller
public class SauAnnAuditController {

    @Autowired
    private SauService sauService;

    @Autowired
    private AnniversaryAuditService auditService;

    @Autowired
    private ClubService clubService;


    /**
     * 查看全部审核的信息的方法
     *
     * @return 审核信息列表
     */
    @ResponseBody
    @RequestMapping(value = "/sau/audit/ann", method = RequestMethod.GET)
    public Map<String, Object> getAllAuditMsg() {

        return null;
    }

    /**
     * 根据某个审核消息id查看年度审核信息的具体内容
     *
     * @param auditMsgId 审核信息的id
     * @param session    会话
     * @return 审核信息列表
     */
    @ResponseBody
    @RequestMapping(value = "/sau/audit/ann/{auditMsgId}", method = RequestMethod.GET)
    public Map<String, Object> getAuditMsgDetail(@PathVariable("auditMsgId") int auditMsgId, HttpSession session) {
        return null;
    }

    /**
     * 发送年度审核结果，得到审核结果
     *
     * @param auditMsgId 审核消息id
     * @param resultMap  结果的map集合
     * @return 是否成功
     */
    @ResponseBody
    @RequestMapping(value = "/sau/audit/ann/{auditMsgId}", method = RequestMethod.PUT)
    public Map<String, Object> sendAuditMsgResult(@PathVariable("auditMsgId") int auditMsgId, @RequestParam Map<String, Object> resultMap) {
        return null;
    }

    /**
     * 在线预览审核文件，
     *
     * @param auditMsgId 审核消息id
     * @param file       审核文件名称
     * @param response   响应
     *                   html文件的输出流，直接输出到浏览器
     */
    @RequestMapping(value = "/sau/audit/ann/{auditMsgId}/file/online", method = RequestMethod.GET)
    public void openOnlineFile(@PathVariable("auditMsgId") int auditMsgId, @RequestParam(required = false) String file, HttpServletResponse response) {

    }

    /**
     * 下载某个审核消息的审核文件，向浏览器输出下载信息
     *
     * @param auditMsgId 审核消息id
     * @param fileName   文件名
     * @param response   响应
     */
    @RequestMapping(value = "/sau/audit/ann/{auditMsgId}/file", method = RequestMethod.GET)
    public void downFile(@PathVariable int auditMsgId, @RequestParam(value = "file", required = false) String fileName, HttpServletResponse response) {
    }

    /**
     * 根据查找内容查找年度审核消息
     *
     * @param findContent 查找内容
     * @return 返回审核消息列表
     */
    @ResponseBody
    @RequestMapping(value = "/sau/audit/ann/reg/search", method = RequestMethod.GET)
    public Map<String, Object> searchAuditMsg(@RequestParam("findContent") String findContent) {
        return null;
    }
}
