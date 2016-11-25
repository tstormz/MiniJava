package com.tstorm.compiler.rules.expressions;

import com.tstorm.compiler.assembler.Assembler;
import com.tstorm.compiler.rules.Type;
import com.tstorm.compiler.typechecker.ExpressionVisitor;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by tstorm on 11/2/16.
 */
public class MethodCall extends Assembler implements Expression {

    private final Expression caller;
    private final String methodName;
    private final List<Expression> args;
    private final List<Type> argsType = new ArrayList<>();
    private int lineNumber;

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

    public List<Type> getArgsType() {
        return argsType;
    }

    public void addArgType(Type t) {
        argsType.add(t);
    }

    public void setLineNumber(int line) {
        lineNumber = line;
    }

    public int getLineNumber() {
        return lineNumber;
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

    @Override
    public void generateCode(PrintWriter out) {

    }
}
