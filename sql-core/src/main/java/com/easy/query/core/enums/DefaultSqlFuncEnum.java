package com.easy.query.core.enums;

import com.easy.query.core.basic.enums.SqlFuncEnum;

/**
 *
 * @FileName: DefaultSqlFuncEnum.java
 * @Description: 文件说明
 * @Date: 2023/2/6 17:33
 * @author xuejiaming
 */
public enum DefaultSqlFuncEnum implements SqlFuncEnum {

    SUM("SUM(%s)"),
    COUNT("COUNT(%s)"),
    MAX("MAX(%s)"),
    MIN("MIN(%s)"),
    AVG("AVG(%s)"),
    LEN("LEN(%s)");

    private final String sql;

    DefaultSqlFuncEnum(String sql) {
        this.sql = sql;
    }

    @Override
    public String getSql() {
        return this.sql;
    }
}