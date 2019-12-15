# MyQqChat
a simple QQ based on Java

# qq聊天项目使用简介：


### **项目环境配置**


 1. 软件开发工具：IntelliJ IDEA 2018(刚开始在eclipse，中途移过来了，现在不确定能不能在eclipse上运行)
 2. 数据库：MySQL8.0
 3. 通信协议：tcp协议
 4. 项目结构：客户端/服务器结构
 5. 功能实现:
    - a. 可与不同好友同时在线聊天
    - b. 可与不同好友同时在线聊天
    - c. 能够区别显示在线离线好友头像
    - d. 能够实时显示聊天记录
 6. 项目启动流程：
    - a. 运行ServerFrame.java，点击开启服务器
    - b. 运行Login.java即可进行登录
    - c. 登录成功进入主页面，双击在线好友即可打开聊天界面
    - d. 多人在线，多次运行Login.java进行登录即可

**注意事项：**
1. 本项目使用编码格式：gbk
2. 数据形式：无论何种信息，都封装成对象形式，登录时发送的是User对象，之后全部为Message对象
3. 本项目未实现离线聊天功能，可打开与离线好友的聊天界面，并可发送消息，但对方无法收到
4. 与好友聊天需双方均打开聊天界面再进行聊天:
比如小明双击小红打开界面，小红双击小明打开界面，之后二人正常开始聊天;如果小明打开与小红聊天界面，发送消息，此时小红未打开与小明的聊天界面，则小红无法收到此消息。

### **项目代码结构如下：**
![](/screenshot/00.png)
![](/screenshot/01.png)


#### **客户端代码结构：**


 - package com.client.view

		Login.java//用户登录界面
		Chat.java//好友聊天界面
		FriendList.java//登录成功主页面
	
 - package com.client.tools

		ClientToServerThread.java//登录成功后开一个线程保持与服务器通讯，接收消息
		ManageThread.java//管理所有与服务器通讯的线程
		ManageChatFrame.java//管理所有打开的聊天界面
		ManageFriendListFrame.java//管理所有的主页面(显示好又列表的界面)
		MyTreeCellRender.java//设置树形结构显示好友时在线离线的不同显示
	
 - package com.client.model

		LoginUser.java//检验用户登录信息并发送到服务器接收结果
		
#### **服务器端代码结构：**


 - package com.server.view

		ServerFrame.java//服务器开启关闭界面
	
 - package com.server.tools

		JDBC_Util.java//所有操作数据库的方法全部在此类实现
		ServerConClientThread.java//客户端登录成功服务器开一个线程与此客户端保持通讯
		ManaClientThread.java//管理所有与客户端通信的线程
	
 - package com.server.model

		Server.java//用户对客户端发起登录请求进行处理，验证身份
#### **公共包**：


 - com.common

		Message.java//统一数据发送格式以对象序列化发送
		User.java//用户类
		MsgType.java//定义不同信息类型

### **数据库配置**：
#### ****创建数据库 chat****
#### **创建表**：

 - t_user保存已有的用户信息
 - t_friends保存用户的好友信息

![](/screenshot/1.png)
![](/screenshot/2.png)

![](/screenshot/3.png)![](/screenshot/4.png)
![](/screenshot/5.png)
![](/screenshot/6.jpg)

### **项目运行截图如下：**
![](/screenshot/s1.png)
![](/screenshot/s2.png)
![](/screenshot/s3.jpg)
![](/screenshot/s4.jpg)
![](/screenshot/s6.jpg)

### **补充以下：**

 - 目前没有提供注册账号功能，因为注册成功还必须添加好友，要不一个空列表没有什么意义。
 - 只有数据库chat中t_user表中用户才能登录成功。
 - 程序对用户输入信息进行各种检验，效果挺好。
 - 项目未实现离线聊天功能，选择与不在线好友聊天会有相应提示
![](/screenshot/o1.jpg)
![](/screenshot/o2.jpg)
![](/screenshot/o4.jpg)
![](/screenshot/o5.jpg)

### **总结：**
只实现了最基本的功能，有很很大部分功能并未实现，感兴趣的朋友可以自己扩展。


