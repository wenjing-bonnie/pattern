package com.android.pattern.decorator;

/**
 * Created by wenjing.liu on 2021/5/19 in J1.
 * 配料装饰类基类:用来定义添加的调料的名称：可以为不同的珍珠奶茶添加珍珠、椰果等配料
 *
 * @author wenjing.liu
 */
public abstract class BatchingDecorator implements CocoComponent {

    CocoComponent cocoComponent;

    public BatchingDecorator(CocoComponent component) {
        this.cocoComponent = component;
    }

    /**
     * 添加的配料的描述
     *
     * @return
     */
    public abstract String addBatchingDescription();

    @Override
    public String toString() {
        return String.format("%s\n原来价格为:%.2f\n添加%s 价格为:%.2f", getCocoName(), cocoComponent.cost(), addBatchingDescription(), cost());
    }

}
