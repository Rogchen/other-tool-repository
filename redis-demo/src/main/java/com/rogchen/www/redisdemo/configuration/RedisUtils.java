package com.rogchen.www.redisdemo.configuration;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * Redis工具类
 *
 * @author zhuhuix
 * @date 2020-06-11
 */
@Component
@AllArgsConstructor
public class RedisUtils {

    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * HashGet根据键值得到对象
     *
     * @param key  键值 @NotNull
     * @param item 项目 @NotNull
     * @return 对象
     */
    public Object hashGet(String key, String item) {
        return redisTemplate.opsForHash().get(key, item);
    }

    /**
     * 根据键值向hash表中写入对象
     *
     * @param key   键值 @NotNull
     * @param item  项目 @NotNull
     * @param value 对象 @NotNull
     * @return true 成功 false失败
     */
    public boolean hashSet(String key, String item, Object value) {
        try {
            redisTemplate.opsForHash().put(key, item, value);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 根据键值向hash表中写入对象
     *
     * @param key   键值 @NotNull
     * @param item  项目 @NotNull
     * @param value 对象 @NotNull
     * @param time  过期时间 @NotNull
     * @return true 成功 false失败
     */
    public boolean hashSet(String key, String item, Object value, long time) {
        try {
            redisTemplate.opsForHash().put(key, item, value);
            if (time > 0) {
                expire(key, time);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 指定缓存的失效时间
     *
     * @param key  键值 @NotNull
     * @param time 时间(秒) @NotNull
     */
    public boolean expire(String key, long time) {
        try {
            if (time > 0) {
                redisTemplate.expire(key, time, TimeUnit.SECONDS);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    /**
     * 加法/减法
     *
     * @param key
     * @param field
     * @param value
     * @return
     */
    public long hincr(String key, String field, long value) {
        return redisTemplate.opsForHash().increment(key, field, value);
    }

    /**
     * 全部拿出来
     *
     * @param key
     * @return
     */
    public Map<String, String> hgetall(String key) {
        return (Map<String, String>) redisTemplate.execute((RedisCallback<Map<String, String>>) con -> {
            Map<byte[], byte[]> result = con.hGetAll(key.getBytes());
            if (CollectionUtils.isEmpty(result)) {
                return new HashMap<>(0);
            }

            Map<String, String> ans = new HashMap<>(result.size());
            for (Map.Entry<byte[], byte[]> entry : result.entrySet()) {
                ans.put(new String(entry.getKey()), new String(entry.getValue()));
            }
            return ans;
        });
    }

    /**
     * 指定key field
     *
     * @param key
     * @param fields
     * @return
     */
    public Map<String, String> hmget(String key, List<String> fields) {
        List<String> result = redisTemplate.<String, String>opsForHash().multiGet(key, fields);
        Map<String, String> ans = new HashMap<>(fields.size());
        int index = 0;
        for (String field : fields) {
            if (result.get(index) == null) {
                continue;
            }
            ans.put(field, result.get(index));
        }
        return ans;
    }


    /**
     * 列表添加
     * list:lpush key value1
     *
     * @param k
     * @param v
     */

    public void lPush(String k, Object v) {
        ListOperations<String, Object> list = redisTemplate.opsForList();
        list.rightPush(k, v);
    }

    /**
     * 通过索引获取值
     *
     * @param index
     */
    public Object lGetByIndex(String key, long index) {
        return redisTemplate.opsForList().index(key, index);
    }

    /**
     * 列表List获取
     * lrange： key 0 10 (读取的个数 从0开始 读取到下标为10 的数据)
     *
     * @param k
     * @param l
     * @param l1
     * @return
     */

    public List<Object> lRange(String k, long l, long l1) {
        ListOperations<String, Object> list = redisTemplate.opsForList();
        return list.range(k, l, l1);
    }

}
