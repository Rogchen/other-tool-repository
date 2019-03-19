package com.rogchen.study.tomcatlearning.servlet;

import com.rogchen.study.tomcatlearning.http.Request;
import com.rogchen.study.tomcatlearning.http.Response;

import java.io.OutputStream;

/**
 * @Description:
 * @Product: IntelliJ IDEA
 * @Author Rogchen rogchen128@gmail.com
 * @Created Date: 19-3-19 22:53
 **/
public class CustomServlet extends HttpServlet {
    @Override
    public void doGet(Request request, Response response) {
        try {
            System.out.println("执行线程是：" + Thread.currentThread());
            System.out.println("请求方法：" + request.getMethod());
            System.out.println("请求的地址：" + request.getUrl());
            String res = Response.responseHeader + "hello word!";
            OutputStream outputStream = response.outputStream;
            outputStream.write(res.getBytes("utf-8"));
            outputStream.flush();
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void doPost(Request request, Response response) {

    }
}
