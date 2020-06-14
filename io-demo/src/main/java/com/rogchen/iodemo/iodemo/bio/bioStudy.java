package com.rogchen.iodemo.iodemo.bio;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Description:
 * @Product: IntelliJ IDEA
 * @Author Rogchen rogchen128@gmail.com
 * @Created Date: 19-3-21 22:00
 **/
public class bioStudy {

    public static void main(String[] args) throws IOException {
        //单线程模式--缺点：无法使用并发
//        useBuffer();
//        userScanner();
        //多线程模式--缺点：每次请求都需要获取线程，如果线程池满了还是阻塞住了。
        useThread();
    }

    private static void useThread() throws IOException {
        ExecutorService executor = Executors.newScheduledThreadPool(3);
        ServerSocket serverSocket = new ServerSocket(9092);
        System.out.println("BIO service has start,listening on port :" + serverSocket.getLocalSocketAddress());
        while (true) {
            Socket socket = serverSocket.accept();
            executor.submit(new Runnable() {
                @Override
                public void run() {
                    try {
                        System.out.println("Connection from " + socket.getRemoteSocketAddress());
                        Scanner scanner = new Scanner(socket.getInputStream());
                        String msg = null;
                        while (true) {
                            msg = scanner.nextLine();
                            if ("quit".equalsIgnoreCase(msg)) {
                                break;
                            }
                            System.out.println(String.format("client input: %s : %s", socket.getRemoteSocketAddress(), msg));
                            String text = "service return: " + msg + "\r\n";
                            socket.getOutputStream().write(text.getBytes());
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    }




    //使用buffer来作为输入接收
    private static void useBuffer() throws IOException {
        ServerSocket serverSocket = new ServerSocket(9090);
        System.out.println("BIO service has start,listening on port :" + serverSocket.getLocalSocketAddress());
        while (true) {
            Socket socket = serverSocket.accept();
            System.out.println("Connection from " + socket.getRemoteSocketAddress());
            InputStream inputStream = socket.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "utf-8"));
            String msg = null;
            while ((msg = bufferedReader.readLine()) != null) {
                if (msg.length() == 0) {    //如果信息为空
                    continue;
                }
                if ("quit".equalsIgnoreCase(msg)) {
                    break;
                }
                System.out.println("Client input :" + msg);
                OutputStream outputStream = socket.getOutputStream();
                String text = "service return: " + msg + "\r\n";
                outputStream.write(text.getBytes());
            }
            socket.close();
        }
    }

    public static void userScanner() throws IOException {
        ServerSocket serverSocket = new ServerSocket(9091);
        System.out.println("BIO service has start,listening on port :" + serverSocket.getLocalSocketAddress());
        Socket socket = serverSocket.accept();
        System.out.println("Connection from " + socket.getRemoteSocketAddress());
        Scanner scanner = new Scanner(socket.getInputStream());
        while (scanner.hasNext()) {
            String msg = scanner.nextLine();
            if ("quit".equalsIgnoreCase(msg)) {
                break;
            }
            System.out.println(String.format("client input: %s:%s", socket.getRemoteSocketAddress(), msg));
            String text = "service return: " + msg + "\r\n";
            socket.getOutputStream().write(text.getBytes());
        }
        socket.close();

    }
}
