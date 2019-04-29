package com.server.tools;

import com.common.Message;
import com.common.MsgType;
import com.server.view.ServerFrame;


import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * 服务器与客户端通信线程
 */
public class ServerConClientThread extends Thread {

    private Socket client;
    private volatile boolean isRunning;

    public ServerConClientThread(Socket client) {
        this.client = client;
        this.isRunning = true;
    }

    public Socket getClient() {
        return client;
    }

    public void myStop(){
        isRunning = false;
    }

    /**
     * 将自己上线或下线的消息通知好友
     * @param uid
     */
    public void notifyOthers(String uid){
        ObjectOutputStream out = null;
        Message msg = new Message();
        msg.setType(MsgType.RET_ONLINE_FRIENDS);
        msg.setContent(ManageClientThread.getOnLineList());
        for (Object o : ManageClientThread.getClientThreads().keySet()) {
            try {
                String id = o.toString();
                if(!id.equals(uid)){//不用通知自己
                    msg.setGetterId(id);
                    out = new ObjectOutputStream(ManageClientThread.getClientThread(id).client.getOutputStream());
                    out.writeObject(msg);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    @Override
    public void run() {
        try {
            while(isRunning){
                ObjectInputStream input = new ObjectInputStream(this.client.getInputStream());
                Message msg = (Message) input.readObject();
                //判断消息类型
                if(msg.getType() == MsgType.GET_ONLINE_FRIENDS) {
                    msg.setType(MsgType.RET_ONLINE_FRIENDS);
                    msg.setGetterId(msg.getSenderId());
                    msg.setContent(ManageClientThread.getOnLineList());
                    ObjectOutputStream output = new ObjectOutputStream(ManageClientThread.getClientThread(msg.getGetterId()).client.getOutputStream());
                    output.writeObject(msg);
                    System.out.println("返回列表成功");
                }else if(msg.getType() == MsgType.COMMON_MESSAGE) {
                    System.out.println(msg.toString());
                    ServerConClientThread thread = ManageClientThread.getClientThread(msg.getGetterId());//找到接收者的线程
                    if(null == thread){//该用户不在线
                        //通知发送者好友不在线
                        ObjectOutputStream output = new ObjectOutputStream(ManageClientThread.getClientThread(msg.getSenderId()).client.getOutputStream());
                        msg.setType(MsgType.NOT_ONLINE);
                        output.writeObject(msg);
                        System.out.println("通知成功");
                    }else{
                        ObjectOutputStream output = new ObjectOutputStream(thread.client.getOutputStream());
                        output.writeObject(msg);
                        System.out.println("转发成功");
                    }
                }else if(msg.getType() == MsgType.UNLOAD_LOGIN) {
                    String fromId = msg.getSenderId();
                    //结束此线程
                    myStop();
                    ManageClientThread.removeClientThread(fromId);
                    notifyOthers(fromId);
                    System.out.println(fromId+" 退出登录");
                    ServerFrame.showMsg("用户"+fromId+"退出登录！");
                }

            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}