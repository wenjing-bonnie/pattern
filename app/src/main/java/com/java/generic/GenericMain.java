package com.java.generic;

/**
 * Created by wenjing.liu on 2021/4/8 in J1.
 *
 * @author wenjing.liu
 */
public class GenericMain<U,T> {

//    public <T> T test(Function<U,T> utFunction) {
//       System.out.println(utFunction.apply("333"));
//       return utFunction.apply("22");
//    }

    public void ss(){
//        test(new Function<U, T>() {
//            @Override
//            public T apply(U u) {
//                return null;
//            }
//        });
    }

    public static void main(String[] args) {
        GenericMain main = new GenericMain();
        main.ss();;
    }

}
