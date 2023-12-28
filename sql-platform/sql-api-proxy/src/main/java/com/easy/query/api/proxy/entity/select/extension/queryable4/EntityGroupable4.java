package com.easy.query.api.proxy.entity.select.extension.queryable4;

import com.easy.query.api.proxy.entity.select.EntityQueryable4;
import com.easy.query.core.common.tuple.MergeTuple4;
import com.easy.query.core.expression.lambda.SQLFuncExpression1;
import com.easy.query.core.expression.lambda.SQLFuncExpression4;
import com.easy.query.core.proxy.ProxyEntity;
import com.easy.query.core.proxy.SQLGroupByExpression;

/**
 * create time 2023/8/16 08:49
 * 文件说明
 *
 * @author xuejiaming
 */
public interface EntityGroupable4<T1Proxy extends ProxyEntity<T1Proxy, T1>, T1,
        T2Proxy extends ProxyEntity<T2Proxy, T2>, T2,
        T3Proxy extends ProxyEntity<T3Proxy, T3>, T3,
        T4Proxy extends ProxyEntity<T4Proxy, T4>, T4> extends ClientEntityQueryable4Available<T1, T2, T3, T4>, EntityQueryable4Available<T1Proxy, T1, T2Proxy, T2, T3Proxy, T3, T4Proxy, T4> {


    default EntityQueryable4<T1Proxy, T1, T2Proxy, T2, T3Proxy, T3, T4Proxy, T4> groupByFlat(SQLFuncExpression4<T1Proxy, T2Proxy, T3Proxy, T4Proxy,SQLGroupByExpression> selectExpression) {
        return groupByFlat(true, selectExpression);
    }

    default EntityQueryable4<T1Proxy, T1, T2Proxy, T2, T3Proxy, T3, T4Proxy, T4> groupByFlat(boolean condition, SQLFuncExpression4<T1Proxy, T2Proxy, T3Proxy, T4Proxy,SQLGroupByExpression> selectExpression) {
        if (condition) {
            getClientQueryable4().groupBy((t, t1, t2,t3) -> {
                SQLGroupByExpression sqlGroupByExpression = selectExpression.apply(get1Proxy(), get2Proxy(), get3Proxy(), get4Proxy());
                sqlGroupByExpression.accept(t.getGroupSelector());
            });
        }
        return getQueryable4();
    }

    default EntityQueryable4<T1Proxy, T1, T2Proxy, T2, T3Proxy, T3, T4Proxy, T4> groupByFlatMerge(SQLFuncExpression1<MergeTuple4<T1Proxy, T2Proxy, T3Proxy,T4Proxy>,SQLGroupByExpression> selectExpression) {
        return groupByFlatMerge(true, selectExpression);
    }

    default EntityQueryable4<T1Proxy, T1, T2Proxy, T2, T3Proxy, T3, T4Proxy, T4> groupByFlatMerge(boolean condition, SQLFuncExpression1<MergeTuple4<T1Proxy, T2Proxy, T3Proxy,T4Proxy>,SQLGroupByExpression> selectExpression) {
        return groupByFlat(condition, (t, t1, t2, t3) -> {
            return selectExpression.apply(new MergeTuple4<>(t, t1, t2,t3));
        });
    }
}
