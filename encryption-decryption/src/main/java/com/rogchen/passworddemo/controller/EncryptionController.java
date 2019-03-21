package com.rogchen.passworddemo.controller;

import com.rogchen.passworddemo.service.EncryptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description: 解密操作
 * @Product: IntelliJ IDEA
 * @Author Rogchen rogchen128@gmail.com
 * @Created Date: 2019/3/21 16:03
 **/
@RestController
public class EncryptionController {

    @Autowired
    private EncryptionService encryptionService;
    /**
    * @Description 参数进行base64处理后过来
    * @Author Rogchen
    * @Date 16:04 2019/3/21
    * @Param [type, param] type标识是何种加密方式,可以添加个参数标识是否自动生成key，还是使用默认key
    * @Return java.lang.String
    **/
    @GetMapping("encryption")
    public String encryptionByType(String type,String param){
        String success = encryptionService.encryptionByType(type,param);
        return success;
    }


    @GetMapping("decryption")
    public String decryptionByType(String type,String param){
        encryptionService.decryptionByType(type,param);
        return "success";
    }
}
