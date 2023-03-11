package org.easy.query.core.basic.api.select.impl;

import org.easy.query.core.basic.api.select.abstraction.AbstractQueryable2;
import org.easy.query.core.enums.MultiTableTypeEnum;
import org.easy.query.core.query.SqlEntityQueryExpression;

/**
 * @FileName: EasyQueryable2.java
 * @Description: 文件说明
 * @Date: 2023/3/6 08:30
 * @Created by xuejiaming
 */
public class EasyQueryable2<T1,T2> extends AbstractQueryable2<T1,T2> {
    public EasyQueryable2(Class<T1> t1Class, Class<T2> t2Class, SqlEntityQueryExpression sqlEntityExpression) {
        super(t1Class, t2Class, sqlEntityExpression);
    }

    @Override
    public String toInternalSql() {
        return sqlEntityExpression.toSql();
    }
}