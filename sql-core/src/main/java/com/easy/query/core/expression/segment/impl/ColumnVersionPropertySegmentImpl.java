package com.easy.query.core.expression.segment.impl;

import com.easy.query.core.basic.extension.version.VersionStrategy;
import com.easy.query.core.basic.jdbc.parameter.PropertySQLParameter;
import com.easy.query.core.basic.jdbc.parameter.ToSQLContext;
import com.easy.query.core.basic.jdbc.parameter.VersionPropertySQLParameter;
import com.easy.query.core.expression.parser.core.available.TableAvailable;
import com.easy.query.core.expression.sql.builder.ExpressionContext;
import com.easy.query.core.util.EasySQLUtil;

/**
 * create time 2023/3/27 16:00
 * 文件说明
 *
 * @author xuejiaming
 */
public class ColumnVersionPropertySegmentImpl extends UpdateColumnSegmentImpl {

    private final VersionStrategy easyVersionStrategy;

    public ColumnVersionPropertySegmentImpl(TableAvailable table, String propertyName, VersionStrategy easyVersionStrategy, ExpressionContext expressionContext) {
        super(table, propertyName, expressionContext);

        this.easyVersionStrategy = easyVersionStrategy;
    }

    @Override
    public String toSQL(ToSQLContext toSQLContext) {
        EasySQLUtil.addParameter(toSQLContext,new VersionPropertySQLParameter(new PropertySQLParameter(table,propertyName),easyVersionStrategy));
        return "?";
    }
}
