package com.android.pattern.chain;

import android.app.Activity;
import android.content.DialogInterface;

import com.android.pattern.chain.fakebusiness.FakeBusinessDialogUtils;
import com.android.pattern.template.AppLauncherToNextEventHandler;

/**
 * Created by wenjing.liu on 2021/9/3.
 * 显示提示用户打开通知栏的对话框,仅在第一次安装的时候提示用户
 *
 * @author wenjing.liu
 */
public class CheckNotificationEventHandler extends AppLauncherToNextEventHandler {

    @Override
    public void handlerSelfAppLauncherEvent(Activity context) {
        FakeBusinessDialogUtils.showFakeBusinessDialog(context, " CheckNotification", "正在显示提示用户打开通知对话框", new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialogInterface) {
                goToNextAppLauncherEventHandler(context);
            }
        });
    }

    @Override
    protected boolean isSelfAppLauncherEventHandler(Activity context) {
        //始终显示该对话框
        return true;
    }
}
