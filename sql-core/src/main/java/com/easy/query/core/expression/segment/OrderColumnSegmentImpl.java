package com.easy.query.core.expression.segment;

import com.easy.query.core.abstraction.EasyQueryRuntimeContext;
import com.easy.query.core.basic.jdbc.parameter.SQLParameterCollector;
import com.easy.query.core.enums.SQLKeywordEnum;
import com.easy.query.core.expression.parser.core.available.TableAvailable;
import com.easy.query.core.util.SQLExpressionUtil;

/**
 * @FileName: OrderColumnSegment.java
 * @Description: 文件说明
 * @Date: 2023/2/13 22:18
 * @author xuejiaming
 */
public class OrderColumnSegmentImpl extends ColumnSegmentImpl implements OrderByColumnSegment {
    @Override
    public GroupByColumnSegment createGroupByColumnSegment() {
        return new GroupColumnSegmentImpl(table,propertyName,runtimeContext);
    }

    @Override
    public boolean isAsc() {
        return asc;
    }

    private final boolean asc;

    public OrderColumnSegmentImpl(TableAvailable table, String propertyName, EasyQueryRuntimeContext runtimeContext, boolean asc) {
        super(table,propertyName, runtimeContext);
        this.asc = asc;
    }

    @Override
    public String toSQL(SQLParameterCollector sqlParameterCollector) {

        String sqlColumnSegment = SQLExpressionUtil.getSQLOwnerColumn(runtimeContext,table,propertyName);
        StringBuilder sql = new StringBuilder().append(sqlColumnSegment);
        if(asc){
            sql.append(" ").append(SQLKeywordEnum.ASC.toSQL());
        }else {
            sql.append(" ").append(SQLKeywordEnum.DESC.toSQL());
        }
        return sql.toString();
    }
}