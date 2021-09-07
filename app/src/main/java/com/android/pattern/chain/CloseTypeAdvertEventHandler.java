package com.android.pattern.chain;

import android.app.Activity;
import android.content.DialogInterface;

import com.android.PatternApplication;
import com.android.pattern.chain.fakebusiness.FakeBusinessDialogUtils;

/**
 * Created by wenjing.liu on 2021/9/3.
 * 显示半屏广告,一天只显示一次
 *
 * @author wenjing.liu
 */
public class CloseTypeAdvertEventHandler extends AppLauncherToNextEventHandler {

    @Override
    protected void handlerSelfAppLauncherEvent(Activity context) {
        PatternApplication.getInstance().isShowedCloseTypeAdvert = true;
        FakeBusinessDialogUtils.showFakeBusinessDialog(context, " CloseTypeAdvert", "正在显示一个半屏广告", new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialogInterface) {
                goToNextAppLauncherEventHandler(context);
            }
        });
    }

    @Override
    protected boolean isSelfAppLauncherEventHandler(Activity context) {
        //最基本前提:新的一天 && 从来没有显示过 && 该context是一个FragmentActivity && 获取的广告内容有效（该逻辑不做展示）
        return !PatternApplication.getInstance().isShowedCloseTypeAdvert;
    }
}
