package com.tstorm.compiler.rules.expressions;

import com.tstorm.compiler.rules.Type;
import com.tstorm.compiler.typechecker.ExpressionVisitor;

/**
 * Created by tstorm on 11/2/16.
 */
public class ArrayRef extends Expression {

    private Expression array, index;

    public ArrayRef(Expression array, Expression index) {
        this.array = array;
        this.index = index;
    }

    public String toString() {
        return array.toString() + "[" + index.toString() + "]";
    }

    @Override
    public Type accept(ExpressionVisitor v) {
        v.visit(this);
        return null;
    }
}
