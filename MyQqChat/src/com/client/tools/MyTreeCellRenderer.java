package com.client.tools;

import com.common.Message;
import com.common.MsgType;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import java.awt.*;

/** 
 * 自定义树描述类,将树的每个节点设置成不同的图标
 * 主要用于对好友是否在线的区分显示
 * 
 */  
public class MyTreeCellRenderer extends DefaultTreeCellRenderer {

    private static final long serialVersionUID = 1L;
    private Message msg;

    public MyTreeCellRenderer(Message msg){
        this.msg = msg;
    }

    /**
     * 重写getTreeCellRendererComponent()
     */
    public Component getTreeCellRendererComponent(JTree tree, Object value,
            boolean sel, boolean expanded, boolean leaf, int row,
            boolean hasFocus) {

        //执行父类原型操作
        super.getTreeCellRendererComponent(tree, value, sel, expanded, leaf,
                row, hasFocus);

        //得到每个节点的TreeNode
        DefaultMutableTreeNode node = (DefaultMutableTreeNode) value;
        //得到每个节点的text
        String str = node.toString();
        //登录成功时初始化列表
		if(msg.getType() == MsgType.LOGIN_SUCCEED) {
            if (node.isLeaf()) {
                this.setIcon(new ImageIcon("image/friendlist/qq.png"));
            }else
                this.setIcon(new ImageIcon("image/friendlist/lie.png"));
        //已获取到在线好友列表
        }else if(msg.getType() == MsgType.RET_ONLINE_FRIENDS) {
        	String [] onlineFriends = msg.getContent().split(" ");
        	if (node.isLeaf()) {
                //得到其中的id部分
                str = str.split("\\(")[1];
                str = str.substring(0,str.length()-1);
        		this.setIcon(new ImageIcon("image/friendlist/qq.png"));
                for (String onlineFriend : onlineFriends) {//进行更新操作
                    if (str.equals(onlineFriend))
                        this.setIcon(new ImageIcon("image/friendlist/qq.gif"));
                }

        	}else
        		this.setIcon(new ImageIcon("image/friendlist/lie.png"));
        }
        return this;  
    }  
  
}  