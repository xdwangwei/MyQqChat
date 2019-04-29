package com.test;

import javax.swing.*;
import javax.swing.tree.DefaultTreeCellRenderer;
import java.awt.*;
import java.util.Hashtable;

public class Test {
    public static void main(String[] args) {
        JFrame f = new JFrame();
        f.setTitle("JFrame窗口");//设置标题
        f.setBounds(150, 150, 300, 300);//设置窗体坐标及大小,单位:像素
        f.setVisible(true);//设置窗体可见
        f.setResizable(false);//设置窗体大小不可变
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//设置窗体关闭方式

        Container c = f.getContentPane();//获取此窗体容器,用以添加其他组件
//        //添加文本标签并居中显示
//        JLabel l = new JLabel("我就是个标签!!!",SwingConstants.CENTER);
//        c.add(l);
//        Icon imag = new ImageIcon("image/friendlist/qq.png");
//        l.setIcon(imag);
//        l.setText("我被改变了！");//修改标签内容
//        System.out.println(l.getText());//获取标签内容
//        //l.setFont(new Font("微软雅黑",Font.BOLD,18));//(加粗)设置标签字体(类型+样式+大小)
//        l.setForeground(Color.RED);//更改标签前景色,即字体颜色
        Hashtable<String,Object> ht = new Hashtable<>();
        String[] friends = {"10055","10077","10099","10011","10012"};
        JLabel[] jlbs = new JLabel[friends.length];
        Icon imag = new ImageIcon("image/friendlist/qq.png");
//        for(int i=0; i<friends.length; i++){
//            jlbs[i] = new JLabel(friends[i]);
//            jlbs[i].setIcon(imag);
//        }
        ht.put("我的好友",friends);
        JTree jtree = new JTree(ht);
        DefaultTreeCellRenderer renderer = (DefaultTreeCellRenderer) jtree.getCellRenderer();
/*        renderer.setLeafIcon(null);
        renderer.setClosedIcon(new ImageIcon("image/friendlist/lie.png"));
        renderer.setOpenIcon(new ImageIcon("image/friendlist/lie.png"));*/
        //jtree.setCellRenderer(new MyTreeCellRenderer());
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setViewportView(jtree);
        scrollPane.setBounds(0, 0, 400, 200);
        f.add(scrollPane);

    }
}
