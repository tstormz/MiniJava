package com.tstorm.compiler.rules.expressions;

import com.tstorm.compiler.rules.Type;
import com.tstorm.compiler.typechecker.ExpressionVisitor;

/**
 * Created by tstorm on 11/1/16.
 */
public class BinaryExpression extends Expression {

    private Expression left, right;
    private String op;

    public BinaryExpression(Expression left, String op, Expression right) {
        this.left = left;
        this.right = right;
        this.op = op;
    }

    public Expression getLeft() {
        return left;
    }

    public Expression getRight() {
        return right;
    }

    public String getOperator() {
        return op;
    }

    public String toString() {
        return left.toString() + op + right.toString();
    }

    @Override
    public Type accept(ExpressionVisitor v) {
        return v.visit(this);
    }
}
