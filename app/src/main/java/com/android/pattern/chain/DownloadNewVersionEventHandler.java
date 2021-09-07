package com.android.pattern.chain;

import android.app.Activity;
import android.content.Intent;

import com.android.PatternApplication;
import com.android.pattern.chain.fakebusiness.DownloadActivity;
import com.android.pattern.template.AppLauncherToNextEventHandler;

/**
 * Created by wenjing.liu on 2021/9/3 .
 * <p>
 * 提示下载最新版本
 *
 * @author wenjing.liu
 */
public class DownloadNewVersionEventHandler extends AppLauncherToNextEventHandler {

    @Override
    public void handlerSelfAppLauncherEvent(Activity context) {
        PatternApplication.getInstance().isShowedDownloadNewVersion = true;
        Intent intent = new Intent(context, DownloadActivity.class);
        context.startActivity(intent);
        //直接进入到下一个Handler
        goToNextAppLauncherEventHandler(context);
    }

    @Override
    protected boolean isSelfAppLauncherEventHandler(Activity context) {
        //最基本前提:从来没有显示过 && 有效的下载链接(该逻辑不做展示)
        return !PatternApplication.getInstance().isShowedDownloadNewVersion;
    }
}
