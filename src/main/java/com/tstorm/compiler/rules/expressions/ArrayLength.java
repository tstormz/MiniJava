package com.tstorm.compiler.rules.expressions;

import com.tstorm.compiler.assembler.Assembler;
import com.tstorm.compiler.rules.Type;
import com.tstorm.compiler.typechecker.ExpressionVisitor;

import java.io.PrintWriter;

/**
 * Created by tstorm on 11/2/16.
 */
public class ArrayLength extends Assembler implements Expression {

    private Expression array;

    public ArrayLength(Expression array) {
        this.array = array;
    }

    public Expression getArray() {
        return array;
    }

    public String toString() {
        return array.toString() + ".length";
    }

    @Override
    public Type accept(ExpressionVisitor v) {
        return v.visit(this);
    }

    @Override
    public void generateCode(PrintWriter out) {
        ((Assembler) array).generateCode(out);
        out.println("arraylength");
    }
}
