package com.android.pattern.decoratoractivitylifecycle;

import android.app.Activity;
import android.app.Application;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.android.PatternApplication;
import com.android.common.Log;

import java.util.HashMap;

/**
 * Created by wenjing.liu on 2021/8/19 .
 * <p>
 * 用来管理应用的activity的生命周期
 * 装饰者模式中的具体的装饰类.最后都是用来装饰被装饰类{@link ApplicationLifecycleCallbacksConcreteComponent}
 *
 * @author wenjing.liu
 */
public class ActivityLifecycleOnALCallbacksConcreteDecorator extends ApplicationLifecycleCallbacksDecorator {
    /**
     * 用来记录前一个页面
     */
    private String preActivityName = "";
    /**
     * 保存当前页面，在下次打开的时候，赋值给preActivityName
     */
    private String currentActivityName = "Launcher";

    /**
     * 存放着onRestart的Activity的之前的上一个页面:
     * 因为有可能存在着多个onStop的activity：例如 分类->搜索结果页->商品详情页->购物车
     * 当依次返回的时候：要保持他们的上一个页面不变.
     * 由于在页面start另一个Activity的时候，会首先执行第二个Activity的onResume(),然后在执行第一个Activity的onStop,所以需要在{@link #onActivityStopped(Activity)}的时候为第二个Activity保存前一个页面即传入参数
     */
    private HashMap<String, String> onRestartActivityOfPreActivity = new HashMap<>();

    public ActivityLifecycleOnALCallbacksConcreteDecorator(Application.ActivityLifecycleCallbacks callbacks) {
        super(callbacks);
    }

    @Override
    public void onActivityCreated(@NonNull Activity activity, @Nullable Bundle savedInstanceState) {
       super.onActivityCreated(activity,savedInstanceState);
        /**不写在{@link #onActivityCreated(activity, savedInstanceState)}中是为了防止在onCreate()协议调用完毕的时候，该字段还没有赋值*/
        if (Intent.ACTION_MAIN.equals(activity.getIntent().getAction())) {
            currentActivityName = "Launcher";
        }
        preActivityName = currentActivityName;
        //如果是Fragment的话，需要替换成Fragment的名字
        currentActivityName = activity.getLocalClassName();
        Log.d("preActivityName = " + preActivityName + " , currentActivityName = " + currentActivityName);
        PatternApplication.getInstance().setPrePageForAppActionOfActivity(preActivityName);
    }


    @Override
    public void onActivityResumed(@NonNull Activity activity) {
        super.onActivityResumed(activity);
        //如果是一个初次打开的currentActivityName与activity.getLocalClassName()是同一个值
        //但是如果从onStop->onResume的时候，此时currentActivityName已经替换成上一个页面，所以这里要重新赋值
        currentActivityName = activity.getLocalClassName();
        //说明这是之前处在onStop的Activity，重新onResume,需要对prePageAndEventForAppAction重新赋值
        if (onRestartActivityOfPreActivity.containsKey(activity.getLocalClassName())) {
            //需要将之前的保存的前一个页面保存下来
            PatternApplication.getInstance().setPrePageForAppActionOfActivity(onRestartActivityOfPreActivity.get(activity.getLocalClassName()));
        }
        Log.v(currentActivityName + "  prePage  = " + PatternApplication.getInstance().prePageAndEventForAppAction);
    }

    /**
     * 关键现在的逻辑是先执行第二个页面onResume(),然后才执行前一个页面onStop,如果保存prePageAndEventForAppAction，在第二个页面创建出来的时候会覆盖
     * 所以在该集合中保存当前页面的前一个页面，也就是onStop的页面
     */
    @Override
    public void onActivityStopped(@NonNull Activity activity) {
        super.onActivityStopped(activity);
        //关键现在的逻辑是第二个页面onResume(),然后前一个页面才执行onStop,所以在第二个页面创建出来的时候，会把这个值给覆盖了,所以不能保存prePageAndEventForAppAction
        //只能保存当前Activity对应着的正在onStop的activity
        //LogUtils.w(currentActivityName + " onStop value = " + activity.getLocalClassName());
        //目前数据人员需要保存的是第一次打开该页面的那个Activity,所以增加containsKey()的判断:
        // 所以如分类->点击一个分类->搜索结果页->商品详情页,然后依次返回到分类页,此时分类页的前一个页面仍然要为第一次打开该页面的WelcomeActivity
        if (onRestartActivityOfPreActivity.containsKey(currentActivityName)) {
            return;
        }
        onRestartActivityOfPreActivity.put(currentActivityName, activity.getLocalClassName());
    }

    @Override
    public void onActivityDestroyed(@NonNull  Activity activity) {
        super.onActivityDestroyed(activity);
        //如果是Destroyed，则移除该Activity,清空onRestartActivityOfPreActivity的对应的值
        onRestartActivityOfPreActivity.remove(activity.getLocalClassName());
    }
}
