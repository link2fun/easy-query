package com.easy.query.core.expression.parser.impl;

import com.easy.query.core.expression.sql.builder.EntityQueryExpressionBuilder;

/**
 * @FileName: DefaultSqlColumnSelector.java
 * @Description: 文件说明
 * @Date: 2023/2/12 21:36
 * @author xuejiaming
 */
public class DefaultSqlGroupColumnSelector<T1> extends DefaultSqlColumnSelector<T1> {
    public DefaultSqlGroupColumnSelector(int index, EntityQueryExpressionBuilder sqlEntityExpression) {
        super(index, sqlEntityExpression,sqlEntityExpression.getGroup());
    }
}
