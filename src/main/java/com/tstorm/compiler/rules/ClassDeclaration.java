package com.tstorm.compiler.rules;

/**
 * Created by tstorm on 10/29/16.
 */
public class ClassDeclaration {

    private final String className;

    public ClassDeclaration(String className) {
        this.className = className;
    }

    @Override
    public String toString() {
        return className;
    }

}
