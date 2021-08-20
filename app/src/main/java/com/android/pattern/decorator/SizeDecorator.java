package com.android.pattern.decorator;

/**
 * Created by wenjing.liu on 2021/5/19 in J1.
 * <p>
 * 尺寸的装饰类基类：用来定义不同的尺寸
 *
 * @author wenjing.liu
 */
public abstract class SizeDecorator implements CocoComponent {
    CocoComponent cocoComponent;

    public SizeDecorator(CocoComponent component) {
        this.cocoComponent = component;
    }

    public abstract String addSizeDescription();

    @Override
    public String toString() {
        return String.format("%s\n原来价格为:%.2f\n升级%s 价格为:%.2f", getCocoName(), cocoComponent.cost(), addSizeDescription(), cost());
    }
}
