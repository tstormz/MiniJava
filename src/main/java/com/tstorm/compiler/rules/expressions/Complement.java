package com.tstorm.compiler.rules.expressions;

import com.tstorm.compiler.rules.Type;

/**
 * Created by tstorm on 11/2/16.
 */
public class Complement implements Expression {

    private final Expression expression;

    public Complement(Expression expression) {
        this.expression = expression;
    }

    @Override
    public Type resolveToType() {
        return null;
    }
}
