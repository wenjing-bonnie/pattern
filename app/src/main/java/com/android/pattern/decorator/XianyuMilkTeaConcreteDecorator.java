package com.android.pattern.decorator;

/**
 * Created by wenjing.liu on 2021/5/19 in J1.
 * <p>
 * 具体的装饰类：为添加了珍珠的珍珠奶茶计算价格
 *
 * @author wenjing.liu
 */
public class XianyuMilkTeaConcreteDecorator extends BatchingDecorator {

    public XianyuMilkTeaConcreteDecorator(CocoComponent component) {
        super(component);
    }

    @Override
    public String addBatchingDescription() {
        return "鲜芋";
    }

    @Override
    public String getCocoName() {
        return String.format("%s&%s", cocoComponent.getCocoName(), addBatchingDescription());
    }

    @Override
    public double cost() {
        return 1 + cocoComponent.cost();
    }

}
