package com.easy.query.api.proxy.extension.partition;

import com.easy.query.core.exception.EasyQueryInvalidOperationException;
import com.easy.query.core.proxy.PropTypeColumn;
import com.easy.query.core.proxy.core.EntitySQLContext;
import com.easy.query.core.proxy.extension.functions.executor.ColumnFunctionCompareComparablePartitionByChainExpression;
import com.easy.query.core.proxy.extension.functions.executor.impl.ColumnFunctionCompareComparablePartitionByChainExpressionImpl;
import com.easy.query.core.util.EasyArrayUtil;

/**
 * create time 2024/8/4 14:35
 *
 * @author xuejiaming
 */
public class RankOverBuilder {
    private final EntitySQLContext entitySQLContext;

    public RankOverBuilder(EntitySQLContext entitySQLContext) {
        this.entitySQLContext = entitySQLContext;
    }

    public ColumnFunctionCompareComparablePartitionByChainExpression<Long> partitionBy(PropTypeColumn<?>... columns){
        if(EasyArrayUtil.isEmpty(columns)){
            throw new EasyQueryInvalidOperationException("rank over partition by empty");
        }
        return new ColumnFunctionCompareComparablePartitionByChainExpressionImpl<>(entitySQLContext, columns[0].getTable(), null, f->{
            return f.rankNumberOver(x->{
                for (PropTypeColumn<?> column : columns) {
                    PropTypeColumn.columnFuncSelector(x,column);
                }
            });
        } , Long.class);
    }
}
