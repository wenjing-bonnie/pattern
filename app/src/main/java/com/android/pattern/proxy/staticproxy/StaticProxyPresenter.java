package com.android.pattern.proxy.staticproxy;

/**
 * Created by wenjing.liu on 2021/4/7 in J1.
 * Presenter层:用来实现根据服务器返回的数据情况调用不同的UI渲染方式
 * 代理类:消息预处理（用来实现服务器调用返回服务器结果）之后在调用UI
 *
 * @author wenjing.liu
 */
public class StaticProxyPresenter implements IStaticProxyViewInterface {
    private IStaticProxyViewInterface iStaticProxyViewInterface;
    private AfterSaleOrderCacheBean cacheBean;

    public StaticProxyPresenter(IStaticProxyViewInterface viewInterface) {
        this.iStaticProxyViewInterface = viewInterface;
        cacheBean = new AfterSaleOrderCacheBean();
    }

    /**
     * 用来模拟调用服务器接口，用来返回所有的售后列表和返回数据失败的场景
     */
    public void senderAfterSaleOrder(int count) {
        if (count % 2 == 0) {
            responseAfterSaleOrderFailureLayout("服务器返回数据失败");
        } else {
            responseAfterSaleOrderSuccessLayout(cacheBean);
        }

    }

    @Override
    public void responseAfterSaleOrderSuccessLayout(AfterSaleOrderCacheBean cacheBean) {
        iStaticProxyViewInterface.responseAfterSaleOrderSuccessLayout(cacheBean);

    }

    @Override
    public void responseAfterSaleOrderFailureLayout(String errorInfo) {
        iStaticProxyViewInterface.responseAfterSaleOrderFailureLayout(errorInfo);
    }
}
