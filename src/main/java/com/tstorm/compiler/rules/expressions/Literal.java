package com.tstorm.compiler.rules.expressions;

import com.tstorm.compiler.rules.Type;

/**
 * Created by tstorm on 11/1/16.
 */
public class Literal implements Expression {

    private String value;

    public Literal(String value) {
        this.value = value;
    }

    @Override
    public Type resolveToType() {
        return null;
    }

    public String toString() {
        return value;
    }

}
