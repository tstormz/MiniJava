package com.tstorm.compiler.visitors;

import com.tstorm.compiler.minijava.MiniJavaBaseVisitor;
import com.tstorm.compiler.minijava.MiniJavaParser;
import com.tstorm.compiler.rules.Goal;
import com.tstorm.compiler.rules.Klass;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by tstorm on 10/29/16.
 */
public class GoalVisitor extends MiniJavaBaseVisitor<Goal> {

    @Override
    public Goal visitGoal(MiniJavaParser.GoalContext ctx) {
        MainClassVisitor mainClassVisitor = new MainClassVisitor();
        Klass mainClass = ctx.mainClass().accept(mainClassVisitor);
        ClassDeclarationVisitor classDeclarationVisitor = new ClassDeclarationVisitor();
        Set<Klass> classes = new HashSet<>();
        for (MiniJavaParser.ClassDeclarationContext klass : ctx.classDeclaration()) {
            classes.add(klass.accept(classDeclarationVisitor));
        }
        return new Goal(mainClass, classes);
    }

}
