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
        return new Klass(ctx.className().getText());
    }

}
