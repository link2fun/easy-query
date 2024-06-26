package com.easy.query.clickhouse.config;

import com.easy.query.clickhouse.expression.ClickHouseExpressionFactory;
import com.easy.query.clickhouse.func.ClickHouseFuncImpl;
import com.easy.query.core.bootstrapper.DatabaseConfiguration;
import com.easy.query.core.configuration.dialect.Dialect;
import com.easy.query.core.expression.sql.expression.factory.ExpressionFactory;
import com.easy.query.core.func.SQLFunc;
import com.easy.query.core.inject.ServiceCollection;

/**
 * create time 2023/5/10 13:40
 * 文件说明
 *
 * @author xuejiaming
 */
public class ClickHouseDatabaseConfiguration implements DatabaseConfiguration {
    @Override
    public void configure(ServiceCollection services) {
        services.addService(Dialect.class, ClickHouseDialect.class);
        services.addService(ExpressionFactory.class, ClickHouseExpressionFactory.class);
        services.addService(SQLFunc.class, ClickHouseFuncImpl.class);
    }
}
