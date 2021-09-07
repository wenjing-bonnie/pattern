package com.android.pattern.chain;

import android.app.Activity;

/**
 * Created by wenjing.liu on 2021/9/1 .
 * <p>
 * App启动要处理事件的的抽象类
 *
 * @author wenjing.liu
 */
public abstract class AppLauncherEventHandler {

    /**
     * 设置的是下一个Handler
     */
    private AppLauncherEventHandler nextEventHandler;
    /**
     * 拦截器
     */
    private InterruptChainCallBack interruptChainCallBack;

    /**
     * 获取下一个Handler
     *
     * @return
     */
    protected AppLauncherEventHandler getNextAppLauncherEventHandler() {
        return this.nextEventHandler;
    }

    /**
     * 设置其下一个Handler
     *
     * @param handler
     */
    protected void setNextAppLauncherEventHandler(AppLauncherEventHandler handler) {
        this.nextEventHandler = handler;
    }

    /**
     * 如果设置了下一个Handler,则调用下一个Handler
     */
    protected void goToNextAppLauncherEventHandler(Activity context) {
        //拦截下次调用
        if (interruptChainCallBack != null) {
            interruptChainCallBack.interruptChainCallBack(context);
            return;
        }
        if (getNextAppLauncherEventHandler() == null) {
            return;
        }
        getNextAppLauncherEventHandler().handlerAppLauncherEvent(context);
    }

    /**
     * 用来处理该子类需要实现的功能
     *
     * @param context
     */
    protected abstract void handlerAppLauncherEvent(Activity context);

    /**
     * 当前Handler需要处理
     *
     * @return true:则直接交给当前Handler;
     * false:交给下一个Handler进行处理
     */
    protected abstract boolean isSelfAppLauncherEventHandler(Activity context);

    public void setInterruptChainCallBack(InterruptChainCallBack callback) {
        this.interruptChainCallBack = callback;
    }

    /**
     * 中止往下传递，可以选择在该拦截器中启动其他的启动链
     */
    public interface InterruptChainCallBack {
        void interruptChainCallBack(Activity activity);
    }

}
