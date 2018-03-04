package com.fekpal.web.controller.sauAdmin;


import com.fekpal.api.SauService;
import com.fekpal.common.constant.Operation;
import com.fekpal.common.constant.ResponseCode;
import com.fekpal.common.json.JsonResult;
import com.fekpal.dao.model.Org;
import com.fekpal.service.model.domain.SauMsg;
import com.fekpal.web.model.OrgDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/**
 * 校社联中心信息的控制类
 * Created by hasee on 2017/8/19.
 */
@Controller
public class SauCenterController {

    @Autowired
    private SauService sauService;

    /**
     * 上传校社联头像的
     *
     * @param sauMsg 校社联修改信息封装
     * @return 图片文件名
     */
    @ResponseBody
    @RequestMapping(value = "/sau/center/info/head", method = RequestMethod.POST)
    public JsonResult<String> uploadLogo(@ModelAttribute SauMsg sauMsg) {
        String logoName = sauService.updateLogo(sauMsg);

        JsonResult<String> result = new JsonResult<>();
        result.setStateCode(ResponseCode.RESPONSE_SUCCESS, "更新头像成功");
        result.setData(logoName);
        return result;
    }

    /**
     * 得到校社联中心的信息
     *
     * @return 校社联的一些基本信息
     */
    @ResponseBody
    @RequestMapping(value = "/sau/center/info", method = RequestMethod.GET)
    public JsonResult<List<OrgDetail>> getSauCenterMsg() {
        List<Org> list = sauService.loadAllSau(0, 1);

        JsonResult<List<OrgDetail>> result = new JsonResult<>();
        if (list == null || list.size() == 0) {
            result.setStateCode(ResponseCode.RESPONSE_ERROR, "无结果");
        } else {
            List<OrgDetail> sauDetails = new ArrayList<>();
            for (Org sau : list) {
                OrgDetail sauDetail = new OrgDetail();
                sauDetail.setOrgName(sau.getOrgName());
                sauDetail.setLogo(sau.getOrgLogo());
                sauDetail.setDescription(sau.getDescription());
                sauDetail.setAdminName(sau.getAdminName());
                sauDetail.setEmail(sau.getContactEmail());
                sauDetail.setPhone(sau.getContactNumber());
                sauDetail.setFoundTime(sau.getFoundTime());
                sauDetail.setMembers(sau.getMembers());
                sauDetails.add(sauDetail);
            }
            result.setStateCode(ResponseCode.RESPONSE_SUCCESS, "加载成功");
            result.setData(sauDetails);
        }
        return result;
    }

    /**
     * 修改校社联信息
     *
     * @param sauMsg 校社联修改信息封装
     * @return 是否提交成功
     */
    @ResponseBody
    @RequestMapping(value = "/sau/center/info", method = RequestMethod.PUT)
    public JsonResult<String> subNewCenterMsg(@RequestBody SauMsg sauMsg) {
        int state = sauService.updateSauInfo(sauMsg);

        JsonResult<String> result = new JsonResult<>();
        if (state == Operation.SUCCESSFULLY) {
            result.setStateCode(ResponseCode.RESPONSE_SUCCESS, "修改成功");
        } else if (state == Operation.FAILED) {
            result.setStateCode(ResponseCode.RESPONSE_ERROR, "修改失败");
        } else if (state == Operation.INPUT_INCORRECT) {
            result.setStateCode(ResponseCode.RESPONSE_ERROR, "校社联名称已被使用");
        }
        return result;
    }
}
