package com.android.pattern.decorator;

/**
 * Created by wenjing.liu on 2021/5/19 in J1.
 * <p>
 * 具体的装饰类：一杯中杯珍珠奶茶的价格
 *
 * @author wenjing.liu
 */
public class MiddleSizeConcreteDecorator extends SizeDecorator {

    public MiddleSizeConcreteDecorator(CocoComponent coco) {
       super(coco);
    }

    @Override
    public String addSizeDescription() {
        return "中杯";
    }


    @Override
    public String getCocoName() {
        return String.format("%s&%s", cocoComponent.getCocoName(), addSizeDescription());
    }

    @Override
    public double cost() {
        return 10 + cocoComponent.cost();
    }


}

