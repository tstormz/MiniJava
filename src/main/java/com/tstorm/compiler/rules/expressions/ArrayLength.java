package com.tstorm.compiler.rules.expressions;

import com.tstorm.compiler.rules.Type;

/**
 * Created by tstorm on 11/2/16.
 */
public class ArrayLength implements Expression {

    private Expression array;

    public ArrayLength(Expression array) {
        this.array = array;
    }

    @Override
    public Type resolveToType() {
        return null;
    }

    public String toString() {
        return array.toString() + ".length";
    }

}
