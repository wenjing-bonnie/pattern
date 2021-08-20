package com.android.pattern.decorator;

/**
 * Created by wenjing.liu on 2021/5/19 in J1.
 * <p>
 * 具体的被装饰类：一杯小杯珍珠奶茶的价格为13元
 *
 * @author wenjing.liu
 */
public class MilkTeaConcreteComponent implements CocoComponent {

    @Override
    public String getCocoName() {
        System.out.println("MilkTeaConcreteComponent");
        return "珍珠奶茶";
    }

    @Override
    public double cost() {
        return 13;
    }

    @Override
    public String toString() {
        return String.format("%s\n价格为:%.2f", getCocoName(), cost());
    }
}
