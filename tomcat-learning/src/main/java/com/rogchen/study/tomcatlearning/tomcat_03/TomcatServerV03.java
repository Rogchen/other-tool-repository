package com.rogchen.study.tomcatlearning.tomcat_03;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @Description: 使用对象来完成输入输出，加入线程bio处理
 * @Product: IntelliJ IDEA
 * @Author Rogchen rogchen128@gmail.com
 * @Created Date: 19-3-19 22:15
 * @version v0.3
 **/
public class TomcatServerV03 {

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(8081); //暴露服务的端口
        System.out.println("tomcat v0.3 服务器已经启动了！");
        while (true) {  //无限--等待请求
            //使用socket建立请求，如果客户端已经连接上。
            Socket socket = serverSocket.accept();
            //建立线程
            Thread thread = new ThreadProcess(socket);
            thread.start();
        }
    }
}
