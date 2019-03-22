package com.rogchen.passworddemo.runner;

import com.alibaba.fastjson.JSON;
import com.rogchen.passworddemo.utils.RSA.CryptoUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.util.Map;

/**
 * @Description: 项目启动获取公私玥，如果存在redis就放入redis否则只放入线程共享。
 * @Product: IntelliJ IDEA
 * @Author Rogchen rogchen128@gmail.com
 * @Created Date: 2019/3/22 15:33
 **/
@Configuration
public class RsaGenerateRunner implements CommandLineRunner {

    @Autowired
    private StringRedisTemplate redisTemplate;
    private ThreadLocal<Map<String, String>> threadLocal = new ThreadLocal();

    @Override
    public void run(String... args) throws Exception {
        if (redisTemplate == null) {
            Map<String, String> param = threadLocal.get();
            if (param != null && !param.isEmpty()) {
                Map<String, String> keyMap = CryptoUtils.gegerateKey();
                threadLocal.set(keyMap);
            } else {
                //不更新key
            }
        } else {
            if (redisTemplate.opsForValue().get(CryptoUtils.rsa_key) == null) {
                Map<String, String> keyMap = CryptoUtils.gegerateKey();
                String jsonString = JSON.toJSONString(keyMap);
                redisTemplate.opsForValue().set(CryptoUtils.rsa_key, jsonString);
                threadLocal.set(keyMap);
            }
        }
    }

}
