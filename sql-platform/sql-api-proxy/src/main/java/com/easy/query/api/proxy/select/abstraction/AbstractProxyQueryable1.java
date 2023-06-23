package com.easy.query.api.proxy.select.abstraction;

import com.easy.query.api.proxy.core.ProxyQuery;
import com.easy.query.core.basic.api.select.ClientQueryable;

/**
 * create time 2023/6/23 22:08
 * 文件说明
 *
 * @author xuejiaming
 */
public abstract class AbstractProxyQueryable1<T1Proxy extends ProxyQuery<T1Proxy, T1>, T1> extends AbstractProxyQueryable<T1Proxy,T1> {


    public AbstractProxyQueryable1(T1Proxy t1Proxy, ClientQueryable<T1> entityQueryable) {
        super(t1Proxy, entityQueryable);
    }
}
