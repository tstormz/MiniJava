package com.tstorm.compiler.visitors;

import com.tstorm.compiler.minijava.MiniJavaBaseVisitor;
import com.tstorm.compiler.minijava.MiniJavaParser;
import com.tstorm.compiler.rules.Method;
import com.tstorm.compiler.rules.Type;

/**
 * Created by tstorm on 10/30/16.
 */
public class MethodDeclarationVisitor extends MiniJavaBaseVisitor<Method> {

    @Override
    public Method visitMethodDeclaration(MiniJavaParser.MethodDeclarationContext ctx) {
        Type t = Type.fromString(ctx.returnType().getText());
        return new Method(t, ctx.methodName().getText());
    }

}
