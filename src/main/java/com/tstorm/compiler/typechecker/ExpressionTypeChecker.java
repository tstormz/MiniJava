package com.tstorm.compiler.typechecker;

import com.tstorm.compiler.rules.*;
import com.tstorm.compiler.rules.expressions.*;
import com.tstorm.compiler.visitors.GoalVisitor;

import java.util.List;
import java.util.Optional;

/**
 * Created by tstorm on 11/2/16.
 */
public class ExpressionTypeChecker extends ExpressionVisitor {

    private final Klass klass;
    private Method currentMethod;

    public ExpressionTypeChecker(Klass k) {
        this.klass = k;
    }

    public void setCurrentMethod(Method m) {
        this.currentMethod = m;
    }

    public Method getCurrentMethod() {
        return currentMethod;
    }

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
        Type array = expr.getArray().accept(this);
        Type index = expr.getIndex().accept(this);
        if (array.is(Type.Primitive.ARRAY)) {
            if (index.is(Type.Primitive.INT)) {
                return new Type(Type.Primitive.INT);
            } else {
                System.err.println("Array Reference Error: index should be an int");
                return badType();
            }
        } else {
            System.err.println("Array Reference Error: variable not of type int[]");
            return badType();
        }
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
        return null;
    }

    @Override
    public Type visit(Identifier expr) {
        if (expr.toString().equals("this")) {
            return new Type(klass.getClassName());
        }
        // locals and parameters
        Optional<Variable> v = currentMethod.findVariable(expr.toString());
        if (v.isPresent()) {
            Type type = v.get().getType();
            if (type instanceof OptionalType) {
                Type t = tryUnwrapping(expr, type);
                expr.bind(v);
                return t;
            } else if (expr instanceof UnwrappedIdentifier) {
                System.err.println(OptionalType.UNWRAP_ERROR);
                return badType();
            } else {
                expr.bind(v);
                return type;
            }
        }
        // fields
        Optional<Variable> t = klass.getField(expr.toString());
        if (t.isPresent()) {
            Type type = t.get().getType();
            if (type instanceof OptionalType) {
                expr.bind(t);
                return tryUnwrapping(expr, type);
            } else if (expr instanceof UnwrappedIdentifier) {
                System.err.println(OptionalType.UNWRAP_ERROR);
            } else {
                expr.bind(t);
                return type;
            }
        }
        // methods
        Optional<Type> methodType = klass.getMethodType(expr.toString());
        if (methodType.isPresent()) {
            return methodType.get();
        }
        // other
        return badType();
    }

    private Type tryUnwrapping(Identifier id, Type type) {
        if (id instanceof UnwrappedIdentifier) {
            return unwrap(type);
        } else {
            return type;
        }
    }

    private Type unwrap(Type type) {
        if (type.getClassName().isPresent()) {
            return new Type(type.getClassName().get());
        } else {
            return new Type(type.getPrimitive());
        }
    }

    @Override
    public Type visit(Instance expr) {
        Optional<String> className = expr.getClassName();
        if (className.isPresent()) {
            return new Type(className.get());
        } else {
            Expression arrayIndex = expr.getArrayIndex();
            if (arrayIndex != null) {
                Type indexType = arrayIndex.accept(this);
                if (indexType.is(Type.Primitive.INT)) {
                    return new Type(Type.Primitive.ARRAY);
                } else {
                    return badType();
                }
            } else {
                return badType();
            }
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
            return resolveMethodCall(expr, (Identifier) expr.getCaller());
        } else if (expr.getCaller() instanceof Instance) {
            if (((Instance) expr.getCaller()).getClassName().isPresent()) {
                return findClassOfCaller(((Instance) expr.getCaller()).getClassName(), expr);
            } else {
                System.err.println("error: ExpressionTypeChecker::methodcall");
                return badType();
            }
        } else {
            System.err.println("error: ExpressionTypeChecker::methodcall");
            return badType();
        }
    }

    /**
     * looks for the caller in the locals, parameters, and fields of the current class
     *
     * @param methodCall the method call
     * @return the return type of the method
     */
    private Type resolveMethodCall(MethodCall methodCall, Identifier caller) {
        String callerId = methodCall.getCaller().toString();
        if (callerId.equals("this")) {
            return confirmMethodExists(Optional.of(klass), methodCall);
        }
        Optional<Variable> local = currentMethod.findVariable(callerId);
        if (local.isPresent()) {
            return checkVariable(local, caller, methodCall);
        }
        Optional<Variable> field = klass.getField(callerId);
        if (field.isPresent()) {
            return checkVariable(field, caller, methodCall);
        }
        Optional<Variable> inherited = findInheritedCaller(klass.getParent(), callerId);
        if (inherited.isPresent()) {
            return checkVariable(inherited, caller, methodCall);
        } else {
            System.err.println(String.format(Type.NOT_FOUND_ERROR, callerId));
            return badType();
        }
    }

    private Type checkVariable(Optional<Variable> v, Identifier caller, MethodCall methodCall) {
        if (v.get().getType() instanceof OptionalType
                && !(methodCall.getCaller() instanceof UnwrappedIdentifier)) {
            System.err.println(OptionalType.USE_ERROR);
            return badType();
        } else {
            if (v.get().isInitialized()) {
                caller.bind(v);
                return findClassOfCaller(v.get().getType().getClassName(), methodCall);
            } else {
                System.err.println(String.format(Variable.INIT_ERROR, v.get().getVariableName()));
                return badType();
            }
        }
    }

    private boolean valueIsPresent(Variable variable, Identifier caller) {
        if (variable.getType() instanceof OptionalType) {
            return caller instanceof UnwrappedIdentifier;
        } else {
            return variable.isInitialized();
        }
    }

    /**
     * searches classes, and their super classes, for a caller
     *
     * @param k        the class to look for the caller
     * @param callerId the caller's identifer name
     * @return the first class where a match was found
     */
    private Optional<Variable> findInheritedCaller(Optional<Klass> k, String callerId) {
        if (k.isPresent()) {
            if (k.get().hasField(callerId)) {
                return k.get().getField(callerId);
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
            // if the class has a method by this name, verify it's signature
            if (klass.get().hasMethod(methodId)) {
                Type t = verifyMethodSignature(klass.get().getMethodSet().get(methodId), methodCall);
                if (!t.is(Type.Primitive.BAD_TYPE)) {
                    methodCall.setKlass(klass.get());
                    return t;
                }
            }
            // if we have bad_type, recurse on the parent
            if (klass.get().hasParent()) {
                return confirmMethodExists(klass.get().getParent(), methodCall);
            } else {
                System.err.println(String.format(Method.NOT_FOUND_ERROR, methodId, methodCall.getCaller().toString(), methodCall.getLineNumber()));
                return badType();
            }
        } else {
            System.err.println(String.format(Klass.NOT_FOUND_ERROR, methodCall.getCaller().toString()));
            return badType();
        }
    }

    /**
     * checks the type of each argument against the type specified in the method declaration
     *
     * @param methods    the method being called
     * @param methodCall the method call
     * @return the return type of the method
     */
    private Type verifyMethodSignature(List<Method> methods, MethodCall methodCall) {
        // argument count
        final int argc = methodCall.getArgsType().size();
        // for all methods that match the method name
        boolean goodType = true;
        for (Method method : methods) {
            List<Variable> params = method.getParameters();
            List<Type> args = methodCall.getArgsType();
            // can only be a match if they have the same number of arguments as parameters
            if (params.size() == args.size()) {
                for (int i = 0; i < argc; i++) {
                    Type param = params.get(i).getType();
                    Type arg = args.get(i);
                    goodType &= typesMatch(param, arg);
                }
            } else {
                goodType = false;
            }
            if (goodType) {
                return method.getReturnType();
            }
        }
        return badType();
    }

    /**
     * Checks if the type of the parameter is equal to the type of the argument, or any of the
     * argument's super classes
     *
     * @param param the type of the parameter
     * @param arg   the type of the argument
     * @return true if the type of param equals the type of arg or the type of any of arg's parent classes
     */
    private boolean typesMatch(Type param, Type arg) {
        Optional<Klass> argKlass = Optional.empty();
        if (arg.getClassName().isPresent()) {
            argKlass = GoalVisitor.findClass(arg.getClassName().get());
        }
        if (param.equals(arg) && !isOptionalError(param, arg)) {
            return true;
        } else if (argKlass.isPresent() && argKlass.get().hasParent()) {
            return typesMatch(param, new Type(argKlass.get().getParentName().get()));
        } else {
            return false;
        }
    }

    private boolean isOptionalError(Type param, Type arg) {
        if ((arg instanceof OptionalType) && !(param instanceof OptionalType)) {
            System.err.println(OptionalType.USE_ERROR);
            return true;
        } else {
            return false;
        }
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
        return expr.accept(this);
    }

}
