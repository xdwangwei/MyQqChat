package com.client.model;

import com.client.tools.ClientToServerThread;
import com.client.tools.ManageThread;
import com.common.Message;
import com.common.MsgType;
import com.common.User;

import javax.swing.*;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.regex.Pattern;

/**
 * 对用户输入的登录信息进行校验，符合格式后发送到服务器，并接收校验结果将其返回
 */
public class LoginUser {

    private Socket client;
    private ObjectOutputStream output;
    private ObjectInputStream input;

    public LoginUser() {
        try {
            client = new Socket("localhost", 9999);
            output = new ObjectOutputStream(client.getOutputStream());
            input = new ObjectInputStream(client.getInputStream());
        } catch (IOException e) {
            System.out.println("连接服务器失败！");
            e.printStackTrace();
        }
    }

    /**
     * 检测用户输入信息是否合理
     * 合理则将登录信息封存到对象中返回
     * 不合理则显示提示信息
     *
     * @param f
     * @param uid
     * @param pwd
     * @return
     */
    private User checkLoginInfo(JFrame f, String uid, String pwd) {
        User u = null;
        if (!uid.equals("") && !pwd.equals("")) {
            String pattern = "[0-9]{3,10}";//账号为1-10位数字
            if (Pattern.matches(pattern, uid)) {//账号合法
                u = new User(uid, pwd);
            } else {//账号不合法
                JOptionPane.showMessageDialog(f, "账号为3到10位数字序列，请重新输入！");
            }
        } else if (uid.equals("")) {//账号为空
            JOptionPane.showMessageDialog(f, "请输入账号！");
        } else if (pwd.equals("")) {//密码为空
            JOptionPane.showMessageDialog(f, "请输入密码！");
        }
        return u;
    }

    /**
     * 将通过校验的登录信息发送到服务器
     * 并将得到的消息包返回(包含当前用户的所有好友)
     *
     * @param f
     * @param uid
     * @param pwd
     */
    public Message sendLoginInfoToServer(JFrame f, String uid, String pwd) {
        User u = checkLoginInfo(f, uid, pwd);
        if (u != null) {
            try {
                output.writeObject(u);//发送到服务器
                System.out.println("ok " + u.toString());
                Message msg = (Message) input.readObject();//接收返回结果
                if (msg.getType() == MsgType.LOGIN_SUCCEED) {//登录成功
                    ClientToServerThread th = new ClientToServerThread(client);
                    th.start();//创建与服务器通信线程
                    ManageThread.addThread(uid, th);
                    return msg;
                } else if (msg.getType() == MsgType.LOGIN_FAILED) {
                    JOptionPane.showMessageDialog(f, "账号或密码输入错误，请重新输入！");
                } else if (msg.getType() == MsgType.ALREADY_LOGIN) {
                    JOptionPane.showMessageDialog(f, "该用户已登录，请勿重复操作！");
                }
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

}
