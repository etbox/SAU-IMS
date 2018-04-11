package com.fekpal.web.controller.sauAdmin;

import com.fasterxml.jackson.databind.annotation.JsonAppend;
import com.fekpal.api.*;
import com.fekpal.common.constant.AuditState;
import com.fekpal.common.constant.Operation;
import com.fekpal.common.constant.ResponseCode;
import com.fekpal.common.constant.SystemRole;
import com.fekpal.common.json.JsonResult;
import com.fekpal.dao.model.*;
import com.fekpal.web.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.*;

/**
 * 注册审核的控制类
 * Created by hasee on 2017/8/27.
 */
@Controller
public class SauAuditRegController {

    @Autowired
    private  ClubAuditService clubAuditService;
    @Autowired
    private  UserService userService;
    @Autowired
    private  PersonService personService;
    @Autowired
    private  OrgMemberService orgMemberService;
    @Autowired
    private  OrgService orgService;


    /**
     * 查看全部审核的信息的方法
     * @return 审核信息列表
     */
    @ResponseBody
    @RequestMapping(value = "/sau/audit/reg", method = RequestMethod.GET)
    public JsonResult<List<ClubAuditListMsg>> getAllAuditMsg(PageList page) {
        //将前端发送过来的页码offset，转化为跳过数offset
        if(page!=null){page.setOffset((page.getOffset()-1)*page.getLimit());}

        JsonResult<List<ClubAuditListMsg>> result = new JsonResult<>();
        List<ClubAuditListMsg> auditList = new ArrayList<>();
        //社团审核
        List<ClubAudit> clubAudits = clubAuditService.loadAllClubAudit(page.getOffset(),page.getLimit());
        //个人审核 //暂时个人审核的先全部加载
        List<OrgMember> orgMemberList = orgMemberService.loadAllUnAuditMember(1,100000);
        if((clubAudits==null || clubAudits.size()==0) && (orgMemberList== null || orgMemberList.size()==0)){result.setStateCode(ResponseCode.REQUEST_ERROR,"暂无审核消息"); return result;}
        if(clubAudits!=null) {
            for (ClubAudit clubAudit : clubAudits) {
                ClubAuditListMsg audit = new ClubAuditListMsg();
                audit.setAuditMsgId(clubAudit.getId());
                audit.setAuditState(clubAudit.getAuditState());
                audit.setAuditTitle(clubAudit.getAuditTitle());
                audit.setRegisterName(clubAudit.getApplyName());
                audit.setRegisterTime(clubAudit.getRegisterTime());
                audit.setRole(SystemRole.CLUB);
                auditList.add(audit);
            }
        }
        if(orgMemberList !=null){
            for(OrgMember orgMember : orgMemberList){
                ClubAuditListMsg audit = new ClubAuditListMsg();
                audit.setAuditMsgId(orgMember.getId());
                audit.setAuditState(orgMember.getAvailable());
                int personId = orgMember.getPersonId();
                Person person = personService.selectByPrimaryKey(personId);
                audit.setAuditTitle(person.getRealName());
                audit.setRegisterName(null);
                audit.setRegisterTime(orgMember.getJoinTime());
                audit.setRole(SystemRole.PERSON);
                auditList.add(audit);
            }
        }
        result.setCode(ResponseCode.RESPONSE_SUCCESS);
        result.setData(auditList);
        return result;
    }

