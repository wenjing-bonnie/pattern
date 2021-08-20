package com.java.pattern.proxy.staticproxy;

import com.java.pattern.proxy.common.SqlSession;

/**
 * Created by wenjing.liu on 2021/4/7 in J1.
 * 代理类:
 * (1)可以预处理、过滤消息;
 * (2)传递消息给到代理类;
 * (3) 后处理消息.
 *
 * @author wenjing.liu
 */
public class SqlSessionTemplate implements SqlSession {
    /**
     * 持有一个代理类的引用
     */
    private SqlSession sqlSession;

    public SqlSessionTemplate(SqlSession sqlSession) {
        this.sqlSession = sqlSession;

    }

    @Override
    public int insert(String var1) {
        //可以预处理、过滤以及将消息传递给被代理类
        System.out.println("可以在\'代理类\'执行插入操作之前做一些预检查工作:例如增加事务管理");
        //通过代理类的引用来实现具体的逻辑功能
        sqlSession.insert(var1);
        //可以消息后处理
        System.out.println("可以在\'代理类\'执行插入操作之后做一些后处理工作:例如自动提交事务");
        return 0;
    }

    @Override
    public void commit(boolean isCommit) {
        //可以预处理、过滤以及将消息传递给被代理类
        System.out.println("可以在\'代理类\'执行删除操作之前做一些预检查工作:例如增加事务管理");
        //通过代理类的引用来实现具体的逻辑功能
        sqlSession.commit(true);
        //可以消息后处理
        System.out.println("可以在\'代理类\'执行删除操作之后做一些后处理工作:例如自动提交事务");
    }
}
