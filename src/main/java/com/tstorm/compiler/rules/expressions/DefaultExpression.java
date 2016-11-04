package com.tstorm.compiler.rules.expressions;

import com.tstorm.compiler.rules.Type;
import com.tstorm.compiler.typechecker.ExpressionVisitor;

/**
 * Created by tstorm on 11/1/16.
 */
public class DefaultExpression extends Expression {

    private Expression expression;

    public DefaultExpression(){}

    public DefaultExpression(Expression expression) {
        this.expression = expression;
    }

    @Override
    public Type accept(ExpressionVisitor v) {
        v.visit(this);
        return null;
    }
}
