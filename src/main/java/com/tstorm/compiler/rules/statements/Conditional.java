package com.tstorm.compiler.rules.statements;

import com.tstorm.compiler.assembler.Assembler;
import com.tstorm.compiler.rules.expressions.Expression;
import com.tstorm.compiler.typechecker.Visitor;

import java.io.PrintWriter;

/**
 * Created by tstorm on 10/31/16.
 */
public class Conditional extends Assembler implements Statement {

    public static final String ERROR = "Error: conditional expression must be a boolean";
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
        return "if(" + expression.toString() + ")" +
                ifStatement.toString() + "\nelse" + elseStatement.toString();
    }

    @Override
    public boolean accept(Visitor v) {
        return v.visit(this);
    }

    @Override
    public void generateCode(PrintWriter out) {
        String elseStmt = generateLabel();
        String done = generateLabel();
        Assembler testExpr = (Assembler) expression;
        testExpr.setLabel(elseStmt);
        testExpr.generateCode(out);
//        out.println(elseStmt.substring(0, elseStmt.length() - 1));
        ((Assembler) ifStatement).generateCode(out);
        out.println("goto " + done.substring(0, done.length() - 1));
        out.println(elseStmt);
        ((Assembler) elseStatement).generateCode(out);
        out.println(done);
    }

}
