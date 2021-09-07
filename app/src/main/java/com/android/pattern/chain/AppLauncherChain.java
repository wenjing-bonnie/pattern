package com.android.pattern.chain;

import android.app.Activity;

/**
 * Created by wenjing.liu on 2021/9/3 in.
 * <p>
 * {@link PrivateConfirmEventHandler} 优先级最高,所以需要将该移除启动链,只有通过该链才可以执行后面
 *
 * @author wenjing.liu
 */
public class AppLauncherChain {
    private PrivateConfirmEventHandler privateConfirm;
    private CheckNotificationEventHandler check;
    private DownloadNewVersionEventHandler download;
    //广告
    private FullScreenTypeAdvertEventHandler fullScreen;
    private CloseTypeAdvertEventHandler close;
    private AlertMessageAdvertEventHandler alertMessage;

    public AppLauncherChain() {
        initAppLauncherChain();
        setAppLauncherChain();
    }

    /**
     * 实例化每个Handler
     */
    private void initAppLauncherChain() {
        privateConfirm = new PrivateConfirmEventHandler();
        check = new CheckNotificationEventHandler();
        fullScreen = new FullScreenTypeAdvertEventHandler();
        close = new CloseTypeAdvertEventHandler();
        alertMessage = new AlertMessageAdvertEventHandler();
        download = new DownloadNewVersionEventHandler();
    }

    /**
     * 设置责任链结构
     */
    private void setAppLauncherChain() {
        //设置责任链
        privateConfirm.setNextAppLauncherEventHandler(check);
        check.setNextAppLauncherEventHandler(fullScreen);
        fullScreen.setNextAppLauncherEventHandler(close);
        close.setNextAppLauncherEventHandler(alertMessage);
        alertMessage.setNextAppLauncherEventHandler(download);
    }

    /**
     * APP启动的时候的责任链
     *
     * @param context
     */
    public void startAppLauncherChain(Activity context) {
        //启动责任链
        privateConfirm.handlerAppLauncherEvent(context);
    }

}
