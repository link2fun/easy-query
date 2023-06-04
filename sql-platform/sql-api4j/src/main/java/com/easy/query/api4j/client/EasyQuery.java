package com.easy.query.api4j.client;

import com.easy.query.api4j.delete.EntityDeletable;
import com.easy.query.api4j.delete.ExpressionDeletable;
import com.easy.query.api4j.insert.EntityInsertable;
import com.easy.query.api4j.select.Queryable;
import com.easy.query.api4j.update.EntityUpdatable;
import com.easy.query.api4j.update.ExpressionUpdatable;
import com.easy.query.core.basic.jdbc.parameter.EasyConstSQLParameter;
import com.easy.query.core.basic.jdbc.parameter.SQLParameter;
import com.easy.query.core.basic.jdbc.tx.Transaction;
import com.easy.query.core.context.QueryRuntimeContext;
import com.easy.query.core.util.EasyCollectionUtil;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * @author xuejiaming
 * @FileName: JQDCClient.java
 * @Description: 文件说明
 * @Date: 2023/2/5 21:27
 */
public interface EasyQuery {
    QueryRuntimeContext getRuntimeContext();

    default <T> List<T> sqlQuery(String sql, Class<T> clazz) {
        return sqlQuery(sql, clazz, Collections.emptyList());
    }

    default <T> List<T> sqlQuery(String sql, Class<T> clazz, List<Object> parameters) {
        List<SQLParameter> sqlParameters = EasyCollectionUtil.map(parameters, o -> new EasyConstSQLParameter(null, null, o));
        return sqlEasyQuery(sql, clazz, sqlParameters);
    }

    <T> List<T> sqlEasyQuery(String sql, Class<T> clazz, List<SQLParameter> parameters);

    default List<Map<String, Object>> sqlQueryMap(String sql) {
        return sqlQueryMap(sql, Collections.emptyList());
    }

    List<Map<String, Object>> sqlQueryMap(String sql, List<Object> parameters);

    default long sqlExecute(String sql) {
        return sqlExecute(sql, Collections.emptyList());
    }

    long sqlExecute(String sql, List<Object> parameters);

    <T> Queryable<T> queryable(Class<T> clazz);

    <T> Queryable<T> queryable(String sql, Class<T> clazz);

    default Transaction beginTransaction() {
        return beginTransaction(null);
    }

    /**
     * 数据库隔离级别:
     * Connection.TRANSACTION_READ_UNCOMMITTED,
     * Connection.TRANSACTION_READ_COMMITTED,
     * Connection.TRANSACTION_REPEATABLE_READ,
     * Connection.TRANSACTION_SERIALIZABLE
     *
     * @param isolationLevel null表示不使用任何指定隔离级别,使用默认的
     * @return
     */
    Transaction beginTransaction(Integer isolationLevel);

    <T> EntityInsertable<T> insertable(T entity);

    <T> EntityInsertable<T> insertable(Collection<T> entities);

    <T> ExpressionUpdatable<T> updatable(Class<T> entityClass);

    <T> EntityUpdatable<T> updatable(T entity);

    <T> EntityUpdatable<T> updatable(Collection<T> entities);

    <T> EntityDeletable<T> deletable(T entity);

    <T> EntityDeletable<T> deletable(Collection<T> entities);

    <T> ExpressionDeletable<T> deletable(Class<T> entityClass);

    /**
     * 添加到当前环境的追踪里面
     * 如果当前线程未开启追踪那么添加直接忽略无效
     * 相同对象多次追踪结果还是true
     *
     * @param entity
     * @return true:添加成功,false:已经存在相同对象 或者未开启追踪
     */
    boolean addTracking(Object entity);
}