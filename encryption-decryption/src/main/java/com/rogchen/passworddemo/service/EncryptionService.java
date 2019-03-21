package com.rogchen.passworddemo.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.rogchen.passworddemo.AesUtils;
import com.rogchen.passworddemo.InterimRedis;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description:
 * @Product: IntelliJ IDEA
 * @Author Rogchen rogchen128@gmail.com
 * @Created Date: 2019/3/21 16:05
 **/
@Service
public class EncryptionService {
    @Autowired
    private InterimRedis interimRedis;

    public String encryptionByType(String data, String type) {
        if (!StringUtils.isEmpty(type)) {
            String redisdt = getFromRedis(type);
            JSONObject jsonObject = JSONObject.parseObject(redisdt);
            String pwd = jsonObject.getJSONObject(type).getString("AES");
            //获取密码--直接写死跟枚举配对，后续在采用数据库跟redis
            String param = AesUtils.encodeToBase64(data, pwd);
            System.out.println("加密后的参数是：" + param);
            return param;
        }
        throw new RuntimeException("加密类型不能为空！");
    }

    private String getFromRedis(String type) {
        String redisdt = interimRedis.getRedis();
        if (StringUtils.isEmpty(redisdt)) {
            Map<String, Map<String, String>> dt = new HashMap<>();
            Map<String, String> content = new HashMap<>();
            content.put("AES", AesUtils.generateKey());
            dt.put(type, content);
            redisdt = JSON.toJSONString(dt);
            interimRedis.setRedis(redisdt);
        }
        return redisdt;
    }

    public String decryptionByType(String data, String key) {
        if (!StringUtils.isEmpty(key)) {
            String redisdt = getFromRedis(key);
            JSONObject jsonObject = JSONObject.parseObject(redisdt);
            String pwd = jsonObject.getJSONObject(key).getString("AES");
            String param = AesUtils.decryptBase64ToString(data, pwd);
            System.out.println("加密后的参数是：" + param);
            return param;
        }
        throw new RuntimeException("加密类型不能为空！");
    }
}
