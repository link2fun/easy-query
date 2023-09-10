package com.easy.query.api.proxy.select.abstraction;

import com.easy.query.api.proxy.select.ProxyQueryable;
import com.easy.query.api.proxy.select.ProxyQueryable2;
import com.easy.query.api.proxy.select.extension.queryable2.sql.MultiProxyFilter2;
import com.easy.query.api.proxy.select.extension.queryable2.sql.MultiProxyFilter2Impl;
import com.easy.query.api.proxy.select.impl.EasyProxyQueryable;
import com.easy.query.api.proxy.sql.ProxyAsSelector;
import com.easy.query.api.proxy.sql.ProxyFilter;
import com.easy.query.api.proxy.sql.ProxySelector;
import com.easy.query.api.proxy.sql.impl.ProxyAsSelectorImpl;
import com.easy.query.api.proxy.sql.impl.ProxyFilterImpl;
import com.easy.query.api.proxy.sql.impl.ProxySelectorImpl;
import com.easy.query.core.api.pagination.EasyPageResult;
import com.easy.query.core.basic.api.select.ClientQueryable;
import com.easy.query.core.basic.api.select.ClientQueryable2;
import com.easy.query.core.basic.jdbc.executor.internal.enumerable.JdbcStreamResult;
import com.easy.query.core.basic.jdbc.parameter.ToSQLContext;
import com.easy.query.core.context.QueryRuntimeContext;
import com.easy.query.core.enums.sharding.ConnectionModeEnum;
import com.easy.query.core.expression.builder.core.ConditionAccepter;
import com.easy.query.core.expression.lambda.SQLExpression1;
import com.easy.query.core.expression.lambda.SQLExpression2;
import com.easy.query.core.expression.segment.ColumnSegment;
import com.easy.query.core.expression.sql.builder.EntityQueryExpressionBuilder;
import com.easy.query.core.proxy.ProxyEntity;
import com.easy.query.core.util.EasyCollectionUtil;
import com.easy.query.core.util.EasyObjectUtil;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

/**
 * create time 2023/6/23 22:08
 * 文件说明
 *
 * @author xuejiaming
 */
