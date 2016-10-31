package com.tstorm.compiler.rules;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

/**
 * Created by tstorm on 10/29/16.
 */
public class Method {

    private final String methodName;
    private final Type returnType;
    private final Set<Variable> parameters;

    public Method(String methodName, Type returnType, Set<Variable> params) {
        this.methodName = methodName;
        this.returnType = returnType;
        this.parameters = params;
    }

    @Override
    public String toString() {
        String s = returnType + " " + methodName + "()";
        if (!parameters.isEmpty()) {
            for (Variable v : parameters) {
                s += "\n   parameter: " + v.toString();
            }
        }
        return s;
    }

}
