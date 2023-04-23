package com.easy.query.core.expression.segment.condition.predicate;

import com.easy.query.core.basic.jdbc.parameter.SQLParameter;

import java.util.Collection;

/**
 * create time 2023/4/22 17:24
 * 文件说明
 *
 * @author xuejiaming
 */
public interface ValuesPredicate extends Predicate {
    Collection<SQLParameter> getParameters();
}
