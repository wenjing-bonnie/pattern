package com.android;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.android.common.BinderThreadPool;
import com.android.common.Log;
import com.android.pattern.R;
import com.android.pattern.adaptermediastore.DownloadQScopedStorage;
import com.android.pattern.adaptermediastore.IHandlerFileOnQScopedStorage;
import com.android.pattern.proxy.aidl.BaiduPushMessageActivity;
import com.android.pattern.proxy.staticproxy.StaticProxyActivity;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BinderThreadPool.getAllBinderThread(getClass().getSimpleName());
    }

    public void staticProxy(View view) {
        Intent intent = new Intent(MainActivity.this, StaticProxyActivity.class);
        startActivity(intent);
    }

    public void baiduService(View view) {
        Intent intent = new Intent(MainActivity.this, BaiduPushMessageActivity.class);
        startActivity(intent);
    }

    public void adapterMediaStore(View view) {
        String content = "123456789";
        String fileName = "123.txt";
        Log.d(String.format("将 %s 正在写入文件", content));
        IHandlerFileOnQScopedStorage storage = new DownloadQScopedStorage();
        storage.writeAndAppend(MainActivity.this, fileName, content);
        content = "abcdefghigklmnopqrstuvwxyz";
        Log.d(String.format("将 %s 正在追加写入文件", content));
        storage.writeAndAppend(MainActivity.this, fileName, content);
    }

    @Override
    protected void onPause() {
        super.onPause();
        BinderThreadPool.getAllBinderThread(getClass().getSimpleName() + " , onPause");
    }

    @Override
    protected void onResume() {
        super.onResume();
        BinderThreadPool.getAllBinderThread(getClass().getSimpleName() + " , onResume");
    }

    @Override
    protected void onStart() {
        super.onStart();
        BinderThreadPool.getAllBinderThread(getClass().getSimpleName() + " , onStart");
    }
}