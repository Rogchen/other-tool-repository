package com.rogchen.study.tomcatlearning.http;

import java.io.OutputStream;

/**
 * @Description:
 * @Product: IntelliJ IDEA
 * @Author Rogchen rogchen128@gmail.com
 * @Created Date: 19-3-19 22:31
 **/
public class Response {
    public OutputStream outputStream;

    public static String responseHeader = "HTTP/1.1 200 \r\n"
            + "Content-Type:text/html;charset=utf-8\r\n" +
            "\r\n";

    public Response(OutputStream outputStream) {
        this.outputStream = outputStream;
    }
}
