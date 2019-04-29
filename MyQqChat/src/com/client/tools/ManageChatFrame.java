package com.client.tools;

import java.util.Hashtable;

import com.client.view.Chat;

/**
 * 管理全部客户端打开的的聊天界面
 */
public class ManageChatFrame {
    private static Hashtable<String,Chat> chatFrames = new Hashtable<>();

    public static void addChatFrame(String frameName,Chat chat){
    	chatFrames.put(frameName,chat);
    }

    public static Chat getChatFrame(String frameName){
        return chatFrames.get(frameName);
    }
    
    public static Chat removeChatFrame(String frameName){
        return chatFrames.remove(frameName);
    }
}
