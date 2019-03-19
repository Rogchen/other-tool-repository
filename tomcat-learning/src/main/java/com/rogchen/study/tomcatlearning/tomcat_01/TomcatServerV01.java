package com.rogchen.study.tomcatlearning.tomcat_01;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @Description: 直接输入输出
 * @Product: IntelliJ IDEA
 * @Author Rogchen rogchen128@gmail.com
 * @Created Date: 19-3-19 22:15
 * @version v0.1
 **/
public class TomcatServerV01 {

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(8081); //暴露服务的端口
        System.out.println("tomcat v0.1 服务器已经启动了！");
        while (true) {  //无限--等待请求
            //使用socket建立请求，如果客户端已经连接上。
            Socket socket = serverSocket.accept();
            InputStream inputStream = socket.getInputStream();
            System.out.println("收到了请求：");
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, "utf-8"));
            String msg = null;
            while ((msg = reader.readLine()) != null) {  //读取每行请求信息
                if (msg.length() == 0) {    //如果信息为空
                    break;
                }
                System.out.println(msg);
            }
            String res = "hello word!";
            OutputStream outputStream = socket.getOutputStream();
            outputStream.write(res.getBytes());
            outputStream.flush();
            outputStream.close();
            socket.close();
        }
    }
}
