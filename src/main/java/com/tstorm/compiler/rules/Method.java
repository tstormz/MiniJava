package com.tstorm.compiler.rules;

/**
 * Created by tstorm on 10/29/16.
 */
public class Method {

    private final String methodName;
    private final Type returnType;

    public Method(Type returnType, String methodName) {
        this.methodName = methodName;
        this.returnType = returnType;
    }

    @Override
    public String toString() {
        return returnType + " " + methodName + "()";
    }

}
