package com.android.pattern.qstorage;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.DocumentsContract;
import android.view.View;

import com.android.common.Log;
import com.android.pattern.R;

public class NotMediaQStorageActivity extends Activity {
    private Context context;
    private static final int CODE_ACTION_OPEN_DOCUMENT = 1;
    private static final int CODE_ACTION_OPEN_DOCUMENT_TREE = 2;
    private static final int CODE_ACTION_CREATE_DOCUMENT = 3;
    //private static final int CODE_

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_not_media_qstorage);
        context = NotMediaQStorageActivity.this;
    }


    public void btnSingleFile(View view) {
        Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
        intent.setType("*/*");
        startActivityForResult(intent, CODE_ACTION_OPEN_DOCUMENT);
    }

    public void btnSingleFolder(View view) {

        Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT_TREE);
        startActivityForResult(intent, CODE_ACTION_OPEN_DOCUMENT_TREE);

    }

    public void btnCreateFile(View view) {
        Intent intent = new Intent(Intent.ACTION_CREATE_DOCUMENT);
        //intent.addCategory(Intent.CATEGORY_OPENABLE);
       // intent.setType("text/*");
        //打开特定的文件
        intent.putExtra(Intent.EXTRA_TITLE,"123.txt");
        startActivityForResult(intent, CODE_ACTION_CREATE_DOCUMENT);
    }

    public void btnDeleteFile(View view) {

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode != RESULT_OK) {
            return;
        }
        switch (requestCode) {
            case CODE_ACTION_OPEN_DOCUMENT: {
                Uri uri = data.getData();
                writeText(uri);
                break;
            }
            case CODE_ACTION_OPEN_DOCUMENT_TREE: {
                Uri uri = data.getData();
                printAllUri(uri);
                break;
            }
            case CODE_ACTION_CREATE_DOCUMENT: {

                break;
            }
            default:
        }
    }

    private void writeText(Uri uri) {
        AbsHandlerOnQScopedStorage storage = new DownloadsHandlerOnQScopedStorage();
        storage.writeAndAppend(context, uri, "adc".getBytes());
        //读文件OK
        String info = (String) storage.read(context, uri);
        Log.d("info = " + info.substring(info.length() - 3));
    }

    private void writeImage(Uri uri) {
        AbsHandlerOnQScopedStorage storage = new ImageHandlerOnQScopedStorage();
        storage.writeAndAppend(context, uri, "123".getBytes());
    }

    private void printAllUri(Uri uri) {
        AbsHandlerOnQScopedStorage storage = new DownloadsHandlerOnQScopedStorage();
        storage.getAllUris(context, uri);
       // DocumentsContract.getDocumentId(uri);
    }
}