package com.tstorm.compiler.rules;

/**
 * Created by tstorm on 11/16/16.
 */
public class OptionalType extends Type {

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
