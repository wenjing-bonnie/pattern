package com.java.pattern.proxy.dynamicproxy;

import com.java.pattern.proxy.common.SqlSession;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Created by wenjing.liu on 2021/4/7 in J1.
 * 反射机制
 *
 * @author wenjing.liu
 */
public class DynamicProxyInvocationHandler implements InvocationHandler {
    //持有被代理对象的引用
    private SqlSession sqlSessionProxy;

    public DynamicProxyInvocationHandler(SqlSession proxy) {
        this.sqlSessionProxy = proxy;
    }

    /**
     * 这个被代理对象的每个SqlSession方法调用的时候都会执行该方法
     *
     * @param proxy  通过动态代理生成的被代理对象
     * @param method 调用的被代理对象的接口方法
     * @param args   调用的被代理对象的接口方法的传入参数
     * @return 返回调用的被代理对象的接口方法的返回值
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Object result;
        String methodName = method.getName();
        //仅仅模拟SqlSessionUtils.getSqlSession()
        SqlSession sqlSession = SqlSessionUtils.getSqlSession(sqlSessionProxy);
        System.out.println(String.format("可以在\'代理类\'执行%s之前做一些预检查工作:例如增加事务管理", methodName));
        //通过反射机制获取到对应的方法的返回值
        result = method.invoke(sqlSessionProxy, args);
        //模拟!SqlSessionUtils.isSqlSessionTransactional
        if (!SqlSessionUtils.isSqlSessionTransactional(methodName)) {
            sqlSession.commit(true);
        }
        //可以消息后处理
        System.out.println(String.format("可以在\'代理类\'执行%s之后做一些后处理工作:例如自动提交事务", methodName));
        return result;
    }
}
