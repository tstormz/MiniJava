package com.tstorm.compiler.rules.expressions;

import com.tstorm.compiler.rules.Type;
import com.tstorm.compiler.typechecker.ExpressionVisitor;

import java.util.Optional;

/**
 * Created by tstorm on 11/2/16.
 */
public class Instance extends Expression {

    private Expression arrayIndex;
    private Optional<String> className;

    public Instance(Expression expression) {
        this.arrayIndex = expression;
    }

    public Instance(String className) {
        this.className = Optional.ofNullable(className);
    }

    public Optional<String> getClassName() {
        return className;
    }

    @Override
    public Type accept(ExpressionVisitor v) {
        return v.visit(this);
    }
}
