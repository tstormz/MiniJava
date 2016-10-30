package com.tstorm.compiler.visitors;

import com.tstorm.compiler.minijava.MiniJavaBaseVisitor;
import com.tstorm.compiler.minijava.MiniJavaParser;
import com.tstorm.compiler.rules.ClassDeclaration;
import com.tstorm.compiler.rules.Goal;
import com.tstorm.compiler.rules.MainClass;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tstorm on 10/29/16.
 */
public class GoalVisitor extends MiniJavaBaseVisitor<Goal> {

    @Override
    public Goal visitGoal(MiniJavaParser.GoalContext ctx) {
        MainClassVisitor mainClassVisitor = new MainClassVisitor();
        MainClass mainClass = ctx.mainClass().accept(mainClassVisitor);
        final ClassDeclarationVisitor classDeclarationVisitor = new ClassDeclarationVisitor();
        final List<ClassDeclaration> classes = new ArrayList<ClassDeclaration>();
        for (MiniJavaParser.ClassDeclarationContext klass : ctx.classDeclaration()) {
            classes.add(klass.accept(classDeclarationVisitor));
        }
        return new Goal(mainClass, classes);
    }

}
