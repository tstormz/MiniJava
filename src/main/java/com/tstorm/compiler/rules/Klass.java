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
    private final Optional<String> parentName;

    public Klass(String className, String parentName) {
        this.className = className;
        this.parentName = Optional.ofNullable(parentName);
    }

    public Optional<String> getParentName() {
        return parentName;
    }

    public String getClassName() {
        return className;
    }

    public void setParent(Optional<Klass> parent) {
        this.parent = parent;
    }

    public Optional<Klass> getParent() {
        return parent;
    }

    public void addField(Variable v) {
        if (fields.containsKey(v.getVariableName())) {
            System.err.println(String.format("Variable '%s' is already defined in this scope",
                    v.getVariableName()));
        } else {
            fields.put(v.getVariableName(), v);
        }
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

    public boolean hasMethod(String methodId) {
        return methods.containsKey(methodId);
    }

    public boolean hasParent() {
        return parent.isPresent();
    }

    public boolean hasField(String fieldId) {
        return fields.containsKey(fieldId);
    }

    @Override
    public String toString() {
        return className;
    }

}
