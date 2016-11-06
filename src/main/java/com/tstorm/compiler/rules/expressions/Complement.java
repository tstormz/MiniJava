package com.tstorm.compiler.rules.expressions;

import com.tstorm.compiler.rules.Type;
import com.tstorm.compiler.typechecker.ExpressionVisitor;

/**
 * Created by tstorm on 11/2/16.
 */
public class Complement extends Expression {

    private final Expression expression;

    public Complement(Expression expression) {
        this.expression = expression;
    }

    @Override
    public Type accept(ExpressionVisitor v) {
        v.visit(this);
        return null;
    }
}