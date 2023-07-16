package com.easy.query.api.proxy.insert;

import com.easy.query.api.proxy.sql.ProxyUpdateSetSelector;
import com.easy.query.api.proxy.sql.impl.ProxyUpdateSetSelectorImpl;
import com.easy.query.core.basic.api.insert.ClientInsertable;
import com.easy.query.core.basic.jdbc.parameter.ToSQLContext;
import com.easy.query.core.enums.SQLExecuteStrategyEnum;
import com.easy.query.core.expression.lambda.SQLExpression1;
import com.easy.query.core.proxy.SQLColumn;

import java.util.Collection;
import java.util.function.Function;

/**
 * create time 2023/6/2 16:15
 * 文件说明
 *
 * @author xuejiaming
 */
public abstract class AbstractProxyEntityInsertable<T> implements ProxyEntityInsertable<T> {
    private final ClientInsertable<T> clientInsertable;

    public AbstractProxyEntityInsertable(ClientInsertable<T> clientInsertable) {

        this.clientInsertable = clientInsertable;
    }

    @Override
    public ProxyEntityInsertable<T> insert(T entity) {
        clientInsertable.insert(entity);
        return this;
    }

    @Override
    public ProxyEntityInsertable<T> insert(Collection<T> entities) {
        clientInsertable.insert(entities);
        return this;
    }

    @Override
    public long executeRows(boolean fillAutoIncrement) {
        return clientInsertable.executeRows(fillAutoIncrement);
    }

    @Override
    public ProxyEntityInsertable<T> noInterceptor() {
        clientInsertable.noInterceptor();
        return this;
    }

    @Override
    public ProxyEntityInsertable<T> useInterceptor(String name) {
        clientInsertable.useInterceptor(name);
        return this;
    }

    @Override
    public ProxyEntityInsertable<T> noInterceptor(String name) {
        clientInsertable.noInterceptor(name);
        return this;
    }

    @Override
    public ProxyEntityInsertable<T> useInterceptor() {
        clientInsertable.useInterceptor();
        return this;
    }

    @Override
    public ProxyEntityInsertable<T> setSQLStrategy(boolean condition, SQLExecuteStrategyEnum sqlStrategy) {
        clientInsertable.setSQLStrategy(condition, sqlStrategy);
        return this;
    }

    @Override
    public ProxyEntityInsertable<T> asTable(Function<String, String> tableNameAs) {
        clientInsertable.asTable(tableNameAs);
        return this;
    }

    @Override
    public ProxyEntityInsertable<T> asSchema(Function<String, String> schemaAs) {
        clientInsertable.asSchema(schemaAs);
        return this;
    }

    @Override
    public ProxyEntityInsertable<T> asAlias(String alias) {
        clientInsertable.asAlias(alias);
        return this;
    }

    @Override
    public ProxyEntityInsertable<T> onDuplicateKeyIgnore() {
        clientInsertable.onDuplicateKeyIgnore();
        return this;
    }
    @Override
    public ProxyEntityInsertable<T> onDuplicateKeyUpdate() {
        clientInsertable.onDuplicateKeyUpdate();
        return this;
    }

    @Override
    public String toSQL(Object entity) {
        return clientInsertable.toSQL(entity);
    }

    @Override
    public String toSQL(Object entity, ToSQLContext toSQLContext) {
        return clientInsertable.toSQL(entity,toSQLContext);
    }

    @Override
    public ProxyEntityInsertable<T> onConflictDoUpdate() {
        clientInsertable.onConflictDoUpdate();
        return this;
    }

    @Override
    public ProxyEntityInsertable<T> onConflictDoUpdate(SQLColumn<?> constraintProperty) {
        clientInsertable.onConflictDoUpdate(constraintProperty.value());
        return this;
    }

    @Override
    public ProxyEntityInsertable<T> onConflictDoUpdate(SQLColumn<?> constraintProperty, SQLExpression1<ProxyUpdateSetSelector> setColumnSelector) {
        clientInsertable.onConflictDoUpdate(constraintProperty.value(),setSelector->{
            setColumnSelector.apply(new ProxyUpdateSetSelectorImpl(setSelector.getUpdateSetSelector()));
        });
        return this;
    }

    @Override
    public ProxyEntityInsertable<T> onConflictDoUpdate(SQLExpression1<ProxyUpdateSetSelector> setColumnSelector) {
        clientInsertable.onConflictDoUpdate(setSelector->{
            setColumnSelector.apply(new ProxyUpdateSetSelectorImpl(setSelector.getUpdateSetSelector()));
        });
        return this;
    }

    @Override
    public ProxyEntityInsertable<T> onDuplicateKeyUpdate(SQLExpression1<ProxyUpdateSetSelector> setColumnSelector) {
        clientInsertable.onDuplicateKeyUpdate(setSelector->{
            setColumnSelector.apply(new ProxyUpdateSetSelectorImpl(setSelector.getUpdateSetSelector()));
        });
        return this;
    }

    @Override
    public ProxyEntityInsertable<T> batch(boolean use) {
        clientInsertable.batch(use);
        return this;
    }
}