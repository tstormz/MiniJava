package com.tstorm.compiler.rules.expressions;

import com.tstorm.compiler.rules.Type;

/**
 * Created by tstorm on 11/2/16.
 */
public class Instance implements Expression {

    private Expression arrayIndex;
    private String className;

    public Instance(Expression expression) {
        this.arrayIndex = expression;
    }

    public Instance(String className) {
        this.className = className;
    }

    @Override
    public Type resolveToType() {
        return null;
    }
}
