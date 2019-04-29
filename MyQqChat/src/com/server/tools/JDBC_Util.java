package com.server.tools;

import com.common.User;

import java.sql.*;

/**
 * 建立一个用于JDBC连接MySql的工具类
 * 实现所有对数据库的操作，对外提供方法
 */
public class JDBC_Util {
	private Connection con;
	private PreparedStatement ps;
	private ResultSet rs;

	public JDBC_Util(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            //建立连接
            con = DriverManager.getConnection("jdbc:mysql://localhost/chat?useSSL=FALSE&serverTimezone=UTC","root","123456");
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    /**
     * 根据uid和pwd在数据库中核实用户信息
     * 并将qq昵称返回
     * @param u
     * @return
     */
    public String checkUserInfo(User u){
        String qname = null;
        int uid = Integer.parseInt(u.getUid());
        String pwd = u.getPwd();
        String sql = "select uname from  t_user where uid=? and pwd=?";
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1,uid);
            ps.setString(2,pwd);
            rs = ps.executeQuery();
            while (rs.next()){
                qname = rs.getString("uname");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            release(con,ps,rs);
        }
        return qname;
    }
    /**
     * 返回全部好友列表
     * @return
     */
    public String getFriendsList(String uid){
        StringBuilder contents = new StringBuilder();
        int mqq = Integer.parseInt(uid);
        String sql = "select fname,fqq from  t_friends where mqq=?";
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1,mqq);
            rs = ps.executeQuery();
            while(rs.next()){
                contents.append(rs.getString("fname")+"("+rs.getString("fqq")+") ");
            }
            return contents.toString();
        } catch (SQLException e) {
            e.printStackTrace();
            release(con,ps,rs);
        }
        return null;
    }
    /**
     * 释放资源
     * @param conn
     * @param st
     * @param rs
     */
	public void release(Connection conn,Statement st,ResultSet rs) {
		//关闭资源
		try {
			if(null != rs)
				rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			if(null != st)
				st.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			if(null != conn)
				conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

    /**
     * 释放资源重载
     * @param conn
     * @param st
     */
	public void release(Connection conn,Statement st) {
		//关闭资源
		try {
			if(null != st)
				st.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			if(null != conn)
				conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
