package com.tstorm.compiler.rules.statements;

import com.tstorm.compiler.assembler.Assembler;
import com.tstorm.compiler.rules.expressions.Expression;
import com.tstorm.compiler.typechecker.Visitor;

import java.io.PrintWriter;

/**
 * Created by tstorm on 10/31/16.
 */
public class Loop extends Assembler implements Statement {

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

    @Override
    public void generateCode(PrintWriter out) {
        Label loop = generateLabel();
        out.println(loop.getLabel() + " ; test expr");
        ((Assembler) expression).generateCode(out);
        Label quit = generateLabel();
        out.println("ifeq " + quit.getLabelUse() + " ; break");
        ((Assembler) statement).generateCode(out);
        out.println("goto " + loop.getLabelUse() + " ; loop");
        out.println(quit.getLabel());
    }
}
