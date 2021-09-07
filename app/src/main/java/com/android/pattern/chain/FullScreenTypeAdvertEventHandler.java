package com.android.pattern.chain;


import android.app.Activity;
import android.content.DialogInterface;

import com.android.PatternApplication;
import com.android.pattern.chain.fakebusiness.FakeBusinessDialogUtils;

/**
 * Created by wenjing.liu on 2021/9/3 in J1.
 * 显示全屏大广告提示框
 *
 * @author wenjing.liu
 */
public class FullScreenTypeAdvertEventHandler extends AppLauncherToNextEventHandler {

    @Override
    protected void handlerSelfAppLauncherEvent(Activity context) {
        PatternApplication.getInstance().isShowedFullScreenAdvert = true;
        FakeBusinessDialogUtils.showFakeBusinessDialog(context, "FullScreenAdvert", "正在显示一个FullScreenAdvert", new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialogInterface) {
                goToNextAppLauncherEventHandler(context);
            }
        });
    }

    @Override
    protected boolean isSelfAppLauncherEventHandler(Activity context) {
        //最基本前提:从来没有显示过 && 获取的广告内容有效(该逻辑不做展示)
        return !PatternApplication.getInstance().isShowedFullScreenAdvert;
    }

}
