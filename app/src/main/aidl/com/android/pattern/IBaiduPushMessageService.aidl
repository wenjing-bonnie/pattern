// IBaiduPushMessageService.aidl
package com.android.pattern;
import com.android.pattern.proxy.aidl.BaiduMessage;

// Declare any non-default types here with import statements
//用来定义百度推送接收和发送消息

interface IBaiduPushMessageService {
    /**
    * 设置要发送的Message的标题
    **/
    void setBaiduMessageTitle(String msg);
    /**
    *获取服务器最近一次推送的Message的标题
    **/
    String getBaiduMessageTitle();
//in表示数据只能从客户端传向服务端,客户端的那个对象不会因为服务器对传参的修改而发生改动
//out表示服务端接收到的是一个空对象，但是服务端对空对象的修改会同步到客户端
//inout表示双向流通,服务端接收的是客户端传过来的完整对象，并且服务端的修改会同步到客户端
    void setBaiduMessage(in BaiduMessage msg);
    BaiduMessage getBaiduMessage();

}