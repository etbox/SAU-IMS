package com.fekpal.common.utils;

import com.fekpal.common.constant.ResponseCode;
import com.fekpal.common.json.JsonResult;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 处理上传图片的工具类类
 * Created by hasee on 2017/8/16.
 */
public class ImagesUploadUtils {

    /**
     * 上传头像的方法
     *
     * @param files        文件
     * @param request      请求
     * @param childrenPath 子目录如：club
     * @return 标准json数据
     */
    public static Map<String, Object> uploadImage(MultipartFile[] files, HttpServletRequest request, String childrenPath) {
        //得到基本返回数据模板
        JsonResult returnData = new JsonResult();
        //判断图片格式和大小是否符合
        for (MultipartFile file : files) {
            if (!file.isEmpty()) {

                if (file.getSize() > 1024 * 1024 * 10) {
                    returnData.setStateCode(ResponseCode.REQUEST_ERROR, "图片大于10m请重新上传");
                    return returnData.getMap();
                }
                if (!file.getContentType().toString().equals("image/png")
                        && !file.getContentType().toString().equals("image/jpeg")
                        && !file.getContentType().toString().equals("image/jpg")
                        && !file.getContentType().toString().equals("image/bmp")) {
                    returnData.setStateCode(ResponseCode.REQUEST_ERROR, "上传的图片不符合格式，请重新上传");
                    return returnData.getMap();
                }
            } else {
                returnData.setStateCode(1, "没上传文件，请重新上传");
                return returnData.getMap();
            }
        }
        //如果上传的文件为空，返回提示语句
        if (files != null) {
            //如果上传的文件不是空，那么将图片存入服务器中的与本项目同目录的//MySAUImages/club文件夹中
            List<String> fileNameList = FileUploadUtils.fileHandle(files, request, childrenPath);
            Map<String, String> clubLogoMap = new HashMap<String, String>();
            clubLogoMap.put("clubLogo", fileNameList.get(0));
            returnData.setData(clubLogoMap);
        } else {
            returnData.setStateCode(1, "没上传文件，请重新上传");
        }
        return returnData.getMap();
    }

}
