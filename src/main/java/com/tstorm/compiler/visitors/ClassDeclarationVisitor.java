package com.tstorm.compiler.visitors;

import com.tstorm.compiler.minijava.MiniJavaBaseVisitor;
import com.tstorm.compiler.minijava.MiniJavaParser;
import com.tstorm.compiler.rules.Klass;

/**
 * Created by tstorm on 10/29/16.
 */
public class ClassDeclarationVisitor extends MiniJavaBaseVisitor<Klass> {

    @Override
    public Klass visitClassDeclaration(MiniJavaParser.ClassDeclarationContext ctx) {
        Klass klass = new Klass(ctx.className().getText());
        VarDeclarationVisitor varDeclarationVisitor = new VarDeclarationVisitor();
        for (MiniJavaParser.VarDeclarationContext var : ctx.varDeclaration()) {
            klass.addField(var.accept(varDeclarationVisitor));
        }
        MethodDeclarationVisitor methodDeclarationVisitor = new MethodDeclarationVisitor();
        for (MiniJavaParser.MethodDeclarationContext method : ctx.methodDeclaration()) {
            klass.addMethod(method.accept(methodDeclarationVisitor));
        }
        return klass;
    }

}
