package com.android.pattern.chain.fakebusiness;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

/**
 * Created by wenjing.liu on 2021/9/7.
 * 用来模拟业务逻辑的Dialog
 *
 * @author wenjing.liu
 */
public class FakeBusinessDialogUtils {

    public static void showFakeBusinessDialog(Context context, String title, String message, DialogInterface.OnDismissListener listener) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.setPositiveButton(android.R.string.ok, null);
        AlertDialog fakeDialog = builder.create();
        if (listener != null) {
            fakeDialog.setOnDismissListener(listener);
        }
        fakeDialog.show();
    }

}
