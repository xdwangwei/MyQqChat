
package com.server.view;

import com.common.Message;
import com.common.MsgType;
import com.server.model.Server;
import com.server.tools.ManageClientThread;
import com.server.tools.ServerConClientThread;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 服务器启动关闭界面
 */
public class ServerFrame extends JFrame implements ActionListener ,MouseListener{

	private static final long serialVersionUID = 1L;

	JButton btn_start, btn_close;//功能按钮
	public static JTextArea textArea_log;//日志记录面板
	private JLabel jlb_border1, jlb_border2, jlb_border3, jlb_border4, jlb_border5;//用于界面分割
	private JLabel label_log;//日志记录标签
    private static DateFormat df;//日期解析
    private Server s;

	public static void main(String[] args) {
		new ServerFrame();
	}

	public ServerFrame()
	{
        df = new SimpleDateFormat("yyyy-MM-dd a hh:mm:ss");
		//获取窗口容器
        Container c = this.getContentPane();
        //设置布局
        c.setLayout(null);
        //左上部分
        label_log = new JLabel();
        label_log.setFont(new Font("微软雅黑",Font.BOLD,14));
        label_log.setText("系统日志记录");
        label_log.setBounds(155, 10, 100, 15);
        c.add(label_log);
        //日志记录面板
        textArea_log = new JTextArea();
        JScrollPane scrollPane_log = new JScrollPane(textArea_log);
        scrollPane_log.setBounds(10, 33, 377, 376);
        c.add(scrollPane_log);

        //服务器启动按钮
		btn_start = new JButton("启动服务器");
		btn_start.setBounds(45, 450, 120, 24);
		btn_start.addActionListener(this);
		c.add(btn_start);
		//服务器关闭按钮
		btn_close = new JButton("关闭服务器");
		btn_close.setBounds(230, 450, 120, 24);
		btn_close.addActionListener(this);
		c.add(btn_close);

        //界面分割标签1
		jlb_border1 = new JLabel();
		jlb_border1.setBounds(392, 0, 2, 500);
		jlb_border1.setOpaque(true);//不透明
		jlb_border1.setBackground(Color.GREEN);
		c.add(jlb_border1);
        //界面分割标签2
		jlb_border2 = new JLabel();
		jlb_border2.setBounds(5, 425, 404, 2);
		jlb_border2.setOpaque(true);//不透明
		jlb_border2.setBackground(Color.GREEN);
		c.add(jlb_border2);
        //界面分割标签3
		jlb_border3 = new JLabel();
		jlb_border3.setBounds(3, 0, 2, 502);
		jlb_border3.setOpaque(true);//不透明
		jlb_border3.setBackground(Color.GREEN);
		c.add(jlb_border3);
        //界面分割标签4
        jlb_border4 = new JLabel();
        jlb_border4.setOpaque(true);
        jlb_border4.setBackground(Color.GREEN);
        jlb_border4.setBounds(5, 500, 404, 2);
        c.add(jlb_border4);
        //界面分割标签5
		jlb_border5 = new JLabel();
		jlb_border5.setBounds(5, 0, 404, 2);
		jlb_border5.setOpaque(true);//不透明
		jlb_border5.setBackground(Color.GREEN);
		c.add(jlb_border5);

		this.setResizable(false);//设置页面大小固定
		this.setSize(411, 560);//设置大小
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);//默认关闭方式
		this.setVisible(true);//页面可见
	}

	public void mouseClicked(MouseEvent e) {

	}

	public void mousePressed(MouseEvent e) {

	}

	public void mouseReleased(MouseEvent e) {

	}

	public void mouseEntered(MouseEvent e) {

	}

	public void mouseExited(MouseEvent e) {

	}

    /**
     * 处理按钮点击事件
     * @param e
     */
	@Override
	public void actionPerformed(ActionEvent e) {

        if(e.getSource() == btn_start){//启动服务器
            s = new Server();
            showMsg("服务器启动...");
        }
        if(e.getSource() == btn_close){//关闭服务器
            beforeServerClose();
            showMsg("服务器已关闭...");
        }
	}

    /**
     * 用于在日志面板显示信息
     * @param s
     */
    public static void showMsg(String s) {
        textArea_log.append(df.format(new Date())+": "+s+"\n"+"-".repeat(92)+"\n\n");
    }

    /**
     * 关闭服务器前，通知所有用户，并结束所有线程
     */
    private void beforeServerClose(){
        Message msg = new Message();
        msg.setType(MsgType.SERVER_CLOSE);
        for(Object o: ManageClientThread.getClientThreads().keySet()){
            String toId = o.toString();
            msg.setGetterId(toId);
            ServerConClientThread th = ManageClientThread.getClientThread(toId);
            try {
                ObjectOutputStream out = new ObjectOutputStream(th.getClient().getOutputStream());
                out.writeObject(msg);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            Thread.sleep(1000);//等待所有客户端下线
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        s.myStop();//服务器停止运行
    }

}
