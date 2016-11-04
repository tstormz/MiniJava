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
        System.out.println(expr.getLeft());
        System.out.println(expr.getRight());
        return Type.BAD_TYPE;
    }

    @Override
    public Type visit(Complement expr) {
        return null;
    }

    @Override
    public Type visit(DefaultExpression expr) {
        return null;
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
        return null;
    }

}
