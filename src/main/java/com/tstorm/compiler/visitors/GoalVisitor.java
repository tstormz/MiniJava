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

}
