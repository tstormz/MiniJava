package com.tstorm.compiler.rules;

/**
 * Created by tstorm on 10/30/16.
 */
public enum Type {

    INT ("int"),
    BOOLEAN ("boolean"),
    ARRAY ("int[]");

    private String text;

    Type(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public static Type fromString(String text) {
        for (Type t : Type.values()) {
            if (text.equals(t.getText())) {
                return t;
            }
        }
        return null;
    }

}
