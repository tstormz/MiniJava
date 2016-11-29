package com.tstorm.compiler.rules.expressions;

import com.tstorm.compiler.assembler.Assembler;
import com.tstorm.compiler.rules.Klass;
import com.tstorm.compiler.rules.Method;
import com.tstorm.compiler.rules.Type;
import com.tstorm.compiler.rules.Variable;
import com.tstorm.compiler.typechecker.ExpressionVisitor;
import com.tstorm.compiler.visitors.GoalVisitor;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Created by tstorm on 11/2/16.
 */
public class MethodCall extends Assembler implements Expression {

    private final Expression caller;
    private Optional<Klass> owner;
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

    public void setKlass(Klass klass) {
        owner = Optional.of(klass);
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
        ((Assembler) caller).generateCode(out);
        for (Expression arg : args) {
            ((Assembler) arg).generateCode(out);
        }
        out.print("invokevirtual ");
        if (owner.isPresent()) {
            methodSignature(out);
        } else {
            System.err.println("Error: Method " + methodName + " doesn't know where he lives");
        }
    }

    private void methodSignature(PrintWriter out) {
        out.print(String.format("%s/%s(", owner.get().getClassName(), methodName));
        List<Method> methods = owner.get().getMethodSet().get(methodName);
        Method method = getMethod(methods, argsType);
        assert method != null;
        for (Variable param : method.getParameters()) {
            out.print(param.getType().toJasmin());
        }
        out.println(")" + returnType());
    }

    private String returnType() {
        Klass owner = this.owner.get();
        Method method = getMethod(owner.getMethodSet().get(methodName), argsType);
        assert method != null;
        return method.getReturnType().toJasmin();
    }

    private Method getMethod(List<Method> methods, List<Type> args) {
        for (Method m : methods) {
            if (args.size() == m.getParameters().size()) {
                if (parametersMatch(m.getParameters(), args)) {
                    return m;
                }
            }
        }
        return null;
    }

    private boolean parametersMatch(List<Variable> params, List<Type> args) {
        boolean match = true;
        for (int i = 0; i < args.size(); i++) {
            match &= checkTypesMatch(params.get(i).getType(), args.get(i));
        }
        return match;
    }

    private boolean checkTypesMatch(Type param, Type arg) {
        if (param.equals(arg)) {
            return true;
        } else if (arg.getClassName().isPresent()) {
            Optional<Klass> argType = GoalVisitor.findClass(arg.getClassName().get());
            if (argType.isPresent() && argType.get().hasParent()) {
                Klass argSuperType = argType.get().getParent().get();
                return checkTypesMatch(param, new Type(argSuperType.getClassName()));
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

}
