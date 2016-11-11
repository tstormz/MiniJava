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
    private Klass currentKlass;
    private Method currentMethod;

    public ExpressionTypeChecker(Klass k, SymbolTable table) {
        this.currentKlass = k;
        this.symbolTable = table;
    }

    public void setCurrentMethod(Method m) {
        this.currentMethod = m;
    }

    public Method getCurrentMethod() {
        return currentMethod;
    }

    public Klass getCurrentKlass() {
        return currentKlass;
    };

    @Override
    public Type visit(ArrayLength expr) {
        Type typeOfCaller = expr.getArray().accept(this);
        if (typeOfCaller.is(Type.Primitive.ARRAY)) {
            return new Type(Type.Primitive.INT);
        } else {
            System.err.println("can't call length on a non-array");
            return badType();
        }
    }

    @Override
    public Type visit(ArrayRef expr) {
        return null;
    }

    @Override
    public Type visit(BinaryExpression expr) {
        Type left = expr.getLeft().accept(this);
        Type right = expr.getRight().accept(this);
        switch (expr.getOperator()) {
            case "+":
            case "-":
            case "*":
                if (left.is(Type.Primitive.INT) && right.is(Type.Primitive.INT)) {
                    return new Type(Type.Primitive.INT);
                } else {
                    return badType();
                }
            case "<":
                if (left.is(Type.Primitive.INT) && right.is(Type.Primitive.INT)) {
                    return new Type(Type.Primitive.BOOLEAN);
                } else {
                    return badType();
                }
            case "&&":
                if (left.is(Type.Primitive.BOOLEAN) && right.is(Type.Primitive.BOOLEAN)) {
                    return new Type(Type.Primitive.BOOLEAN);
                } else {
                    return badType();
                }
            default:
                return badType();
        }
    }

    @Override
    public Type visit(Complement expr) {
        Type t = expr.getExpression().accept(this);
        if (t.is(Type.Primitive.BOOLEAN)) {
            return t;
        } else {
            System.err.println(String.format("Error: operator '!' cannot be applied to '%s'", t.toString()));
            return badType();
        }
    }

    @Override
    public Type visit(DefaultExpression expr) {
        System.out.println("default expression");
        // TODO probably not return array
        return new Type("array");
    }

    @Override
    public Type visit(Identifier expr) {
        System.out.print("identifier ");
        // fields
        Optional<Type> t = symbolTable.getVarType(expr.toString());
        if (t.isPresent()) {
            System.out.println("variable");
            return t.get();
        }
        // methods
        t = symbolTable.getMethodType(expr.toString());
        if (t.isPresent()) {
            System.out.println("method");
            return t.get();
        }
        // locals and parameters
        Optional<Variable> v = currentMethod.findVariable(expr.toString());
        if (v.isPresent()) {
            return v.get().getType();
        }
        System.out.println();
        return new Type(Type.Primitive.BAD_TYPE);
    }

    @Override
    public Type visit(Instance expr) {
        System.out.println("instance");
        Optional<String> className = expr.getClassName();
        if (className.isPresent()) {
            return new Type(className.get());
        } else {
            return badType();
        }

    }

    @Override
    public Type visit(Literal expr) {
        return expr.getType();
    }

    /**
     * resolves each argument's type, and reduces an anonymous caller to it's return type
     *
     * @param expr a method call
     * @return the return type of the method
     */
    @Override
    public Type visit(MethodCall expr) {
        for (Expression e : expr.getArgs()) {
            expr.addArgType(e.accept(this));
        }
        if (expr.getCaller() instanceof DefaultExpression) {
            Type caller = expr.getCaller().accept(this);
            if (caller.getClassName().isPresent()) {
                return confirmMethodExists(GoalVisitor.findClass(caller.getClassName().get()), expr);
            } else {
                return badType();
            }
        } else if (expr.getCaller() instanceof Identifier) {
            return resolveMethodCall(expr);
        } else {
            System.err.println("err");
            return badType();
        }
    }

    /**
     * looks for the caller in the locals, parameters, and fields of the current class
     *
     * @param methodCall the method call
     * @return the return type of the method
     */
    private Type resolveMethodCall(MethodCall methodCall) {
        String callerId = methodCall.getCaller().toString();
        if (callerId.equals("this")) {
            return confirmMethodExists(Optional.of(currentKlass), methodCall);
        }
        Optional<Variable> local = currentMethod.findVariable(callerId);
        if (local.isPresent()) {
            return findClassOfCaller(local.get().getType().getClassName(), methodCall);
        }
        Optional<Type> field = symbolTable.getVarType(callerId);
        if (field.isPresent()) {
            return findClassOfCaller(field.get().getClassName(), methodCall);
        }
        Optional<String> inherited = findInheritedCaller(currentKlass.getParent(), callerId);
        if (inherited.isPresent()) {
            return findClassOfCaller(inherited, methodCall);
        } else {
            System.err.println("could not resolve id " + callerId + " or of primitive type");
            return badType();
        }
    }

    /**
     * searches classes, and their super classes, for a caller
     *
     * @param k the class to look for the caller
     * @param callerId the caller's identifer name
     * @return the first class where a match was found
     */
    private Optional<String> findInheritedCaller(Optional<Klass> k, String callerId) {
        if (k.isPresent()) {
            if (k.get().hasField(callerId)) {
                Variable caller = k.get().getFieldSet().get(callerId);
                return caller.getType().getClassName();
            } else {
                return findInheritedCaller(k.get().getParent(), callerId);
            }
        } else {
            return Optional.empty();
        }
    }

    /**
     * if a caller is found, determine the class of the caller
     *
     * @param name       the name of the caller
     * @param methodCall the method call
     * @return the method's return type
     */
    private Type findClassOfCaller(Optional<String> name, MethodCall methodCall) {
        if (name.isPresent()) {
            return confirmMethodExists(GoalVisitor.findClass(name.get()), methodCall);
        } else {
            return badType();
        }
    }

    /**
     * looks for the method name in the class's method set, and recurses in
     * parent's method set in the case that it isn't found
     *
     * @param klass      class type of the caller
     * @param methodCall the method call
     * @return the return type of the method
     */
    private Type confirmMethodExists(Optional<Klass> klass, MethodCall methodCall) {
        if (klass.isPresent()) {
            String methodId = methodCall.getMethodName();
            // need a list of all matching method names
            if (klass.get().hasMethod(methodId)) {
            // then change this to a while loop
                return verifyMethodSignature(klass.get().getMethodSet().get(methodId), methodCall);
            }
            // then if this class doesn't return, and there is a parent, recurse
            if (klass.get().hasParent()) {
                return confirmMethodExists(klass.get().getParent(), methodCall);
            } else {
                System.err.println("no " + methodId + " method found for " + methodCall.getCaller().toString());
                return badType();
            }
        } else {
            System.err.println("class type not present for " + methodCall.getCaller().toString());
            return badType();
        }
    }

    /**
     * checks the type of each argument against the type specified in the method declaration
     *
     * @param method     the method being called
     * @param methodCall the method call
     * @return the return type of the method
     */
    private Type verifyMethodSignature(Method method, MethodCall methodCall) {
        for (int i = 0; i < methodCall.getArgsType().size(); i++) {
            Type param = method.getParameters().get(i).getType();
            Type arg = methodCall.getArgsType().get(i);
            if (!param.equals(arg)) {
                System.err.println("found argument of type " + arg + ", expecting " + param);
                return badType();
            }
        }
        return method.getReturnType();
    }

    /**
     * utility to return a bad type
     *
     * @return BAD_TYPE
     */
    private Type badType() {
        return new Type(Type.Primitive.BAD_TYPE);
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
