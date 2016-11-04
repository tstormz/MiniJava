package com.tstorm.compiler.rules.statements;

import com.tstorm.compiler.rules.expressions.Expression;
import com.tstorm.compiler.typechecker.Visitor;

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

    public Statement getIf() {
        return ifStatement;
    }

    public Statement getElse() {
        return elseStatement;
    }

    public Expression getExpression() {
        return expression;
    }

    public String toString() {
        return "if(" + expression.toString() + ")\n   " +
                ifStatement.toString() + "\nelse\n   " + elseStatement.toString();
    }

    @Override
    public void accept(Visitor v) {
        v.visit(this);
    }
}
