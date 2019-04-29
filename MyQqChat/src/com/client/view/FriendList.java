package com.client.view;

import com.client.tools.ClientToServerThread;
import com.client.tools.ManageChatFrame;
import com.client.tools.ManageThread;
import com.common.Message;
import com.common.MsgType;
import com.client.tools.MyTreeCellRenderer;

import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Hashtable;
import javax.swing.*;
import javax.swing.event.TreeModelEvent;
import javax.swing.event.TreeModelListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreePath;

/**
 * 登录成功后的主页面，显示好友列表，未在线好友头像灰色，
 * 双击某好友即可打开与其聊天界面
 * 点击退出按钮即可退出登录
 */
public class FriendList extends JFrame implements ActionListener,MouseListener,TreeModelListener{

	private static final long serialVersionUID = 1L;
    private Container c;//本窗口面板
    private Point tmp,loc;//记录位置
	private boolean isDragged = false;//是否拖拽
    private String ownerId;//本人qq
    private String myName;//本人昵称
    private JTree jtree;//树组件显示好友列表

	public FriendList(String name, String ownerId, Message msg) {
	    this.ownerId = ownerId;
	    this.myName = name;
		//获取本窗体容器
		c = this.getContentPane();
		//设置窗体大小
		this.setSize(274,600);
		//设置布局
		c.setLayout(null);
		//右上角最小化按钮
		JButton btn_min = new JButton(new ImageIcon("image/friendlist/fmin.png"));
		btn_min.setBounds(217, 0, 28, 28);
		btn_min.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//窗体最小化
				setExtendedState(JFrame.ICONIFIED);
			}
		});
		c.add(btn_min);
		//右上角退出按钮
		JButton btn_exit = new JButton(new ImageIcon("image/friendlist/fexit.png"));
		btn_exit.addActionListener(this);
		btn_exit.setBounds(245, 0, 28, 28);
		btn_exit.addActionListener(e->{
            sendUnloadMsgToServer();
        });
		c.add(btn_exit);
		//左上角qq标签
		JLabel jbl_leftTop = new JLabel(new ImageIcon("image/friendlist/biaoti.png"));
		jbl_leftTop.setBounds(0, 0, 44, 21);
		c.add(jbl_leftTop);
		//qq头像
		JLabel jbl_photo = new JLabel(new ImageIcon("image/friendlist/Qtouxiang.jpg"));
		jbl_photo.setBounds(10, 23, 78, 78);
		c.add(jbl_photo);
		//qq昵称
		JLabel jbl_qqName = new JLabel(name+"("+ownerId+")");
		jbl_qqName.setBounds(109, 32, 68, 21);
		c.add(jbl_qqName);
		//个性签名
		JTextField jtf_personalSign = new JTextField();
		jtf_personalSign.setBounds(110, 63, 167, 21);
		c.add(jtf_personalSign);
		//在线状态选择列表
		String[] status = {"在线","隐身","离线"};
		JComboBox<String> online_status = new JComboBox<>(status);
		online_status.setSelectedIndex(0);
		online_status.setBounds(195, 32, 63, 21);
		c.add(online_status);
		//头像下方七个功能按钮
		//按钮1
		JButton btn_h1 = new JButton(new ImageIcon("image/friendlist/tubiao1.png"));
		btn_h1.setBounds(0, 111, 20, 23);
		c.add(btn_h1);
		//按钮2
		JButton btn_h2 = new JButton(new ImageIcon("image/friendlist/tubiao2.png"));
		btn_h2.setBounds(28, 111, 20, 23);
		c.add(btn_h2);
		//按钮3
		JButton btn_h3 = new JButton(new ImageIcon("image/friendlist/tubiao3.png"));
		btn_h3.setBounds(58, 111, 20, 23);
		c.add(btn_h3);
		//按钮4
		JButton btn_h4 = new JButton(new ImageIcon("image/friendlist/tubiao4.png"));
		btn_h4.setBounds(88, 111, 20, 23);
		c.add(btn_h4);
		//按钮5
		JButton btn_h5 = new JButton(new ImageIcon("image/friendlist/tubiao5.png"));
		btn_h5.setBounds(118, 111, 20, 23);
		c.add(btn_h5);
		//按钮6
		JButton btn_h6 = new JButton(new ImageIcon("image/friendlist/tubiao6.png"));
		btn_h6.setBounds(148, 111, 20, 23);
		c.add(btn_h6);
		//按钮7
		JButton btn_h7 = new JButton(new ImageIcon("image/friendlist/tubiao7.png"));
		btn_h7.setBounds(178, 111, 20, 23);
		c.add(btn_h7);
		//搜索框
		JTextField jtf_search = new JTextField();
		jtf_search.setBounds(0, 134, 247, 23);
		c.add(jtf_search);
		//搜索按钮
		JButton btn_search = new JButton(new ImageIcon("image/friendlist/search.png"));
		btn_search.setBounds(247, 134, 30, 23);
		c.add(btn_search);
		
		//上半部分背景图
		JLabel jbl_background = new JLabel(new ImageIcon("image/friendlist/beijing.png"));
		jbl_background.setBounds(0, 0, 277, 156);
		c.add(jbl_background);
		//底部8个功能按钮
		//按钮1
		JButton btn_l1 = new JButton(new ImageIcon("image/friendlist/mainmenue.png"));
		btn_l1.setBounds(0, 577, 30, 23);
		c.add(btn_l1);

		//按钮2
		JButton btn_l2 = new JButton(new ImageIcon("image/friendlist/shezhi.png"));
		btn_l2.setBounds(30, 577, 30, 23);
		c.add(btn_l2);
		//按钮3
		JButton btn_l3 = new JButton(new ImageIcon("image/friendlist/messagemanage.png"));
		btn_l3.setBounds(60, 577, 30, 23);
		c.add(btn_l3);
		//按钮4
		JButton btn_l4 = new JButton(new ImageIcon("image/friendlist/filehleper.png"));
		btn_l4.setBounds(90, 577, 30, 23);
		c.add(btn_l4);
		//按钮5
		JButton btn_l5 = new JButton(new ImageIcon("image/friendlist/shoucang.png"));
		btn_l5.setBounds(120, 577, 30, 23);
		c.add(btn_l5);
		//按钮6
		JButton btn_l6 = new JButton(new ImageIcon("image/friendlist/tubiao8.png"));
		btn_l6.setBounds(150, 577, 30, 23);
		c.add(btn_l6);
		//按钮7
		JButton btn_l7 = new JButton(new ImageIcon("image/friendlist/tubiao9.png"));
		btn_l7.addActionListener(this);
		btn_l7.setBounds(180, 577, 30, 23);
		c.add(btn_l7);
		//按钮8
		JButton btn_l8 = new JButton(new ImageIcon("image/friendlist/apl.png"));
		btn_l8.addActionListener(this);
		btn_l8.setBounds(210, 577, 64, 23);
		c.add(btn_l8);

		//显示好友列表
        initList(this, msg);

		//去除其定义装饰框
		this.setUndecorated(true);
		//设置窗体可见
		this.setVisible(true); 
        //添加鼠标监听事件
        this.addMouseListener(new java.awt.event.MouseAdapter() { 
        	@Override
            public void mouseReleased(MouseEvent e) {  
                isDragged = false;  
                //拖拽结束图标恢复
                setCursor(new Cursor(Cursor.DEFAULT_CURSOR));  
            }  
        	@Override
            public void mousePressed(MouseEvent e) {  
        		//限定范围内可拖拽
            	if(e.getY()<30)
            	{
            		//获取鼠标按下位置
            		tmp = new Point(e.getX(), e.getY());  
            		isDragged = true;
            		//拖拽时更改鼠标图标
            		setCursor(new Cursor(Cursor.MOVE_CURSOR));  
            	}  
            }
        });  
   
        this.addMouseMotionListener(new MouseMotionAdapter() {  
        	@Override
            public void mouseDragged(MouseEvent e) {
                if (isDragged) {  
                	//设置鼠标与窗体相对位置不变
                	loc = new Point(getLocation().x + e.getX() - tmp.x,
                    getLocation().y + e.getY() - tmp.y);  
                    setLocation(loc);  
                }  
            }  

        });
	}

    /**
     * 将下线消息发送到服务器
     */
    public void sendUnloadMsgToServer() {
	    Message msg = new Message();
	    msg.setSenderId(ownerId);
	    msg.setType(MsgType.UNLOAD_LOGIN);
        try {
            ClientToServerThread th = ManageThread.getThread(ownerId);
            ObjectOutputStream out = new ObjectOutputStream(th.getClient().getOutputStream());
            out.writeObject(msg);
            //结束线程
            th.myStop();
            ManageThread.removeThread(ownerId);
            this.dispose();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.exit(0);
    }

    /**
     * 以树形结构显示全部好友列表
     * @param msg
     */
	public void initList(JFrame f, Message msg){
        //用Hashtable创建jtree显示好友列表
        Hashtable<String,Object> ht = new Hashtable<>();
        String[] friends = msg.getContent().split(" ");
        ht.put("我的好友",friends);
        jtree = new JTree(ht);
        jtree.setCellRenderer(new MyTreeCellRenderer(msg));
        jtree.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(e.getClickCount() == 2){
                    JTree tree = (JTree) e.getSource();
                    TreePath path = tree.getSelectionPath();
                    if(null != path){
                        DefaultMutableTreeNode node = (DefaultMutableTreeNode) path.getLastPathComponent();
                        if(node.isLeaf()){
                            String[] info = node.toString().split("\\(");
                            String friendId = info[1].substring(0,info[1].length()-1);//取出id号
                            String frameName = ownerId+friendId;
                            if(ManageChatFrame.getChatFrame(frameName) == null){
                                System.out.println("添加frameName="+frameName);
                                ManageChatFrame.addChatFrame(frameName, new Chat(ownerId, myName, friendId, info[0]));
                            }else{
                                JOptionPane.showMessageDialog(f,"该窗口已存在!");
                            }
                        }
                    }
                }
            }
        });
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setViewportView(jtree);
        scrollPane.setBounds(0, 157, 274, 422);
        c.add(scrollPane);
    }
    /**
     * 刷新在线好友列表
     * @param msg
     */
    public void updateOnlineFriends(Message msg) {
        this.jtree.setCellRenderer(new MyTreeCellRenderer(msg));
    }

	@Override
	public void treeNodesChanged(TreeModelEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void treeNodesInserted(TreeModelEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void treeNodesRemoved(TreeModelEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void treeStructureChanged(TreeModelEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

	}

}

