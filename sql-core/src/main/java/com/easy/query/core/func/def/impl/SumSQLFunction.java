package com.easy.query.core.func.def.impl;

import com.easy.query.core.expression.func.AggregationType;
import com.easy.query.core.expression.parser.core.available.TableAvailable;
import com.easy.query.core.func.SQLFunction;
import com.easy.query.core.func.column.ColumnExpression;
import com.easy.query.core.func.def.AbstractWithValueOrDefaultExpressionSQLFunction;
import com.easy.query.core.util.EasyCollectionUtil;

import java.util.List;

/**
 * create time 2023/10/18 09:52
 * 文件说明
 *
 * @author xuejiaming
 */
public class SumSQLFunction extends AbstractWithValueOrDefaultExpressionSQLFunction {

    public SumSQLFunction(List<ColumnExpression> columnExpressions) {
        super(columnExpressions);
    }

    @Override
    public String sqlSegment(TableAvailable defaultTable) {
        Iterable<String> params = EasyCollectionUtil.select(columnExpressions, (t, i) -> "{" + i + "}");
        if (distinct) {
            return String.format("SUM(DISTINCT %s)", String.join(",", params));
        }
        return String.format("SUM(%s)", String.join(",", params));
    }

    @Override
    public AggregationType getAggregationType() {
        if (distinct) {
            return AggregationType.SUM_DISTINCT;
        }
        return AggregationType.SUM;
    }

    @Override
    protected SQLFunction createValueOrDefaultSQLFunction(List<ColumnExpression> columnExpressions) {
        return new ValueOrDefaultSQLFunction(columnExpressions);
    }
}
