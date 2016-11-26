package com.tstorm.compiler.rules;

import com.tstorm.compiler.assembler.Assembler;

import java.io.PrintWriter;

/**
 * Created by tstorm on 10/29/16.
 */
public class Variable extends Assembler {

    public static final String INIT_ERROR = "'%s' might not have been initialized";
    public static final String ASSIGN_ERROR = "Assignment Error: cannot resolve type for %s";

    private final Type type;
    private final String variableName;
    private boolean isInitialized = false;
    private int id = -1;

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

    public int getId() {
        return this.id;
    }

    public boolean isInitialized() {
        return isInitialized;
    }

    public void initialize() {
        isInitialized = true;
    }

    @Override
    public String toString() {
        return "   " + type.toString() + " " + variableName;
    }

    public void init() {
        id = generateId();
    }

    public void declare(PrintWriter out) {
        init();
        if (type.isPrimitive()) {
            out.println("bipush 0");
            out.println("istore " + id);
        }
    }

    @Override
    public void generateCode(PrintWriter out) {

    }
}
