package com.tstorm.compiler.rules.statements;

import com.tstorm.compiler.assembler.Assembler;
import com.tstorm.compiler.rules.expressions.Expression;
import com.tstorm.compiler.typechecker.ExpressionTypeChecker;
import com.tstorm.compiler.typechecker.Visitor;

import java.io.PrintWriter;

/**
 * Created by tstorm on 11/23/16.
 */
public class Print extends Assembler implements Statement {

    private final Expression expression;

    public Print(Expression expression) {
        this.expression = expression;
    }

    public Expression getExpression() {
        return expression;
    }

    public String toString() {
        return "System.out.println(" + expression.toString() + ");";
    }

    @Override
    public boolean accept(Visitor v) {
        return v.visit(this);
    }

    @Override
    public void generateCode(PrintWriter out) {
        out.println("getstatic java/lang/System/out Ljava/io/PrintStream;");
        ((Assembler) expression).generateCode(out);
        out.println("invokestatic java/lang/String/valueOf(I)Ljava/lang/String;");
        out.println("invokevirtual java/io/PrintStream/println(Ljava/lang/String;)V");
    }
}
