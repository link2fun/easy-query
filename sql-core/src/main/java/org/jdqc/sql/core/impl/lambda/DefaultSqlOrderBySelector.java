package org.jdqc.sql.core.impl.lambda;

import org.jdqc.sql.core.abstraction.sql.base.SqlColumnSelector;
import org.jdqc.sql.core.impl.SelectContext;

/**
 * @FileName: DefaultSqlGroupSelector.java
 * @Description: 文件说明
 * @Date: 2023/2/8 12:42
 * @Created by xuejiaming
 */
public class DefaultSqlOrderBySelector<T1> extends AbstractSqlColumnSelector<T1,SqlColumnSelector<T1>> implements SqlColumnSelector<T1> {
    public DefaultSqlOrderBySelector(int index, SelectContext selectContext) {
        super(index, selectContext);
    }

    @Override
    public StringBuilder getSql() {
        return this.getSelectContext().getOrder();
    }
}
