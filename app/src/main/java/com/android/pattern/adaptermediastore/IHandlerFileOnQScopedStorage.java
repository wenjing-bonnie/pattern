package com.android.pattern.adaptermediastore;

import android.content.Context;

/**
 * Created by wenjing.liu on 2021/8/24.
 * <p>
 * 适配AndroidQ及以上的版本读写多媒体文件:
 * 针对AndroidQ及以上使用MediaStore来读写文件,AndroidQ以下的通过File进行读写文件.
 * <相同点>针对不同的多媒体文件不同的就是File或者Uri的获取</相同点>
 * <不同点>获取到OutputStream之后的进行读写内容都是一致的了</不同点>
 *
 * @author wenjing.liu
 */
public interface IHandlerFileOnQScopedStorage {
    /**
     * 追加的方式写文件
     *
     * @param context:  Context对象
     * @param fileName: 文件的名字
     * @param content:  要写入的内容
     */
     void writeAndAppend(Context context, String fileName, String content);

}
