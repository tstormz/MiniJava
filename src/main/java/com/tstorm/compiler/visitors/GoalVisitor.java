package com.tstorm.compiler.visitors;

import com.tstorm.compiler.minijava.MiniJavaBaseVisitor;
import com.tstorm.compiler.minijava.MiniJavaParser;
import com.tstorm.compiler.rules.Goal;
import com.tstorm.compiler.rules.Klass;

import java.util.*;

/**
 * Created by tstorm on 10/29/16.
 */
public class GoalVisitor extends MiniJavaBaseVisitor<Goal> {

    private static final Set<Klass> classes = new HashSet<>();

    @Override
    public Goal visitGoal(MiniJavaParser.GoalContext ctx) {
        MainClassVisitor mainClassVisitor = new MainClassVisitor();
        Klass mainClass = ctx.mainClass().accept(mainClassVisitor);
        ClassDeclarationVisitor classDeclarationVisitor = new ClassDeclarationVisitor();
        for (MiniJavaParser.ClassDeclarationContext klass : ctx.classDeclaration()) {
            classes.add(klass.accept(classDeclarationVisitor));
        }
        linkClasses();
        return new Goal(mainClass, classes);
    }

    public static Optional<Klass> findClass(String className) {
        for (Klass k : classes) {
            if (k.getClassName().equals(className)) {
                return Optional.of(k);
            }
        }
        return Optional.empty();
    }

    private void linkClasses() {
        for (Klass k : classes) {
            Optional<Klass> parent = findParent(k);
            if (parent.isPresent()) {
                if (containsNoCycles(k)) {
                    k.setParent(parent);
                } else {
                    System.err.println(k.getClassName() + " contains cycle");
                }
            }
        }
    }

    /**
     * Traverses the list of super classes looking for a cycle
     *
     * @param k the child klass
     * @return true if no cycles are detected
     */
    private boolean containsNoCycles(Klass k) {
        Set<String> classes = new HashSet<>();
        classes.add(k.getClassName());
        Optional<Klass> parent = findParent(k);
        while (parent.isPresent()) {
            if (classes.contains(parent.get().getClassName())) {
                return false;
            } else {
                classes.add(parent.get().getClassName());
                parent = findParent(parent.get());
            }
        }
        return true;
    }

    /**
     * Utility to find a Klass's parent
     *
     * @param k the child klass
     * @return the parent klass if one exists, Optional.empty() otherwise
     */
    private Optional<Klass> findParent(Klass k) {
        if (k.getParentName().isPresent()) {
            Optional<Klass> parent = GoalVisitor.findClass(k.getParentName().get());
            if (parent.isPresent()) {
                return parent;
            } else {
                return Optional.empty();
            }
        } else {
            return Optional.empty();
        }
    }

}
