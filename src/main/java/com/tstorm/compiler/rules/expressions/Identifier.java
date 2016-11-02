package com.tstorm.compiler.rules.expressions;

import com.tstorm.compiler.rules.Type;

/**
 * Created by tstorm on 11/1/16.
 */
public class Identifier implements Expression {

    private String name;

    public Identifier(String name) {
        this.name = name;
    }

    @Override
    public Type resolveToType() {
        return null;
    }

    public String toString() {
        return name;
    }

}
