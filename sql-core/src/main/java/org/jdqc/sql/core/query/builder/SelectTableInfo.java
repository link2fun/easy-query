package org.jdqc.sql.core.query.builder;

import org.jdqc.sql.core.abstraction.sql.base.SqlPredicate;
import org.jdqc.sql.core.enums.SelectTableInfoTypeEnum;
import org.jdqc.sql.core.schema.TableInfo;

/**
 * @FileName: SelectTableInfo.java
 * @Description: 文件说明
 * @Date: 2023/2/7 11:36
 * @Created by xuejiaming
 */
public class SelectTableInfo {
    private final TableInfo table;
    private final String alias;
    private final StringBuilder on;
    private final int index;

    private final SelectTableInfoTypeEnum selectTableInfoType;

    public SelectTableInfo(TableInfo table, String alias, int index,SelectTableInfoTypeEnum selectTableInfoType) {
        this.table = table;
        this.alias = index == 0 ? alias : alias + index;
        this.index = index;
        this.selectTableInfoType = selectTableInfoType;
        this.on=new StringBuilder();
    }

    public TableInfo getTable() {
        return table;
    }

    public String getAlias() {
        return alias;
    }

    public int getIndex() {
        return index;
    }

    public StringBuilder getOn() {
        return on;
    }


    public SelectTableInfoTypeEnum getSelectTableInfoType() {
        return selectTableInfoType;
    }
    public String getSelectTableSource(){
        if(SelectTableInfoTypeEnum.LEFT_JOIN.equals(getSelectTableInfoType())){
            return  " LEFT JOIN ";
        }
        else if(SelectTableInfoTypeEnum.INNER_JOIN.equals(getSelectTableInfoType())){
            return  " INNER JOIN ";
        }
        else if(SelectTableInfoTypeEnum.RIGHT_JOIN.equals(getSelectTableInfoType())){
            return  " RIGHT JOIN ";
        }
        return " FROM ";
    }
}
