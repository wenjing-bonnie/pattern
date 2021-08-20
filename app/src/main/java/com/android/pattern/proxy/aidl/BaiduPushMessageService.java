package com.android.pattern.proxy.aidl;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;

import androidx.annotation.Nullable;

import com.android.common.Log;
import com.android.pattern.IBaiduPushMessageService;

/**
 * Created by wenjing.liu on 2021/4/8 in J1.
 * 用来模拟:
 * 客户端将要发出的消息发送给{@link BaiduPushMessageService}，{@link BaiduPushMessageService}完成向服务器注册；
 * {@link BaiduPushMessageService}接收到服务器发送过来的消息，并且将消息返回给客户端{@link BaiduPushMessageActivity}
 * 服务端的相关代码
 *
 * @author wenjing.liu
 */
public class BaiduPushMessageService extends Service {
    BaiduPushMessageBinder binder;

    @Override
    public void onCreate() {
        super.onCreate();
        binder = new BaiduPushMessageBinder();
        Log.d("onCreate = "+System.currentTimeMillis());
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.d("onBind = "+System.currentTimeMillis());
        return binder;
    }

    /**
     * 具体服务的功能实现
     */
    public class BaiduPushMessageBinder extends IIBaiduPushMessageService.Stub {

        @Override
        public void setBaiduMessageTitle(String msg) throws RemoteException {
            sendMessageToHttpServer(msg);
        }

        @Override
        public String getBaiduMessageTitle() throws RemoteException {
            return getBaiduMessage().getTitle();
        }

        @Override
        public void setBaiduMessage(BaiduMessage msg) throws RemoteException {
            sendMessageToHttpServer(msg);
        }

        @Override
        public BaiduMessage getBaiduMessage() throws RemoteException {
            return getMessageFromHttpServer();
        }

        /**
         * 模拟将消息发送给服务器
         *
         * @param message
         */
        private void sendMessageToHttpServer(BaiduMessage message) {
            Log.d(message.toString());
            Log.v("已经发给服务器保存!");
        }

        /**
         * 模拟从服务器返回的需要Client显示的消息
         *
         * @return
         */
        private BaiduMessage getMessageFromHttpServer() {
            return new BaiduMessage("从服务器返回的标题", "从服务器返回的内容");
        }

        /**
         * 模拟将消息发送给服务器
         *
         * @param title
         */
        private void sendMessageToHttpServer(String title) {
            BaiduMessage message = new BaiduMessage(title, "");
            sendMessageToHttpServer(message);
        }
    }

}
