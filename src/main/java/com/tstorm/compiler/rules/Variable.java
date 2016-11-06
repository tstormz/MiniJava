package com.tstorm.compiler.rules;

import java.util.Optional;

/**
 * Created by tstorm on 10/29/16.
 */
public class Variable {

    private final Type type;
    private Optional<String> classType;
    private final String variableName;

    /**
     * Constructor for variables of Klass type
     *
     * @param type Type.CLASS
     * @param classType the className
     * @param variableName the variableName
     */
    public Variable(Type type, String classType, String variableName) {
        this(type, variableName);
        this.classType = Optional.of(classType);
    }

    /**
     * Constructor for variables of primitive type
     *
     * @param type the primitive type
     * @param variableName the variable name
     */
    public Variable(Type type, String variableName) {
        this.variableName = variableName;
        this.type = type;
        classType = Optional.empty();
    }

    public String getVariableName() {
        return variableName;
    }

    public Type getType() {
        return type;
    }

    public Optional<String> getClassType() {
        return classType;
    }

    @Override
    public String toString() {
        return "   " + type + " " + variableName;
    }

}
