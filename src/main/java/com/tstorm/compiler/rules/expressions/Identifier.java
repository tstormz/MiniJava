package com.tstorm.compiler.rules.expressions;

import com.tstorm.compiler.rules.Type;
import com.tstorm.compiler.typechecker.ExpressionVisitor;

/**
 * Created by tstorm on 11/1/16.
 */
public class Identifier implements Expression {

    private String name;

    public Identifier(String name) {
        this.name = name;
    }

    public String toString() {
        return name;
    }

    @Override
    public Type accept(ExpressionVisitor v) {
        return v.visit(this);
    }
}
