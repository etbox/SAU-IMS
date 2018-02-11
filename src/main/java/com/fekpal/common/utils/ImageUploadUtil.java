package com.fekpal.common.utils;

import com.fekpal.common.constant.ResponseCode;
import com.fekpal.common.json.JsonResult;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 处理上传图片的工具类类
 * Created by hasee on 2017/8/16.
 */
public class ImageUploadUtil {

    /**
     * 上传头像的方法
     *
     * @param files    文件
     * @param savePath 存放路径
     * @return 储存后的文件名或者null
     */
    public static List<String> handle(MultipartFile[] files, String savePath) throws IOException {
        return FileUploadUtil.fileHandle(files, savePath);
    }

    /**
     * 上传头像的方法
     *
     * @param file     文件
     * @param savePath 存放路径
     * @return 储存后的文件名或者null
     */
    public static String handle(MultipartFile file, String savePath) throws IOException {
        return FileUploadUtil.fileHandle(file, savePath);
    }

    /**
     * 判断图片格式合法性
     *
     * @param file 文件
     * @return 是否正确
     */
    public static boolean isValid(MultipartFile file) {
        return (file != null
                && !file.getContentType().equals("image/png")
                && !file.getContentType().equals("image/jpeg")
                && !file.getContentType().equals("image/jpg")
                && !file.getContentType().equals("image/bmp"));
    }
}
