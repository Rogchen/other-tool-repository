package com.rogchen.www.redisdemo;

import com.rogchen.www.redisdemo.configuration.RedisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.support.atomic.RedisAtomicLong;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
@RestController
public class RedisDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(RedisDemoApplication.class, args);
    }

    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private RedisUtils redisUtils;


    /**
     * 返回统计信息
     * 先假设type变了需要从头算
     * <p>
     * 数据存储结构  id countpv {content:222,pv:2,count:1}
     * <p>
     * list key  id:list:type:pv
     * hash id:hash type:pv
     *
     * @param id testcount
     * @return
     */
    @GetMapping("count/{id}")
    public Map id(@PathVariable("id") String id) {
        long totalcount = incr(id + ":total", 10);
        Map object = getObject(id);
        long count = redisUtils.hincr(id + ":hash", object.get("type").toString() + ":" + object.get("pv").toString(), 1);
        int type = Integer.valueOf(object.get("type").toString());
        int pv = Integer.valueOf(object.get("pv").toString());
        // long currentPvIndex = countTotalPv % (contentSize * pv) / pv;
        long index = (count % 10) / 2;
        System.out.println("当前总数" + totalcount + ",当前访问次数：" + count + ",当前游标：" + index);
        Object o = redisUtils.lGetByIndex(id, index);    //位置游标
        Map result = new HashMap();
        if (o == null) {
            return result;
        }
        result.put("result", true);
        result.put("totalPv", totalcount+1);
        result.put("content", o);
        result.put("type", 2);
        return result;
    }


    private Map getObject(String id) {
        Map map = new HashMap();
        map.put("id", id);
        map.put("pv", 2);
        map.put("content", "111,222,333");
        map.put("type", 2);
        return map;
    }

    public Long incr(String key, long liveTime) {
        RedisAtomicLong entityIdCounter = new RedisAtomicLong(key, redisTemplate.getConnectionFactory());
        Long increment = entityIdCounter.getAndIncrement();

//        if ((null == increment || increment.longValue() == 0) && liveTime > 0) {//初始设置过期时间
//            entityIdCounter.expire(liveTime, TimeUnit.MINUTES);
//        }

        return increment;
    }

}
