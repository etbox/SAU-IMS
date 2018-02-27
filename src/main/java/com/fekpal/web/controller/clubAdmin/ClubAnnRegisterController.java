package com.fekpal.web.controller.clubAdmin;

import com.fekpal.common.constant.ResponseCode;
import com.fekpal.common.json.JsonResult;
import com.fekpal.api.AnniversaryAuditService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import javax.servlet.http.HttpServletRequest;

import javax.servlet.http.HttpSession;

import java.util.*;


/**
 * 社团管理端的年度注册的控制类
 * Created by hasee on 2017/8/27.
 */
@Controller
public class ClubAnnRegisterController {

    @Autowired
    private AnniversaryAuditService auditService;


    /**
     * 查看全部注册的信息的方法
     *
     * @param annARegisterMap 年度审核信息
     * @param session         会话
     * @param request         请求
     * @return 全部年度注册信息
     */
    @ResponseBody
    @RequestMapping(value = "/club/ann", method = RequestMethod.GET)
    public Map<String, Object> getAllAuditMsg(@RequestParam(required = false) Map<String, Object> annARegisterMap, HttpSession session, HttpServletRequest request) {


        return null;
    }

    /**
     * 根据某个年度注册消息id查看年度审核信息的具体内容
     *
     * @param auditMsgId 注册信息的id
     * @param session    会话
     * @return 注册信息列表
     */
    @ResponseBody
    @RequestMapping(value = "/club/ann/{auditMsgId}", method = RequestMethod.GET)
    public Map<String, Object> getAuditMsgDetail(@PathVariable("auditMsgId") int auditMsgId, HttpSession session) {


        return null;
    }

    /**
     * 根据查找内容查找年度审核消息
     *
     * @param findContent 查找内容
     * @param session     会话
     * @return 返回审核消息列表
     */
    @ResponseBody
    @RequestMapping(value = "/club/ann/search", method = RequestMethod.GET)
    public Map<String, Object> searchAuditMsg(@RequestParam String findContent, HttpSession session, HttpServletRequest request) {



        return null;
    }

    // TODO: 2017/9/2

    /**
     * 社团管理端提交年度注册信息
     * 审核文件放在D://masterspring//MySAUImages//clubAnnRegister
     * <p>
     * 结果的map集合
     *
     * @param session 会话
     * @return 是否成功
     */
    @ResponseBody
    @RequestMapping(value = "/club/ann/one", method = RequestMethod.POST)
    public Map<String, Object> submitRegisterMsg(@RequestParam MultipartFile[] file, @RequestParam Map<String, Object> clubMsgMap, HttpServletRequest request, HttpSession session) {




        return null;
    }


    /**
     * 处理上传文件的方法
     *
     * @param files 上传的文件
     * @return 上传信息是否正确
     */
    public static Map<String, Object> handleFile(MultipartFile[] files) {

        JsonResult returnData = new JsonResult();

        //判断文件格式和大小是否符合
        for (MultipartFile file : files) {

            if (!file.isEmpty()) {
                if (file.getSize() > 1024 * 1024 * 10) {
                    returnData.setStateCode(ResponseCode.REQUEST_ERROR, "文件大于10m请重新上传");
                    return null;
                }
                if (!file.getContentType().equals("application/vnd.openxmlformats-officedocument.wordprocessingml.document")
                        && !file.getContentType().equals("application/msword")) {
                    returnData.setStateCode(ResponseCode.REQUEST_ERROR, "上传的文件不符合格式，请重新上传");
                    return null;
                }
            } else {
                returnData.setStateCode(1, "没上传文件，请重新上传");
                return null;
            }
        }

        return null;
    }
}
