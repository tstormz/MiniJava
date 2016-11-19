package com.tstorm.compiler.typechecker;

import com.tstorm.compiler.rules.*;
import com.tstorm.compiler.rules.expressions.Expression;
import com.tstorm.compiler.rules.statements.*;
import com.tstorm.compiler.visitors.GoalVisitor;

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
            for (Method method : methods) {
                expressionTypeChecker.setCurrentMethod(method);
                readBody(method);
            }
        }
    }

    /**
     * Type checks the body of the method, if the method signature has no conflict
     * with any method declarations in it's super classes
     *
     * @param method the method under inspection
     */
    private void readBody(Method method) {
        for (Variable param : method.getParameters()) {
            param.initialize();
        }
        if (!hasReturnTypeClash(currentKlass.getParent(), method)) {
            for (Statement stmt : method.getBody()) {
                stmt.accept(this);
            }
        } else {
            System.err.println("Return type clashes with another signature");
        }
    }

    /**
     * Checks for a matching method name and parameter list, but conflicting return type
     *
     * @param klass the parent klass
     * @param method the method under inspection
     * @return true if there is a conflict on only the return type
     */
    private boolean hasReturnTypeClash(Optional<Klass> klass, Method method) {
        if (klass.isPresent()) {
            List<Method> methods = klass.get().getMethodSet().get(method.getMethodName());
            boolean conflict = hasConflict(Optional.ofNullable(methods), method);
            return conflict || hasReturnTypeClash(klass.get().getParent(), method);
        } else {
            return false;
        }
    }

    /**
     * Helper method to compare each method against previously defined methods
     *
     * @param methods the previously defined methods of the same name as method
     * @param method the method under inspection
     * @return true if there is a conflict
     */
    private boolean hasConflict(Optional<List<Method>> methods, Method method) {
        if (methods.isPresent()) {
            for (Method m : methods.get()) {
                if (hasMatchingParams(method, m) && !hasMatchingReturnType(method, m)) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Compares the return type of two methods
     *
     * @param m1 the method being defined
     * @param m2 the previously defined method
     * @return true if the return types match
     */
    private boolean hasMatchingReturnType(Method m1, Method m2) {
        return m1.getReturnType().equals(m2.getReturnType());
    }

    /**
     * Compares the parameters one by one
     *
     * @param m1 the method being defined
     * @param m2 the previously defined method
     * @return true if each parameter has the same type
     */
    private boolean hasMatchingParams(Method m1, Method m2) {
        if (m1.getParameters().size() == m2.getParameters().size()) {
            for (int i = 0; i < m1.getParameters().size(); i++) {
                Type t1 = m1.getParameters().get(i).getType();
                Type t2 = m2.getParameters().get(i).getType();
                if (!t1.equals(t2)) {
                    return false;
                }
            }
        } else {
            return false;
        }
        return true;
    }

    @Override
    public void visit(Conditional statement) {
        Type t = statement.getExpression().accept(expressionTypeChecker);
        if (!t.is(Type.Primitive.BOOLEAN) && !(t instanceof OptionalType)) {
            System.err.println(Conditional.ERROR);
            System.out.println(t.toString());
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
            if (checkType(local.get(), statement.getExpression())) {
                local.get().initialize();
            } else {
                System.err.println(String.format(Assignment.ERROR, srcVar, local.get().getType()));
            }
            return;
        }
        // check fields
        Optional<Variable> field = currentKlass.getField(srcVar);
        if (field.isPresent()) {
            if (checkType(field.get(), statement.getExpression())) {
                field.get().initialize();
            } else {
                System.err.println(String.format(Assignment.ERROR, srcVar, field.get().getType()));
            }
            return;
        }
        // check for inherited field
        Optional<Variable> inherited = findInheritedField(Optional.of(currentKlass), srcVar);
        if (inherited.isPresent()) {
            if (checkType(inherited.get(), statement.getExpression())) {
                inherited.get().initialize();
            } else {
                System.err.println(String.format(Assignment.ERROR, srcVar, inherited.get().getType()));
            }
        } else {
            System.err.println(String.format(Variable.ASSIGN_ERROR, statement.getSrcVariableName()));
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

    private boolean checkType(Variable variable, Expression assignment) {
        Type assignType = assignment.accept(expressionTypeChecker);
        if(assignType instanceof OptionalType && !(variable.getType() instanceof OptionalType)) {
            System.err.println(String.format(Assignment.ERROR, variable.getVariableName(), variable.getType().toString()));
            return false;
        } else if (variable.getType().equals(assignType)) {
            return true;
        } else if(assignType.getClassName().isPresent()) {
            Optional<Klass> k = GoalVisitor.findClass(assignType.getClassName().get());
            if (k.isPresent() && k.get().hasParent()) {
                return checkSuperClasses(variable, k.get().getParent());
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    private boolean checkSuperClasses(Variable variable, Optional<Klass> klass) {
        if (klass.isPresent()) {
            if (variable.getType().equals(new Type(klass.get().getClassName()))) {
                return true;
            } else if (klass.get().hasParent()) {
                return checkSuperClasses(variable, klass.get().getParent());
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    @Override
    public void visit(ElementAssignment statement) {
        String arrayName = statement.getDestination();
        Type assignment = statement.getAssignment().accept(expressionTypeChecker);
        Type index = statement.getIndex().accept(expressionTypeChecker);
        Optional<Variable> local = expressionTypeChecker.getCurrentMethod().findVariable(arrayName);
        if (local.isPresent()) {
            if (local.get().isInitialized()) {
                requireInt(assignment, ElementAssignment.ASSIGN_ERROR);
                requireInt(index, ElementAssignment.ASSIGN_ERROR);
            } else {
                System.err.println("array not initialized");
            }
            return;
        }
        Optional<Variable> field = currentKlass.getField(arrayName);
        if (field.isPresent()) {
            if (field.get().isInitialized()) {
                requireInt(assignment, ElementAssignment.ASSIGN_ERROR);
                requireInt(index, ElementAssignment.ASSIGN_ERROR);
            } else {
                System.err.println("array not initialized");
            }
            return;
        }
        Optional<Variable> inherited = findInheritedField(Optional.of(currentKlass), arrayName);
        if (inherited.isPresent()) {
            if (inherited.get().isInitialized()) {
                requireInt(assignment, ElementAssignment.ASSIGN_ERROR);
                requireInt(index, ElementAssignment.ASSIGN_ERROR);
            } else {
                System.err.println("array not initialized");
            }
        } else {
            System.err.println("cant find array");
        }
    }

    private void requireInt(Type type, String errorMsg) {
        if (!type.is(Type.Primitive.INT)) {
            System.err.println(String.format(errorMsg, type.toString()));
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
        if (returnExpression instanceof OptionalType) {
            returnExpression = unwrap((OptionalType) returnExpression);
            if (expectedReturnType instanceof OptionalType) {
                expectedReturnType = unwrap((OptionalType) expectedReturnType);
            } else {
                System.err.println("can't return an optional type when expecting a non-optional");
            }
        }
        if (!returnExpression.equals(expectedReturnType)) {
            System.err.println(String.format(ReturnStatement.ERROR, expectedReturnType, returnExpression));
        }
    }

    private Type unwrap(OptionalType optionalType) {
        if (optionalType.getClassName().isPresent()) {
            return new Type(optionalType.getClassName().get());
        } else {
            return new Type(optionalType.getPrimitive());
        }
    }

    @Override
    public void visit(DefaultStatement statement) {
        for (Statement s : statement.getNestedStatements()) {
            s.accept(this);
        }
    }

}
