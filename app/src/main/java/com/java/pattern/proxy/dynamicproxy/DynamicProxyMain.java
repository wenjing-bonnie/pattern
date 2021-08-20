package com.java.pattern.proxy.dynamicproxy;

import com.java.pattern.proxy.common.DefaultSqlSession;
import com.java.pattern.proxy.common.SqlSession;

import java.lang.reflect.Proxy;

/**
 * Created by wenjing.liu on 2021/4/6 in J1.
 * 动态代理：可以不需要提前创建代理类对象，通过反射机制在运行的时候创建代理类
 *
 * @author wenjing.liu
 */

public class DynamicProxyMain {

    public static void main(String[] args) {
        //实例化被代理类
        DefaultSqlSession defaultSqlSession = new DefaultSqlSession();
        //动态代理获得代理类
        DynamicProxyInvocationHandler handler = new DynamicProxyInvocationHandler(defaultSqlSession);
        SqlSession sqlSession = (SqlSession) Proxy.newProxyInstance(defaultSqlSession.getClass().getClassLoader(),
                defaultSqlSession.getClass().getInterfaces(), handler);

        sqlSession.insert("插入语句");
    }
}
