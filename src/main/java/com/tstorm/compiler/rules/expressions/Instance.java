package com.tstorm.compiler.rules.expressions;

import com.tstorm.compiler.assembler.Assembler;
import com.tstorm.compiler.rules.Type;
import com.tstorm.compiler.typechecker.ExpressionVisitor;

import java.io.PrintWriter;
import java.util.Optional;

/**
 * Created by tstorm on 11/2/16.
 */
public class Instance extends Assembler implements Expression {

    private Expression arrayIndex;
    private Optional<String> className = Optional.empty();

    public Instance(Expression expression) {
        this.arrayIndex = expression;
    }

    public Instance(String className) {
        this.className = Optional.ofNullable(className);
    }

    public Optional<String> getClassName() {
        return className;
    }

    public Expression getArrayIndex() {
        return arrayIndex;
    }

    @Override
    public Type accept(ExpressionVisitor v) {
        return v.visit(this);
    }

    public String toString() {
        if (className.isPresent()) {
            return className.get();
        } else {
            return arrayIndex.toString();
        }
    }

    @Override
    public void generateCode(PrintWriter out) {
        if (className.isPresent()) {
            out.println("new " + className.get());
            out.println("dup");
            out.println("invokespecial " + className.get() + "/<init>()V");
        }
    }
}
