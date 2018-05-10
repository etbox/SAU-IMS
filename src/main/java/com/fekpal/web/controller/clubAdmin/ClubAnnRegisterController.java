package com.fekpal.web.controller.clubAdmin;

import com.fekpal.api.ClubService;
import com.fekpal.common.constant.AuditState;
import com.fekpal.common.constant.Operation;
import com.fekpal.common.constant.ResponseCode;
import com.fekpal.common.json.JsonResult;
import com.fekpal.api.AnniversaryAuditService;
import com.fekpal.common.session.SessionLocal;
import com.fekpal.common.utils.FileUtil;
import com.fekpal.dao.model.AnniversaryAudit;
import com.fekpal.dao.model.Org;
import com.fekpal.web.model.*;
import javafx.scene.media.AudioTrack;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import javax.servlet.http.HttpServletRequest;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.util.*;


/**
 * 社团管理端的年度注册的控制类
 * @author kanlon
 * @time 2018/4/7
 */
@Controller
public class ClubAnnRegisterController {

    @Autowired
    private AnniversaryAuditService auditService;

    @Autowired
    ClubService clubService;

    @Autowired
    HttpSession session;

    /**
     * 查看全部注册的信息的方法
     * @param page 要查看的开始条数和共多少条
     * @return 全部年度注册信息
     */
    @ResponseBody
    @RequestMapping(value = "/club/ann", method = RequestMethod.GET)
    public JsonResult<List<AnnAuditListModel>> getAllAuditMsg(PageList page) {

        //将前端发送过来的页码offset，转化为跳过数offset
        if(page!=null){page.setOffset((page.getOffset()-1)*page.getLimit());}

        int uid = SessionLocal.local(session).getUserIdentity().getUid();
        JsonResult<List<AnnAuditListModel>> result = new JsonResult<>();
        List<AnnAuditListModel> clubAuditList = new ArrayList<>();
        List<AnniversaryAudit> auditList = auditService.selectByOrgId(uid,page.getOffset(),page.getLimit());

        if (auditList==null){result.setStateCode(ResponseCode.REQUEST_ERROR,"无结果");return result;}

        Calendar c = Calendar.getInstance();
        for (AnniversaryAudit audit : auditList){
            AnnAuditListModel clubAudit = new AnnAuditListModel();
            clubAudit.setAuditMsgId(audit.getId());
            clubAudit.setAuditState(audit.getAuditState());
            Org org = clubService.selectByPrimaryKey(audit.getOrgId());
            //获取提交年份-1的年份作为年度审核的年份
            c.setTime(audit.getSubmitTime());
            int year = c.get(Calendar.YEAR)-1;
            clubAudit.setRegisterName(year+org.getOrgName());
            clubAudit.setRegisterTime(audit.getSubmitTime());
            clubAudit.setRegisterName(org.getAdminName());
            clubAudit.setRegisterTitle(audit.getAuditTitle());
            clubAuditList.add(clubAudit);
        }
        result.setCode(ResponseCode.RESPONSE_SUCCESS);
        result.setData(clubAuditList);
        return result;
    }

    /**
     * 根据某个年度注册消息id查看年度审核信息的具体内容
     *
     * @param auditMsgId 注册信息的id
     * @return 注册信息列表
     */
    @ResponseBody
    @RequestMapping(value = "/club/ann/{auditMsgId}", method = RequestMethod.GET)
    public JsonResult<ClubAnnAuditDetail> getAuditMsgDetail(@PathVariable("auditMsgId") int auditMsgId) {
        JsonResult<ClubAnnAuditDetail> result = new JsonResult<>();
        AnniversaryAudit audit =  auditService.selectByAuditId(auditMsgId);
        if(audit==null){result.setStateCode(ResponseCode.REQUEST_ERROR,"获取错误");return  result;}
        Org org  = clubService.selectByPrimaryKey(audit.getOrgId());
        ClubAnnAuditDetail detail = new ClubAnnAuditDetail();

        detail.setAuditMsgId(audit.getId());
        detail.setAdminName(org.getAdminName());
        detail.setClubName(org.getOrgName());
        detail.setDescription(audit.getSubmitDescription());
        detail.setFileName(audit.getFileName());
        detail.setSubmitTime(audit.getSubmitTime());
        detail.setAuditResult(audit.getAuditResult());
        detail.setAuditState(audit.getAuditState());
        detail.setAuditTime(audit.getAuditTime());

        result.setCode(ResponseCode.RESPONSE_SUCCESS);
        result.setData(detail);
        return result;
    }

