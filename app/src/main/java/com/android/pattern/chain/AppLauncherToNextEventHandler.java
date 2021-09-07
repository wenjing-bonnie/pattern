package com.android.pattern.chain;

import android.app.Activity;

import com.android.common.Log;


/**
 * Created by wenjing.liu on 2021/9/3 in J1.
 * <p>
 * 所有广告的父类,已经封装处理下一个Handler的逻辑.
 * 每个广告只需处理自己的广告的{@link #isSelfAppLauncherEventHandler(Activity)}和{@link #handlerSelfAppLauncherEvent(Activity)}
 *
 * @author wenjing.liu
 */
public abstract class AppLauncherToNextEventHandler extends AppLauncherEventHandler {

    @Override
    protected void handlerAppLauncherEvent(Activity context) {
        //符合该条件的直接交给该Handler进行处理
        if (isSelfAppLauncherEventHandler(context)) {
            Log.v(String.format("~~~~~~~~~~~~ 进入到 \"%s\" 处理逻辑", getClass().getSimpleName()));
            handlerSelfAppLauncherEvent(context);
            return;
        }
        Log.d(String.format("~~~~~~~~~~~~ 交给下一个 \"%s\" 处理逻辑", getClass().getSimpleName()));
        //否则交给下一个Handler进行处理
        goToNextAppLauncherEventHandler(context);
    }

    /**
     * 显示本Handler处理的广告逻辑
     */
    protected abstract void handlerSelfAppLauncherEvent(Activity context);

}
