package com.rogchen.study.tomcatlearning.servlet;

import com.rogchen.study.tomcatlearning.http.Request;
import com.rogchen.study.tomcatlearning.http.Response;

import javax.servlet.ServletException;
import java.io.IOException;

/**
 * @Description: http请求的servlet
 * @Product: IntelliJ IDEA
 * @Author Rogchen rogchen128@gmail.com
 * @Created Date: 19-3-19 22:50
 **/
public abstract class HttpServlet implements IServlet {
    @Override
    public void init() {

    }

    @Override
    public void service(Request var1, Response var2) throws ServletException, IOException {
        if ("get".equalsIgnoreCase(var1.getMethod())) {
            doGet(var1, var2);
        } else {
            doPost(var1, var2);
        }
    }

    public abstract void doGet(Request var1, Response var2);

    public abstract void doPost(Request var1, Response var2);

    @Override
    public void destroy() {

    }
}
