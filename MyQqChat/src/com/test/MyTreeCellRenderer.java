package com.test;
import java.awt.Component;

import com.common.Message;
import com.common.MsgType;
import javax.swing.ImageIcon;
import javax.swing.JTree;  
import javax.swing.tree.DefaultMutableTreeNode;  
import javax.swing.tree.DefaultTreeCellRenderer;  
  
/** 
 * 自定义树描述类,将树的每个节点设置成不同的图标 
 * @author RuiLin.Xie - xKF24276 
 * 
 */  
public class MyTreeCellRenderer extends DefaultTreeCellRenderer {

    private static final long   serialVersionUID    = 1L;
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
        		this.setIcon(new ImageIcon("image/friendlist/qq.png"));
        		for(int i=0;i<onlineFriends.length;i++) {
        			if(str.equals(onlineFriends[i]))
        				this.setIcon(new ImageIcon("image/friendlist/qq.gif"));
        		}

        	}else
        		this.setIcon(new ImageIcon("image/friendlist/lie.png"));
        }
        return this;  
    }  
  
}  