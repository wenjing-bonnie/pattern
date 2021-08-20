package com.android.pattern.proxy.staticproxy;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.android.common.BaseActivity;
import com.android.pattern.R;

/**
 * Created by wenjing.liu on 2021/4/7 in J1.
 * View层:
 * 被代理类:只专注UI渲染
 *
 * @author wenjing.liu
 */
public class StaticProxyActivity extends BaseActivity implements IStaticProxyViewInterface {
    private TextView tvContent;
    private StaticProxyPresenter proxyPresenter;
    private int testCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_static_proxy);

        tvContent = findViewById(R.id.tv_content);
        proxyPresenter = new StaticProxyPresenter(StaticProxyActivity.this);
    }


    /**
     * 模拟成功和失败的场景
     *
     * @param view
     */
    public void testClick(View view) {
        testCount++;
        proxyPresenter.senderAfterSaleOrder(testCount);
    }

    /**
     * 售后列表成功返回数据的UI
     *
     * @param cacheBean
     */
    @Override
    public void responseAfterSaleOrderSuccessLayout(AfterSaleOrderCacheBean cacheBean) {
        tvContent.setText("服务器成功返回数据");
        tvContent.setTextColor(Color.BLACK);
    }

    /**
     * 获取售后列表失败的UI
     *
     * @param errorInfo
     */
    @Override
    public void responseAfterSaleOrderFailureLayout(String errorInfo) {
        tvContent.setText("服务器未成功返回数据");
        tvContent.setTextColor(Color.RED);

    }
}