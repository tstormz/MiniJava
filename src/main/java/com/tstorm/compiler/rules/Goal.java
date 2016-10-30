package com.tstorm.compiler.rules;

import java.util.List;

/**
 * Created by tstorm on 10/29/16.
 */
public class Goal {

    private final MainClass mainClass;
    private final List<ClassDeclaration> classes;

    public Goal(MainClass mainClass, List<ClassDeclaration> classes) {
        this.mainClass = mainClass;
        this.classes = classes;
    }

    public void addClass(ClassDeclaration klass) {
        classes.add(klass);
    }

    public boolean isDefined(ClassDeclaration klass) {
        return classes.contains(klass);
    }

    @Override
    public String toString() {
        String s = "main: " + mainClass.toString();
        for (ClassDeclaration klass : classes) {
            s += "\n   class: " + klass.toString();
        }
        return s;
    }

}
