package com.easy.query.api.proxy.entity.select.extension.queryable9;

import com.easy.query.api.proxy.entity.select.EntityQueryable;
import com.easy.query.api.proxy.entity.select.EntityQueryable9;
import com.easy.query.api.proxy.entity.select.impl.EasyEntityQueryable;
import com.easy.query.core.basic.api.select.impl.EasyClientQueryable;
import com.easy.query.core.common.tuple.MergeTuple9;
import com.easy.query.core.expression.lambda.SQLFuncExpression1;
import com.easy.query.core.expression.lambda.SQLFuncExpression9;
import com.easy.query.core.proxy.ProxyEntity;
import com.easy.query.core.proxy.SQLGroupByExpression;

/**
 * create time 2023/8/16 08:49
 * 文件说明
 *
 * @author xuejiaming
 */
public interface EntityGroupable9<T1Proxy extends ProxyEntity<T1Proxy, T1>, T1,
        T2Proxy extends ProxyEntity<T2Proxy, T2>, T2,
        T3Proxy extends ProxyEntity<T3Proxy, T3>, T3,
        T4Proxy extends ProxyEntity<T4Proxy, T4>, T4,
        T5Proxy extends ProxyEntity<T5Proxy, T5>, T5,
        T6Proxy extends ProxyEntity<T6Proxy, T6>, T6,
        T7Proxy extends ProxyEntity<T7Proxy, T7>, T7,
        T8Proxy extends ProxyEntity<T8Proxy, T8>, T8,
        T9Proxy extends ProxyEntity<T9Proxy, T9>, T9> extends ClientEntityQueryable9Available<T1, T2, T3, T4, T5, T6, T7, T8, T9>, EntityQueryable9Available<T1Proxy, T1, T2Proxy, T2, T3Proxy, T3, T4Proxy, T4, T5Proxy, T5, T6Proxy, T6, T7Proxy, T7, T8Proxy, T8, T9Proxy, T9> {


    default EntityQueryable9<T1Proxy, T1, T2Proxy, T2, T3Proxy, T3, T4Proxy, T4, T5Proxy, T5, T6Proxy, T6, T7Proxy, T7, T8Proxy, T8, T9Proxy, T9> groupByExpression(SQLFuncExpression9<T1Proxy, T2Proxy, T3Proxy, T4Proxy, T5Proxy, T6Proxy, T7Proxy, T8Proxy, T9Proxy, SQLGroupByExpression> selectExpression) {
        return groupByExpression(true, selectExpression);
    }

    default EntityQueryable9<T1Proxy, T1, T2Proxy, T2, T3Proxy, T3, T4Proxy, T4, T5Proxy, T5, T6Proxy, T6, T7Proxy, T7, T8Proxy, T8, T9Proxy, T9> groupByExpression(boolean condition, SQLFuncExpression9<T1Proxy, T2Proxy, T3Proxy, T4Proxy, T5Proxy, T6Proxy, T7Proxy, T8Proxy, T9Proxy, SQLGroupByExpression> selectExpression) {
        if (condition) {
            getClientQueryable9().groupBy((t, t1, t2, t3, t4, t5, t6, t7, t8) -> {
                SQLGroupByExpression sqlGroupByExpression = selectExpression.apply(get1Proxy(), get2Proxy(), get3Proxy(), get4Proxy(), get5Proxy(), get6Proxy(), get7Proxy(), get8Proxy(), get9Proxy());
                sqlGroupByExpression.accept(t.getGroupSelector());
            });
        }
        return getQueryable9();
    }

    default EntityQueryable9<T1Proxy, T1, T2Proxy, T2, T3Proxy, T3, T4Proxy, T4, T5Proxy, T5, T6Proxy, T6, T7Proxy, T7, T8Proxy, T8, T9Proxy, T9> groupByExpressionMerge(SQLFuncExpression1<MergeTuple9<T1Proxy, T2Proxy, T3Proxy, T4Proxy, T5Proxy, T6Proxy, T7Proxy, T8Proxy, T9Proxy>,SQLGroupByExpression> selectExpression) {
        return groupByExpressionMerge(true, selectExpression);
    }

    default EntityQueryable9<T1Proxy, T1, T2Proxy, T2, T3Proxy, T3, T4Proxy, T4, T5Proxy, T5, T6Proxy, T6, T7Proxy, T7, T8Proxy, T8, T9Proxy, T9> groupByExpressionMerge(boolean condition, SQLFuncExpression1<MergeTuple9<T1Proxy, T2Proxy, T3Proxy, T4Proxy, T5Proxy, T6Proxy, T7Proxy, T8Proxy, T9Proxy>,SQLGroupByExpression> selectExpression) {
        return groupByExpression(condition, (t, t1, t2, t3, t4, t5, t6, t7, t8) -> {
            return selectExpression.apply(new MergeTuple9<>(t, t1, t2, t3, t4, t5, t6, t7, t8));
        });
    }
    default <TRProxy extends ProxyEntity<TRProxy, TR> & SQLGroupByExpression, TR>
    EntityQueryable<TRProxy, TR> groupBy(
            SQLFuncExpression9<T1Proxy, T2Proxy, T3Proxy, T4Proxy, T5Proxy, T6Proxy, T7Proxy, T8Proxy, T9Proxy,
                    SQLFuncExpression1<MergeTuple9<T1Proxy, T2Proxy, T3Proxy, T4Proxy, T5Proxy, T6Proxy, T7Proxy, T8Proxy, T9Proxy>, TRProxy>> selectExpression) {

        SQLFuncExpression1<MergeTuple9<T1Proxy, T2Proxy, T3Proxy, T4Proxy, T5Proxy, T6Proxy, T7Proxy, T8Proxy, T9Proxy>, TRProxy> keysExpression =
                selectExpression.apply(get1Proxy(), get2Proxy(), get3Proxy(), get4Proxy(), get5Proxy(), get6Proxy(), get7Proxy(), get8Proxy(), get9Proxy());
        TRProxy grouping1Proxy = keysExpression.apply(new MergeTuple9<>(get1Proxy(), get2Proxy(), get3Proxy(), get4Proxy(), get5Proxy(), get6Proxy(), get7Proxy(), get8Proxy(), get9Proxy()));

        getClientQueryable9().groupBy((selector1, selector2, selector3, selector4, selector5, selector6, selector7, selector8, selector9) -> {
            grouping1Proxy.accept(selector1.getGroupSelector());
        });

        TRProxy groupProxy = grouping1Proxy.create(null, get1Proxy().getEntitySQLContext());
        EasyClientQueryable<TR> groupQueryable = new EasyClientQueryable<>(grouping1Proxy.getEntityClass(), getClientQueryable9().getSQLEntityExpressionBuilder());
        return new EasyEntityQueryable<>(groupProxy, groupQueryable);
    }

    default <TRProxy extends ProxyEntity<TRProxy, TR> & SQLGroupByExpression, TR>
    EntityQueryable<TRProxy, TR> groupByMerge(
            SQLFuncExpression1<MergeTuple9<T1Proxy, T2Proxy, T3Proxy, T4Proxy, T5Proxy, T6Proxy, T7Proxy, T8Proxy, T9Proxy>,
                    SQLFuncExpression1<MergeTuple9<T1Proxy, T2Proxy, T3Proxy, T4Proxy, T5Proxy, T6Proxy, T7Proxy, T8Proxy, T9Proxy>, TRProxy>> selectExpression) {

        return groupBy((t1, t2, t3, t4, t5, t6, t7, t8, t9) -> selectExpression.apply(new MergeTuple9<>(t1, t2, t3, t4, t5, t6, t7, t8, t9)));
    }
}
