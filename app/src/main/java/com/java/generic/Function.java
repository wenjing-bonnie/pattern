package com.java.generic;

/**
 * Created by wenjing.liu on 2021/4/8 in J1.
 *
 * @author wenjing.liu
 */
public interface Function<U, T> {

    T apply(U u);

    //public void dd(T t);
    //  <T> T apply(U data);
//    void test(T data);
    //private List dd = new ArrayList<String>();
    //private List<String> ddd= new ArrayList<String>();
}
