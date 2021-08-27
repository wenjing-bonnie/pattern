package com.android.pattern.qstorage;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageView;

import com.android.common.Log;
import com.android.pattern.R;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * create by wenjing.liu on 2021/08/27
 * 使用MediaStore对多媒体文件的读写操作
 * TODO 注意里面的文件名字都是我的手机上的文件，需要自行修改
 */
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
        String content = "123456789";
        String fileName = "980.txt";
        Log.d(String.format("将 %s 正在写入文件", content));
        AbsHandlerOnQScopedStorage storage = new DownloadsHandlerOnQScopedStorage();
        Log.v(" ===  写入之前   === ");
        storage.getAllUris(context);
        storage.writeAndAppend(context, fileName, content.getBytes());
        Log.v(" ===  写入之后   === ");
        storage.getAllUris(context);
        String info = (String) storage.read(context, fileName);
        Log.d(String.format("读出的文件信息如下  %s ", info));
    }

    public void btnReadAndWriteNot(View view) {
        Intent intent = new Intent(context, SafQStorageActivity.class);
        startActivity(intent);
    }

    public void btnWriteImageStorage(View view) {
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

    public void btnDeleteImageStorage(View view) {
        AbsHandlerOnQScopedStorage scopedStorage = new ImageHandlerOnQScopedStorage();
        String fileName = "downloadfile1.jpg";
        scopedStorage.delete(context, fileName);
    }

    public void btnCheckReadPermission(View view) {
        String read = Manifest.permission.READ_EXTERNAL_STORAGE;

        String[] permissions = new String[]{read};

        if (this.checkSelfPermission(read) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(permissions, 1);
        }
    }

    // TODO 这个还没有验证成功
    public void btnCreateWDeleteRequest(View view) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.R) {
            return;
        }
        AbsHandlerOnQScopedStorage storage = new DownloadsHandlerOnQScopedStorage();
        Log.v(" ===  写入之前   === ");
        List<AbsHandlerOnQScopedStorage.AndroidQFileInfo> infos = storage.getAllUris(context);
        Collection<Uri> uris = new ArrayList<Uri>();
        for (AbsHandlerOnQScopedStorage.AndroidQFileInfo file : infos) {
            uris.add(file.uri);
        }
        MediaStore.createTrashRequest(context.getContentResolver(), uris, true);
    }
}