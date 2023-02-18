package org.easy.query.core.impl;

import org.easy.query.core.abstraction.*;
import org.easy.query.core.abstraction.lambda.Property;
import org.easy.query.core.abstraction.lambda.SqlExpression;
import org.easy.query.core.abstraction.sql.Select0;
import org.easy.query.core.abstraction.sql.base.*;
import org.easy.query.core.segments.ColumnSegment;

import java.util.ArrayList;
import java.util.List;

/**
 * @FileName: AbstractSelect0.java
 * @Description: 文件说明
 * @Date: 2023/2/6 23:44
 * @Created by xuejiaming
 */
public abstract class AbstractSelect0<T1, TChain> implements Select0<T1, TChain> {
    protected final Class<T1> t1Class;
    private final SelectContext selectContext;

    public AbstractSelect0(Class<T1> t1Class, SelectContext selectContext) {
        this.t1Class = t1Class;
        this.selectContext = selectContext;
    }


    @Override
    public abstract int count();

    @Override
    public abstract boolean any();

    @Override
    public T1 firstOrNull() {
        SqlExpression<SqlColumnSelector<T1>> selectExpression = getDefaultColumnAll();
        return firstOrNull(selectExpression);
    }

    @Override
    public T1 firstOrNull(SqlExpression<SqlColumnSelector<T1>> selectExpression) {
        this.limit(1);
        List<T1> list = toList(selectExpression);
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    @Override
    public <TR> TR firstOrNull(Class<TR> resultClass) {
        SqlExpression<SqlColumnAsSelector<T1, TR>> selectExpression = getDefaultColumnAsAll();
        return firstOrNull(resultClass, selectExpression);
    }

    @Override
    public <TR> TR firstOrNull(Class<TR> resultClass, SqlExpression<SqlColumnAsSelector<T1, TR>> selectExpression) {
        this.limit(1);
        List<TR> list = toList(resultClass, selectExpression);
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }
    protected SqlExpression<SqlColumnSelector<T1>> getDefaultColumnAll(){
        if(selectContext.getGroup().isEmpty()){
            return  ColumnSelector::columnAll;
        }else{
            return o->{};
        }
    }
    protected <TR> SqlExpression<SqlColumnAsSelector<T1,TR>> getDefaultColumnAsAll(){
        if(selectContext.getGroup().isEmpty()){
            return  ColumnSelector::columnAll;
        }else{
            return o->{};
        }
    }

    @Override
    public List<T1> toList() {
        SqlExpression<SqlColumnSelector<T1>> selectorExpression = getDefaultColumnAll();
        return toList(selectorExpression);
    }

    @Override
    public <TR> List<TR> toList(Class<TR> resultClass) {
        SqlExpression<SqlColumnAsSelector<T1, TR>> selectExpression=getDefaultColumnAsAll();
        return toList(resultClass,selectExpression);
    }

    @Override
    public <TR> List<TR> toList(Class<TR> resultClass, SqlExpression<SqlColumnAsSelector<T1, TR>> selectExpression) {
        SelectSqlSegmentBuilder sqlSegmentBuilder = new SelectSqlSegmentBuilder();
        SqlColumnAsSelector<T1,TR> sqlColumnSelector = getSqlBuilderProvider1().getSqlColumnAsSelector1(sqlSegmentBuilder);
        selectExpression.apply(sqlColumnSelector);
        return toInternalList(resultClass,sqlSegmentBuilder.toSql());
    }

    @Override
    public List<T1> toList(SqlExpression<SqlColumnSelector<T1>> selectExpression) {
        SelectSqlSegmentBuilder sqlSegmentBuilder = new SelectSqlSegmentBuilder();
        SqlColumnSelector<T1> sqlColumnSelector = getSqlBuilderProvider1().getSqlColumnSelector1(sqlSegmentBuilder);
        selectExpression.apply(sqlColumnSelector);
        return toInternalList(t1Class,sqlSegmentBuilder.toSql());
    }

    /**
     * 子类实现方法
     *
     * @return
     */
    protected <TR> List<TR> toInternalList(Class<TR> resultClass,String columns) {
        String s = toSql(columns);
        EasyExecutor easyExecutor = selectContext.getRuntimeContext().getEasyExecutor();
        return easyExecutor.execute(ExecutorContext.create(selectContext.getRuntimeContext()),resultClass,s,selectContext.getParams());
    }

    @Override
    public String toSql() {
        SqlExpression<SqlColumnSelector<T1>> selectExpression = ColumnSelector::columnAll;
        return toSql(selectExpression);
    }

    @Override
    public String toSql(SqlExpression<SqlColumnSelector<T1>> selectExpression) {
        SelectSqlSegmentBuilder sqlSegmentBuilder = new SelectSqlSegmentBuilder();
        SqlColumnSelector<T1> sqlColumnSelector = getSqlBuilderProvider1().getSqlColumnSelector1(sqlSegmentBuilder);
        selectExpression.apply(sqlColumnSelector);
        return toSql(sqlSegmentBuilder.toSql());
    }

    @Override
    public <TR> String toSql(Class<TR> resultClass, SqlExpression<SqlColumnAsSelector<T1, TR>> selectExpression) {
        SelectSqlSegmentBuilder sqlSegmentBuilder = new SelectSqlSegmentBuilder();
        SqlColumnAsSelector<T1,TR> sqlColumnSelector = getSqlBuilderProvider1().getSqlColumnAsSelector1(sqlSegmentBuilder);
        selectExpression.apply(sqlColumnSelector);
        return toSql(sqlSegmentBuilder.toSql());
    }

    @Override
    public abstract String toSql(String columns);

    protected abstract TChain castSelf();

    @Override
    public TChain where(boolean condition, SqlExpression<SqlPredicate<T1>> whereExpression) {
        if (condition) {
            SqlPredicate<T1> sqlPredicate = getSqlBuilderProvider1().getSqlWherePredicate1();
            whereExpression.apply(sqlPredicate);
        }
        return castSelf();
    }

    @Override
    public TChain groupBy(boolean condition, SqlExpression<SqlColumnSelector<T1>> selectExpression) {
        if (condition) {
            SqlColumnSelector<T1> sqlPredicate = getSqlBuilderProvider1().getSqlGroupColumnSelector1();
            selectExpression.apply(sqlPredicate);
        }
        return castSelf();
    }

    @Override
    public TChain having(boolean condition, SqlExpression<SqlAggregatePredicate<T1>> predicateExpression) {

        if (condition) {
            SqlAggregatePredicate<T1> sqlAggregatePredicate = getSqlBuilderProvider1().getSqlAggregatePredicate1();
            predicateExpression.apply(sqlAggregatePredicate);
        }
        return castSelf();
    }

    @Override
    public TChain orderByAsc(boolean condition, SqlExpression<SqlColumnSelector<T1>> selectExpression) {
        if (condition) {
            SqlColumnSelector<T1> sqlPredicate = getSqlBuilderProvider1().getSqlOrderColumnSelector1(true);
            selectExpression.apply(sqlPredicate);
        }
        return castSelf();
    }

    @Override
    public TChain orderByDesc(boolean condition, SqlExpression<SqlColumnSelector<T1>> selectExpression) {
        if (condition) {
            SqlColumnSelector<T1> sqlPredicate = getSqlBuilderProvider1().getSqlOrderColumnSelector1(false);
            selectExpression.apply(sqlPredicate);
        }
        return castSelf();
    }

    @Override
    public TChain limit(boolean condition, long offset, long rows) {
        if(condition){
            selectContext.setOffset(offset);
            selectContext.setRows(rows);
        }
        return castSelf();
    }

    public SelectContext getSelectContext() {
        return selectContext;
    }

    protected abstract EasyQuerySqlBuilderProvider<T1> getSqlBuilderProvider1();
}
