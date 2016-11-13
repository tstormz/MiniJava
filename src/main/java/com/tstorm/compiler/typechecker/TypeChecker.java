package com.tstorm.compiler.typechecker;

import com.tstorm.compiler.rules.Klass;
import com.tstorm.compiler.rules.Method;
import com.tstorm.compiler.rules.Type;
import com.tstorm.compiler.rules.Variable;
import com.tstorm.compiler.rules.expressions.Expression;
import com.tstorm.compiler.rules.statements.*;

import java.util.List;
import java.util.Optional;

/**
 * Created by tstorm on 11/2/16.
 */
public class TypeChecker extends Visitor {

    private ExpressionTypeChecker expressionTypeChecker;
    private Klass currentKlass;

    public TypeChecker(Klass k) {
        this.currentKlass = k;
        expressionTypeChecker = new ExpressionTypeChecker(k);
        for (List<Method> methods : k.getMethodSet().values()) {
            for (Method m : methods) {
                expressionTypeChecker.setCurrentMethod(m);
                for (Statement stat : m.getBody()) {
                    stat.accept(this);
                }
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
        // check local variables
        Optional<Variable> local = expressionTypeChecker.getCurrentMethod().findVariable(srcVar);
        if (local.isPresent()) {
            if (checkType(local.get(), statement.getExpression(), srcVar)) {
                local.get().initialize();
            }
            return;
        }
        // check fields
        Optional<Variable> field = currentKlass.getField(srcVar);
        if (field.isPresent()) {
            if (checkType(field.get(), statement.getExpression(), srcVar)) {
                field.get().initialize();
            }
            return;
        }
        // check for inherited field
        Optional<Variable> inherited = findInheritedField(Optional.of(currentKlass), srcVar);
        if (inherited.isPresent()) {
            if (checkType(inherited.get(), statement.getExpression(), srcVar)) {
                inherited.get().initialize();
            }
        } else {
            System.err.println("Assignment Error: cannot resolve type for " + statement.getSrcVariableName());
        }
    }

    private Optional<Variable> findInheritedField(Optional<Klass> klass, String srcVar) {
        if (klass.isPresent()) {
            if (klass.get().hasField(srcVar)) {
                return klass.get().getField(srcVar);
            } else {
                return findInheritedField(klass.get().getParent(), srcVar);
            }
        } else {
            return Optional.empty();
        }
    }

    private boolean checkType(Variable variable, Expression assignment, String srcVar) {
        if (!variable.getType().equals(assignment.accept(expressionTypeChecker))) {
            System.err.println(String.format(Assignment.ERROR, srcVar, variable.getType()));
            return false;
        }
        return true;
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
        Type expectedReturnType = expressionTypeChecker.getCurrentMethod().getReturnType();
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
