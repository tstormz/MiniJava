package com.tstorm.compiler.visitors;

import com.tstorm.compiler.minijava.MiniJavaBaseVisitor;
import com.tstorm.compiler.minijava.MiniJavaParser;
import com.tstorm.compiler.rules.ClassDeclaration;

/**
 * Created by tstorm on 10/29/16.
 */
public class ClassDeclarationVisitor extends MiniJavaBaseVisitor<ClassDeclaration> {

    @Override
    public ClassDeclaration visitClassDeclaration(MiniJavaParser.ClassDeclarationContext ctx) {
        return new ClassDeclaration(ctx.className().getText());
    }

}
