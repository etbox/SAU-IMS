package com.fekpal.common.utils;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 处理上传文件的工具类
 * Created by hasee on 2017/8/16.
 */
public class FileUploadUtil {

    /**
     * 处理上传的文件
     *
     * @param files    传输的文件集
     * @param savePath 存放路径
     * @return 返回文件名集
     */
    public static List<String> fileHandle(MultipartFile[] files, String savePath) throws IOException {

        List<String> fileNames = new ArrayList<>();
        for (MultipartFile file : files) {
            if (!file.isEmpty()) {
                String[] originalFileName = file.getOriginalFilename().split("\\.");
                String fileName = RandomUtil.createFileName() + "." + originalFileName[1];
                file.transferTo(new File(savePath, fileName));
                fileNames.add(fileName);
            }
        }
        return fileNames;
    }

    /**
     * 处理上传的文件
     *
     * @param file     传输的文件
     * @param savePath 存放路径
     * @return 返回文件名
     */
    public static String fileHandle(MultipartFile file, String savePath) throws IOException {

        if (!file.isEmpty()) {
            String[] originalFileName = file.getOriginalFilename().split("\\.");
            String fileName = RandomUtil.createFileName() + "." + originalFileName[1];
            file.transferTo(new File(savePath, fileName));
            return fileName;
        }
        return null;
    }
}
