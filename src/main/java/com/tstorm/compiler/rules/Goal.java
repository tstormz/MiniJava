package com.tstorm.compiler.rules;

import java.util.List;

/**
 * Created by tstorm on 10/29/16.
 */
public class Goal {

    private final Klass mainClass;
    private final List<Klass> classes;

    public Goal(Klass mainClass, List<Klass> classes) {
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
            }
        }
    }

}
