package com.rogchen.www.redisdemo;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.concurrent.*;

/**
 * @Description:
 * @Product: IntelliJ IDEA
 * @Author Rogchen rogchen128@gmail.com
 * @Created Date: 2020/6/12 09:03
 **/
public class RedisTest {


    int count = 200;
    CountDownLatch latch = new CountDownLatch(count);
    ExecutorService threadPoolExecutor = Executors.newFixedThreadPool(count);

    @Test
    public void test() throws IOException, InterruptedException {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .get()
//                .addHeader("Content-Type", "application/json;charset=utf-8")
//                .url("http://localhost:28081/imageChange/redis/2")
                .url("http://localhost:18081/count/testcount")
//                .url("https://www.lamajihua.com/temp/ReferrerShow/2145")
                .build();
        if (request.isHttps()) {
            System.out.println("this is https");
        }
        for (int i = 0; i < count; i++) {
            threadPoolExecutor.submit(() -> {
                try {
                    latch.await();
                    Response response = client.newCall(request).execute();
                    if (response.isSuccessful()) {
                        byte[] result = response.body().bytes();
                        System.out.println(Thread.currentThread().getName() + ":" + new String(result));
                    } else {
                        System.out.println("error:" + response.message());
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
            latch.countDown();
        }
        TimeUnit.SECONDS.sleep(5);
    }
}
