package com.android.pattern.qstorage;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.android.common.Log;
import com.android.pattern.R;


public class MediaQStorageActivity extends Activity {
    private Context context;
    private ImageView ivImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = MediaQStorageActivity.this;
        setContentView(R.layout.activity_qstorage);
        ivImage = findViewById(R.id.iv_img);
    }

    public void btnReadAndWrite(View view) {
        checkReadPermission();
        writeAndWriteMediaStore();
        writeImageStorage();
        deleteImageStorage();
    }

    public void btnReadAndWriteNot(View view) {
        Intent intent = new Intent(context, NotMediaQStorageActivity.class);
        startActivity(intent);
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
        //String fileName = "www.sina.jpg"; //android10 模拟器中的图片
        String fileName = "IMG_20210826_103025.jpg"; //android10 模拟器中的图片
        scopedStorage.writeAndAppend(context, fileName, "12".getBytes());
        Bitmap bitmap = (Bitmap) scopedStorage.read(context, fileName);
        //必须有权限才可以读出该图片
        if (bitmap != null) {
            Log.d("height = " + bitmap.getHeight());
            ivImage.setImageBitmap(bitmap);
        }
    }

    private void deleteImageStorage() {
        AbsHandlerOnQScopedStorage scopedStorage = new ImageHandlerOnQScopedStorage();
        String fileName = "downloadfile1.jpg";
        scopedStorage.delete(context, fileName);
    }

    private void checkReadPermission() {
        String read = Manifest.permission.READ_EXTERNAL_STORAGE;

        String[] permissions = new String[]{read};

        if (this.checkSelfPermission(read) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(permissions, 1);
        }
    }
}