package com.rogchen.study.tomcatlearning.servlet;

import com.rogchen.study.tomcatlearning.http.Request;
import com.rogchen.study.tomcatlearning.http.Response;

import javax.servlet.ServletException;
import java.io.IOException;

/**
 * @Description:
 * @Product: IntelliJ IDEA
 * @Author Rogchen rogchen128@gmail.com
 * @Created Date: 19-3-19 22:49
 **/
public interface IServlet {
    void init();

    void service(Request var1, Response var2) throws ServletException, IOException;

    void destroy();
}
