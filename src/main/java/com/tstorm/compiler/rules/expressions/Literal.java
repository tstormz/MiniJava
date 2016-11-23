package com.tstorm.compiler.rules.expressions;

import com.tstorm.compiler.rules.Type;
import com.tstorm.compiler.typechecker.ExpressionVisitor;

/**
 * Created by tstorm on 11/1/16.
 */
public class Literal implements Expression {

    private String value;

    public Literal(String value) {
        this.value = value;
    }

    public String toString() {
        return value;
    }

    @Override
    public Type accept(ExpressionVisitor v) {
        return v.visit(this);
    }

    public Type getType() {
        switch (value) {
            case "true":
            case "false":
                return new Type(Type.Primitive.BOOLEAN);
            default:
                return new Type(Type.Primitive.INT);
        }
    }
}
