package com.tstorm.compiler.rules.expressions;

import com.tstorm.compiler.assembler.Assembler;
import com.tstorm.compiler.rules.Type;
import com.tstorm.compiler.typechecker.ExpressionVisitor;

import java.io.PrintWriter;

/**
 * Created by tstorm on 11/2/16.
 */
public class Complement extends Assembler implements Expression {

    private final Expression expression;

    public Complement(Expression expression) {
        this.expression = expression;
    }

    public Expression getExpression() {
        return expression;
    }

    @Override
    public Type accept(ExpressionVisitor v) {
        return v.visit(this);
    }

    @Override
    public void generateCode(PrintWriter out) {
        Assembler expr = (Assembler) expression;
        expr.generateCode(out);
        out.println("bipush 1 ; complement");
        out.println("iadd");
        out.println("bipush 2");
        out.println("irem");
    }
}
