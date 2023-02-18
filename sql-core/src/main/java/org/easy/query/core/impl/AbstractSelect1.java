package org.easy.query.core.impl;

import org.easy.query.core.abstraction.EasyQuerySqlBuilderProvider;
import org.easy.query.core.abstraction.lambda.SqlExpression2;
import org.easy.query.core.abstraction.metadata.EntityMetadata;
import org.easy.query.core.abstraction.sql.Select1;
import org.easy.query.core.abstraction.sql.Select2;
import org.easy.query.core.abstraction.sql.base.SqlPredicate;
import org.easy.query.core.abstraction.sql.enums.PredicateModeEnum;
import org.easy.query.core.enums.SelectTableInfoTypeEnum;
import org.easy.query.core.query.builder.SelectTableInfo;

/**
 *
 * @FileName: AbstractSelect1.java
 * @Description: 文件说明
 * @Date: 2023/2/6 23:43
 * @Created by xuejiaming
 */
public abstract class AbstractSelect1<T1> extends AbstractSelect0<T1, Select1<T1>> implements Select1<T1> {

    private final Select1SqlProvider<T1> sqlPredicateProvider;
    public AbstractSelect1(Class<T1> t1Class,SelectContext selectContext) {
        super(t1Class,selectContext);
        EntityMetadata entityMetadata = selectContext.getRuntimeContext().getEntityMetadataManager().getEntityMetadata(t1Class);
        selectContext.addSelectTable(new SelectTableInfo(entityMetadata,selectContext.getAlias(),selectContext.getNextTableIndex(), SelectTableInfoTypeEnum.FROM));
        sqlPredicateProvider= new Select1SqlProvider<>(selectContext);
    }
    @Override
    public <T2> Select2<T1, T2> leftJoin(Class<T2> joinClass, SqlExpression2<SqlPredicate<T1>, SqlPredicate<T2>> on) {
        AbstractSelect2<T1, T2> select2 = createSelect2(joinClass, SelectTableInfoTypeEnum.LEFT_JOIN);
        SqlPredicate<T1> on1 = select2.getSqlBuilderProvider2().getSqlOnPredicate1();
        SqlPredicate<T2> on2 = select2.getSqlBuilderProvider2().getSqlOnPredicate2();
        on.apply(on1,on2);
        return select2;
    }


    @Override
    public <T2> Select2<T1, T2> innerJoin(Class<T2> joinClass, SqlExpression2<SqlPredicate<T1>, SqlPredicate<T2>> on) {
        AbstractSelect2<T1, T2> select2 = createSelect2(joinClass, SelectTableInfoTypeEnum.INNER_JOIN);
        SqlPredicate<T1> sqlOnPredicate1 = select2.getSqlBuilderProvider2().getSqlOnPredicate1();
        SqlPredicate<T2> sqlOnPredicate2 = select2.getSqlBuilderProvider2().getSqlOnPredicate2();
        on.apply(sqlOnPredicate1,sqlOnPredicate2);
        return select2;
    }
    @Override
    protected Select1<T1> castSelf() {
        return this;
    }
    protected abstract <T2> AbstractSelect2<T1, T2> createSelect2(Class<T2> joinClass,SelectTableInfoTypeEnum selectTableInfoType);

    @Override
    protected EasyQuerySqlBuilderProvider<T1> getSqlBuilderProvider1() {
        return sqlPredicateProvider;
    }
}