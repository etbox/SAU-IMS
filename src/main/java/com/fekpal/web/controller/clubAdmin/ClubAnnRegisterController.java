package com.fekpal.web.controller.clubAdmin;

import com.fekpal.common.constant.ResponseCode;
import com.fekpal.common.json.JsonResult;
import com.fekpal.dao.model.User;
import com.fekpal.api.AnniversaryAuditService;
import com.fekpal.common.utils.FileUploadUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
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

        User user = (User) session.getAttribute("userCode");

        //从dao中根据用户id得到本社社团历史年度注册信息的对象，并获取该对象属性

        //将得到的数据放入每个map集合中
        Map<String, Object> auditMsgListMap = new LinkedHashMap<>();
        auditMsgListMap.put("auditMsgId", 234);
        auditMsgListMap.put("auditTitle", "2017年");
        auditMsgListMap.put("submitTime", new Date());
        auditMsgListMap.put("auditState", 2);

        //创建放全部年度注册信息的list集合，并将它放入返回数据
        List<Map<String, Object>> auditMsgList = new ArrayList<>();
        auditMsgList.add(auditMsgListMap);

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

        User user = (User) session.getAttribute("userCode");

        if (auditMsgId > 0) {
            //根据审核id和用户id从dao中得到年度注册消息内容

            //该社团年度注册信息
            //创建封装某个年度注册消息的map对象
            Map<String, Object> auditMsgDetailMap = new LinkedHashMap<String, Object>();
            auditMsgDetailMap.put("auditMsgId", auditMsgId);
            auditMsgDetailMap.put("submitTime", new Date());
            auditMsgDetailMap.put("description", "这是乒乓球协会的描述");
            auditMsgDetailMap.put("fileName", "a.doc");
            auditMsgDetailMap.put("auditState", 0);
            auditMsgDetailMap.put("auditResult", "");

            //将map集合数据放入到返回数据中，返回

        } else {
            //returnData.setStateCode(ResponseCode.REQUEST_ERROR, "要查询的年度注册消息的id不符合条件，请重新查询");
            return null;
        }
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

        User user = (User) session.getAttribute("userCode");

        //如果要查找的消息的是空的话，返回全部审核消息
        if (StringUtils.isEmpty(findContent)) {
            return getAllAuditMsg(null, session, request);
        }

        //根据用户id和查找内容，通过dao从数据库中查找相应的年度注册信息
        // TODO: 2017/8/27

        //将得到的数据放入每个map集合中
        Map<String, Object> auditMsgListMap1 = new LinkedHashMap<String, Object>();
        auditMsgListMap1.put("auditMsgId", 1);
        auditMsgListMap1.put("auditTitle", "2017年" + "。查找的内容是：" + findContent);
        auditMsgListMap1.put("registerName", "张三");
        auditMsgListMap1.put("registerTime", new Date());
        auditMsgListMap1.put("auditState", 2);


        //创建放全部审核信息的list集合，并将它放入返回数据
        List<Map<String, Object>> auditMsgList = new ArrayList<Map<String, Object>>();
        auditMsgList.add(auditMsgListMap1);

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


        //得到用户id
        User user = (User) session.getAttribute("userCode");

        //在service层验证描述，提交的文件的安全性等
        // TODO: 2017/9/2

        //如果上传的文件不符合符合大小，文件类型等
        if ((Integer) (handleFile(file).get("code")) != 0) {
            //如果上传的文件不符合条件，返回相应内容
            return handleFile(file);
        }

        //初始化文件名，描述，提交时间
        String fileName = "";
        String description = "";
        Date submitTime = new Date();

        //将文件存入服务器中的与本项目同目录的//MySAUImages/clubAnnRegister文件夹中，返回文件名
        List<String> fileNameList = null;//FileUploadUtil.fileHandle(file,"clubAnnRegister");
        if (fileNameList != null) {
            fileName = fileNameList.get(0);
        } else {
            //returnData.setStateCode(ResponseCode.REQUEST_ERROR, "还没有发送文件过来，请重新发送");
            return null;
        }

        //从发来的map集合中得到年度注册描述
        if (clubMsgMap.get("description") != null) {
            description = clubMsgMap.get("description").toString();
        }

        //根据用户id，将本社团的年度注册信息，描述，文件名，和提交时间存入数据库
        // TODO: 2017/8/27

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
