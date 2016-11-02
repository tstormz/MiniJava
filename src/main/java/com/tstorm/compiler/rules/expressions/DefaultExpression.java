package com.tstorm.compiler.rules.expressions;

import com.tstorm.compiler.rules.Type;
import com.tstorm.compiler.rules.expressions.Expression;

/**
 * Created by tstorm on 11/1/16.
 */
public class DefaultExpression implements Expression {

    private Expression expression;

    public DefaultExpression(){}

    public DefaultExpression(Expression expression) {
        this.expression = expression;
    }

    @Override
    public Type resolveToType() {
        return null;
    }

}