public abstract class AbstractProxyQueryable2<T1Proxy extends ProxyEntity<T1Proxy, T1>, T1,
        T2Proxy extends ProxyEntity<T2Proxy, T2>, T2> implements ProxyQueryable2<T1Proxy, T1,T2Proxy,T2> {

    protected final T1Proxy t1Proxy;
    protected final QueryRuntimeContext runtimeContext;
    protected final ClientQueryable2<T1,T2> entityQueryable;
    private final T2Proxy t2Proxy;

    public AbstractProxyQueryable2(T1Proxy t1Proxy,T2Proxy t2Proxy, ClientQueryable2<T1,T2> entityQueryable) {
        EntityQueryExpressionBuilder sqlEntityExpressionBuilder = entityQueryable.getSQLEntityExpressionBuilder();
        this.runtimeContext = sqlEntityExpressionBuilder.getRuntimeContext();
        this.t1Proxy = t1Proxy.create(sqlEntityExpressionBuilder.getTable(0).getEntityTable());
        this.t2Proxy = t2Proxy.create(sqlEntityExpressionBuilder.getTable(1).getEntityTable());
        this.entityQueryable = entityQueryable;
    }

    @Override
    public T1Proxy get1Proxy() {
        return t1Proxy;
    }
    @Override
    public T2Proxy get2Proxy() {
        return t2Proxy;
    }


    @Override
    public QueryRuntimeContext getRuntimeContext() {
        return runtimeContext;
    }

    @Override
    public ClientQueryable2<T1,T2> getClientQueryable2() {
        return entityQueryable;
    }

    @Override
    public ProxyQueryable2<T1Proxy, T1,T2Proxy,T2> getQueryable2() {
        return this;
    }

    @Override
    public Class<T1> queryClass() {
        return entityQueryable.queryClass();
    }

    @Override
    public long count() {
        return entityQueryable.count();
    }

    @Override
    public long countDistinct(SQLExpression2<ProxySelector, T1Proxy> selectExpression) {
        return entityQueryable.countDistinct(selector -> {
            selectExpression.apply(new ProxySelectorImpl(selector.getSelector()), get1Proxy());
        });
    }

    @Override
    public boolean any() {
        return entityQueryable.any();
    }

    @Override
    public boolean all(SQLExpression2<ProxyFilter,T1Proxy> whereExpression) {
        return entityQueryable.all(where -> {
            whereExpression.apply(new ProxyFilterImpl(where.getFilter()), get1Proxy());
        });
    }


    @Override
    public <TR> TR firstOrNull(Class<TR> resultClass) {
        return entityQueryable.firstOrNull(resultClass);
    }

    @Override
    public <TR> TR firstNotNull(Class<TR> resultClass, String msg, String code) {
        return entityQueryable.firstNotNull(resultClass, msg, code);
    }
    @Override
    public List<Map<String, Object>> toMaps() {
        return entityQueryable.toMaps();
    }

    @Override
    public <TRProxy extends ProxyEntity<TRProxy, TR>, TR> List<TR> toList(TRProxy trProxy) {
        return toList(trProxy.getEntityClass());
    }

    @Override
    public <TR> List<TR> toList(Class<TR> resultClass) {
        return entityQueryable.toList(resultClass);
    }

    @Override
    public <TR> JdbcStreamResult<TR> toStreamResult(Class<TR> resultClass) {
        return entityQueryable.toStreamResult(resultClass);
    }

    @Override
    public <TR> String toSQL(Class<TR> resultClass, ToSQLContext toSQLContext) {
        return entityQueryable.toSQL(resultClass, toSQLContext);
    }

    @Override
    public ProxyQueryable<T1Proxy,T1> select(SQLExpression2<ProxySelector, T1Proxy> selectExpression) {
        ClientQueryable<T1> select = entityQueryable.select(columnSelector -> {
            selectExpression.apply(new ProxySelectorImpl(columnSelector.getSelector()), t1Proxy);
        });
        return new EasyProxyQueryable<>(t1Proxy, select);
    }

    @Override
    public <TRProxy extends ProxyEntity<TRProxy, TR>, TR> ProxyQueryable<TRProxy, TR> select(TRProxy trProxy, SQLExpression2<ProxyAsSelector<TRProxy, TR>, T1Proxy> selectExpression) {

        ClientQueryable<TR> select = entityQueryable.select(trProxy.getEntityClass(), columnAsSelector -> {
            selectExpression.apply(new ProxyAsSelectorImpl<>(trProxy, columnAsSelector.getAsSelector()), get1Proxy());
        });
        return new EasyProxyQueryable<>(trProxy, select);
    }

    @Override
    public ProxyQueryable<T1Proxy,T1> select(String columns) {
        entityQueryable.select(columns);
        return new EasyProxyQueryable<>(t1Proxy, entityQueryable);
    }

    @Override
    public ProxyQueryable<T1Proxy,T1> select(Collection<ColumnSegment> columnSegments, boolean clearAll) {
        entityQueryable.select(columnSegments, clearAll);
        return new EasyProxyQueryable<>(t1Proxy, entityQueryable);
    }

    @Override
    public <TRProxy extends ProxyEntity<TRProxy, TR>, TR> ProxyQueryable<TRProxy, TR> select(ProxyEntity<TRProxy, TR> trProxy) {
        ClientQueryable<TR> select = entityQueryable.select(trProxy.getEntityClass());
        return new EasyProxyQueryable<>(EasyObjectUtil.typeCastNullable(trProxy), select);
    }


    @Override
    public ProxyQueryable2<T1Proxy,T1,T2Proxy,T2> where(boolean condition, SQLExpression1<MultiProxyFilter2<T1Proxy,T2Proxy>> whereExpression) {
        if (condition) {
            entityQueryable.where(wherePredicate -> {
                whereExpression.apply(new MultiProxyFilter2Impl<>(wherePredicate.getFilter(), get1Proxy(),get2Proxy()));
            });
        }
        return this;
    }

//    @Override
//    public ProxyQueryable2<T1Proxy,T1,T2Proxy,T2> where1(SQLFuncExpression1<T1Proxy, SQLPredicate> whereExpression) {
//        SQLPredicate sqlPredicate = whereExpression.apply(get1Proxy());
//        entityQueryable.where(wherePredicate -> {
//            sqlPredicate.accept(wherePredicate.getFilter());
//        });
//        return this;
//    }

    @Override
    public ProxyQueryable2<T1Proxy,T1,T2Proxy,T2> whereById(boolean condition, Object id) {
        if (condition) {
            entityQueryable.whereById(id);
        }
        return this;
    }

    @Override
    public <TProperty> ProxyQueryable2<T1Proxy,T1,T2Proxy,T2> whereByIds(boolean condition, Collection<TProperty> ids) {

        if (condition) {
            entityQueryable.whereByIds(ids);
        }
        return this;
    }

    @Override
    public ProxyQueryable2<T1Proxy,T1,T2Proxy,T2> whereObject(boolean condition, Object object) {
        if (condition) {
            entityQueryable.whereObject(object);
        }
        return this;
    }

    @Override
    public ProxyQueryable2<T1Proxy,T1,T2Proxy,T2> distinct(boolean condition) {
        if (condition) {
            entityQueryable.distinct();
        }
        return this;
    }

    @Override
    public ProxyQueryable2<T1Proxy,T1,T2Proxy,T2> limit(boolean condition, long offset, long rows) {
        if (condition) {
            entityQueryable.limit(offset, rows);
        }
        return this;
    }

    @Override
    public EasyPageResult<T1> toPageResult(long pageIndex, long pageSize, long pageTotal) {
        return entityQueryable.toPageResult(pageIndex, pageSize, pageTotal);
    }

    @Override
    public EasyPageResult<T1> toShardingPageResult(long pageIndex, long pageSize, List<Long> totalLines) {
        return entityQueryable.toShardingPageResult(pageIndex, pageSize, totalLines);
    }

    @Override
    public EntityQueryExpressionBuilder getSQLEntityExpressionBuilder() {
        return entityQueryable.getSQLEntityExpressionBuilder();
    }


    @Override
    public ProxyQueryable<T1Proxy,T1> union(Collection<ProxyQueryable<T1Proxy,T1>> unionQueries) {

        if (EasyCollectionUtil.isEmpty(unionQueries)) {
            throw new IllegalArgumentException("unionQueries is empty");
        }
        ClientQueryable<T1> unionQueryable = entityQueryable.union(EasyCollectionUtil.select(unionQueries, (queryable, i) -> queryable.getClientQueryable()));
        return new EasyProxyQueryable<>(t1Proxy, unionQueryable);
    }

    @Override
    public ProxyQueryable<T1Proxy,T1> unionAll(Collection<ProxyQueryable<T1Proxy,T1>> unionQueries) {
        if (EasyCollectionUtil.isEmpty(unionQueries)) {
            throw new IllegalArgumentException("unionQueries is empty");
        }
        ClientQueryable<T1> unionQueryable = entityQueryable.unionAll(EasyCollectionUtil.select(unionQueries, (queryable, i) -> queryable.getClientQueryable()));
        return new EasyProxyQueryable<>(t1Proxy, unionQueryable);
    }

    @Override
    public ProxyQueryable2<T1Proxy,T1,T2Proxy,T2> useLogicDelete(boolean enable) {
        entityQueryable.useLogicDelete(enable);
        return this;
    }

    @Override
    public ProxyQueryable2<T1Proxy,T1,T2Proxy,T2> noInterceptor() {
        entityQueryable.noInterceptor();
        return this;
    }

    @Override
    public ProxyQueryable2<T1Proxy,T1,T2Proxy,T2> useInterceptor(String name) {
        entityQueryable.useInterceptor(name);
        return this;
    }

    @Override
    public ProxyQueryable2<T1Proxy,T1,T2Proxy,T2> noInterceptor(String name) {
        entityQueryable.noInterceptor(name);
        return this;
    }

    @Override
    public ProxyQueryable2<T1Proxy,T1,T2Proxy,T2> useInterceptor() {
        entityQueryable.useInterceptor();
        return this;
    }

    @Override
    public ProxyQueryable2<T1Proxy,T1,T2Proxy,T2> asTracking() {
        entityQueryable.asTracking();
        return this;
    }

    @Override
    public ProxyQueryable2<T1Proxy,T1,T2Proxy,T2> asNoTracking() {
        entityQueryable.asNoTracking();
        return this;
    }

    @Override
    public ProxyQueryable2<T1Proxy,T1,T2Proxy,T2> asTable(Function<String, String> tableNameAs) {
        entityQueryable.asTable(tableNameAs);
        return this;
    }

    @Override
    public ProxyQueryable2<T1Proxy,T1,T2Proxy,T2> asSchema(Function<String, String> schemaAs) {
        entityQueryable.asSchema(schemaAs);
        return this;
    }

    @Override
    public ProxyQueryable2<T1Proxy,T1,T2Proxy,T2> asAlias(String alias) {
        entityQueryable.asAlias(alias);
        return this;
    }

    @Override
    public ProxyQueryable2<T1Proxy,T1,T2Proxy,T2> asTableLink(Function<String, String> linkAs) {
        entityQueryable.asTableLink(linkAs);
        return this;
    }

    @Override
    public ProxyQueryable2<T1Proxy,T1,T2Proxy,T2> useShardingConfigure(int maxShardingQueryLimit, ConnectionModeEnum connectionMode) {
        entityQueryable.useShardingConfigure(maxShardingQueryLimit, connectionMode);
        return this;
    }

    @Override
    public ProxyQueryable2<T1Proxy,T1,T2Proxy,T2> useMaxShardingQueryLimit(int maxShardingQueryLimit) {
        entityQueryable.useMaxShardingQueryLimit(maxShardingQueryLimit);
        return this;
    }

    @Override
    public ProxyQueryable2<T1Proxy,T1,T2Proxy,T2> useConnectionMode(ConnectionModeEnum connectionMode) {
        entityQueryable.useConnectionMode(connectionMode);
        return this;
    }

    @Override
    public ProxyQueryable2<T1Proxy,T1,T2Proxy,T2> queryLargeColumn(boolean queryLarge) {
        entityQueryable.queryLargeColumn(queryLarge);
        return this;
    }

    @Override
    public ProxyQueryable2<T1Proxy,T1,T2Proxy,T2> conditionConfigure(ConditionAccepter conditionAccepter) {
        entityQueryable.conditionConfigure(conditionAccepter);
        return this;
    }
}
