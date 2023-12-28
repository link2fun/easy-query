package com.easy.query.api.proxy.entity.select.extension.queryable5;

import com.easy.query.api.proxy.entity.select.EntityQueryable5;
import com.easy.query.core.common.tuple.MergeTuple5;
import com.easy.query.core.expression.lambda.SQLFuncExpression1;
import com.easy.query.core.expression.lambda.SQLFuncExpression5;
import com.easy.query.core.proxy.ProxyEntity;
import com.easy.query.core.proxy.SQLGroupByExpression;

/**
 * create time 2023/8/16 08:49
 * 文件说明
 *
 * @author xuejiaming
 */
public interface EntityGroupable5<T1Proxy extends ProxyEntity<T1Proxy, T1>, T1,
        T2Proxy extends ProxyEntity<T2Proxy, T2>, T2,
        T3Proxy extends ProxyEntity<T3Proxy, T3>, T3,
        T4Proxy extends ProxyEntity<T4Proxy, T4>, T4,
        T5Proxy extends ProxyEntity<T5Proxy, T5>, T5> extends ClientEntityQueryable5Available<T1, T2, T3, T4, T5>, EntityQueryable5Available<T1Proxy, T1, T2Proxy, T2, T3Proxy, T3, T4Proxy, T4, T5Proxy, T5> {


    default EntityQueryable5<T1Proxy, T1, T2Proxy, T2, T3Proxy, T3, T4Proxy, T4, T5Proxy, T5> groupByFlat(SQLFuncExpression5<T1Proxy, T2Proxy, T3Proxy, T4Proxy, T5Proxy, SQLGroupByExpression> selectExpression) {
        return groupByFlat(true, selectExpression);
    }

    default EntityQueryable5<T1Proxy, T1, T2Proxy, T2, T3Proxy, T3, T4Proxy, T4, T5Proxy, T5> groupByFlat(boolean condition, SQLFuncExpression5<T1Proxy, T2Proxy, T3Proxy, T4Proxy, T5Proxy, SQLGroupByExpression> selectExpression) {
        if (condition) {
            getClientQueryable5().groupBy((t, t1, t2, t3, t4) -> {
                SQLGroupByExpression sqlGroupByExpression = selectExpression.apply(get1Proxy(), get2Proxy(), get3Proxy(), get4Proxy(), get5Proxy());
                sqlGroupByExpression.accept(t.getGroupSelector());
            });
        }
        return getQueryable5();
    }

    default EntityQueryable5<T1Proxy, T1, T2Proxy, T2, T3Proxy, T3, T4Proxy, T4, T5Proxy, T5> groupByFlatMerge(SQLFuncExpression1<MergeTuple5<T1Proxy, T2Proxy, T3Proxy, T4Proxy, T5Proxy>,SQLGroupByExpression> selectExpression) {
        return groupByFlatMerge(true, selectExpression);
    }

    default EntityQueryable5<T1Proxy, T1, T2Proxy, T2, T3Proxy, T3, T4Proxy, T4, T5Proxy, T5> groupByFlatMerge(boolean condition, SQLFuncExpression1<MergeTuple5<T1Proxy, T2Proxy, T3Proxy, T4Proxy, T5Proxy>,SQLGroupByExpression> selectExpression) {
        return groupByFlat(condition, (t, t1, t2, t3, t4) -> {
            return selectExpression.apply(new MergeTuple5<>(t, t1, t2, t3, t4));
        });
    }
}
