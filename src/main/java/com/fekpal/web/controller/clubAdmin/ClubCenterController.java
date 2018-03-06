package com.fekpal.web.controller.clubAdmin;

import com.fekpal.api.ClubService;
import com.fekpal.common.constant.Operation;
import com.fekpal.common.constant.ResponseCode;
import com.fekpal.common.json.JsonResult;
import com.fekpal.dao.model.Org;
import com.fekpal.service.model.domain.ClubMsg;
import com.fekpal.web.model.OrgDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;


/**
 * 校社联中心信息的控制类
 * Created by hasee on 2017/8/19.
 */
@Controller
public class ClubCenterController {

    @Autowired
    private ClubService clubService;


    /**
     * 得到社团中心的信息的方法
     *
     * @return 社团的一些基本信息
     */
    @ResponseBody
    @RequestMapping("/club/center/info")
    public JsonResult<OrgDetail> getClubsCenterMsg() {
        Org org = clubService.selectByPrimaryId();

        JsonResult<OrgDetail> result = new JsonResult<>();
        if (org == null) {
            result.setStateCode(ResponseCode.RESPONSE_ERROR, "无结果");

        } else {
            OrgDetail detail = new OrgDetail();
            detail.setOrgName(org.getOrgName());
            detail.setLogo(org.getOrgLogo());
            detail.setDescription(org.getDescription());
            detail.setAdminName(org.getAdminName());
            detail.setEmail(org.getContactEmail());
            detail.setPhone(org.getContactNumber());
            detail.setFoundTime(org.getFoundTime());
            detail.setMembers(org.getMembers());
            detail.setView(org.getOrgView());

            result.setStateCode(ResponseCode.RESPONSE_SUCCESS, "加载成功");
            result.setData(detail);
        }
        return result;
    }

    /**
     * 上传社团头像的方法
     *
     * @param msg 社团信息封装
     * @return 图片文件名
     */
    @ResponseBody
    @RequestMapping(value = "/club/center/info/head", method = RequestMethod.POST)
    public JsonResult<String> uploadLogo(@ModelAttribute ClubMsg msg) {
        String logoName = clubService.updateLogo(msg);

        JsonResult<String> result = new JsonResult<>();
        result.setStateCode(ResponseCode.RESPONSE_SUCCESS, "上传社团头像成功");
        result.setData(logoName);
        return result;
    }

    /**
     * 上传社团展示图片的方法
     *
     * @return 图片文件名
     */
    @ResponseBody
    @RequestMapping(value = "/club/center/info/view", method = RequestMethod.POST)
    public JsonResult<String> uploadView() {
        return null;
    }

    /**
     * 社团用来提交修改社团中心的信息
     *
     * @return 是否提交成功
     */
    @ResponseBody
    @RequestMapping(value = "/club/center/info", method = RequestMethod.PUT)
    public JsonResult<OrgDetail> subNewCenterMsg(@RequestBody ClubMsg msg) {
        int state = clubService.updateClubInfo(msg);

        JsonResult<OrgDetail> result = new JsonResult<>();
        if (state == Operation.SUCCESSFULLY) {
            result.setStateCode(ResponseCode.RESPONSE_SUCCESS, "修改成功");
        } else if (state == Operation.FAILED) {
            result.setStateCode(ResponseCode.RESPONSE_ERROR, "修改失败");
        } else if (state == Operation.INPUT_INCORRECT) {
            result.setStateCode(ResponseCode.RESPONSE_ERROR, "社团名称已被使用");
        }
        return result;
    }
}
