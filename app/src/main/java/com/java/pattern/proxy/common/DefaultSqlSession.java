package com.java.pattern.proxy.common;

/**
 * Created by wenjing.liu on 2021/4/6 in J1.
 * 被代理类:实现具体的逻辑功能
 *
 * @author wenjing.liu
 */

public class DefaultSqlSession implements SqlSession {
    @Override
    public int insert(String var1) {
        System.out.println("DefaultSqlSession 在\'被代理类\'中insert实现具体的插入操作");
        return 0;
    }

    @Override
    public void commit(boolean isCommit) {
        System.out.println("DefaultSqlSession 在\'被代理类\'中commit实现具体的提交操作");
    }
}
