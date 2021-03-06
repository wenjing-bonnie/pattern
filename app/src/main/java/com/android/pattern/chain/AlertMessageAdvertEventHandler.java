package com.android.pattern.chain;

import android.app.Activity;
import android.content.DialogInterface;

import com.android.PatternApplication;
import com.android.pattern.chain.fakebusiness.FakeBusinessDialogUtils;
import com.android.pattern.template.AppLauncherToNextEventHandler;

/**
 * Created by wenjing.liu on 2021/9/3 .
 * <p>
 * 显示系统提示文字信息
 *
 * @author wenjing.liu
 */
public class AlertMessageAdvertEventHandler extends AppLauncherToNextEventHandler {
    @Override
    public void handlerSelfAppLauncherEvent(Activity context) {
        PatternApplication.getInstance().isShowedAlertMessageAdvert = true;
        FakeBusinessDialogUtils.showFakeBusinessDialog(context, "AlertMessageAdvert", "正在显示一个对话框形式的广告", new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialogInterface) {
                goToNextAppLauncherEventHandler(context);
            }
        });
    }

    @Override
    protected boolean isSelfAppLauncherEventHandler(Activity context) {
        //最基本前提:从来没有显示过 && 该context是一个FragmentActivity && 获取到一个有效的广告（该逻辑不做展示）
        return !PatternApplication.getInstance().isShowedAlertMessageAdvert;
    }
}
