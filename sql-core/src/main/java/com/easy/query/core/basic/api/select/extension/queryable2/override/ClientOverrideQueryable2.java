package com.easy.query.core.basic.api.select.extension.queryable2.override;

import com.easy.query.core.api.client.EasyQueryClient;
import com.easy.query.core.basic.api.select.ClientQueryable;
import com.easy.query.core.basic.api.select.ClientQueryable2;
import com.easy.query.core.enums.sharding.ConnectionModeEnum;
import com.easy.query.core.expression.builder.core.ValueFilter;
import com.easy.query.core.expression.lambda.SQLExpression1;
import com.easy.query.core.expression.lambda.SQLFuncExpression1;
import com.easy.query.core.expression.parser.core.base.ColumnGroupSelector;
import com.easy.query.core.expression.parser.core.base.ColumnOrderSelector;
import com.easy.query.core.expression.parser.core.base.NavigateInclude;
import com.easy.query.core.expression.parser.core.base.WhereAggregatePredicate;
import com.easy.query.core.expression.parser.core.base.WherePredicate;
import com.easy.query.core.expression.sql.builder.internal.ContextConfigurer;

import java.util.Collection;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * create time 2023/8/16 08:12
 * 文件说明
 *
 * @author xuejiaming
 */
public interface ClientOverrideQueryable2<T1, T2> extends ClientQueryable<T1> {

    ClientQueryable<T1> getClientQueryable();

    @Override
    ClientQueryable2<T1, T2> cloneQueryable();


    @Override
    default ClientQueryable2<T1, T2> whereById(Object id) {
        return whereById(true, id);
    }

    @Override
    ClientQueryable2<T1, T2> whereById(boolean condition, Object id);

    @Override
    default <TProperty> ClientQueryable2<T1, T2> whereByIds(Collection<TProperty> ids) {
        return whereByIds(true, ids);
    }

    @Override
    <TProperty> ClientQueryable2<T1, T2> whereByIds(boolean condition, Collection<TProperty> ids);

    /**
     * 仅支持主表的动态对象查询
     *
     * @param object 对象查询的对象
     * @return
     */
    @Override
    default ClientQueryable2<T1, T2> whereObject(Object object) {
        return whereObject(true, object);
    }

    /**
     * 仅支持主表的动态对象查询
     *
     * @param condition 是否使用对象查询
     * @param object    对象查询的对象
     * @return
     */
    @Override
    ClientQueryable2<T1, T2> whereObject(boolean condition, Object object);


    @Override
    default ClientQueryable2<T1, T2> where(SQLExpression1<WherePredicate<T1>> whereExpression) {
        return where(true, whereExpression);
    }

    @Override
    ClientQueryable2<T1, T2> where(boolean condition, SQLExpression1<WherePredicate<T1>> whereExpression);

    @Override
    default ClientQueryable2<T1, T2> groupBy(SQLExpression1<ColumnGroupSelector<T1>> selectExpression) {
        return groupBy(true, selectExpression);
    }

    @Override
    ClientQueryable2<T1, T2> groupBy(boolean condition, SQLExpression1<ColumnGroupSelector<T1>> selectExpression);

    @Override
    default ClientQueryable2<T1, T2> having(SQLExpression1<WhereAggregatePredicate<T1>> predicateExpression) {
        return having(true, predicateExpression);
    }

    @Override
    ClientQueryable2<T1, T2> having(boolean condition, SQLExpression1<WhereAggregatePredicate<T1>> predicateExpression);

    @Override
    default ClientQueryable2<T1, T2> orderByAsc(SQLExpression1<ColumnOrderSelector<T1>> selectExpression) {
        return orderByAsc(true, selectExpression);
    }

    @Override
    ClientQueryable2<T1, T2> orderByAsc(boolean condition, SQLExpression1<ColumnOrderSelector<T1>> selectExpression);

    @Override
    default ClientQueryable2<T1, T2> orderByDesc(SQLExpression1<ColumnOrderSelector<T1>> selectExpression) {
        return orderByDesc(true, selectExpression);
    }

    @Override
    ClientQueryable2<T1, T2> orderByDesc(boolean condition, SQLExpression1<ColumnOrderSelector<T1>> selectExpression);

    @Override
    default <TREntity> ClientQueryable2<T1, T2> include(SQLFuncExpression1<NavigateInclude<T1>, ClientQueryable<TREntity>> navigateIncludeSQLExpression) {
        return include(true, navigateIncludeSQLExpression);
    }

