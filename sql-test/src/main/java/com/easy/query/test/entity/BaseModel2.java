package com.easy.query.test.entity;

import com.easy.query.core.proxy.ProxyEntity;
import com.easy.query.core.proxy.ProxyEntityAvailable;

/**
 * create time 2024/5/25 21:36
 * 文件说明
 *
 * @author xuejiaming
 */
public abstract class BaseModel2<TEntity, TProxy extends ProxyEntity<TProxy, TEntity>> extends BaseModel<TEntity, TProxy> {
}
