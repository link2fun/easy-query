package com.easy.query.core.basic.api.update.abstraction;

import com.easy.query.core.basic.api.internal.AbstractSqlExecuteRows;
import com.easy.query.core.basic.api.update.EntityUpdatable;
import com.easy.query.core.basic.api.internal.SqlEntityNode;
import com.easy.query.core.basic.jdbc.parameter.DefaultSqlParameterCollector;
import com.easy.query.core.basic.jdbc.parameter.SQLParameter;
import com.easy.query.core.basic.jdbc.parameter.SqlParameterCollector;
import com.easy.query.core.basic.plugin.interceptor.EasyInterceptorEntry;
import com.easy.query.core.configuration.EasyQueryConfiguration;
import com.easy.query.core.enums.MultiTableTypeEnum;
import com.easy.query.core.enums.SqlExecuteStrategyEnum;
import com.easy.query.core.exception.EasyQueryException;
import com.easy.query.core.expression.lambda.SqlExpression;
import com.easy.query.core.expression.parser.abstraction.SqlColumnSelector;
import com.easy.query.core.expression.parser.impl.DefaultSqlColumnSetSelector;
import com.easy.query.core.basic.plugin.interceptor.EasyEntityInterceptor;
import com.easy.query.core.expression.sql.builder.impl.TableExpressionBuilder;
import com.easy.query.core.expression.sql.builder.EntityTableExpressionBuilder;
import com.easy.query.core.expression.sql.builder.EntityUpdateExpressionBuilder;
import com.easy.query.core.basic.plugin.track.TrackManager;
import com.easy.query.core.basic.jdbc.executor.EasyOldExecutor;
import com.easy.query.core.basic.jdbc.executor.ExecutorContext;
import com.easy.query.core.metadata.EntityMetadata;
import com.easy.query.core.util.ArrayUtil;
import com.easy.query.core.util.SqlExpressionUtil;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author xuejiaming
 * @FileName: AbstractUpdate.java
 * @Description: 文件说明
 * @Date: 2023/2/24 22:06
 */
public abstract class AbstractEntityUpdatable<T> extends AbstractSqlExecuteRows<EntityUpdatable<T>> implements EntityUpdatable<T> {
    protected final List<T> entities = new ArrayList<>();
    protected final EntityTableExpressionBuilder table;
    protected final EntityMetadata entityMetadata;
    protected final EntityUpdateExpressionBuilder entityUpdateExpression;

    public AbstractEntityUpdatable(Collection<T> entities, EntityUpdateExpressionBuilder entityUpdateExpression) {
        super(entityUpdateExpression);
        if (entities == null || entities.isEmpty()) {
            throw new EasyQueryException("不支持空对象的update");
        }
        this.entities.addAll(entities);

        Class<?> clazz = entities.iterator().next().getClass();
        this.entityUpdateExpression = entityUpdateExpression;

        entityMetadata = this.entityUpdateExpression.getRuntimeContext().getEntityMetadataManager().getEntityMetadata(clazz);
        entityMetadata.checkTable();
        table = new TableExpressionBuilder(entityMetadata, 0, null, MultiTableTypeEnum.FROM);
        this.entityUpdateExpression.addSqlEntityTableExpression(table);
    }

    /**
     * 不分组就生成一条sql
     *
     * @return
     */
    private boolean useUpdateSqlGroup() {
        SqlExecuteStrategyEnum updateStrategy = SqlExpressionUtil.getExecuteStrategy(entityUpdateExpression.getExpressionContext());
        if (!SqlExecuteStrategyEnum.ALL_COLUMNS.equals(updateStrategy)) {
            return true;
        }
        TrackManager trackManager = entityUpdateExpression.getRuntimeContext().getTrackManager();
        return trackManager.currentThreadTracking();
    }

    private List<SqlEntityNode> createUpdateEntityNode() {
        if (useUpdateSqlGroup()) {
            Map<String, SqlEntityNode> updateEntityNodeMap = new LinkedHashMap<>();
            for (T entity : entities) {
                SqlParameterCollector sqlParameterCollector = DefaultSqlParameterCollector.defaultCollector();
                String updateSql = toSqlWithParam(entity,sqlParameterCollector);
                //如果当前对象没有需要更新的列直接忽略
                if (updateSql == null) {
                    continue;
                }
                List<SQLParameter> parameters = new ArrayList<>(sqlParameterCollector.getParameters());
                SqlEntityNode updateEntityNode = updateEntityNodeMap.computeIfAbsent(updateSql, k -> new SqlEntityNode(updateSql, parameters));
                updateEntityNode.getEntities().add(entity);
            }
            return new ArrayList<>(updateEntityNodeMap.values());
        } else {
            SqlParameterCollector sqlParameterCollector = DefaultSqlParameterCollector.defaultCollector();
            String updateSql = toSqlWithParam(null,sqlParameterCollector);
            SqlEntityNode updateEntityNode = new SqlEntityNode(updateSql, new ArrayList<>(sqlParameterCollector.getParameters()), entities.size());
            updateEntityNode.getEntities().addAll(entities);
            return Collections.singletonList(updateEntityNode);
        }
    }

