package com.android.pattern.chain;

/**
 * Created by wenjing.liu on 2021/9/1 in J1.
 * <p>
 * 广告的抽象类
 *
 * @author wenjing.liu
 */
public abstract class AdvertHandler {
    private AdvertHandler advertHandler;

    public void setNextAdvert(AdvertHandler handler) {
        this.advertHandler = handler;
    }

    public AdvertHandler getAdvertHandler() {
        return this.advertHandler;
    }

    public abstract void handlerAdvert(String type);

}
