package com.client.tools;

import com.client.view.Chat;
import com.client.view.FriendList;
import com.common.Message;
import com.common.MsgType;

import javax.swing.*;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * 客户端与服务器通信线程
 */
public class ClientToServerThread extends Thread{

    private Socket client;
    private volatile boolean isRunning;

    public ClientToServerThread(Socket client){
        this.client = client;
        this.isRunning = true;
    }

    public Socket getClient() {
        return client;
    }

    public void myStop(){
        isRunning = false;
    }

    @Override
    public void run() {
        try {
            while(isRunning){
                ObjectInputStream input = new ObjectInputStream(this.client.getInputStream());
                Message msg = (Message) input.readObject();
                //判断消息类型
                if(msg.getType() == MsgType.RET_ONLINE_FRIENDS) {
                    String uid = msg.getGetterId();
                    System.out.println("find FriendList uid="+uid);
                    FriendList fl = ManageFriendListFrame.getFriendListFrame(uid);
                    //第一个用户上线通知其他用户时，其他用户不在线，这里为空
                    if(null != fl){
                        fl.updateOnlineFriends(msg);
                    }
                }else if(msg.getType() == MsgType.COMMON_MESSAGE) {
                    String frameName = msg.getGetterId()+msg.getSenderId();
                    System.out.println("find Chat framename="+frameName);
                    ManageChatFrame.getChatFrame(frameName).showMessage(msg,false);
                }else if(msg.getType() == MsgType.NOT_ONLINE) {
                    Chat chat = ManageChatFrame.getChatFrame(msg.getSenderId() + msg.getGetterId());
                    JOptionPane.showMessageDialog(chat, "该好友未上线，暂未实现离线聊天功能！");
                }else if(msg.getType() == MsgType.SERVER_CLOSE){
                    String toId = msg.getGetterId();
                    //自动下线
                    ManageFriendListFrame.getFriendListFrame(toId).sendUnloadMsgToServer();
                    ManageThread.removeThread(toId);
                    ManageFriendListFrame.removeFriendListFrame(toId);
                }
            }

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
