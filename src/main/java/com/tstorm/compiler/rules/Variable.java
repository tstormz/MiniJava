package com.tstorm.compiler.rules;

/**
 * Created by tstorm on 10/29/16.
 */
public class Variable {

    private final String variableName;

    public Variable(String variableName) {
        this.variableName = variableName;
    }

    @Override
    public String toString() {
        return variableName;
    }

}
