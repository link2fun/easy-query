package com.easy.query.api.proxy.sql;

import com.easy.query.api.proxy.sql.core.SQLProxyNative;
import com.easy.query.api.proxy.sql.core.filter.ProxyAssertPredicate;
import com.easy.query.api.proxy.sql.core.filter.ProxyLikePredicate;
import com.easy.query.api.proxy.sql.core.filter.ProxyRangePredicate;
import com.easy.query.api.proxy.sql.core.filter.ProxySelfPredicate;
import com.easy.query.api.proxy.sql.core.filter.ProxySubQueryPredicate;
import com.easy.query.api.proxy.sql.core.filter.ProxyValuePredicate;
import com.easy.query.api.proxy.sql.core.filter.ProxyValuesPredicate;
import com.easy.query.api.proxy.sql.impl.ProxyFilterImpl;
import com.easy.query.core.enums.SQLPredicateCompare;
import com.easy.query.core.expression.builder.Filter;
import com.easy.query.core.expression.lambda.SQLExpression1;
import com.easy.query.core.proxy.ProxyEntity;

/**
 * @author xuejiaming
 * @FileName: WherePredicate.java
 * @Description: 文件说明
 * @Date: 2023/2/5 09:09
 */
public interface ProxyFilter extends SQLProxyNative<ProxyFilter>
        , ProxyAssertPredicate<ProxyFilter>
        , ProxyRangePredicate<ProxyFilter>
        , ProxySelfPredicate<ProxyFilter>
        , ProxySubQueryPredicate<ProxyFilter>
        , ProxyValuePredicate<ProxyFilter>
        , ProxyValuesPredicate<ProxyFilter>
        , ProxyLikePredicate<ProxyFilter> {
    Filter getFilter();



    default <TProxy extends ProxyEntity<TProxy, T>, T, TProperty> ProxyFilter columnFunc(ProxyColumnPropertyFunction columnPropertyFunction, SQLPredicateCompare sqlPredicateCompare, TProperty val) {
        return columnFunc(true, columnPropertyFunction, sqlPredicateCompare, val);
    }

    default <TProxy extends ProxyEntity<TProxy, T>, T, TProperty> ProxyFilter columnFunc(boolean condition, ProxyColumnPropertyFunction columnPropertyFunction, SQLPredicateCompare sqlPredicateCompare, TProperty val) {
        if (condition) {
            getFilter().columnFunc(columnPropertyFunction.getColumn().getTable(), columnPropertyFunction.getColumnPropertyFunction(), sqlPredicateCompare, val);
        }
        return this;
    }


    default ProxyFilter and() {
        return and(true);
    }

    default ProxyFilter and(boolean condition) {
        if (condition) {
            getFilter().and();
        }
        return this;
    }

    default ProxyFilter and(SQLExpression1<ProxyFilter> proxyFilterExpression) {
        return and(true, proxyFilterExpression);
    }

    default ProxyFilter and(boolean condition, SQLExpression1<ProxyFilter> proxyFilterExpression) {
        if (condition) {
            getFilter().and(filter -> {
                proxyFilterExpression.apply(new ProxyFilterImpl(filter));
            });
        }
        return this;
    }

    default ProxyFilter or() {
        return or(true);
    }

    default ProxyFilter or(boolean condition) {
        if (condition) {
            getFilter().or();
        }
        return this;
    }

    default ProxyFilter or(SQLExpression1<ProxyFilter> sqlWherePredicateSQLExpression) {
        return or(true, sqlWherePredicateSQLExpression);
    }

    default ProxyFilter or(boolean condition, SQLExpression1<ProxyFilter> proxyFilterExpression) {
        if (condition) {
            getFilter().or(filter -> {
                proxyFilterExpression.apply(new ProxyFilterImpl(filter));
            });
        }
        return this;
    }
}
