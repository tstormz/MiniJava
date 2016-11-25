package com.tstorm.compiler.rules;

import com.tstorm.compiler.typechecker.TypeChecker;

import java.io.IOException;
import java.util.List;
import java.util.Set;

/**
 * Created by tstorm on 10/29/16.
 */
public class Goal {

    private final Klass mainClass;
    private final Set<Klass> classes;

    public Goal(Klass mainClass, Set<Klass> classes) {
        this.mainClass = mainClass;
        this.classes = classes;
    }

    public boolean typeCheck() {
        TypeChecker typeChecker;
        boolean pass = true;
        for (Klass k : classes) {
            typeChecker = new TypeChecker(k);
            pass &= typeChecker.checkType();
        }
        typeChecker = new TypeChecker(mainClass);
        pass &= typeChecker.checkType();
        return pass;
    }

    public void generateCode() throws IOException {
        mainClass.generateCode();
        for (Klass k : classes) {
            k.generateCode();
        }
    }

    public void print() {
        System.out.println("main: " + mainClass.toString());
        for (Klass klass : classes) {
            System.out.println("class: " + klass.toString());
            if (klass.getParent().isPresent()) {
                System.out.println(" parent: " + klass.getParent().get().toString());
            }
            if (!klass.getFields().isEmpty()) {
                for (Variable var : klass.getFields().values()) {
                    System.out.println(var.toString());
                }
            }
            if (!klass.getMethods().isEmpty()) {
                for (List<Method> methods : klass.getMethodSet().values()) {
                    for (Method method : methods) {
                        System.out.println(method.toString());
                    }
                }
            }
        }
    }

}
