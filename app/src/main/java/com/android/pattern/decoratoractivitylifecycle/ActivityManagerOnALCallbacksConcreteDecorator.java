package com.android.pattern.decoratoractivitylifecycle;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wenjing.liu on 2021/8/17 .
 * <p>
 * 用来管理应用的还没有被销毁的activity（也就是栈中的Activity的情况）.
 * 在关闭应用的时候，将所有的栈中的Activity执行finish()
 * 装饰者模式中的具体的装饰类.最后都是用来装饰被装饰类{@link ApplicationLifecycleCallbacksConcreteComponent}
 * <p>
 * 类似于{@link java.io.BufferedInputStream}
 *
 * @author wenjing.liu
 */
public class ActivityManagerOnALCallbacksConcreteDecorator extends ApplicationLifecycleCallbacksDecorator {
    /**
     * 管理Activity的栈
     */
    private List<Activity> activityManager = new ArrayList<>();


    public ActivityManagerOnALCallbacksConcreteDecorator(Application.ActivityLifecycleCallbacks callbacks) {
        super(callbacks);
    }


    @Override
    public void onActivityCreated(@NonNull Activity activity, Bundle savedInstanceState) {
        super.onActivityCreated(activity, savedInstanceState);
        //保存
        activityManager.add(activity);
    }


    @Override
    public void onActivityDestroyed(@NonNull Activity activity) {
        super.onActivityDestroyed(activity);
        activityManager.remove(activity);
    }

    /**
     * 当APP退出的时候，关闭还没有被finish掉的Activity，否则APP会出现黑屏
     */
    public void clearActivityManagerWhenAppExit() {
        //清空缓存值
        if (activityManager == null || activityManager.size() == 0) {
            return;
        }
        for (Activity activity : activityManager) {
            activity.finish();
        }
        activityManager.clear();

    }

    /**
     * 判断app是否打开
     *
     * @return
     */
    public boolean isApplicationActive() {
        return activityManager.size() > 0;
    }
}
