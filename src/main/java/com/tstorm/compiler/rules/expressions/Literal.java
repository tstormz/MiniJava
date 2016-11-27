package com.tstorm.compiler.rules.expressions;

import com.tstorm.compiler.assembler.Assembler;
import com.tstorm.compiler.rules.Type;
import com.tstorm.compiler.typechecker.ExpressionVisitor;

import java.io.PrintWriter;

/**
 * Created by tstorm on 11/1/16.
 */
public class Literal extends Assembler implements Expression {

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

    @Override
    public void generateCode(PrintWriter out) {
        if (getType().is(Type.Primitive.INT)) {
            out.println("ldc " + value);
        } else if (getType().is(Type.Primitive.BOOLEAN)) {
            if (value.equals("true")) {
                out.println("bipush 1");
            } else {
                out.println("bipush 0");
            }
        }
    }
}
