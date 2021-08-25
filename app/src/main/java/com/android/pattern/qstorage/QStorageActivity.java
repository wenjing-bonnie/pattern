package com.android.pattern.qstorage;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;

import com.android.common.Log;
import com.android.pattern.R;

public class QStorageActivity extends Activity {
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = QStorageActivity.this;
        setContentView(R.layout.activity_qstorage);
    }

    public void btnReadAndWrite(View view) {
        //checkReadPermission();
        writeAndWriteMediaStore();
        writeImageStorage();
    }

    private void writeAndWriteMediaStore() {

        String content = "123456789";
        String fileName = "1235669.txt";
        Log.d(String.format("将 %s 正在写入文件", content));
        AbsHandlerOnQScopedStorage storage = new DownloadsHandlerOnQScopedStorage();
        //AbsHandlerOnQScopedStorage storage = new FileHandlerOnQScopedStorage();
        Log.v(" ===  写入之前   === ");
        storage.getAllUris(context);
        storage.writeAndAppend(context, fileName, content.getBytes());
        Log.v(" ===  写入之后   === ");
        storage.getAllUris(context);
        String info = (String) storage.read(context, fileName);
        Log.d(String.format("读出的文件信息如下  %s ", info));
    }

    private void writeImageStorage() {
        AbsHandlerOnQScopedStorage scopedStorage = new ImageHandlerOnQScopedStorage();
        scopedStorage.getAllUris(context);
    }

    private void checkReadPermission() {
        String read = Manifest.permission.WRITE_EXTERNAL_STORAGE;

        String[] permissions = new String[]{read};

        if (this.checkSelfPermission(read) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(permissions, 1);
        }
    }
}