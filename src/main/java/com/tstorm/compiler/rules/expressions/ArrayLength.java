package com.tstorm.compiler.rules.expressions;

import com.tstorm.compiler.rules.Type;
import com.tstorm.compiler.typechecker.ExpressionVisitor;

/**
 * Created by tstorm on 11/2/16.
 */
public class ArrayLength extends Expression {

    private Expression array;

    public ArrayLength(Expression array) {
        this.array = array;
    }

    public String toString() {
        return array.toString() + ".length";
    }

    @Override
    public Type accept(ExpressionVisitor v) {
        v.visit(this);
        return null;
    }
}