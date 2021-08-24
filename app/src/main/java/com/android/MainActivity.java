package com.android;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.android.common.BinderThreadPool;
import com.android.common.Log;
import com.android.pattern.R;
import com.android.pattern.adaptermediastore.DownloadsHandlerOnQScopedStorage;
import com.android.pattern.adaptermediastore.AbsHandlerOnQScopedStorage;
import com.android.pattern.adaptermediastore.ImageHandlerOnQScopedStorage;
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
        String fileName = "log.txt";
        Log.d(String.format("将 %s 正在写入文件", content));
        AbsHandlerOnQScopedStorage storage = new DownloadsHandlerOnQScopedStorage();
       // storage.writeAndAppend(MainActivity.this, fileName, content.getBytes());
        //content = "abcdefghigklmnopqrstuvwxyz";
        //Log.d(String.format("将 %s 正在追加写入文件", content));
        //storage.writeAndAppend(MainActivity.this, fileName, content.getBytes());
storage.getAllUris(MainActivity.this);
        String info = (String) storage.read(MainActivity.this, fileName);
        Log.d(String.format("读出的文件信息如下  %s ", info));
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