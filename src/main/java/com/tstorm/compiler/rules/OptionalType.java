package com.tstorm.compiler.rules;

/**
 * Created by tstorm on 11/16/16.
 */
public class OptionalType extends Type {

    public static final String UNWRAP_ERROR = "can't use ! on non-optional datatype";
    public static final String USE_ERROR = "Error: using optional value without unwrapping it's value";
    private boolean booleanContext = false;

    public OptionalType(Primitive primitive) {
        super(primitive);
    }

    public OptionalType(String className) {
        super(className);
    }

    @Override
    public String toString() {
        return super.toString() + "?";
    }

}
