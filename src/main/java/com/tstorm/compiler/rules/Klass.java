package com.tstorm.compiler.rules;

import java.io.*;
import java.util.*;

/**
 * Created by tstorm on 10/29/16.
 */
public class Klass {

    public static final String NOT_FOUND_ERROR = "class type not present for %s";
    private final String className;
    private final Map<String, Variable> fields = new HashMap<>();
    private final Map<String, List<Method>> methods = new HashMap<>();
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

    public Map<String, Variable> getFields() {
        return fields;
    }

    public boolean hasField(String fieldId) {
        return fields.containsKey(fieldId);
    }

    public Optional<Variable> getField(String id) {
        if (fields.containsKey(id)) {
            return Optional.of(fields.get(id));
        } else {
            return Optional.empty();
        }
    }

    public void addMethod(Method m) {
        String methodName = m.getMethodName();
        if (hasMethod(methodName)) {
            List<Method> methodList = methods.get(methodName);
            methodList.add(m);
        } else {
            List<Method> methodList = new LinkedList<>();
            methodList.add(m);
            methods.put(methodName, methodList);
        }
    }

    public Map<String, List<Method>> getMethodSet() {
        return methods;
    }

    public Map<String, Type> getMethods() {
        Map<String, Type> methods = new HashMap<>();
        // you can't override on the type so just take the first match
        for (List<Method> m : this.methods.values()) {
            methods.put(m.get(0).getMethodName(), m.get(0).getReturnType());
        }
        return methods;
    }

    public Optional<Type> getMethodType(String symbol) {
        return Optional.ofNullable(getMethods().get(symbol));
    }

    public boolean hasMethod(String methodId) {
        return methods.containsKey(methodId);
    }

    public boolean hasParent() {
        return parent.isPresent();
    }

    @Override
    public String toString() {
        return className;
    }

    public void generateCode() throws IOException {
        File classFile = new File(className + ".j");
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(classFile)));
        declaration(out);
        constructor(out);
        for (List<Method> method : methods.values()) {
            for (Method m : method) {
                if (m.getReturnType().equals(new Type("void"))) {
                    // main method
                    out.println("\n.method public static main([Ljava/lang/String;)V");
                } else {
                    m.declaration(out);
                }
                out.println(".limit stack 32");
                out.println(".limit locals 32");
                m.generateBody(out);
                out.println("return");
                out.println(".end method\n");
            }
        }
        out.close();
    }

    private void declaration(PrintWriter out) {
        out.println(".class public " + className);
        out.println(".super java/lang/Object");
    }

    private void constructor(PrintWriter out) {
        out.println("\n.method public <init>()V");
        out.println("aload_0");
        out.println("invokenonvirtual java/lang/Object/<init>()V");
        out.println("return");
        out.println(".end method\n");
    }

}
