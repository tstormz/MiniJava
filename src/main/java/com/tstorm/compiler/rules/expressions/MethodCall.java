package com.tstorm.compiler.rules.expressions;

import com.tstorm.compiler.rules.Type;

import java.util.List;

/**
 * Created by tstorm on 11/2/16.
 */
public class MethodCall implements Expression {

    private final Expression caller;
    private final String methodName;
    private final List<Expression> args;

    public MethodCall(Expression caller, String methodName, List<Expression> args) {
        this.caller = caller;
        this.methodName = methodName;
        this.args = args;
    }

    @Override
    public Type resolveToType() {
        return null;
    }

    public String toString() {
        String s = "";
        for (Expression arg : args) {
            s += ", " + arg.toString();
        }
        s = caller.toString() + "." + methodName + "(" + s.substring(2);
        return s + ")";
    }

}
