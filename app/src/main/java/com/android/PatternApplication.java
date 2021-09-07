package com.android;

import android.app.Application;

import com.android.common.BinderThreadPool;
import com.android.common.Log;
import com.android.pattern.decoratoractivitylifecycle.ActivityLifecycleOnALCallbacksConcreteDecorator;
import com.android.pattern.decoratoractivitylifecycle.ActivityManagerOnALCallbacksConcreteDecorator;
import com.android.pattern.decoratoractivitylifecycle.ApplicationLifecycleCallbacksConcreteComponent;

/**
 * Created by wenjing.liu on 2021/5/12 .
 *
 * @author wenjing.liu
 */
public class PatternApplication extends Application implements ApplicationLifecycleCallbacksConcreteComponent.IApplicationLifecycle {
    /**
     * application实例
     */
    private static PatternApplication sInstance;
    // ==== 装饰者模式decoratoractivitylifecycle中添加的内容 ====
    /**
     * 为app 打点增加的前一个页面的标识：为"下一个页面的类名_事件"，若有事件，则赋值为事件对应的字符串，若无事件，则""即可
     */
    public String prePageAndEventForAppAction;
    /**
     * 生命周期监听
     */
    private ActivityManagerOnALCallbacksConcreteDecorator activityManagerOnALCallbacksConcreteDecorator;
    // ==== 责任链模式chain中添加的内容:true:已经显示过了;false:还没有显示过 ====
    /**
     * 是否显示过AlertMessageAdvert
     */
    public boolean isShowedAlertMessageAdvert;
    public boolean isShowedCloseTypeAdvert;
    public boolean isShowedFullScreenAdvert;
    public boolean isShowedDownloadNewVersion;
    public boolean isShowedPrivateConfirm;

    public static synchronized PatternApplication getInstance() {
        //理论上该值不会为null
        if (sInstance == null) {
            sInstance = new PatternApplication();
        }
        //该实例是在onCreate事件中实例化的
        return sInstance;
    }


    @Override
    public void onCreate() {
        super.onCreate();
        BinderThreadPool.getAllBinderThread(getClass().getSimpleName());
        sInstance = PatternApplication.this;
        registerPharmacyApplicationActivityManager();
    }

    /**
     * 注册Activity生命周期管理
     */
    private void registerPharmacyApplicationActivityManager() {
        activityManagerOnALCallbacksConcreteDecorator =
                new ActivityManagerOnALCallbacksConcreteDecorator(
                        new ActivityLifecycleOnALCallbacksConcreteDecorator(
                                new ApplicationLifecycleCallbacksConcreteComponent(PatternApplication.this)));
        registerActivityLifecycleCallbacks(activityManagerOnALCallbacksConcreteDecorator);
    }

    /**
     * 设置Activity的前一个页面
     *
     * @param prePage
     */
    public void setPrePageForAppActionOfActivity(String prePage) {
        this.prePageAndEventForAppAction = String.format("%s@", prePage);
    }

    @Override
    public void applicationWillEnterForeground() {
        Log.d("applicationWillEnterForeground");
    }

    @Override
    public void applicationDidEnterBackground() {
        Log.d("applicationDidEnterBackground");
    }

    /**
     * APP关闭的时候需要调用的方法,而{@link Application#onTerminate()}仅会在模拟器关闭时调用
     * TODO 下面unregister的操作需要自行在应用退出的时候调用,本次不做逻辑处理
     */
    public void onTerminateForAPP() {
        activityManagerOnALCallbacksConcreteDecorator.clearActivityManagerWhenAppExit();
        unregisterActivityLifecycleCallbacks(activityManagerOnALCallbacksConcreteDecorator);
    }
}
