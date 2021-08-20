package com.java.pattern.proxy.common;

/**
 * Created by wenjing.liu on 2021/4/6 in J1.
 * 公共接口
 *
 * @author wenjing.liu
 */

public interface SqlSession {
    /**
     * 实现插入操作
     *
     * @param var1
     * @return
     */
    int insert(String var1);

    /**
     * 提交操作
     */
    void commit(boolean isCommit);

}
