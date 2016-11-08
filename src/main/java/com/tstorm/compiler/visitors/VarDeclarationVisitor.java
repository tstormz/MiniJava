package com.tstorm.compiler.visitors;

import com.tstorm.compiler.minijava.MiniJavaBaseVisitor;
import com.tstorm.compiler.minijava.MiniJavaParser;
import com.tstorm.compiler.rules.Type;
import com.tstorm.compiler.rules.Variable;

/**
 * Created by tstorm on 10/29/16.
 */
public class VarDeclarationVisitor extends MiniJavaBaseVisitor<Variable> {

    @Override
    public Variable visitVarDeclaration(MiniJavaParser.VarDeclarationContext ctx) {
        String type = ctx.type().t().getText();
        Type t;
        if (ctx.type().t().className() == null) {
            t = new Type(Type.Primitive.fromString(type));
        } else {
            t = new Type(ctx.type().t().className().getText());
        }
        return new Variable(t, ctx.variableName().getText());
    }

}
