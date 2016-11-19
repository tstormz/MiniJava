package com.tstorm.compiler.rules;

/**
 * Created by tstorm on 10/29/16.
 */
public class Variable {

    public static final String INIT_ERROR = "'%s' might not have been initialized";
    public static final String ASSIGN_ERROR = "Assignment Error: cannot resolve type for %s";

    private final Type type;
    private final String variableName;
    private boolean isInitialized = false;

    public Variable(Type type, String variableName) {
        this.variableName = variableName;
        this.type = type;
    }

    public String getVariableName() {
        return variableName;
    }

    public Type getType() {
        return type;
    }

    public boolean isInitialized() {
        return isInitialized;
    }

    public void initialize() {
        isInitialized = true;
    }

    @Override
    public String toString() {
        return "   " + type.toString() + " " + variableName;
    }

}
