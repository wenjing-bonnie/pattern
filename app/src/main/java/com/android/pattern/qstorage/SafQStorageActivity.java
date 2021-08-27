package com.android.pattern.qstorage;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.view.View;

import androidx.documentfile.provider.DocumentFile;

import com.android.common.Log;
import com.android.pattern.R;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/**
 * create by wenjing.liu on 2021/08/27
 * 使用SAF对非多媒体文件的读写操作
 * TODO 注意里面的文件名字都是我的手机上的文件，需要自行修改
 */
public class SafQStorageActivity extends Activity {
    private Context context;
    private static final int CODE_ACTION_OPEN_DOCUMENT = 1;
    private static final int CODE_ACTION_OPEN_DOCUMENT_TREE = 2;
    private static final int CODE_ACTION_CREATE_DOCUMENT = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_not_media_qstorage);
        context = SafQStorageActivity.this;
    }

    /**
     * 访问单个文件
     *
     * @param view
     */
    public void btnSingleFile(View view) {
        Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
        intent.setType("*/*");
        startActivityForResult(intent, CODE_ACTION_OPEN_DOCUMENT);
    }

    /**
     * 访问单个文件夹
     *
     * @param view
     */
    public void btnSingleFolder(View view) {
        Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT_TREE);
        startActivityForResult(intent, CODE_ACTION_OPEN_DOCUMENT_TREE);
    }

    /**
     * 创建单个文件
     *
     * @param view
     */
    public void btnCreateFile(View view) {
        Intent intent = new Intent(Intent.ACTION_CREATE_DOCUMENT);
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        //设置文件的类型
        intent.setType("text/*");
        //设置文件的名字
        intent.putExtra(Intent.EXTRA_TITLE, "123.txt");
        startActivityForResult(intent, CODE_ACTION_CREATE_DOCUMENT);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode != RESULT_OK) {
            return;
        }
        switch (requestCode) {
            case CODE_ACTION_OPEN_DOCUMENT:
            case CODE_ACTION_CREATE_DOCUMENT: {
                Uri uri = data.getData();
                writeText(uri);
                // writeImage(uri);
                break;
            }
            case CODE_ACTION_OPEN_DOCUMENT_TREE: {
                Uri uri = data.getData();
                printFolderAllUri(uri);
                break;
            }
            default:
        }
    }

    /**
     * 读写文件
     *
     * @param uri
     */
    private void writeText(Uri uri) {
        AbsHandlerOnQScopedStorage storage = new DownloadsHandlerOnQScopedStorage();
        storage.writeAndAppend(context, uri, "adc".getBytes());
        //读文件OK
        String info = (String) storage.read(context, uri);
        Log.d(uri + " info = " + info.substring(info.length() - 3));
    }

    private void writeImage(Uri uri) {
        AbsHandlerOnQScopedStorage storage = new ImageHandlerOnQScopedStorage();
        storage.writeAndAppend(context, uri, "123".getBytes());
    }

    /**
     * 将文件夹下所有文件
     *
     * @param uri
     */
    private void printFolderAllUri(Uri uri) {
        DocumentFile documentFile = DocumentFile.fromTreeUri(context, uri);
        for (DocumentFile doc : documentFile.listFiles()) {
            //writeText(doc.getUri());
            // deleteFile(doc.getUri());
        }
    }

    private void deleteFile(Uri uri) {
        try {
            DocumentsContract.deleteDocument(context.getContentResolver(), uri);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }


}