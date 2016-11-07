package com.tstorm.compiler.typechecker;

import com.tstorm.compiler.rules.Klass;
import com.tstorm.compiler.rules.Method;
import com.tstorm.compiler.rules.Type;
import com.tstorm.compiler.rules.Variable;
import com.tstorm.compiler.rules.expressions.*;
import com.tstorm.compiler.visitors.GoalVisitor;

import java.util.Optional;
import java.util.Stack;

/**
 * Created by tstorm on 11/2/16.
 */
public class ExpressionTypeChecker extends ExpressionVisitor {

    private SymbolTable symbolTable;
    private Method currentMethod;
    private Stack<Klass> anonymousClasses = new Stack<>();

    public ExpressionTypeChecker(SymbolTable table) {
        this.symbolTable = table;
    }

    public void setCurrentMethod(Method m) {
        this.currentMethod = m;
    }

    @Override
    public Type visit(ArrayLength expr) {
        return null;
    }

    @Override
    public Type visit(ArrayRef expr) {
        return null;
    }

    @Override
    public Type visit(BinaryExpression expr) {
        Type left = expr.getLeft().accept(this);
        Type right = expr.getRight().accept(this);
        System.out.println("left: " + left);
        System.out.println("right: " + right);
        switch (expr.getOperator()) {
            case "+":
            case "-":
                if (left == Type.INT && right == Type.INT) {
                    return Type.INT;
                } else {
                    return Type.BAD_TYPE;
                }
            case "<":
                if (left == Type.INT && right == Type.INT) {
                    return Type.BOOLEAN;
                } else {
                    return Type.BAD_TYPE;
                }
            case "&&":
                if (left == Type.BOOLEAN && right == Type.BOOLEAN) {
                    return Type.BOOLEAN;
                } else {
                    return Type.BAD_TYPE;
                }
        }
        return Type.BAD_TYPE;
    }

    @Override
    public Type visit(Complement expr) {
        return null;
    }

    @Override
    public Type visit(DefaultExpression expr) {
        System.out.println("default expression");
        return Type.ARRAY;
    }

    @Override
    public Type visit(Identifier expr) {
        System.out.print("identifier ");
        Type t = symbolTable.getVarType(expr.toString());
        if (t != null) {
            System.out.println("variable");
            return t;
        }
        t = symbolTable.getMethodType(expr.toString());
        if (t != null) {
            System.out.println("method");
            return t;
        }
        System.out.println();
        return Type.BAD_TYPE;
    }

    @Override
    public Type visit(Instance expr) {
        System.out.println("instance");
        return null;
    }

    @Override
    public Type visit(Literal expr) {
        System.out.println("literal");
        return expr.getType();
    }

    private boolean anonymousFlag = false;

    @Override
    public Type visit(MethodCall expr) {
        System.out.println("method call ");
        for (Expression e : expr.getArgs()) {
            expr.addArgType(e.accept(this));
        }
        if (expr.getCaller() instanceof DefaultExpression) {
            System.out.println("ANONYMOUS: " + expr.toString());
            anonymousFlag = true;
            Type caller = expr.getCaller().accept(this);
            if (!anonymousClasses.isEmpty()) {
                confirmMethodExists(Optional.of(anonymousClasses.pop()), expr);
            }
            // TODO introduce an anonymous return type as the caller
            return caller;
        } else if (expr.getCaller() instanceof Identifier) {
            if (resolveMethodCall(expr)) {
                return Type.CLASS;
            } else {
                anonymousFlag = false;
                return Type.BAD_TYPE;
            }
        } else {
            System.err.println("err");
            return Type.BAD_TYPE;
        }
    }

    private boolean resolveMethodCall(MethodCall methodCall) {
        String callerId = methodCall.getCaller().toString();
        Optional<Variable> v = currentMethod.findVariable(callerId);
        if (v.isPresent() && v.get().getClassType().isPresent()) {
            return confirmMethodExists(GoalVisitor.findClass(v.get().getClassType().get()), methodCall);
        } else {
            System.err.println("could not resolve id " + callerId + " or of primitive type");
            return false;
        }
    }

    private boolean confirmMethodExists(Optional<Klass> klass, MethodCall methodCall) {
        if (klass.isPresent()) {
            String methodId = methodCall.getMethodName();
            if (klass.get().hasMethod(methodId)) {
                System.out.println("Found! " + methodId);
                return verifyMethodSignature(klass.get().getMethodSet().get(methodId), methodCall);
            } else {
                System.err.println("no " + methodId + " method found for " + methodCall.getCaller().toString());
                return false;
            }
        } else {
            System.err.println("class type not present for " + methodCall.getCaller().toString());
            return false;
        }
    }

    private boolean verifyMethodSignature(Method method, MethodCall methodCall) {
        for (int i = 0; i < methodCall.getArgsType().size(); i++) {
            Type param = method.getParameters().get(i).getType();
            Type arg = methodCall.getArgsType().get(i);
            if (param != arg) {
                System.err.println("found argument of type " + arg + ", expecting " + param );
                return false;
            }
        }
        if (anonymousFlag) {
            pushAnonymousClass(method);
        }
        return true;
    }

    private void pushAnonymousClass(Method method) {
        anonymousFlag = false;
        if (method.getClassReturnType().isPresent()) {
            Optional<Klass> anonymousClass = GoalVisitor.findClass(method.getClassReturnType().get().toString());
            if (anonymousClass.isPresent()) {
                anonymousClasses.push(anonymousClass.get());
            } else {
                // TODO provide error msg

            }
        } else {
            // TODO provide error msg
        }
    }

    /**
     * Connects the Default Expression's nested expression
     *
     * @param expr default expression
     * @return the result of visiting the nested expression
     */
    @Override
    public Type visit(Expression expr) {
        System.out.println("...");
        return expr.accept(this);
    }

}
