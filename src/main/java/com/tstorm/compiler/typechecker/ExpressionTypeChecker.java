package com.tstorm.compiler.typechecker;

import com.tstorm.compiler.rules.Klass;
import com.tstorm.compiler.rules.Method;
import com.tstorm.compiler.rules.Type;
import com.tstorm.compiler.rules.Variable;
import com.tstorm.compiler.rules.expressions.*;
import com.tstorm.compiler.visitors.GoalVisitor;

import java.util.Optional;

/**
 * Created by tstorm on 11/2/16.
 */
public class ExpressionTypeChecker extends ExpressionVisitor {

    private SymbolTable symbolTable;
    private Method currentMethod;

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

    @Override
    public Type visit(MethodCall expr) {
        System.out.println("method call");
        if (expr.getCaller() instanceof Identifier) {
            confirmMethodExists(expr.getCaller().toString(), expr.getMethodName());
        } else {
            confirmMethodExists(expr.getCallerId(expr), expr.getMethodName());
        }
//        expr.getCaller().accept(this);
        for (Expression e : expr.getArgs()) {
            e.accept(this);
        }
        return Type.BAD_TYPE;
    }

    private void confirmMethodExists(String callerId, String methodId) {
//        System.err.println("caller: " + callerId);
        // resolve caller
        Optional<Variable> v = currentMethod.findVariable(callerId);
        if (v.isPresent() && v.get().getClassType().isPresent()) {
//            Optional<Klass> klass = symbolTable.getClassReference(v.get().getClassType().get());
            Optional<Klass> klass = GoalVisitor.findClass(v.get().getClassType().get());
            if (klass.isPresent()) {
                if (klass.get().hasMethod(methodId)) {
                    System.out.println("Found! " + methodId);
                } else {
                    System.err.println("no " + methodId + " method found for " + callerId);
                }
            } else {
                System.err.println("class type not present for " + callerId);
            }
        } else {
            System.err.println("could not resolve id " + callerId + " or of primitive type");
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
