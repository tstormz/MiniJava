package com.tstorm.compiler.rules;

import java.util.*;

/**
 * Created by tstorm on 10/29/16.
 */
public class Klass {

    private final String className;
    private final Set<Variable> fields = new HashSet<>();
    private final Set<Method> methods = new HashSet<>();
    private Optional<Klass> parent = Optional.empty();

    public Klass(String className, Klass parent) {
        this(className);
        this.parent = Optional.of(parent);
    }

    public Klass(String className) {
        this.className = className;
    }

    public void addField(Variable v) {
        fields.add(v);
    }

    public Set<Variable> getFields() {
        return fields;
    }

    public void addMethod(Method m) {
        methods.add(m);
    }

    public Set<Method> getMethods() {
        return methods;
    }

    @Override
    public String toString() {
        return className;
    }

}
