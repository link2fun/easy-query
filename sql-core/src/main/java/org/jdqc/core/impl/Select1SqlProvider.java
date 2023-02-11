package org.jdqc.core.impl;

import org.jdqc.core.abstraction.sql.enums.PredicateModeEnum;
import org.jdqc.core.abstraction.sql.base.SqlColumnSelector;
import org.jdqc.core.abstraction.sql.base.SqlPredicate;
import org.jdqc.core.impl.lambda.DefaultSqlGroupSelector;
import org.jdqc.core.impl.lambda.DefaultSqlOrderBySelector;
import org.jdqc.core.impl.lambda.DefaultSqlPredicate;

/**
 * @FileName: Select1SqlPredicateProvider.java
 * @Description: 文件说明
 * @Date: 2023/2/7 23:45
 * @Created by xuejiaming
 */
public class Select1SqlProvider<T1> {
    private  DefaultSqlPredicate<T1> sqlPredicate1;
//    private  DefaultSqlSelector<T1,TR> sqlSelector1;
    private  DefaultSqlGroupSelector<T1> sqlGroupSelector1;
    private  DefaultSqlOrderBySelector<T1> sqlOrderBySelector1;
    private final SelectContext selectContext;

    public Select1SqlProvider(SelectContext selectContext) {
        this.selectContext = selectContext;
    }
    public SqlColumnSelector<T1> getSqlOrderBySelector1(){
        if(sqlOrderBySelector1==null){
            sqlOrderBySelector1=new DefaultSqlOrderBySelector<>(0,selectContext);
        }
        return sqlOrderBySelector1;
    }
    public SqlColumnSelector<T1> getSqlGroupSelector1(){
        if(sqlGroupSelector1==null){
            sqlGroupSelector1=new DefaultSqlGroupSelector<>(0,selectContext);
        }
        return sqlGroupSelector1;
    }
//    public SqlColumnAsSelector<T1,TR> getSqlSelector1(){
//        if(sqlSelector1==null){
//            sqlSelector1=new DefaultSqlSelector<>(0,selectContext);
//        }
//        return sqlSelector1;
//    }

    public SqlPredicate<T1> getSqlPredicate1(PredicateModeEnum predicateMode) {
        if(sqlPredicate1==null){
            sqlPredicate1=new DefaultSqlPredicate<>(0,selectContext, PredicateModeEnum.WHERE_PREDICATE);
        }
        sqlPredicate1.setPredicateMode(predicateMode);
        return sqlPredicate1;
    }

    public SqlPredicate<T1> getSqlWherePredicate1() {
        return getSqlPredicate1(PredicateModeEnum.WHERE_PREDICATE);
    }

    public SqlPredicate<T1> getSqlOnPredicate1() {
        return getSqlPredicate1(PredicateModeEnum.ON_PREDICATE);
    }
}