package org.easy.query.core.configuration.types;

import org.easy.query.core.interceptor.select.GlobalSelectInterceptorStrategy;
import org.easy.query.core.expression.lambda.Property;
import org.easy.query.core.expression.parser.abstraction.SqlPredicate;
import org.easy.query.core.query.SqlEntityQueryExpression;
import org.easy.query.core.util.EasyUtil;

/**
 * @FileName: EasyEntityQueryFilterConfiguration.java
 * @Description: 文件说明
 * @Date: 2023/3/7 22:29
 * @Created by xuejiaming
 */
public class EasyEntityQueryFilterConfiguration implements GlobalSelectInterceptorStrategy {

    @Override
    public String interceptorName() {
        return null;
    }

    @Override
    public boolean apply(Class<?> entityClass) {
        return GlobalSelectInterceptorStrategy.class.isAssignableFrom(entityClass);
    }

    @Override
    public void configure(Class<?> entityClass, SqlEntityQueryExpression sqlEntityQueryExpression, SqlPredicate<?> sqlPredicate) {

        Property tenantProperty = EasyUtil.getLambdaProperty(entityClass, "tenantId", String.class);

        sqlPredicate.eq(tenantProperty, "123");
    }
}