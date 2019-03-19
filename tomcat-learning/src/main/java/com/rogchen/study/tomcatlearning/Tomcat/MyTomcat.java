package com.rogchen.study.tomcatlearning.Tomcat;

import com.rogchen.study.tomcatlearning.servlet.HttpServlet;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.File;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description:
 * @Product: IntelliJ IDEA
 * @Author Rogchen rogchen128@gmail.com
 * @Created Date: 19-3-19 22:56
 **/
public class MyTomcat {
    //使用hashMap存储，初始化servlet信息
    public static Map<String, HttpServlet> servletMap = new HashMap<>();

    public static void main(String[] args) throws Exception {
        //第一步骤 初始化servlet
        //资源路径
        String basePath;
        basePath = MyTomcat.class.getResource("/").getPath();
        //1、获取解析器
        SAXReader reader = new SAXReader();
        //2、获取document对象
        Document document = reader.read(new File(basePath + "web.xml"));
        //3、获取根元素
        Element root = document.getRootElement();
        //4、获取根元素下的元素
        List<Element> children = root.elements();
        //5、遍历子元素
        for (Element e : children) {
            if("servlet".equalsIgnoreCase(e.getName())){
                //7、获取servlet-name元素
                Element servletName = e.element("servlet-name");
                //8、获取servlet-class元素
                Element servletClass = e.element("servlet-class");
                System.out.println(servletName.getText()+":="+servletClass.getText());
                servletMap.put(servletName.getText(), (HttpServlet) Class.forName(servletClass.getText()).newInstance());
            }
        }
        //第二阶段---暴露接口，处理请求
        ServerSocket serverSocket = new ServerSocket(8081);
        System.out.println("Tomcat 正式版服务器已经启动了！");
        while (true){
            Socket socket = serverSocket.accept();
            Thread thread = new ServletThreadProcess(socket);
            thread.start();
        }
    }
}
