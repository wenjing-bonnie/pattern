package com.android.pattern.chain;

import android.app.Activity;
import android.content.DialogInterface;

import com.android.PatternApplication;
import com.android.pattern.chain.fakebusiness.FakeBusinessDialogUtils;

/**
 * Created by wenjing.liu on 2021/9/3 .
 * 处理个人隐私确认的对话框
 *
 * @author wenjing.liu
 */
public class PrivateConfirmEventHandler extends AppLauncherToNextEventHandler {

    @Override
    public void handlerSelfAppLauncherEvent(Activity context) {
        PatternApplication.getInstance().isShowedPrivateConfirm = true;
        FakeBusinessDialogUtils.showFakeBusinessDialog(context, "PrivateConfirm", "正在显示一个提示用户同意隐私权限", new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialogInterface) {
                goToNextAppLauncherEventHandler(context);
            }
        });

    }

    @Override
    protected boolean isSelfAppLauncherEventHandler(Activity context) {
        //最基本前提:并且没有显示过 && 仅第一次安装（该逻辑不做展示）
        return !PatternApplication.getInstance().isShowedPrivateConfirm;
    }

}
