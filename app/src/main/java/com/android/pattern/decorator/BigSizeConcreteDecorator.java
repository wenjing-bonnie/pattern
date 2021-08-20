package com.android.pattern.decorator;

/**
 * Created by wenjing.liu on 2021/5/19 in J1.
 * <p>
 * 具体的装饰类：大杯的价格
 *
 * @author wenjing.liu
 */
public class BigSizeConcreteDecorator extends SizeDecorator {

    public BigSizeConcreteDecorator(CocoComponent coco) {
        super(coco);
    }

    @Override
    public String addSizeDescription() {
        return "大杯";
    }

    @Override
    public String getCocoName() {
        return String.format("%s&%s", cocoComponent.getCocoName(), addSizeDescription());
    }

    @Override
    public double cost() {
        return 20 + cocoComponent.cost();
    }


}
