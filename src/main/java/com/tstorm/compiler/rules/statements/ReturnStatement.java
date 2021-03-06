package com.tstorm.compiler.rules.statements;

import com.tstorm.compiler.assembler.Assembler;
import com.tstorm.compiler.rules.expressions.Expression;
import com.tstorm.compiler.typechecker.Visitor;

import java.io.PrintWriter;

/**
 * Created by tstorm on 11/2/16.
 */
public class ReturnStatement extends Assembler implements Statement {

    public static final String ERROR = "Return error: expecting '%s' but found '%s'";
    private final Expression expression;

    public ReturnStatement(Expression expression) {
        this.expression = expression;
    }

    public Expression getExpression() {
        return expression;
    }

    public String toString() {
        return "return " + expression.toString();
    }

    @Override
    public boolean accept(Visitor v) {
        return v.visit(this);
    }

    @Override
    public void generateCode(PrintWriter out) {
        ((Assembler) expression).generateCode(out);
    }
}
