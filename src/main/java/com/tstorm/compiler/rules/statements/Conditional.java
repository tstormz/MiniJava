package com.tstorm.compiler.rules.statements;

import com.tstorm.compiler.assembler.Assembler;
import com.tstorm.compiler.rules.OptionalType;
import com.tstorm.compiler.rules.Variable;
import com.tstorm.compiler.rules.expressions.Expression;
import com.tstorm.compiler.rules.expressions.Identifier;
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
        Assembler expr = (Assembler) expression;
        expr.generateCode(out);
        Label elseStmt = generateLabel();
        if (isOptional(expr)) {
            out.print("ifnull ");
        } else {
            out.print("ifeq ");
        }
        out.println(elseStmt.getLabelUse() + " ; goto else");
        ((Assembler) ifStatement).generateCode(out);
        Label done = generateLabel();
        out.println("goto " + done.getLabelUse() + " ; goto done");
        out.println(elseStmt.getLabel());
        ((Assembler) elseStatement).generateCode(out);
        out.println(done.getLabel());
    }

    private boolean isOptional(Assembler expr) {
        if (expr instanceof Identifier) {
            Identifier id = (Identifier) expr;
            if (id.getVariable().isPresent()) {
                Variable v = id.getVariable().get();
                return v.getType() instanceof OptionalType;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

}
