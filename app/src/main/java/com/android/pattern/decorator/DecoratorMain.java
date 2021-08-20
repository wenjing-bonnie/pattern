package com.android.pattern.decorator;

/**
 * Created by wenjing.liu on 2021/5/19 in J1.
 * 顾客购买CoCo奶茶的价格运行类
 *
 * @author wenjing.liu
 */
public class DecoratorMain {

    public static void main(String[] args) {
        //默认的为小杯珍珠奶茶
        System.out.println("======默认的为小杯珍珠奶茶========");
        CocoComponent cocoComponent = new MilkTeaConcreteComponent();
        System.out.println(cocoComponent.toString());
        //直接为MilkTeaConcreteComponent添加鲜芋
        System.out.println("=======添加鲜芋=======");
        CocoComponent zhenzhu = new XianyuMilkTeaConcreteDecorator(cocoComponent);
        System.out.println(zhenzhu.toString());
        //添加MilkTeaConcreteComponent椰果
        System.out.println("======添加椰果========");
        CocoComponent yeguo = new YeguoMilkTeaConcreteDecorator(cocoComponent);
        System.out.println(yeguo.toString());
        //为YeguoMilkTeaConcreteDecorator升级大杯
        System.out.println("=======升级大杯=======");
        CocoComponent bigYeguo = new BigSizeConcreteDecorator(yeguo);
        System.out.println(bigYeguo.toString());
        //为YeguoMilkTeaConcreteDecorator升级中杯
        System.out.println("=======升级中杯=======");
        CocoComponent middleYeguo = new MiddleSizeConcreteDecorator(yeguo);
        System.out.println(middleYeguo.toString());
    }
}