    @Override
    public long executeRows() {
        if (!entities.isEmpty()) {
            updateBefore();
            List<SqlEntityNode> updateEntityNodes = createUpdateEntityNode();
            EasyOldExecutor easyExecutor = entityUpdateExpression.getRuntimeContext().getEasyExecutor();
            int i = 0;
            for (SqlEntityNode updateEntityNode : updateEntityNodes) {

                i += easyExecutor.executeRows(ExecutorContext.create(entityUpdateExpression.getRuntimeContext(),true), updateEntityNode.getSql(), updateEntityNode.getEntities(), updateEntityNode.getSqlParameters());

            }
            return i;
        }
        return 0;
    }

    protected void updateBefore() {
        List<EasyInterceptorEntry> updateInterceptors = entityMetadata.getEntityInterceptors();
        if (ArrayUtil.isNotEmpty(updateInterceptors)) {
            EasyQueryConfiguration easyQueryConfiguration = entityUpdateExpression.getRuntimeContext().getEasyQueryConfiguration();
            List<EasyEntityInterceptor> entityInterceptors = entityUpdateExpression.getExpressionContext().getInterceptorFilter(updateInterceptors)
                    .map(interceptor -> (EasyEntityInterceptor) easyQueryConfiguration.getEasyInterceptor(interceptor.getName())).collect(Collectors.toList());
            if (ArrayUtil.isNotEmpty(entityInterceptors)) {
                Class<?> entityClass = entityMetadata.getEntityClass();
                for (T entity : entities) {
                    for (EasyEntityInterceptor entityInterceptor : entityInterceptors) {
                        entityInterceptor.configureUpdate(entityClass, entityUpdateExpression, entity);
                    }
                }
            }
        }
    }

    @Override
    public EntityUpdatable<T> setColumns(boolean condition, SqlExpression<SqlColumnSelector<T>>
            columnSelectorExpression) {
        if (condition) {
            DefaultSqlColumnSetSelector<T> columnSelector = new DefaultSqlColumnSetSelector<>(0, entityUpdateExpression, entityUpdateExpression.getSetColumns());
            columnSelectorExpression.apply(columnSelector);
        }
        return this;
    }

    @Override
    public EntityUpdatable<T> setIgnoreColumns(boolean condition, SqlExpression<SqlColumnSelector<T>>
            columnSelectorExpression) {
        if (condition) {
            DefaultSqlColumnSetSelector<T> columnSelector = new DefaultSqlColumnSetSelector<>(0, entityUpdateExpression, entityUpdateExpression.getSetIgnoreColumns());
            columnSelectorExpression.apply(columnSelector);
        }
        return this;
    }

    @Override
    public EntityUpdatable<T> whereColumns(boolean condition, SqlExpression<SqlColumnSelector<T>>
            columnSelectorExpression) {
        if (condition) {
            DefaultSqlColumnSetSelector<T> columnSelector = new DefaultSqlColumnSetSelector<>(0, entityUpdateExpression, entityUpdateExpression.getWhereColumns());
            columnSelectorExpression.apply(columnSelector);
        }
        return this;
    }

    @Override
    public EntityUpdatable<T> asTable(Function<String, String> tableNameAs) {
        entityUpdateExpression.getRecentlyTable().setTableNameAs(tableNameAs);
        return this;
    }

    @Override
    public EntityUpdatable<T> setSqlStrategy(boolean condition, SqlExecuteStrategyEnum sqlStrategy) {
        if (condition) {
            entityUpdateExpression.getExpressionContext().useSqlStrategy(sqlStrategy);
        }
        return this;
    }

    public String toSql(Object entity) {
        return toSqlWithParam(entity,null);
    }
    private String toSqlWithParam(Object entity, SqlParameterCollector sqlParameterCollector){
        return entityUpdateExpression.toExpression(entity).toSql(sqlParameterCollector);
    }
}
