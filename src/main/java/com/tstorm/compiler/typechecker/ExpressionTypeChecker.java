package com.tstorm.compiler.typechecker;

import com.tstorm.compiler.rules.Type;
import com.tstorm.compiler.rules.expressions.*;

/**
 * Created by tstorm on 11/2/16.
 */
public class ExpressionTypeChecker extends ExpressionVisitor {

    private SymbolTable symbolTable;

    public ExpressionTypeChecker(SymbolTable table) {
        this.symbolTable = table;
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
        return Type.ARRAY;
    }

    @Override
    public Type visit(Identifier expr) {
        return symbolTable.getType(expr.toString());
    }

    @Override
    public Type visit(Instance expr) {
        return null;
    }

    @Override
    public Type visit(Literal expr) {
        return expr.getType();
    }

    @Override
    public Type visit(MethodCall expr) {
        System.out.println("method call");
        return null;
    }

    /**
     * Connects the Default Expression's nested expression
     *
     * @param expr default expression
     * @return the result of visiting the nested expression
     */
    @Override
    public Type visit(Expression expr) {
        return expr.accept(this);
    }

}
