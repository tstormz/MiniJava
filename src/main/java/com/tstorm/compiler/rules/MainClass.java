package com.tstorm.compiler.rules;

/**
 * Created by tstorm on 10/29/16.
 */
public class MainClass {

    private final String className;

    public MainClass(String className) {
        this.className = className;
    }

    @Override
    public String toString() {
        return className;
    }

}
