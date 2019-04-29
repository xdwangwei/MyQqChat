package com.test;
/**
 *
 */

import com.common.User;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class TestServer {
    public static void main(String[] args) {
        try {
            ServerSocket server = new ServerSocket(9999);
            //2.阻塞式等待客户端连接  (返回值)Socket accept()侦听要连接到此套接字的客户端并接受它。
            Socket client = server.accept();
            System.out.println("一个客户端已连接....");
            ObjectOutputStream output = new ObjectOutputStream(client.getOutputStream());
            ObjectInputStream input = new ObjectInputStream(client.getInputStream());
            User u = (User) input.readObject();
            System.out.println(u.toString());
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("服务器启动失败！");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
