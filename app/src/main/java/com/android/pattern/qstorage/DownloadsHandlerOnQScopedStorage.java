package com.android.pattern.qstorage;

import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;

import androidx.annotation.RequiresApi;

/**
 * Created by wenjing.liu on 2021/8/24 .
 * <p>
 * 存储在/storage/emulated/0/Downloads的文件进行读写
 *
 * @author wenjing.liu
 */
public class DownloadsHandlerOnQScopedStorage extends AbsHandlerOnQScopedStorage {

    @Override
    public String getExternalStoragePublicDirectoryType() {
        return Environment.DIRECTORY_DOWNLOADS;
    }

    @Override
    @RequiresApi(Build.VERSION_CODES.Q)
    public Uri getExternalContentUri() {
        return MediaStore.Downloads.EXTERNAL_CONTENT_URI;
    }

    @Override
    @RequiresApi(Build.VERSION_CODES.Q)
    public Uri getInternalContentUri() {
        return MediaStore.Downloads.INTERNAL_CONTENT_URI;
    }
}
