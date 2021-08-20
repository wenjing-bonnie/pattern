package com.android.pattern.proxy.staticproxy;

/**
 * Created by wenjing.liu on 2021/4/7 in J1.
 * 公共的接口:实现UI渲染的功能
 *
 * @author wenjing.liu
 */
public interface IStaticProxyViewInterface {

    /**
     * 售后列表
     * 数据获取完之后，直接重新加载服务器返回的UI即可
     */
    void responseAfterSaleOrderSuccessLayout(AfterSaleOrderCacheBean cacheBean);

    /**
     * 售后列表
     * 数据加载失败
     */
    void responseAfterSaleOrderFailureLayout(String errorInfo);
}
