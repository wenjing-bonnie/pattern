package com.java.pattern.proxy.dynamicproxy;

import com.java.pattern.proxy.common.DefaultSqlSession;
import com.java.pattern.proxy.common.SqlSession;

/**
 * Created by wenjing.liu on 2021/4/8 in J1.
 * 具体逻辑可参见org.mybatis.spring.SqlSessionUtils的逻辑，这里仅仅是示意
 *
 * @author wenjing.liu
 */
public class SqlSessionUtils {
    /**
     * 具体逻辑参照org.mybatis.spring.SqlSessionUtils#getSqlSession()。这里仅仅是模拟逻辑
     *
     * @return
     */
    public static SqlSession getSqlSession(SqlSession session) {
        return session;
    }

    /**
     * 具体逻辑参照org.mybatis.spring.SqlSessionUtils#isSqlSessionTransactional()。这里仅仅是模拟逻辑
     *
     * @return
     */
    public static boolean isSqlSessionTransactional(String name) {
        return name.equals("commit");
    }
}
