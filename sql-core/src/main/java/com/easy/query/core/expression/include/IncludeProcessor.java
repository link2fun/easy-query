package com.easy.query.core.expression.include;

import java.util.List;

/**
 * create time 2023/7/16 18:26
 * 文件说明
 *
 * @author xuejiaming
 */
public interface IncludeProcessor {

    <TEntityInclude> void process(List<TEntityInclude> includes);
}
