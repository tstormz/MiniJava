package com.tstorm.compiler.rules;

import java.util.*;

/**
 * Created by tstorm on 10/29/16.
 */
public class Klass {

    private final String className;
    private final Map<String, Variable> fields = new HashMap<>();
    private final Map<String, Method> methods = new HashMap<>();
    private Optional<Klass> parent = Optional.empty();

    public Klass(String className, Klass parent) {
        this(className);
        this.parent = Optional.of(parent);
    }

    public Klass(String className) {
        this.className = className;
    }

    public void addField(Variable v) {
        fields.put(v.getVariableName(), v);
    }

    public Map<String, Variable> getFieldSet() {
        return fields;
    }

    public Map<String, Type> getFields() {
        Map<String, Type> fields = new HashMap<>();
        for (Variable v : this.fields.values()) {
            fields.put(v.getVariableName(), v.getType());
        }
        return fields;
    }

    public void addMethod(Method m) {
        methods.put(m.getMethodName(), m);
    }

    public Map<String, Method> getMethodSet() {
        return methods;
    }

    public Map<String, Type> getMethods() {
        Map<String, Type> methods = new HashMap<>();
        for (Method m : this.methods.values()) {
            methods.put(m.getMethodName(), m.getReturnType());
        }
        return methods;
    }

    public String getClassName() {
        return className;
    }

    public Optional<Klass> getParent() {
        return parent;
    }

    public boolean hasMethod(String methodId) {
        return methods.containsKey(methodId);
    }

    @Override
    public String toString() {
        return className;
    }

}
