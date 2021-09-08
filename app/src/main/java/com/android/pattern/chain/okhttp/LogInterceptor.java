package com.android.pattern.chain.okhttp;

import com.android.common.Log;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by wenjing.liu on 2021/9/8 .
 * <p>
 * 自定义拦截器
 *
 * @author wenjing.liu
 */
public class LogInterceptor implements Interceptor {

    public LogInterceptor(){

    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        Response response = chain.proceed(request);
        return response;
    }
}
