package com.android.pattern.decorator;

/**
 * Created by wenjing.liu on 2021/5/19 in J1.
 * <p>
 * 具体的装饰类：为添加了椰果的珍珠奶茶计算价格
 *
 * @author wenjing.liu
 */
public class YeguoMilkTeaConcreteDecorator extends BatchingDecorator {

    public YeguoMilkTeaConcreteDecorator(CocoComponent coco) {
        super(coco);
    }

    @Override
    public String addBatchingDescription() {
        return "椰果";
    }

    @Override
    public String getCocoName() {
        return String.format("%s&%s", cocoComponent.getCocoName(), addBatchingDescription());
    }

    @Override
    public double cost() {
        return 2 + cocoComponent.cost();
    }
}
