package com.easy.query.core.basic.api.select.provider;

import com.easy.query.core.expression.parser.abstraction.*;
import com.easy.query.core.expression.segment.builder.SqlBuilderSegment;
import com.easy.query.core.expression.parser.abstraction.*;

/**
 * @FileName: EasyQueryLambdaBuilder.java
 * @Description: 文件说明
 * @Date: 2023/2/12 10:02
 * @author xuejiaming
 */
public interface EasyQuerySqlBuilderProvider2<T1,T2> extends EasyQuerySqlBuilderProvider<T1> {
    SqlColumnSelector<T2> getSqlGroupColumnSelector2();
    SqlColumnSelector<T2> getSqlOrderColumnSelector2(boolean asc);
    SqlPredicate<T2> getSqlWherePredicate2();
    SqlAggregatePredicate<T2> getSqlAggregatePredicate2();
    SqlPredicate<T2> getSqlOnPredicate2();

    SqlColumnSelector<T2> getSqlColumnSelector2(SqlBuilderSegment sqlSegment0Builder);
    <TR> SqlColumnAsSelector<T2,TR> getSqlColumnAsSelector2(SqlBuilderSegment sqlSegment0Builder,Class<TR> resultClass);
//    <TR> SqlColumnAsSelector<T2,TR> getSqlAutoColumnAsSelector2(SqlBuilderSegment sqlSegment0Builder,Class<TR> resultClass);
    <TR> SqlColumnResultSelector<T2,TR> getSqlColumnResultSelector2(SqlBuilderSegment sqlSegment0Builder);
}