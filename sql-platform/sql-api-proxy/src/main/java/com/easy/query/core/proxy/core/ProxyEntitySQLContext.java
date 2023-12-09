package com.easy.query.core.proxy.core;

import com.easy.query.core.context.QueryRuntimeContext;
import com.easy.query.core.expression.builder.AggregateFilter;
import com.easy.query.core.expression.builder.Filter;
import com.easy.query.core.expression.lambda.SQLActionExpression;
import com.easy.query.core.proxy.SQLAggregatePredicateExpression;
import com.easy.query.core.proxy.SQLColumnSetExpression;
import com.easy.query.core.proxy.SQLOrderByExpression;
import com.easy.query.core.proxy.SQLPredicateExpression;
import com.easy.query.core.proxy.core.accpet.AggregatePredicateEntityExpressionAccept;
import com.easy.query.core.proxy.core.accpet.AggregatePredicateEntityExpressionAcceptImpl;
import com.easy.query.core.proxy.core.accpet.EntityExpressionAccept;
import com.easy.query.core.proxy.core.accpet.PredicateEntityExpressionAccept;
import com.easy.query.core.proxy.core.accpet.PredicateEntityExpressionAcceptImpl;

/**
 * create time 2023/12/8 15:35
 * 文件说明
 *
 * @author xuejiaming
 */
public class ProxyEntitySQLContext implements EntitySQLContext {
    private final QueryRuntimeContext runtimeContext;
    private EntityExpressionAccept accept = EntityExpressionAccept.empty;

    public ProxyEntitySQLContext(QueryRuntimeContext runtimeContext){

        this.runtimeContext = runtimeContext;
    }
    @Override
    public void accept(EntityExpressionAccept accept, SQLActionExpression sqlActionExpression) {
        EntityExpressionAccept tempAccept = this.accept;
        this.accept= accept;
        sqlActionExpression.apply();
        this.accept=tempAccept;
    }

    @Override
    public void accept(SQLPredicateExpression sqlPredicateExpression) {
        accept.accept(sqlPredicateExpression);

    }

    @Override
    public void accept(SQLAggregatePredicateExpression sqlAggregatePredicateExpression) {
        accept.accept(sqlAggregatePredicateExpression);

    }

    @Override
    public void accept(SQLColumnSetExpression sqlColumnSetExpression) {
        accept.accept(sqlColumnSetExpression);
    }

    @Override
    public void accept(SQLOrderByExpression sqlOrderByExpression) {
        accept.accept(sqlOrderByExpression);
    }


    @Override
    public QueryRuntimeContext getRuntimeContext() {
        return runtimeContext;
    }

    @Override
    public void _whereOr(SQLActionExpression sqlActionExpression) {
        if(accept instanceof PredicateEntityExpressionAccept){
            PredicateEntityExpressionAccept predicateEntityExpressionAccept = (PredicateEntityExpressionAccept) accept;
            Filter filter = predicateEntityExpressionAccept.getFilter();
            filter.and(f->{
                PredicateEntityExpressionAcceptImpl innerAccept = new PredicateEntityExpressionAcceptImpl(f);
                this.accept= innerAccept;
                innerAccept.nextOr(true);
                sqlActionExpression.apply();
                innerAccept.nextOr(false);
            });
            this.accept=predicateEntityExpressionAccept;
        } else if(accept instanceof AggregatePredicateEntityExpressionAccept){
            AggregatePredicateEntityExpressionAccept aggregatePredicateEntityExpressionAccept = (AggregatePredicateEntityExpressionAccept) accept;
            AggregateFilter aggregateFilter = aggregatePredicateEntityExpressionAccept.getAggregateFilter();
            aggregateFilter.and(f->{
                AggregatePredicateEntityExpressionAcceptImpl innerAccept = new AggregatePredicateEntityExpressionAcceptImpl(f);
                this.accept= innerAccept;
                innerAccept.nextOr(true);
                sqlActionExpression.apply();
                innerAccept.nextOr(false);
            });
            this.accept=aggregatePredicateEntityExpressionAccept;
        }else{
            throw new UnsupportedOperationException();
        }
    }
}
