package com.easy.query.core.metadata;

import com.easy.query.core.enums.EntityMetadataTypeEnum;
import com.easy.query.core.util.EasyFieldCheckUtil;

import java.util.Map;

/**
 * create time 2024/3/2 21:09
 * 文件说明
 *
 * @author xuejiaming
 */
public class MapEntityMetadata extends EntityMetadata{
    public MapEntityMetadata(Class<?> entityClass) {
        super(entityClass);
        entityMetadataType = EntityMetadataTypeEnum.MAP;
    }

    @Override
    public String getColumnName(String propertyName) {
        return EasyFieldCheckUtil.toCheckField(propertyName);
    }

    @Override
    public ColumnMetadata getColumnNotNull(String propertyName) {
        String checkField = EasyFieldCheckUtil.toCheckField(propertyName);
        ColumnOption columnOption = new ColumnOption(false, this, checkField);
        columnOption.setGetterCaller(obj->{
            if(obj instanceof Map){
                return ((Map)obj).get(checkField);
            }
            return null;
        });
        columnOption.setSetterCaller((obj,val)->{
            if(obj instanceof Map){
                ((Map)obj).put(checkField,val);
            }
        });
        return new MapColumnMetadata(columnOption);
    }
}
