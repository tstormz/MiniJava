package com.tstorm.compiler.rules;

import java.util.Optional;

/**
 * Created by tstorm on 10/30/16.
 */
public class Type {

    public static final String NOT_FOUND_ERROR = "could not resolve id %s or of primitive type";

    private Primitive primitiveType;
    private String classType;

    public Type(Primitive primitive) {
        this.primitiveType = primitive;
    }

    public Type(String className) {
        this.classType = className;
    }

    public Type() {
        this.classType = "void";
    }

    public enum Primitive {

        INT("int"),
        BOOLEAN("boolean"),
        ARRAY("int[]"),
        BAD_TYPE("bad type");

        private String text;

        Primitive(String text) {
            this.text = text;
        }

        public String getText() {
            return text;
        }

        public static Primitive fromString(String text) {
            for (Primitive t : Primitive.values()) {
                if (text.equals(t.getText())) {
                    return t;
                }
            }
            return BAD_TYPE;
        }

    }

    public boolean is(Primitive p) {
        if (p == null) {
            return false;
        } else {
            return this.primitiveType == p;
        }
    }

    public Primitive getPrimitive() {
        return primitiveType;
    }

    public Optional<String> getClassName() {
        return Optional.ofNullable(classType);
    }

    public boolean equals(Type t) {
        if (t.getClassName().isPresent()) {
            if (classType != null) {
                return t.getClassName().get().equals(classType);
            } else {
                return false;
            }
        } else {
            if (primitiveType != null) {
                return t.getPrimitive() == primitiveType;
            } else {
                return false;
            }
        }
    }

    public String toString() {
        if (classType != null) {
            return classType;
        } else {
            return primitiveType.getText();
        }
    }

}
