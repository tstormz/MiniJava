package com.tstorm.compiler.visitors;

import com.tstorm.compiler.minijava.MiniJavaBaseVisitor;
import com.tstorm.compiler.minijava.MiniJavaParser;
import com.tstorm.compiler.rules.Type;
import com.tstorm.compiler.rules.Variable;

/**
 * Created by tstorm on 10/31/16.
 */
public class ParameterVisitor extends MiniJavaBaseVisitor<Variable> {

    @Override
    public Variable visitParameter(MiniJavaParser.ParameterContext ctx) {
        String type = ctx.type().t().getText();
        Type t = new Type(Type.Primitive.fromString(type));
        return new Variable(t, ctx.parameterName().getText());
    }

}
