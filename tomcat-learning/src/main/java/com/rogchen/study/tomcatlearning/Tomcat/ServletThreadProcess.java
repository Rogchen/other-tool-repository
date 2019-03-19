package com.rogchen.study.tomcatlearning.Tomcat;

import com.rogchen.study.tomcatlearning.http.Request;
import com.rogchen.study.tomcatlearning.http.Response;
import com.rogchen.study.tomcatlearning.servlet.HttpServlet;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

/**
 * @Description: 线程执行类-servlet线程处理
 * @Product: IntelliJ IDEA
 * @Author Rogchen rogchen128@gmail.com
 * @Created Date: 19-3-19 22:43
 **/
public class ServletThreadProcess extends Thread {

    protected Socket socket;    //连接

    public ServletThreadProcess(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            System.out.println("执行线程是："+Thread.currentThread());
            InputStream inputStream = socket.getInputStream();
            Request request = new Request(inputStream); //拿到请求封装
            Response response = new Response(socket.getOutputStream()); //拿到相应封装
            //servlet来处理
            HttpServlet httpServlet = MyTomcat.servletMap.get(request.getUrl().replace("/",""));
            if(httpServlet !=null){ //访问servlet命中了
                httpServlet.service(request,response);
            }else {
                String res = Response.responseHeader + "无定义的servlet输出!";
                OutputStream outputStream = socket.getOutputStream();
                outputStream.write(res.getBytes("utf-8"));
                outputStream.flush();
                outputStream.close();
            }
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