    /**
     * 根据某个审核消息id查看审核信息的具体内容
     * @param auditMsgId 审核信息的id
     * @return 审核信息列表
     */
    @ResponseBody
    @RequestMapping(value = "/sau/audit/join/{auditMsgId}/{role}", method = RequestMethod.GET)
    public JsonResult getAuditMsgDetail(@PathVariable("auditMsgId") int auditMsgId, @PathVariable(value = "role") int role) {
        JsonResult<PersonJoinAuditDetail> personResult = new JsonResult<>();
        JsonResult<SauClubAuditDetail> clubResult = new JsonResult<>();
        PersonJoinAuditDetail personDetail = new PersonJoinAuditDetail();
        SauClubAuditDetail clubDetail = new SauClubAuditDetail();
        if(role == SystemRole.PERSON) {
             OrgMember orgMember = orgMemberService.selectById(auditMsgId);
             personDetail.setAuditId(auditMsgId);
             Person person = personService.selectByPrimaryKey(orgMember.getPersonId());
             User user  = userService.selectByPrimaryKey(person.getUserId());
             personDetail.setAddress(person.getAddress());
             personDetail.setBirthday(person.getBirthday());
             personDetail.setDepartmentName(person.getDepartment());
             personDetail.setEmail(user.getEmail());
             personDetail.setGender(person.getGender() == 0 ? "男" : "女");
             personDetail.setMajorName(person.getMajor());
             personDetail.setPersonLogo(person.getLogo());
             personDetail.setPhone(user.getPhone());
             personDetail.setRealName(person.getRealName());
             personDetail.setRegisterTime(orgMember.getJoinTime());
             personDetail.setStudentId(person.getStudentId());
             personDetail.setUserName(user.getUserName());
             personDetail.setAuditSate(person.getPersonState());

             personResult.setData(personDetail);
             personResult.setCode(ResponseCode.RESPONSE_SUCCESS);
             return personResult;
        }else if(role == SystemRole.CLUB){
            ClubAudit clubAudit = clubAuditService.selectByPrimaryKey(auditMsgId);
            if(clubAudit == null){clubResult.setStateCode(ResponseCode.REQUEST_ERROR,"操作失败"); return clubResult;}
            Org org = orgService.selectByPrimaryKey(clubAudit.getOrgId());
            User user = userService.selectByPrimaryKey(org.getUserId());
            clubDetail = new SauClubAuditDetail();
            clubDetail.setAudtiMsgId(auditMsgId);
            clubDetail.setClubName(org.getOrgName());
            clubDetail.setClubType(org.getOrgType());
            clubDetail.setDescription(org.getDescription());
            clubDetail.setEmail(user.getEmail());
            clubDetail.setFile(clubAudit.getFile());
            clubDetail.setPhone(user.getPhone());
            clubDetail.setRegisterTime(clubAudit.getRegisterTime());
            clubDetail.setRealName(clubAudit.getApplyName());
            clubDetail.setAuditSate(clubAudit.getAuditState());
            clubDetail.setAuditResult(clubAudit.getAuditResult());

            clubResult.setData(clubDetail);
            clubResult.setCode(ResponseCode.RESPONSE_SUCCESS);
            return clubResult;
        }
        //如果执行到这里，则返回错误
        personResult.setStateCode(ResponseCode.REQUEST_ERROR,"操作错误");
        return personResult;
    }

    /**
     * 发送审核结果，得到审核结果
     * @param auditMsgId 审核消息id
     * @param auditResult 结果的map集合
     * @return 是否成功
     */
    @ResponseBody
    @RequestMapping(value = "/sau/audit/reg/{auditMsgId}/{role}", method = RequestMethod.PUT)
    public JsonResult<String> sendAuditMsgResult(@PathVariable int auditMsgId, @PathVariable int role, @RequestBody AuditResult auditResult)  {
        JsonResult<String> result = new JsonResult<>();
        int state = 0;
        if(auditResult == null){result.setStateCode(ResponseCode.REQUEST_ERROR,"发送审核结果错误");}
        if(role==SystemRole.CLUB){
            ClubAuditResultMsg clubAuditResultMsg = new ClubAuditResultMsg();
            clubAuditResultMsg.setAuditResult(auditResult.getAuditResult());
            clubAuditResultMsg.setAuditState(auditResult.getAuditState());
            state = clubAuditService.passOrRejectClubAuditByIdAndResultMsg(auditMsgId,clubAuditResultMsg);
            if(state == Operation.FAILED ){ result.setStateCode(ResponseCode.REQUEST_ERROR,"操作非法"); return result;}
            result.setCode(ResponseCode.RESPONSE_SUCCESS);
            return result;
        }else if(role == SystemRole.PERSON){
            state = orgMemberService.passOrRejectAuditByIdAndModel(auditMsgId,auditResult);
            if(state == Operation.FAILED ){ result.setStateCode(ResponseCode.REQUEST_ERROR,"操作非法");return result;}
            result.setCode(ResponseCode.RESPONSE_SUCCESS);
            return result;
        }
        result.setStateCode(ResponseCode.REQUEST_ERROR,"链接错误");
        return result;
    }

