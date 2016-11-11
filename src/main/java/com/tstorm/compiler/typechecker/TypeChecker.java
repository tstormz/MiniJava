package com.tstorm.compiler.typechecker;

import com.tstorm.compiler.rules.Klass;
import com.tstorm.compiler.rules.Method;
import com.tstorm.compiler.rules.Type;
import com.tstorm.compiler.rules.Variable;
import com.tstorm.compiler.rules.statements.*;

import java.util.Optional;

/**
 * Created by tstorm on 11/2/16.
 */
public class TypeChecker extends Visitor {

    private ExpressionTypeChecker expressionTypeChecker;
    private SymbolTable symbolTable;

    public TypeChecker(Klass k) {
        symbolTable = new SymbolTable(k);
        expressionTypeChecker = new ExpressionTypeChecker(k, symbolTable);
        for (Method m : k.getMethodSet().values()) {
            expressionTypeChecker.setCurrentMethod(m);
            for (Statement stat : m.getBody()) {
                stat.accept(this);
            }
        }
    }

    @Override
    public void visit(Conditional statement) {
        Type t = statement.getExpression().accept(expressionTypeChecker);
        System.out.println(t.toString());
        if (!t.is(Type.Primitive.BOOLEAN)) {
            System.err.println("Error: conditional expression must be a boolean");
        }
        statement.getIf().accept(this);
        statement.getElse().accept(this);
    }

    @Override
    public void visit(Assignment statement) {
        String srcVar = statement.getSrcVariableName();
        // check fields
        Optional<Type> typeOfVariable = symbolTable.getVarType(srcVar);
        if (typeOfVariable.isPresent()) {
            Type typeOfAssignment = statement.getExpression().accept(expressionTypeChecker);
            if (!typeOfVariable.get().equals(typeOfAssignment)) {
                System.err.println(String.format("Assignment Error: '%s' is expecting type %s",
                        srcVar, typeOfVariable.get()));
            }
            return;
        }
        // check local variables
        Optional<Variable> typeOfField = expressionTypeChecker.getCurrentMethod().findVariable(srcVar);
        if (typeOfField.isPresent()) {
            Type typeOfAssignment = statement.getExpression().accept(expressionTypeChecker);
            if (!typeOfField.get().getType().equals(typeOfAssignment)) {
                System.err.println(String.format("Assignment Error: '%s' is expecting type %s",
                        srcVar, typeOfField.get()));
            }
            return;
        }
        // check for inherited field
        Optional<Type> inheritedField = findInheritedField(Optional.of(expressionTypeChecker.getCurrentKlass()), srcVar);
        if (inheritedField.isPresent()) {
            Type typeOfAssignment = statement.getExpression().accept(expressionTypeChecker);
            if (!inheritedField.get().equals(typeOfAssignment)) {
                System.err.println(String.format("Assignment Error: '%s' is expecting type %s",
                        srcVar, inheritedField.get()));
            }
        } else {
            System.err.println("Assignment Error: cannot resolve type for " + statement.getSrcVariableName());
        }
    }

    private Optional<Type> findInheritedField(Optional<Klass> klass, String srcVar) {
        if (klass.isPresent()) {
            if (klass.get().hasField(srcVar)) {
                return Optional.of(klass.get().getFields().get(srcVar));
            } else {
                return findInheritedField(klass.get().getParent(), srcVar);
            }
        } else {
            return Optional.empty();
        }
    }

    @Override
    public void visit(ElementAssignment statement) {
        System.out.println("element assignemnt");
        Type assignment = statement.getAssignment().accept(expressionTypeChecker);
        Type index = statement.getIndex().accept(expressionTypeChecker);
        if (!index.is(Type.Primitive.INT)) {
            System.err.println(String.format("Assignment Error: Incompatible types, expected 'int' found '%s'", index.toString()));
        } else if (!assignment.is(Type.Primitive.INT)) {
            System.err.println(String.format("Assignment Error: Incompatible types, expected 'int' found '%s'", assignment.toString()));
        }
    }

    @Override
    public void visit(Loop statement) {
        statement.getExpression().accept(expressionTypeChecker);
        statement.getStatement().accept(this);
    }

    @Override
    public void visit(ReturnStatement statement) {
        Type returnExpression = statement.getExpression().accept(expressionTypeChecker);
        Type expectedReturnType  = expressionTypeChecker.getCurrentMethod().getReturnType();
        if (!returnExpression.equals(expectedReturnType)) {
            System.err.println(String.format("Return error: expecting '%s' but found '%s'",
                    expectedReturnType, returnExpression));
        }
    }

    @Override
    public void visit(DefaultStatement statement) {
        for (Statement s : statement.getNestedStatements()) {
            s.accept(this);
        }
    }

}
