package com.easy.query.core.expression.parser.abstraction;

import com.easy.query.core.expression.parser.abstraction.internal.ColumnSelector;

/**
 *
 * @FileName: SqlColumnSelector.java
 * @Description: 文件说明
 * @Date: 2023/2/6 23:21
 * @author xuejiaming
 */
public interface SqlColumnSelector<T1> extends ColumnSelector<T1,SqlColumnSelector<T1>> {
}