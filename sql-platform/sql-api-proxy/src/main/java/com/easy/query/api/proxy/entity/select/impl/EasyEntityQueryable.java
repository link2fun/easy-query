package com.easy.query.api.proxy.entity.select.impl;

import com.easy.query.api.proxy.entity.select.EntityQueryable;
import com.easy.query.api.proxy.entity.select.abstraction.AbstractEntityQueryable1;
import com.easy.query.core.basic.api.select.ClientQueryable;
import com.easy.query.core.proxy.ProxyEntity;

/**
 * create time 2023/6/23 22:14
 * 文件说明
 *
 * @author xuejiaming
 */
public class EasyEntityQueryable<T1Proxy extends ProxyEntity<T1Proxy, T1>, T1> extends AbstractEntityQueryable1<T1Proxy,T1> {
    public EasyEntityQueryable(T1Proxy t1Proxy, ClientQueryable<T1> entityQueryable) {
        super(t1Proxy, entityQueryable);
    }

    @Override
    public EntityQueryable<T1Proxy, T1> cloneQueryable() {
        return new EasyEntityQueryable<>(t1Proxy,entityQueryable.cloneQueryable());
    }
}
