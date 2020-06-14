package com.rogchen.www.redisdemo.redis;

import com.rogchen.www.redisdemo.configuration.RedisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description:
 * @Product: IntelliJ IDEA
 * @Author Rogchen rogchen128@gmail.com
 * @Created Date: 20-6-14 13:40
 **/
@RestController
@RequestMapping("hashset")
public class HashSet {
    @Autowired
    private RedisUtils redisUtils;


    @GetMapping("{id}")
    public String hashset(@PathVariable("id") String id) {
        redisUtils.hashSet(id,"222",1);

        return "success";
    }

    @GetMapping("incr")
    public String incr(){
        redisUtils.hincr("22333","5555",-1);
        return "success";
    }
}
