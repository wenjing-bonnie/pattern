package com.android.pattern.decoratoractivitylifecycle;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;


/**
 * Created by wenjing.liu on 2021/8/17 .
 * <p>
 * 装饰者模式中的装饰者:用来持有Component对象,并且实现Component的所有方法,这样具体的装饰类就可以选择自己需要装饰的Component中的方法
 * 类似于{@link java.io.FilterInputStream}的作用
 * <p>
 * 因为注册给Application的是具体装饰者的实例,当Application监测到生命周期方法的回调的时候,会回调其他具体装饰者和被装饰者的生命周期方法
 *
 * @author wenjing.liu
 */
public class ApplicationLifecycleCallbacksDecorator implements Application.ActivityLifecycleCallbacks {

    protected Application.ActivityLifecycleCallbacks applicationLifecycleCallbacks;

    public ApplicationLifecycleCallbacksDecorator(Application.ActivityLifecycleCallbacks callbacks) {
        this.applicationLifecycleCallbacks = callbacks;
    }

    @Override
    public void onActivityCreated(@NonNull Activity activity, @Nullable Bundle savedInstanceState) {
        applicationLifecycleCallbacks.onActivityCreated(activity, savedInstanceState);
    }

    @Override
    public void onActivityStarted(@NonNull Activity activity) {
        applicationLifecycleCallbacks.onActivityStarted(activity);
    }

    @Override
    public void onActivityResumed(@NonNull Activity activity) {
        applicationLifecycleCallbacks.onActivityResumed(activity);
    }

    @Override
    public void onActivityPaused(@NonNull Activity activity) {
        applicationLifecycleCallbacks.onActivityPaused(activity);
    }

    @Override
    public void onActivityStopped(@NonNull Activity activity) {
        applicationLifecycleCallbacks.onActivityStopped(activity);
    }

    @Override
    public void onActivitySaveInstanceState(@NonNull Activity activity, @NonNull Bundle outState) {
        applicationLifecycleCallbacks.onActivitySaveInstanceState(activity, outState);
    }

    @Override
    public void onActivityDestroyed(@NonNull Activity activity) {
        applicationLifecycleCallbacks.onActivityDestroyed(activity);
    }
}
