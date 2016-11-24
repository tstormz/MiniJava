package com.tstorm.compiler.visitors;

import com.tstorm.compiler.minijava.MiniJavaBaseVisitor;
import com.tstorm.compiler.minijava.MiniJavaParser;
import com.tstorm.compiler.rules.statements.Print;

/**
 * Created by tstorm on 11/23/16.
 */
public class PrintVisitor extends MiniJavaBaseVisitor<Print> {

    @Override
    public Print visitPrint(MiniJavaParser.PrintContext ctx) {
        return new Print(ctx.expression().accept(new ExpressionVisitor()));
    }

}
