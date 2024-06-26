package com.easy.query.api.proxy.util;

import com.easy.query.core.proxy.ProxyNavValueAvailable;
import com.easy.query.core.proxy.core.EntitySQLContext;
import com.easy.query.core.proxy.core.FlatEntitySQLContext;

/**
 * create time 2024/6/8 21:37
 * 文件说明
 *
 * @author xuejiaming
 */
public class EasyProxyUtil {

    public static String getNavValue(ProxyNavValueAvailable proxyNavValueAvailable) {
        String navValue = proxyNavValueAvailable.getNavValue();
        if(navValue==null){
            EntitySQLContext entitySQLContext = proxyNavValueAvailable.getEntitySQLContext();
            if(entitySQLContext instanceof FlatEntitySQLContext){
                FlatEntitySQLContext flatEntitySQLContext = (FlatEntitySQLContext) entitySQLContext;
                navValue = flatEntitySQLContext.getNavValue();
            }
        }
        return navValue;
    }

    public static  String getFullNavValue(ProxyNavValueAvailable proxyNavValueAvailable) {
        return getNavValue(proxyNavValueAvailable)+"."+ proxyNavValueAvailable.getValue();
    }
}
