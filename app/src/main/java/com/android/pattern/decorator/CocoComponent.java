package com.android.pattern.decorator;

/**
 * Created by wenjing.liu on 2021/5/19 in J1.
 * <p>
 * 被装饰类的基类 定义了被装饰对象的基本功能:一杯CoCo的价格
 *
 * @author wenjing.liu
 */
public interface CocoComponent {
    String getCocoName();

    double cost();

}
