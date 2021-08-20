package com.android.pattern.proxy.aidl;

import android.app.Service;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.android.common.BaseActivity;
import com.android.common.BinderThreadPool;
import com.android.common.Log;
import com.android.pattern.IBaiduPushMessageService;
import com.android.pattern.R;

/**
 * 跨进程通信
 */
public class BaiduPushMessageActivity extends BaseActivity {
    private IIBaiduPushMessageService baiduPushMessageService;
    private TextView tvTitle;
    private TextView tvContent;
    private EditText etBinder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_baidu_push_message);
        tvTitle = findViewById(R.id.tv_title);
        tvContent = findViewById(R.id.tv_content);
        etBinder = findViewById(R.id.et_binder);
        etBinder.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                BinderThreadPool.getAllBinderThread(getClass().getSimpleName());
            }
        });
    }

    public void startBaiduService(View view) {
        // Service Intent must be explicit
        // 从5.0之后 Service必须是显示声明，如果非要隐性调用，必须增加包名
        Intent intent = new Intent();
        intent.setAction("com.android.pattern.BaiduPushMessageService");
        intent.setPackage(getPackageName());
        bindService(intent, connection, Service.BIND_AUTO_CREATE);
    }

    public void refresh(View view) {
        try {
            baiduPushMessageService.setBaiduMessageTitle("121e");
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            Log.v("onServiceConnected = " + System.currentTimeMillis());
            baiduPushMessageService = IIBaiduPushMessageService.Stub.asInterface(service);
            try {
                String title = baiduPushMessageService.getBaiduMessageTitle();
                refreshBaiduMessage(title, "");
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };

    private void refreshBaiduMessage(String title, String content) {
        tvTitle.setText(title);
        tvContent.setText(content);
    }
}