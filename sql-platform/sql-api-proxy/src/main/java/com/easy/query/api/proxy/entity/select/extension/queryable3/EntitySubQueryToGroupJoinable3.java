package com.easy.query.api.proxy.entity.select.extension.queryable3;

import com.easy.query.api.proxy.entity.select.EntityQueryable3;
import com.easy.query.core.common.ValueHolder;
import com.easy.query.core.expression.lambda.SQLFuncExpression3;
import com.easy.query.core.expression.parser.core.available.TableAvailable;
import com.easy.query.core.proxy.ManyPropColumn;
import com.easy.query.core.proxy.ProxyEntity;
import com.easy.query.core.proxy.ProxyEntityAvailable;

/**
 * create time 2025/3/7 21:40
 * 文件说明
 *
 * @author xuejiaming
 */
public interface EntitySubQueryToGroupJoinable3<T1Proxy extends ProxyEntity<T1Proxy, T1>, T1,
        T2Proxy extends ProxyEntity<T2Proxy, T2>, T2,
        T3Proxy extends ProxyEntity<T3Proxy, T3>, T3> extends ClientEntityQueryable3Available<T1, T2, T3>, EntityQueryable3Available<T1Proxy, T1, T2Proxy, T2, T3Proxy, T3> {
    default <TRProxy extends ProxyEntity<TRProxy, TR>, TR extends ProxyEntityAvailable<TR, TRProxy>> EntityQueryable3<T1Proxy, T1, T2Proxy, T2, T3Proxy, T3> subQueryToGroupJoin(SQLFuncExpression3<T1Proxy, T2Proxy, T3Proxy, ManyPropColumn<TRProxy, TR>> manyPropColumnExpression) {
        return subQueryToGroupJoin(true, manyPropColumnExpression);
    }

    default <TRProxy extends ProxyEntity<TRProxy, TR>, TR extends ProxyEntityAvailable<TR, TRProxy>> EntityQueryable3<T1Proxy, T1, T2Proxy, T2, T3Proxy, T3> subQueryToGroupJoin(boolean condition, SQLFuncExpression3<T1Proxy, T2Proxy, T3Proxy, ManyPropColumn<TRProxy, TR>> manyPropColumnExpression) {
        if (condition) {

            ValueHolder<ManyPropColumn<TRProxy, TR>> valueHolder = new ValueHolder<>();
            get1Proxy().getEntitySQLContext()._include(() -> {
                ManyPropColumn<TRProxy, TR> value = manyPropColumnExpression.apply(get1Proxy(), get2Proxy(), get3Proxy());
                valueHolder.setValue(value);
            });
            TableAvailable table = valueHolder.getValue().getOriginalTable();
            String value = valueHolder.getValue().getValue();
            getClientQueryable3().subQueryToGroupJoin((mj1, mj2, MJ3) -> mj1.subQueryProperty(table, value));
        }
        return getQueryable3();
    }

}
