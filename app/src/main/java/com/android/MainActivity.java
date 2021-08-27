package com.android;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.android.common.BinderThreadPool;
import com.android.pattern.R;
import com.android.pattern.proxy.aidl.BaiduPushMessageActivity;
import com.android.pattern.proxy.staticproxy.StaticProxyActivity;
import com.android.pattern.qstorage.MediaQStorageActivity;

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
        Intent intent = new Intent(MainActivity.this, MediaQStorageActivity.class);
        startActivity(intent);
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