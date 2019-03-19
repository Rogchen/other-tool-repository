package com.rogchen.study.tomcatlearning.tomcat_02;

import com.rogchen.study.tomcatlearning.http.Request;
import com.rogchen.study.tomcatlearning.http.Response;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @Description: 使用对象来完成输入输出
 * @Product: IntelliJ IDEA
 * @Author Rogchen rogchen128@gmail.com
 * @Created Date: 19-3-19 22:15
 * @version v0.2
 **/
public class TomcatServerV02 {

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(8081); //暴露服务的端口
        System.out.println("tomcat v0.2 服务器已经启动了！");
        while (true) {  //无限--等待请求
            //使用socket建立请求，如果客户端已经连接上。
            Socket socket = serverSocket.accept();
            InputStream inputStream = socket.getInputStream();
            Request request = new Request(inputStream);
            System.out.println("请求方法："+request.getMethod());
            System.out.println("请求的地址："+request.getUrl());
            String res = Response.responseHeader+"hello word!";
            OutputStream outputStream = socket.getOutputStream();
            outputStream.write(res.getBytes());
            outputStream.flush();
            outputStream.close();
            socket.close();
        }
    }
}
