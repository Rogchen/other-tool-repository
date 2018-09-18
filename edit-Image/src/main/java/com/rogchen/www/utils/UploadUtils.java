package com.rogchen.www.utils;

import org.jboss.logging.Logger;
import org.springframework.util.ResourceUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

/**
 * @Description: 上传工具类
 * @Product: IntelliJ IDEA
 * @Author Rogchen rogchen128@gmail.com
 * @Created Date: 2018/9/12 18:46
 **/
public class UploadUtils {

    private static Logger logger = Logger.getLogger(UploadUtils.class);
    // 图片类型
    private static List<String> fileTypes = new ArrayList<String>();
    static {
        fileTypes.add(".jpg");
        fileTypes.add(".jpeg");
        fileTypes.add(".bmp");
        fileTypes.add(".gif");
        fileTypes.add(".png");
    }

    public static String upload(HttpServletRequest request, String directoryName,MultipartFile file){
        if (file.isEmpty()) {
            return null;
        }
        String fileName = null;
        try {
            if (file != null) {
                // 取得当前上传文件的文件名称
                String myFileName = file.getOriginalFilename();
                // 如果名称不为“”,说明该文件存在，否则说明该文件不存在
                if (myFileName.trim() != "") {
                    // 获得图片的原始名称
                    String originalFilename = file.getOriginalFilename();
                    String fileNameWithoutSuffix = originalFilename.substring(0, originalFilename.lastIndexOf("."));
                    // 获得图片后缀名称,如果后缀不为图片格式，则不上传
                    String suffix = originalFilename.substring(originalFilename.lastIndexOf(".")).toLowerCase();
                    // 获得上传路径的绝对路径地址(/upload)-->
                    if(StringUtils.isEmpty(directoryName)){
                        directoryName = "/upload";
                    }
                    String realPath =ResourceUtils.getURL("classpath:").getPath();
                    // 如果路径不存在，则创建该路径 --tomcat/classes或者target/classes
                    File realPathDirectory = new File(realPath,directoryName);
                    if (realPathDirectory == null || !realPathDirectory.exists()) {
                        realPathDirectory.mkdirs();
                    }
                    fileName = fileNameWithoutSuffix + new Date().getTime() + suffix;
                    File uploadFile = new File(realPathDirectory + File.separator + "/"+fileName);
                    logger.error("上传的路径：" + uploadFile);
                    file.transferTo(uploadFile);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
        return fileName;
    }

    /**
     *  可批量上传图片
     * @param request
     * @param directoryName 上传指定目录
     * @return
     * @throws IllegalStateException
     * @throws IOException
     */
    public static String uploadImage(HttpServletRequest request, String directoryName)
            throws IllegalStateException, IOException {
        // 创建一个通用的多部分解析器
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(
                request.getSession().getServletContext());
        // 图片名称
        String fileName = null;
        // 判断 request 是否有文件上传,即多部分请求
        if (multipartResolver.isMultipart(request)) {
            // 转换成多部分request
            MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
            // 取得request中的所有文件名
            Iterator<String> iter = multiRequest.getFileNames();
            while (iter.hasNext()) {
                // 记录上传过程起始时的时间，用来计算上传时间
                // int pre = (int) System.currentTimeMillis();
                // 取得上传文件
                MultipartFile file = multiRequest.getFile(iter.next());
                if (file != null) {

                    // 取得当前上传文件的文件名称
                    String myFileName = file.getOriginalFilename();
                    // 如果名称不为“”,说明该文件存在，否则说明该文件不存在
                    if (myFileName.trim() != "") {
                        // 获得图片的原始名称
                        String originalFilename = file.getOriginalFilename();
                        String fileNameWithoutSuffix=originalFilename.substring(0,originalFilename.lastIndexOf("."));
                        // 获得图片后缀名称,如果后缀不为图片格式，则不上传
                        String suffix = originalFilename.substring(originalFilename.lastIndexOf(".")).toLowerCase();
                        if (!fileTypes.contains(suffix)) {
                            continue;
                        }
                        // 获得上传路径的绝对路径地址(/upload)-->
                        String realPath = request.getSession().getServletContext().getRealPath("/" + directoryName);
                        // 如果路径不存在，则创建该路径
                        File realPathDirectory = new File(realPath);
                        if (realPathDirectory == null || !realPathDirectory.exists()) {
                            realPathDirectory.mkdirs();
                        }
                        // 重命名上传后的文件名 111112323.jpg
                        fileName = fileNameWithoutSuffix+new Date().getTime() + suffix;
                        // 定义上传路径 .../upload/111112323.jpg
                        File uploadFile = new File(realPathDirectory + File.separator + fileName);
                        logger.error("图片的路径：" + uploadFile);
                        file.transferTo(uploadFile);
                    }
                }
            }
        }
        return fileName;
    }
}
