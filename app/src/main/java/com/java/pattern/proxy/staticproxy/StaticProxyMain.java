package com.java.pattern.proxy.staticproxy;

import com.java.pattern.proxy.common.DefaultSqlSession;

/**
 * Created by wenjing.liu on 2021/4/6 in J1.
 * 在Android Studio中执行main(),要使用Run with Coverage
 *
 * @author wenjing.liu
 */

public class StaticProxyMain {

    public static void main(String[] args) {
        //实例化被代理类
        DefaultSqlSession sqlSession = new DefaultSqlSession();
        //实例化代理类
        SqlSessionTemplate template = new SqlSessionTemplate(sqlSession);
        //在每次操作数据库的时候，都需要手动提交commit()
        template.insert("插入语句");
        template.commit(true);
    }
}
