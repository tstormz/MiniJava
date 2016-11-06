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

    public Expression getCaller() {
        return caller;
    }

    public List<Expression> getArgs() {
        return args;
    }

    public String getMethodName() {
        return methodName;
    }

    public String getCallerId(Expression e) {
        if (e instanceof Identifier /*|| e instanceof Instance*/) {
            return e.toString();
        } else if (e instanceof MethodCall) {
            return getCallerId(((MethodCall) e).caller);
        } else if (e instanceof DefaultExpression) {
            if (((DefaultExpression) e).getExpression().isPresent()) {
                return getCallerId(((DefaultExpression) e).getExpression().get());
            } else {
               return "ERROR";
            }
        } else {
            return "ERROR";
        }
    }

    public String toString() {
        String s = "";
        for (Expression arg : args) {
            s += ", " + arg.toString();
        }
        String args = this.args.isEmpty() ? "" : s.substring(2);
        s = caller.toString() + "." + methodName + "(" + args;
        return s + ")";
    }

    @Override
    public Type accept(ExpressionVisitor v) {
        return v.visit(this);
    }
}
