package com.easy.query.api4j.sql;

import com.easy.query.core.expression.lambda.Property;
import com.easy.query.core.expression.parser.core.available.TableAvailable;
import com.easy.query.core.expression.parser.core.base.ColumnResultSelector;
import com.easy.query.core.util.EasyLambdaUtil;

/**
 * @author xuejiaming
 * @Description: 文件说明
 * @Date: 2023/2/6 23:20
 */
public interface SQLColumnResultSelector<T1, TMember> {
    ColumnResultSelector<T1> getColumnResultSelector();

    default TableAvailable getTable() {
        return getColumnResultSelector().getTable();
    }

    default void column(Property<T1, TMember> column) {
        getColumnResultSelector().column(EasyLambdaUtil.getPropertyName(column));
    }
}