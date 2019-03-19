package com.rogchen.study.tomcatlearning.tomcat_03;

import com.rogchen.study.tomcatlearning.http.Request;
import com.rogchen.study.tomcatlearning.http.Response;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

/**
 * @Description: 线程执行类
 * @Product: IntelliJ IDEA
 * @Author Rogchen rogchen128@gmail.com
 * @Created Date: 19-3-19 22:43
 **/
public class ThreadProcess extends Thread {

    protected Socket socket;    //连接

    public ThreadProcess(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            System.out.println("执行线程是："+Thread.currentThread());
            InputStream inputStream = socket.getInputStream();
            Request request = new Request(inputStream);
            System.out.println("请求方法：" + request.getMethod());
            System.out.println("请求的地址：" + request.getUrl());
            String res = Response.responseHeader + "hello word!";
            OutputStream outputStream = socket.getOutputStream();
            outputStream.write(res.getBytes());
            outputStream.flush();
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (socket != null) {
                try {
                    socket.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
