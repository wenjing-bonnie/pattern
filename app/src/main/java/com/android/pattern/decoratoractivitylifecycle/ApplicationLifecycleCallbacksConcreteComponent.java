package com.android.pattern.decoratoractivitylifecycle;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.android.common.Log;

/**
 * Created by wenjing.liu on 2021/7/29 .
 * <p>
 * 用来管理整个应用的生命周期.
 * <p>
 * 第一次尝试:
 * 仅仅使用只有复写方法的类作为目前暂时想到仅为装饰者模式的被装饰类,其中Application.ActivityLifecycleCallbacks为装饰者模式的Component
 * 仅仅用来复写接口方法,其他装饰类在使用的时候,可不必都必须进行复写,只需根据需要选择合适的方法进行复写即可
 * 最后实现发现在写装饰类实现具体功能的时候,不满足同时实现Component
 * <p>
 * 第二次尝试:
 * <p>
 * 将application的生命周期管理的功能做为Component,将设置{@link IApplicationLifecycle}作为抽象方法
 * <p>
 * 其他具有功能的装饰类(例如管理Activity的)作为具体的被装饰类.
 * <p>
 * <p>
 * 第三次尝试:
 * 还是接口作为Component
 * 将application的生命周期管理的功能做为具体的被装饰类,其他功能都来装饰该类
 * 装饰类就是一个类去实现接口
 * <p>
 * 类似于{@link java.io.FileInputStream}通过构造函数传入name、file等需要处理的文件
 * 现在通过构造函数传入{@link #applicationLifecycle}的值
 * 如果要多层类进行包装的时候，不能使用具有功能的做为被装饰类，因为多层具体装饰类会多次调用被装饰类。
 * 所以改为仅复写接口方法的一个类为被装饰类
 *
 * @author wenjing.liu
 */
public class ApplicationLifecycleCallbacksConcreteComponent implements Application.ActivityLifecycleCallbacks {
    /**
     * 用来监听应用的生命周期,通过该值来判断是否所有的activity都处于onStop状态
     */
    private int foregroundActivityCount;

    /**
     * 生命周期的回调
     *
     * @return
     */
    private IApplicationLifecycle applicationLifecycle;

    public ApplicationLifecycleCallbacksConcreteComponent(IApplicationLifecycle lifecycle) {
        this.applicationLifecycle = lifecycle;
    }


    @Override
    public void onActivityCreated(@NonNull Activity activity, @Nullable  Bundle savedInstanceState) {
    }

    @Override
    public void onActivityStarted(@NonNull Activity activity) {
        foregroundActivityCount++;
        Log.d("onActivityStarted foregroundActivityCount = " + foregroundActivityCount + " , " + activity.getLocalClassName());
        if (foregroundActivityCount == 1) {
            applicationWillEnterForeground();
        }
    }

    @Override
    public void onActivityResumed(@NonNull Activity activity) {

    }

    @Override
    public void onActivityPaused(@NonNull Activity activity) {

    }


    @Override
    public void onActivityStopped(@NonNull Activity activity) {
        foregroundActivityCount--;
        Log.d("onActivityStopped foregroundActivityCount = " + foregroundActivityCount + " , " + activity.getLocalClassName());
        if (foregroundActivityCount == 0) {
            applicationDidEnterBackground();
        }
    }

    @Override
    public void onActivitySaveInstanceState(@NonNull Activity activity, @NonNull Bundle outState) {

    }

    @Override
    public void onActivityDestroyed(@NonNull Activity activity) {

    }

    /**
     * 应用后台回调
     */
    private void applicationDidEnterBackground() {
        Log.d("The application did enter background ! ");
        if (applicationLifecycle == null) {
            return;
        }
        applicationLifecycle.applicationDidEnterBackground();
    }

    /**
     * 应用前台回调
     */
    private void applicationWillEnterForeground() {
        Log.d("The application will enter foreground !");
        if (applicationLifecycle == null) {
            return;
        }
        applicationLifecycle.applicationWillEnterForeground();
    }

    /**
     * 应用的前后台切换的生命周期
     */
    public interface IApplicationLifecycle {
        /**
         * 应用进入后台
         */

        void applicationDidEnterBackground();

        /**
         * 应用进入到前台
         */
        void applicationWillEnterForeground();
    }

}
