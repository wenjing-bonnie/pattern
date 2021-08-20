package com.android.pattern.proxy.aidl;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by wenjing.liu on 2021/4/8 in J1.
 * 用来模拟百度推送和接收的消息
 *
 * @author wenjing.liu
 */
public class BaiduMessage implements Parcelable {
    /**
     * 标题
     */
    private String title;
    /**
     * 内容
     */
    private String content;

    public BaiduMessage(String title, String content) {
        this.title = title;
        this.content = content;
    }

    protected BaiduMessage(Parcel in) {
        title = in.readString();
        content = in.readString();
    }

    public String getTitle() {
        return title;
    }

    public static final Creator<BaiduMessage> CREATOR = new Creator<BaiduMessage>() {
        @Override
        public BaiduMessage createFromParcel(Parcel in) {
            return new BaiduMessage(in);
        }

        @Override
        public BaiduMessage[] newArray(int size) {
            return new BaiduMessage[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(title);
        dest.writeString(content);
    }

    @Override
    public String toString() {
        return "BaiduMessage{" +
                "title='" + title + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}
