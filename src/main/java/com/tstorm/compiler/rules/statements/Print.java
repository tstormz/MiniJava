package com.tstorm.compiler.rules.statements;

import com.tstorm.compiler.rules.expressions.Expression;
import com.tstorm.compiler.typechecker.Visitor;

/**
 * Created by tstorm on 11/23/16.
 */
public class Print implements Statement {

    private final Expression expression;

    public Print(Expression expression) {
        this.expression = expression;
    }

    public Expression getExpression() {
        return expression;
    }

    @Override
    public boolean accept(Visitor v) {
        return v.visit(this);
    }

}
