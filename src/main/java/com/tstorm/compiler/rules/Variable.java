package com.tstorm.compiler.rules;

/**
 * Created by tstorm on 10/29/16.
 */
public class Variable {

    private final Type type;
    private final String variableName;

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

    @Override
    public String toString() {
        return "   " + type + " " + variableName;
    }

}
