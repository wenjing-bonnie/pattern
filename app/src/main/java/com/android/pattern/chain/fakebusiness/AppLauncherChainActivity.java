package com.android.pattern.chain.fakebusiness;

import android.os.Bundle;
import android.view.View;

import com.android.PatternApplication;
import com.android.common.BaseActivity;
import com.android.pattern.R;
import com.android.pattern.chain.AppLauncherChain;

public class AppLauncherChainActivity extends BaseActivity {
    private AppLauncherChain appLauncherChain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_launcher_chain);
        appLauncherChain = new AppLauncherChain();
    }

    public void btnStartChain(View view) {
        appLauncherChain.startAppLauncherChain(AppLauncherChainActivity.this);
    }

    public void btnRestartApp(View view) {
        PatternApplication.getInstance().isShowedDownloadNewVersion = false;
        PatternApplication.getInstance().isShowedPrivateConfirm = false;
        PatternApplication.getInstance().isShowedFullScreenAdvert = false;
        PatternApplication.getInstance().isShowedCloseTypeAdvert = false;
        PatternApplication.getInstance().isShowedAlertMessageAdvert = false;
        appLauncherChain.startAppLauncherChain(AppLauncherChainActivity.this);

    }
}