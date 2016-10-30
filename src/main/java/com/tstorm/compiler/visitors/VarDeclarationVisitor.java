package com.tstorm.compiler.visitors;

import com.tstorm.compiler.minijava.MiniJavaBaseVisitor;
import com.tstorm.compiler.minijava.MiniJavaParser;
import com.tstorm.compiler.rules.Variable;

/**
 * Created by tstorm on 10/29/16.
 */
public class VarDeclarationVisitor extends MiniJavaBaseVisitor<Variable> {

    @Override
    public Variable visitVarDeclaration(MiniJavaParser.VarDeclarationContext ctx) {
        return new Variable(ctx.variableName().getText());
    }

}
