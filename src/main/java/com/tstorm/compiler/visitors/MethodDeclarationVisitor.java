package com.tstorm.compiler.visitors;

import com.tstorm.compiler.minijava.MiniJavaBaseVisitor;
import com.tstorm.compiler.minijava.MiniJavaParser;
import com.tstorm.compiler.rules.Method;
import com.tstorm.compiler.rules.Type;
import com.tstorm.compiler.rules.Variable;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by tstorm on 10/30/16.
 */
public class MethodDeclarationVisitor extends MiniJavaBaseVisitor<Method> {

    @Override
    public Method visitMethodDeclaration(MiniJavaParser.MethodDeclarationContext ctx) {
        Type returnType = Type.fromString(ctx.returnType().getText());
        Set<Variable> params = new HashSet<>();
        for (MiniJavaParser.ParameterContext param : ctx.parameter()) {
            params.add(param.accept(new ParameterVisitor()));
        }
        return new Method(ctx.methodName().getText(), returnType, params);
    }

}
