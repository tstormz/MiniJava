package com.tstorm.compiler.rules.expressions;

import com.tstorm.compiler.assembler.Assembler;
import com.tstorm.compiler.rules.Type;
import com.tstorm.compiler.typechecker.ExpressionVisitor;

import java.io.PrintWriter;

/**
 * Created by tstorm on 11/2/16.
 */
public class ArrayRef extends Assembler implements Expression {

    private Expression array, index;

    public ArrayRef(Expression array, Expression index) {
        this.array = array;
        this.index = index;
    }

    public Expression getArray() {
        return array;
    }

    public Expression getIndex() {
        return index;
    }

    public String toString() {
        return array.toString() + "[" + index.toString() + "]";
    }

    @Override
    public Type accept(ExpressionVisitor v) {
        return v.visit(this);
    }

    @Override
    public void generateCode(PrintWriter out) {
        ((Assembler) array).generateCode(out);
        ((Assembler) index).generateCode(out);
        out.println("iaload");
    }
}
