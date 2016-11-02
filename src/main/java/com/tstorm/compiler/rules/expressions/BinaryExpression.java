package com.tstorm.compiler.rules.expressions;

import com.tstorm.compiler.rules.Type;

/**
 * Created by tstorm on 11/1/16.
 */
public class BinaryExpression implements Expression {

    private Expression left, right;
    private String op;

    public BinaryExpression(Expression left, String op, Expression right) {
        this.left = left;
        this.right = right;
        this.op = op;
    }

    @Override
    public Type resolveToType() {
        return null;
    }

    public String toString() {
        return left.toString() + op + right.toString();
    }

}
