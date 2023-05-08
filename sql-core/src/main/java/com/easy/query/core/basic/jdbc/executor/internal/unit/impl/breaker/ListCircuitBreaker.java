package com.easy.query.core.basic.jdbc.executor.internal.unit.impl.breaker;

import com.easy.query.core.sharding.merge.context.StreamMergeContext;
import com.easy.query.core.sharding.merge.result.InMemoryStreamMergeResultSet;
import com.easy.query.core.util.EasyCollectionUtil;

import java.util.Collection;

/**
 * create time 2023/5/8 23:01
 * 文件说明
 *
 * @author xuejiaming
 */
public final class ListCircuitBreaker extends AbstractCircuitBreaker{
    public ListCircuitBreaker(StreamMergeContext streamMergeContext) {
        super(streamMergeContext);
    }

    @Override
    protected <TResult> boolean SequenceTerminated(Collection<TResult> results) {
        if(streamMergeContext.isPaginationQuery()){
            long rows = streamMergeContext.getRewriteRows();
            if(rows>0){
                long offset = streamMergeContext.getRewriteOffset();
                int reallyCount = EasyCollectionUtil.sum(results, element -> {
                    if (element instanceof InMemoryStreamMergeResultSet) {
                        return ((InMemoryStreamMergeResultSet)element).getReallyCount();
                    }
                    return 0;
                });
                return (offset+rows)<=reallyCount;
            }
        }
        return false;
    }

    @Override
    protected <TResult> boolean RandomTerminated(Collection<TResult> results) {
        return false;
    }
}