    /**
     * 在线预览审核文件，
     * @param auditMsgId 审核消息id
     * @param response 响应
     * html文件的输出流，直接输出到浏览器
     */
    @RequestMapping(value = "/sau/audit/reg/{auditMsgId}/file/online", method = RequestMethod.GET)
    public void openOnlineFile(@PathVariable("auditMsgId") int auditMsgId, HttpServletResponse response) {
        int state = clubAuditService.viewClubAuditFileById(auditMsgId,response);
        if (state==Operation.FAILED){
            throw new RuntimeException("预览失败，请重新尝试");
        }

    }

    /**
     * 下载某个审核消息的审核文件，向浏览器输出下载信息
     * @param auditMsgId 审核消息id
     * @param response 响应
     */
    @RequestMapping(value = "/sau/audit/reg/{auditMsgId}/file",method = RequestMethod.GET)
    public void downFile(@PathVariable int auditMsgId, HttpServletResponse response){
        //只有社团才有文件下载
        int state = clubAuditService.getClubAuditFileById(auditMsgId,response);
        if(state == Operation.FAILED){
            throw new RuntimeException("下载失败，请重新尝试");
        }
    }

    /**
     * 根据查找内容查找审核消息
     * @param page 查找内容
     * @return 返回审核消息列表
     */
    @ResponseBody
    @RequestMapping(value = "/sau/audit/reg/search",method = RequestMethod.GET)
    public JsonResult<List<ClubAuditListMsg>> searchAuditMsg(SearchPage page){
        //将前端发送的页码offset，转化为跳过条数offset
        if(page!=null){page.setOffset((page.getOffset()-1)*page.getLimit());}

        JsonResult<List<ClubAuditListMsg>> result = new JsonResult<>();
        List<ClubAuditListMsg> auditList = new ArrayList<>();
        //社团审核
        List<ClubAudit> clubAudits = clubAuditService.queryByClubName(page.getFindContent(),page.getOffset(),page.getLimit());
        //个人审核 //暂时个人审核的先全部加载
        List<OrgMember> orgMemberList = orgMemberService.queryByRealName(page.getFindContent(),0,100000);
        if((clubAudits==null || clubAudits.size()==0) && (orgMemberList== null || orgMemberList.size()==0)){result.setStateCode(ResponseCode.REQUEST_ERROR,"搜索不到审核消息"); return result;}
        if(clubAudits!=null) {
            for (ClubAudit clubAudit : clubAudits) {
                ClubAuditListMsg audit = new ClubAuditListMsg();
                audit.setAuditMsgId(clubAudit.getId());
                audit.setAuditState(clubAudit.getAuditState());
                audit.setAuditTitle(clubAudit.getAuditTitle());
                audit.setRegisterName(clubAudit.getApplyName());
                audit.setRegisterTime(clubAudit.getRegisterTime());
                audit.setRole(SystemRole.CLUB);
                auditList.add(audit);
            }
        }
        if(orgMemberList !=null){
            for(OrgMember orgMember : orgMemberList){
                ClubAuditListMsg audit = new ClubAuditListMsg();
                audit.setAuditMsgId(orgMember.getId());
                audit.setAuditState(orgMember.getAvailable());
                int personId = orgMember.getPersonId();
                Person person = personService.selectByPrimaryKey(personId);
                audit.setAuditTitle(person.getRealName());
                audit.setRegisterName(null);
                audit.setRegisterTime(orgMember.getJoinTime());
                audit.setRole(SystemRole.PERSON);
                auditList.add(audit);
            }
        }
        result.setCode(ResponseCode.RESPONSE_SUCCESS);
        result.setData(auditList);
        return result;
    }
}
