package com.tstorm.compiler.rules;

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

    public boolean isDefined(Klass klass) {
        return classes.contains(klass);
    }

    public void print() {
        System.out.println("main: " + mainClass.toString());
        for (Klass klass : classes) {
            System.out.println("   class: " + klass.toString());
            if (!klass.getFields().isEmpty()) {
                for (Variable var : klass.getFields()) {
                    System.out.println(var.toString());
                }
                for (Method method : klass.getMethods()) {
                    System.out.println(method.toString());
                }
            }
        }
    }

}