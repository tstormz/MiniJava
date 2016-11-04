package com.tstorm.compiler.rules.expressions;

import com.tstorm.compiler.rules.Type;
import com.tstorm.compiler.typechecker.ExpressionVisitor;

import java.util.List;

/**
 * Created by tstorm on 11/2/16.
 */
public class MethodCall extends Expression {

    private final Expression caller;
    private final String methodName;
    private final List<Expression> args;

    public MethodCall(Expression caller, String methodName, List<Expression> args) {
        this.caller = caller;
        this.methodName = methodName;
        this.args = args;
    }

    public String toString() {
        String s = "";
        for (Expression arg : args) {
            s += ", " + arg.toString();
        }
        s = caller.toString() + "." + methodName + "(" + s.substring(2);
        return s + ")";
    }

    @Override
    public Type accept(ExpressionVisitor v) {
        v.visit(this);
        return null;
    }
}
