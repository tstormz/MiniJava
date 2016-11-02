package com.tstorm.compiler.rules.expressions;

import com.tstorm.compiler.rules.Type;

/**
 * Created by tstorm on 11/2/16.
 */
public class ArrayRef implements Expression {

    private Expression array, index;

    public ArrayRef(Expression array, Expression index) {
        this.array = array;
        this.index = index;
    }

    @Override
    public Type resolveToType() {
        return null;
    }

    public String toString() {
        return array.toString() + "[" + index.toString() + "]";
    }

}
