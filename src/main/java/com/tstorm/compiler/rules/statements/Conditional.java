package com.tstorm.compiler.rules.statements;

import com.tstorm.compiler.rules.expressions.Expression;

/**
 * Created by tstorm on 10/31/16.
 */
public class Conditional extends Statement {

    private final Expression expression;
    private final Statement ifStatement;
    private final Statement elseStatement;

    public Conditional(Expression expression, Statement ifStatement, Statement elseStatement) {
        this.expression = expression;
        this.ifStatement = ifStatement;
        this.elseStatement = elseStatement;
    }

    public String toString() {
        return "if";
    }

}
