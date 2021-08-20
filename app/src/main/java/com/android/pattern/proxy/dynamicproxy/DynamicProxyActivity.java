package com.android.pattern.proxy.dynamicproxy;

import android.app.Activity;
import android.os.Bundle;

import com.android.pattern.R;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DynamicProxyActivity extends Activity {
    Retrofit retrofit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dynamic_proxy);

        retrofit = new Retrofit.Builder().baseUrl("https://www.baidu.com").
                addConverterFactory(GsonConverterFactory.create()).
                build();
    }
}