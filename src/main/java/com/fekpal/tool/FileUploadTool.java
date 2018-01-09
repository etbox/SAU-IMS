package com.fekpal.tool;

import org.apache.commons.io.FileUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 处理上传文件的工具类
 * Created by hasee on 2017/8/16.
 */
public class FileUploadTool {


    /**
     * 处理上传的文件
     *
     * @param files    传输的文件
     * @param filePath String 放到哪个子目录去，不能为空
     * @return 返回文件访问路径
     */
    public static List<String> fileHandle(MultipartFile[] files, HttpServletRequest request, String filePath) {

        //存放放在服务器的文件名
        List<String> fileNames = new ArrayList<>();

        for (MultipartFile file : files) {

            if (!file.isEmpty()) {
                //这里不必处理IO流关闭的问题，因为FileUtils.copyInputStreamToFile()方法内部会自动把用到的IO流关掉，我是看它的源码才知道的
                try {
                    long time = System.currentTimeMillis();
                    String timeStr = String.valueOf(time);
                    String[] originalFileName = file.getOriginalFilename().split("\\.");
                    String fileName = timeStr + "." + originalFileName[1];
                    String fileRoot = request.getServletContext().getRealPath("/upload");
                    FileUtils.copyInputStreamToFile(file.getInputStream(), new File(fileRoot + filePath, fileName));
                    fileNames.add(originalFileName[0]);

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return fileNames;
    }
}