    @Override
    <TREntity> ClientQueryable2<T1, T2> include(boolean condition, SQLFuncExpression1<NavigateInclude<T1>, ClientQueryable<TREntity>> navigateIncludeSQLExpression);

    @Override
    default ClientQueryable2<T1, T2> limit(long rows) {
        return limit(true, rows);
    }

    @Override
    default ClientQueryable2<T1, T2> limit(boolean condition, long rows) {
        return limit(condition, 0, rows);
    }

    @Override
    default ClientQueryable2<T1, T2> limit(long offset, long rows) {
        return limit(true, offset, rows);
    }

    @Override
    ClientQueryable2<T1, T2> limit(boolean condition, long offset, long rows);

    default ClientQueryable2<T1, T2> distinct() {
        return distinct(true);
    }

    @Override
    ClientQueryable2<T1, T2> distinct(boolean condition);

    @Override
    ClientQueryable2<T1, T2> disableLogicDelete();

    @Override
    ClientQueryable2<T1, T2> enableLogicDelete();

    @Override
    ClientQueryable2<T1, T2> useLogicDelete(boolean enable);

    @Override
    ClientQueryable2<T1, T2> noInterceptor();

    @Override
    ClientQueryable2<T1, T2> useInterceptor(String name);

    @Override
    ClientQueryable2<T1, T2> noInterceptor(String name);

    @Override
    ClientQueryable2<T1, T2> useInterceptor();

    /**
     * 自动将查询结果集合全部添加到当前上下文追踪中,如果当前查询结果十分庞大,并且更新数据只有个别条数,建议不要使用
     * 追踪查询，可以通过开启追踪后使用普通的查询，然后添加到当前的追踪上下文中{@link EasyQueryClient#addTracking(Object)},开始先数据追踪的差异更新
     *
     * @return
     */
    @Override
    ClientQueryable2<T1, T2> asTracking();

    @Override
    ClientQueryable2<T1, T2> asNoTracking();

    @Override
    ClientQueryable2<T1, T2> queryLargeColumn(boolean queryLarge);

    @Override
    ClientQueryable2<T1, T2> useShardingConfigure(int maxShardingQueryLimit, ConnectionModeEnum connectionMode);

    @Override
    ClientQueryable2<T1, T2> useMaxShardingQueryLimit(int maxShardingQueryLimit);

    @Override
    ClientQueryable2<T1, T2> useConnectionMode(ConnectionModeEnum connectionMode);

    /**
     * 将当前表达式最近的一张表的表名修改成 {@param tableName}
     * 如果当前最近的表是正常的数据库表名,那么直接将表名改写
     * 如果当前最近的表是匿名表比如嵌套queryable的表那么将alias改成对应的表名
     *
     * @param tableName
     * @return
     */
    @Override
    default ClientQueryable2<T1, T2> asTable(String tableName) {
        return asTable(old -> tableName);
    }

    /**
     * 将当前表达式最近的一张表的表名修改成 {@param tableNameAs}返回的表名
     * 如果当前最近的表是正常的数据库表名,那么直接将表名改写
     * 如果当前最近的表是匿名表比如嵌套queryable的表那么将alias改成对应的表名
     *
     * @param tableNameAs
     * @return
     */
    @Override
    ClientQueryable2<T1, T2> asTable(Function<String, String> tableNameAs);


    @Override
    default ClientQueryable2<T1, T2> asSchema(String schema) {
        return asSchema(old -> schema);
    }

    @Override
    ClientQueryable2<T1, T2> asSchema(Function<String, String> schemaAs);

    @Override
    ClientQueryable2<T1, T2> asAlias(String alias);

    /**
     * @param linkAs 别名 FROM | LEFT JOIN | RIGHT JOIN
     * @return
     */
    @Override
    default ClientQueryable2<T1, T2> asTableLink(String linkAs) {
        return asTableLink(o -> linkAs);
    }

    @Override
    ClientQueryable2<T1, T2> asTableLink(Function<String, String> linkAs);
    @Override
    ClientQueryable2<T1, T2> asTableSegment(BiFunction<String, String, String> segmentAs);


    @Override
    ClientQueryable2<T1, T2> filterConfigure(ValueFilter valueFilter);

    @Override
    ClientQueryable2<T1, T2> tableLogicDelete(Supplier<Boolean> tableLogicDel);
    @Override
    ClientQueryable2<T1, T2> configure(SQLExpression1<ContextConfigurer> configurer);
}
