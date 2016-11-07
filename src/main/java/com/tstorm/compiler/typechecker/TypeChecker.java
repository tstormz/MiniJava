package com.tstorm.compiler.typechecker;

import com.tstorm.compiler.rules.Klass;
import com.tstorm.compiler.rules.Method;
import com.tstorm.compiler.rules.Type;
import com.tstorm.compiler.rules.statements.*;

/**
 * Created by tstorm on 11/2/16.
 */
public class TypeChecker extends Visitor {

    private ExpressionTypeChecker expressionTypeChecker;

    public TypeChecker(Klass k) {
        SymbolTable symbolTable = new SymbolTable(k);
        expressionTypeChecker = new ExpressionTypeChecker(symbolTable);
        for (Method m : k.getMethodSet().values()) {
            expressionTypeChecker.setCurrentMethod(m);
            for (Statement stat : m.getBody()) {
                stat.accept(this);
            }
        }
    }

    @Override
    public void visit(Conditional statement) {
        System.out.println("conditional");
        Type t = statement.getExpression().accept(expressionTypeChecker);
        System.out.println(t.toString());
        if (t.is(Type.Primitive.BOOLEAN)) {
            System.err.println("expected boolean expression");
        }
        statement.getIf().accept(this);
        statement.getElse().accept(this);
    }

    @Override
    public void visit(Assignment statement) {
        System.out.println("assignment");
        statement.getExpression().accept(expressionTypeChecker);
    }

    @Override
    public void visit(ElementAssignment statement) {
        System.out.println("element assignemnt");
    }

    @Override
    public void visit(Loop statement) {
        System.out.println("loop");
        statement.getExpression().accept(expressionTypeChecker);
        statement.getStatement().accept(this);
    }

    @Override
    public void visit(ReturnStatement statement) {
        System.out.println("return");
    }

    @Override
    public void visit(DefaultStatement statement) {
        System.out.println("nested statement");
        for (Statement s : statement.getNestedStatements()) {
            s.accept(this);
        }
    }

}
