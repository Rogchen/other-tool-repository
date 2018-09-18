package com.rogchen.www.controller;

import com.rogchen.www.utils.UploadUtils;
import com.ylzinfo.dto.Result;
import com.ylzinfo.dto.impl.CommonResult;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

/**
 * @Description: 上传图片处理
 * @Product: IntelliJ IDEA
 * @Author Rogchen rogchen128@gmail.com
 * @Created Date: 2018/9/2 19:55
 **/
@Controller
public class UploadController {

    @RequestMapping(value = {"","/","index"})
    public String index(){
        return "index";
    }

    @RequestMapping(value = "upload")
    public String upload(){
        return "upload";
    }

    @RequestMapping("base/upload")
    @ResponseBody
    public Result uploadAddr(HttpServletRequest request, String directoryName, MultipartFile file){
        String url = UploadUtils.upload(request,directoryName,file);
        CommonResult result = CommonResult.successResult();
        result.setDataAttr("url",url);
        return result;
    }

}
