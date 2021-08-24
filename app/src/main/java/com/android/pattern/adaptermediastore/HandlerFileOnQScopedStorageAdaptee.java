package com.android.pattern.adaptermediastore;

import android.app.Activity;
import android.app.RecoverableSecurityException;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.IntentSender;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;

import androidx.annotation.RequiresApi;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

/**
 * Created by wenjing.liu on 2021/8/24 .
 * <p>
 * 适配每个多媒体文件夹的Uri和File的获取
 *
 * @author wenjing.liu
 */
public abstract class HandlerFileOnQScopedStorageAdaptee {

    /**
     * 当{@link Environment#getExternalStorageState()}是{@link Environment#MEDIA_MOUNTED}的时候返回external content uri
     *
     * @return
     */
    public abstract Uri getExternalContentUri();

    /**
     * 当{@link Environment#getExternalStorageState()}不是{@link Environment#MEDIA_MOUNTED}的时候返回internal content uri
     *
     * @return
     */
    public abstract Uri getInternalContentUri();

    /**
     * The type of storage directory to return.Should be one of
     * {@link Environment#DIRECTORY_MUSIC}, {@link Environment#DIRECTORY_PODCASTS},
     * {@link Environment#DIRECTORY_RINGTONES}, {@link Environment#DIRECTORY_ALARMS},
     * {@link Environment#DIRECTORY_NOTIFICATIONS}, {@link Environment#DIRECTORY_PICTURES},
     * {@link Environment#DIRECTORY_MOVIES}, {@link Environment#DIRECTORY_DOWNLOADS},
     * {@link Environment#DIRECTORY_DCIM}, or {@link Environment#DIRECTORY_DOCUMENTS}. May not be null.
     */
    public abstract String getExternalStoragePublicDirectoryType();

    /**
     * 适配Android Q及以上将文件写入到Download文件夹
     *
     * @param context
     * @param fileName
     * @param content
     */
    protected void writeAndAppendFileForAllSdk(Context context, String fileName, String content) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            writeAndAppendAboveQWithoutWritePermission(context, fileName, content);
        } else {
            writeAndAppendByFile(fileName, content);
        }
    }

    /**
     * 低于Android Q以下往Download文件中写文件
     * 在AndroidQ中也可以使用这种方式,但是如果关闭了存储权限,无法写成功, 要申请写权限
     *
     * @param fileName
     * @param content
     */
    private synchronized void writeAndAppendByFile(String fileName, String content) {
        try {
            File srcFile = getSrcFile(fileName);
            writeOutputStreamOfUtf8(new FileOutputStream(srcFile, true), content);
        } catch (FileNotFoundException e) {
        }
    }

    /**
     * Android Q及以上往Download文件写文件
     * 无需写权限就可以实现写文件
     *
     * @param context
     * @param content
     * @param fileName
     */
    @RequiresApi(Build.VERSION_CODES.Q)
    private synchronized void writeAndAppendAboveQWithoutWritePermission(Context context, String fileName, String content) {
        ContentResolver resolver = context.getContentResolver();
        Uri uri = getUriByDisplayName(context, fileName);
        try {
            writeOutputStreamOfUtf8(resolver.openOutputStream(uri, "wa"), content);
        } catch (FileNotFoundException e) {
        } catch (RecoverableSecurityException e) {
            //修改其他应用创建的多媒体文件会抛出该异常
            handlerRecoverableSecurityException(context, e);
        }
    }

    /**
     * 弹框提示用户是否允许修改或删除此文件.
     * 用户操作的结果,将通过onActivityResult回调返回到APP.如果用户允许,APP将获得该Uri 的修改权限,直到设备下一次重启.
     *
     * @param context
     * @param e
     */
    @RequiresApi(Build.VERSION_CODES.Q)
    private void handlerRecoverableSecurityException(Context context, RecoverableSecurityException e) {
        try {
            ((Activity) context).startIntentSenderForResult(e.getUserAction().getActionIntent().getIntentSender(), 100, null, 0, 0, 0);
        } catch (IntentSender.SendIntentException sendIntentException) {
            sendIntentException.printStackTrace();
        }
    }

    /**
     * 往OutputStream写入内容
     *
     * @param fos
     * @param content
     */
    private synchronized void writeOutputStreamOfUtf8(OutputStream fos, String content) {
        try {
            fos.write(content.getBytes(StandardCharsets.UTF_8));
        } catch (Exception e) {
        } finally {
            try {
                if (fos != null) {
                    fos.close();
                }
            } catch (Exception e) {
            }
        }
    }

    /**
     * 根据fileName获取对应的多媒体文件的File
     *
     * @param fileName
     * @return
     */
    private File getSrcFile(String fileName) {
        return new File(Environment.getExternalStoragePublicDirectory(getExternalStoragePublicDirectoryType()), fileName);
    }

    /**
     * 根据外部存储的状态返回对应的Uri
     *
     * @return
     */
    protected Uri getContentUriByStorageState() {
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            return getExternalContentUri();
        }
        return getInternalContentUri();
    }

    /**
     * 根据fileName获取AndroidQ及以上的Uri
     *
     * @param context
     * @param fileName
     * @return
     */
    private Uri getUriByDisplayName(Context context, String fileName) {
        Uri queryUri = null;
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.Q) {
            return null;
        }
        Uri uri = getContentUriByStorageState();

        String selection = MediaStore.MediaColumns.DISPLAY_NAME + "=?";
        String[] args = new String[]{fileName};
        String[] projection = new String[]{MediaStore.MediaColumns._ID};
        Cursor cursor = context.getContentResolver().query(uri, projection, selection, args, null);
        if (cursor == null) {
            return queryUri;
        }
        if (cursor.moveToFirst()) {
            queryUri = ContentUris.withAppendedId(uri, cursor.getLong(0));
            return queryUri;
        }
        //之前没有保存过该文件,则执行插入操作
        ContentValues values = new ContentValues();
        values.put(MediaStore.MediaColumns.DISPLAY_NAME, fileName);
        //TODO 类型暂时不去设置
        queryUri = context.getContentResolver().insert(uri, values);
        return queryUri;
    }
}
