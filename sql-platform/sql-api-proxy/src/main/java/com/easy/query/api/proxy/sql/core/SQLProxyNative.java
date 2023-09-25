package com.easy.query.api.proxy.sql.core;

import com.easy.query.api.proxy.sql.scec.SQLNativeProxyExpressionContext;
import com.easy.query.api.proxy.sql.scec.SQLNativeProxyExpressionContextImpl;
import com.easy.query.core.expression.builder.core.SQLNative;
import com.easy.query.core.expression.lambda.SQLExpression1;
import com.easy.query.core.expression.parser.core.available.ChainCast;

/**
 * create time 2023/7/31 14:35
 * 文件说明
 *
 * @author xuejiaming
 */
public interface SQLProxyNative<TChain> extends ChainCast<TChain> {
    <T> SQLNative<T> getSQLNative();

    /**
     * 参数格式化 占位符 {0} {1}
     * @param sqlSegment
     * @return
     */
    default TChain sqlNativeSegment(String sqlSegment){
        return sqlNativeSegment(true,sqlSegment);
    }
    /**
     * 参数格式化 占位符 {0} {1}
     * @param sqlSegment
     * @return
     */
    default TChain sqlNativeSegment(boolean condition,String sqlSegment){
        return sqlNativeSegment(condition,sqlSegment,c->{});
    }

    /**
     * 参数格式化 占位符 {0} {1}
     * @param sqlSegment
     * @param contextConsume
     * @return
     */
    default TChain sqlNativeSegment(String sqlSegment, SQLExpression1<SQLNativeProxyExpressionContext> contextConsume){
        return sqlNativeSegment(true,sqlSegment,contextConsume);
    }
    /**
     * 参数格式化 占位符 {0} {1}
     * @param sqlSegment
     * @param contextConsume
     * @return
     */
    default TChain sqlNativeSegment(boolean condition,String sqlSegment, SQLExpression1<SQLNativeProxyExpressionContext> contextConsume){
        if(condition){
            getSQLNative().sqlNativeSegment(sqlSegment,context->{
                contextConsume.apply(new SQLNativeProxyExpressionContextImpl(context));
            });
        }
        return castChain();
    }
}
