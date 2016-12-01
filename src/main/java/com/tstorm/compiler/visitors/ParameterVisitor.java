package com.tstorm.compiler.visitors;

import com.tstorm.compiler.minijava.MiniJavaBaseVisitor;
import com.tstorm.compiler.minijava.MiniJavaParser;
import com.tstorm.compiler.rules.OptionalType;
import com.tstorm.compiler.rules.Type;
import com.tstorm.compiler.rules.Variable;

/**
 * Created by tstorm on 10/31/16.
 */
public class ParameterVisitor extends MiniJavaBaseVisitor<Variable> {

    @Override
    public Variable visitParameter(MiniJavaParser.ParameterContext ctx) {
        String type = ctx.type().t().getText();
        Type t;
        if (ctx.type().t().className() != null) {
            if (ctx.type().optional() != null) {
                t = new OptionalType(ctx.type().t().getText());
            } else {
                t = new Type(ctx.type().t().className().getText());
            }
        } else {
            if (ctx.type().optional() != null) {
                t = new OptionalType(Type.Primitive.fromString(type));
            } else {
                t = new Type(Type.Primitive.fromString(type));
            }
        }
        return new Variable(t, ctx.parameterName().getText());
    }

}