    /**
     * 根据查找内容查找年度审核消息
     *
     * @param page 查找内容
     * @return 返回审核消息列表
     */
    @ResponseBody
    @RequestMapping(value = "/club/ann/search", method = RequestMethod.GET)
    public JsonResult<List<AnnAuditListModel>> searchAuditMsg(SearchPage page) {
        //将前端发送的页码offset，转化为跳过条数offset
        if(page!=null){page.setOffset((page.getOffset()-1)*page.getLimit());}

        JsonResult<List<AnnAuditListModel>> result = new JsonResult<>();
        List<AnniversaryAudit> auditList = auditService.queryByAuditTitle(page.getFindContent(),page.getOffset(),page.getLimit());
        List<AnnAuditListModel> sauAuditList = new ArrayList<>();
        if(auditList==null || auditList.size()==0){result.setStateCode(ResponseCode.REQUEST_ERROR,"搜索结果为空"); return result;}
        Calendar c = Calendar.getInstance();
        for (AnniversaryAudit audit : auditList){
            AnnAuditListModel sauAudit = new AnnAuditListModel();
            sauAudit.setAuditMsgId(audit.getId());
            sauAudit.setAuditState(audit.getAuditState());
            Org org = clubService.selectByPrimaryKey(audit.getOrgId());
            //获取提交年份-1的年份作为年度审核的年份
            c.setTime(audit.getSubmitTime());
            int year = c.get(Calendar.YEAR)-1;
            sauAudit.setRegisterName(year+org.getOrgName());
            sauAudit.setRegisterTime(audit.getSubmitTime());
            sauAudit.setRegisterTitle(audit.getAuditTitle());
            sauAudit.setRegisterName(org.getAdminName());
            sauAuditList.add(sauAudit);
        }
        result.setCode(ResponseCode.RESPONSE_SUCCESS);
        result.setData(sauAuditList);
        return result;
    }

    /**
     * 社团管理端提交年度注册信息
     *
     * @return 是否成功
     */
    @ResponseBody
    @RequestMapping(value = "/club/ann/one", method = RequestMethod.POST)
    public JsonResult<String> submitRegisterMsg(@ModelAttribute ClubSubmitAnnMsg submitAudit) {
        JsonResult<String> result = new JsonResult();
        AnniversaryAudit audit = new AnniversaryAudit();
        if(submitAudit==null){result.setStateCode(ResponseCode.REQUEST_ERROR,"提交的审核为空");return result;}
        int state = auditService.sendAuditMessage(submitAudit);
        if(state== Operation.SUCCESSFULLY){
            result.setCode(ResponseCode.RESPONSE_SUCCESS);
        }else {
            result.setStateCode(ResponseCode.REQUEST_ERROR,"发送失败，请注意该年年度注册是否已经通过");
        }
        return result;
    }

    /**
     * 下载某个审核消息的审核文件，向浏览器输出下载信息
     *
     * @param auditMsgId 审核消息id
     * @param response   响应
     */
    @RequestMapping(value = "/club/ann/{auditMsgId}/file", method = RequestMethod.GET)
    public void downFile(@PathVariable int auditMsgId, HttpServletResponse response) {
        int state = auditService.getAuditFileById(auditMsgId,response);
        if(state == Operation.FAILED){
            throw new RuntimeException("下载失败，请重新下载");
        }
    }



}
