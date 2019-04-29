package com.test;

import com.common.User;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class TestClient {
    public static void main(String[] args) {
        try {
            Socket client = new Socket("localhost",9999);
            ObjectInputStream input = new ObjectInputStream(client.getInputStream());
            ObjectOutputStream output = new ObjectOutputStream(client.getOutputStream());
            User u = new User("111","aaa");
            output.writeObject(u);
            System.out.println("OK");
        } catch (IOException e) {
            System.out.println("客户端连接失败");
            e.printStackTrace();
        }
    }
}
