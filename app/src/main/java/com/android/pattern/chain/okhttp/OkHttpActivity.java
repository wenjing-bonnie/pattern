package com.android.pattern.chain.okhttp;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import com.android.common.Log;
import com.android.pattern.R;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class OkHttpActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ok_http);
    }


    public void btnOkHttp(View view) {

        Request request = new Request.Builder()
                .url("https://www.baidu.com/")
                .get()
                .build();
        OkHttpClient.Builder clientBuilder = new OkHttpClient.Builder();
        clientBuilder.addInterceptor(new LogInterceptor());
        OkHttpClient client = clientBuilder.build();
        Call call = client.newCall(request);
        call.enqueue(new Callback() {

            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Log.d("msg = " + response.message() + " \n body = " + response.body().source().readUtf8());

            }
        });
    }
}