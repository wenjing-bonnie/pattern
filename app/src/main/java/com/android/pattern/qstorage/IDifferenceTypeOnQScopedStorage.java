package com.android.pattern.qstorage;

import android.content.Context;

/**
 * Created by wenjing.liu on 2021/8/24 .
 *
 * @author wenjing.liu
 */
public interface IDifferenceTypeOnQScopedStorage {
    /**
     * 以追加方式添加内容
     *
     * @param context
     * @param fileName
     * @param content
     */
    void writeAndAppend(Context context, String fileName, byte[] content);

    /**
     * 读取文件中的内容
     *
     * @param context
     * @param fileName
     * @return
     */
    Object read(Context context, String fileName);

    /**
     * 删除文件
     *
     * @param context
     * @param fileName
     */
    void delete(Context context, String fileName);

}
