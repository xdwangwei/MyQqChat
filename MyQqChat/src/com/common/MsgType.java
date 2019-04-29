package com.common;

/**
 * 定义通信涉及到的全部消息类型
 */
public enum MsgType {
    LOGIN_SUCCEED,//登录成功
    LOGIN_FAILED,//登录失败
    ALREADY_LOGIN,//已登录
    UNLOAD_LOGIN,//退出登录
    GET_ONLINE_FRIENDS,//获取在线好友列表
    RET_ONLINE_FRIENDS,//返回在线好友
    NOT_ONLINE,//不在线
    SERVER_CLOSE,//服务器关闭
    COMMON_MESSAGE//普通信息
}
