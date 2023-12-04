package com.easy.query.core.proxy;

import com.easy.query.core.expression.builder.GroupSelector;
import com.easy.query.core.proxy.impl.SQLGroupSelectImpl;

/**
 * create time 2023/12/2 18:57
 * 文件说明
 *
 * @author xuejiaming
 */
public interface SQLGroupSelect extends TablePropColumn {

    default SQLGroupSelect then(SQLGroupSelect sqlGroupSelect){
        return new SQLGroupSelectImpl(x->{
            accept(x);
            sqlGroupSelect.accept(x);
        });
    }
    default void accept(GroupSelector s) {
        s.column(this.getTable(), this.value());
    }

    SQLGroupSelect empty = new SQLGroupSelectImpl(s -> {
    });
}
