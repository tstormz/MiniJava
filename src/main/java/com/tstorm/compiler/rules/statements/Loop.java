package com.tstorm.compiler.rules.statements;

import com.tstorm.compiler.rules.expressions.Expression;
import com.tstorm.compiler.typechecker.Visitor;

/**
 * Created by tstorm on 10/31/16.
 */
public class Loop implements Statement {

    private final Expression expression;
    private final Statement statement;

    public Loop(Expression expression, Statement statement) {
        this.expression = expression;
        this.statement = statement;
    }

    public Expression getExpression() {
        return expression;
    }

    public Statement getStatement() {
        return statement;
    }

    public String toString() {
        return "while (" + expression.toString() + ")\n   " + statement.toString();
    }

    @Override
    public boolean accept(Visitor v) {
        return v.visit(this);
    }
}
