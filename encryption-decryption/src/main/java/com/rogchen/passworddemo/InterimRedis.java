package com.rogchen.passworddemo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * @Description:
 * @Product: IntelliJ IDEA
 * @Author Rogchen rogchen128@gmail.com
 * @Created Date: 2019/3/21 18:01
 **/
@Service
public class InterimRedis {

    @Autowired
    private StringRedisTemplate redisTemplate;

    public static final String REDIS_CONST = "redis_pwd";

    public String getRedis() {
        return redisTemplate.opsForValue().get(REDIS_CONST);
    }

    public void setRedis(String data) {
        redisTemplate.opsForValue().set(REDIS_CONST, data,10, TimeUnit.MINUTES);
    }

    public void delRedis(String key) {
        redisTemplate.delete(key);
    }
}
