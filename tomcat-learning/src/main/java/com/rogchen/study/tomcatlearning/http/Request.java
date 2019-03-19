package com.rogchen.study.tomcatlearning.http;

import lombok.Data;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * @Description:
 * @Product: IntelliJ IDEA
 * @Author Rogchen rogchen128@gmail.com
 * @Created Date: 19-3-19 22:10
 **/
@Data
public class Request {

    private String method;
    private String url;

    /**
     * ET / HTTP/1.1
     * Host: localhost:8081
     * Connection: keep-alive
     * Upgrade-Insecure-Requests: 1
     * User-Agent: Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/69.0.3497.100 Safari/537.36
     * Accept: text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*\/*;q=0.8* Accept-Encoding:gzip,deflate,br
     * Accept-Language:zh-CN,zh;q=0.9
     * @param inputStream
     * @throws IOException
     */
    public Request(InputStream inputStream) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, "utf-8"));
        String[] input = reader.readLine().split(" ");
        this.method = input[0]; //get/post
        this.url = input[1];    //请求路径
    }
}
