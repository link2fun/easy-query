package com.easy.query.search.op;

public class In
        implements Op {
    @Override
    public String getName() {
        return "in";
    }

    @Override
    public void filter(FilterContext context) {
        context.getFilter().in(context.getTable(), context.getPropertyName(), context.getParams());
    }
}
