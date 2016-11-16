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
        return new Type(Type.Primitive.INT);
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
        // fields
        Optional<Type> t = klass.getVarType(expr.toString());
        if (t.isPresent()) {
            return t.get();
        }
        // methods
        t = klass.getMethodType(expr.toString());
        if (t.isPresent()) {
            return t.get();
        }
        // locals and parameters
        Optional<Variable> v = currentMethod.findVariable(expr.toString());
        if (v.isPresent()) {
            return v.get().getType();
        }
        return badType();
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
            }else {
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
            return confirmMethodExists(Optional.of(klass), methodCall);
        }
        Optional<Variable> local = currentMethod.findVariable(callerId);
        // TODO initialize variable when assigned to an initialized variable
        if (local.isPresent()) {
            if (local.get().getType() instanceof OptionalType
                    && !(methodCall.getCaller() instanceof UnwrappedIdentifier)) {
                System.err.println("Error: using optional value without unwrapping it's value");
                return badType();
            } else {
                if (local.get().isInitialized()) {
                    return findClassOfCaller(local.get().getType().getClassName(), methodCall);
                } else {
                    System.err.println(String.format(Variable.INIT_ERROR, local.get().getVariableName()));
                }
            }
        }
        Optional<Variable> field = klass.getField(callerId);
        if (field.isPresent()) {
            if (field.get().getType() instanceof OptionalType
                    && !(methodCall.getCaller() instanceof UnwrappedIdentifier)) {
                System.err.println("Error: using optional value without unwrapping it's value");
                return badType();
            } else {
                if (field.get().isInitialized()) {
                    return findClassOfCaller(field.get().getType().getClassName(), methodCall);
                } else {
                    System.err.println(String.format(Variable.INIT_ERROR, field.get().getVariableName()));
                }
            }
        }
        Optional<Variable> inherited = findInheritedCaller(klass.getParent(), callerId);
        if (inherited.isPresent()) {
            if (inherited.get().getType() instanceof OptionalType
                    && !(methodCall.getCaller() instanceof UnwrappedIdentifier)) {
                System.err.println("Error: using optional value without unwrapping it's value");
                return badType();
            } else {
                if (inherited.get().isInitialized()) {
                    return findClassOfCaller(inherited.get().getType().getClassName(), methodCall);
                } else {
                    System.err.println(String.format(Variable.INIT_ERROR, inherited.get().getVariableName()));
                    return badType();
                }
            }
        } else {
            System.err.println("could not resolve id " + callerId + " or of primitive type");
            return badType();
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
     * @param k the class to look for the caller
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
                    return t;
                }
            }
            // if we have bad_type, recurse on the parent
            if (klass.get().hasParent()) {
                return confirmMethodExists(klass.get().getParent(), methodCall);
            } else {
                System.err.println("no " + methodId + " method found for " + methodCall.getCaller().toString() + " on line " + methodCall.lineNumber);
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
     * @param methods     the method being called
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
     * @param arg the type of the argument
     * @return true if the type of param equals the type of arg or the type of any of arg's parent classes
     */
    private boolean typesMatch(Type param, Type arg) {
        Optional<Klass> argKlass = Optional.empty();
        if (arg.getClassName().isPresent()) {
            argKlass = GoalVisitor.findClass(arg.getClassName().get());
        }
        if (param.equals(arg)) {
            return true;
        } else if (argKlass.isPresent() && argKlass.get().hasParent()) {
            return typesMatch(param, new Type(argKlass.get().getParentName().get()));
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
