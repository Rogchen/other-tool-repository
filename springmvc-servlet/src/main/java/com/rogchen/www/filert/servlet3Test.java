package com.rogchen.www.filert;

import javax.servlet.AsyncContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

/**
 * @Description: servlet3测试类
 * @Product: IntelliJ IDEA
 * @Author Rogchen rogchen128@gmail.com
 * @Created Date: 2018/8/24 16:13
 **/
@WebServlet(urlPatterns = "/ws/demo.sjson",asyncSupported = true)
public class servlet3Test extends HttpServlet{


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");
        PrintWriter out = resp.getWriter();
        out.println("进入Servlet的时间：" + new Date().toLocaleString() + ".");
        out.flush();

        //在子线程中执行业务调用，并由其负责输出响应，主线程退出
        AsyncContext ctx = req.startAsync();
        new Thread(new Executor(ctx)).start();

        out.println("结束Servlet的时间：" + new Date() + ".");
        out.flush();

    }
}

 class Executor implements Runnable {
    private AsyncContext ctx = null;
    public Executor(AsyncContext ctx){
        this.ctx = ctx;
    }

    public void run(){
        try {
            //等待十秒钟，以模拟业务方法的执行
            Thread.sleep(10000);
            PrintWriter out = ctx.getResponse().getWriter();
            out.println("业务处理完毕的时间：" + new Date() + ".");
            out.flush();
            ctx.complete();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}