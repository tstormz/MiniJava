package com.tstorm.compiler.rules.expressions;

import com.tstorm.compiler.rules.Type;
import com.tstorm.compiler.typechecker.ExpressionVisitor;

/**
 * Created by tstorm on 11/2/16.
 */
public class Instance extends Expression {

    private Expression arrayIndex;
    private String className;

    public Instance(Expression expression) {
        this.arrayIndex = expression;
    }

    public Instance(String className) {
        this.className = className;
    }

    public String getClassName() {
        return className;
    }

    @Override
    public Type accept(ExpressionVisitor v) {
        v.visit(this);
        return null;
    }
}
