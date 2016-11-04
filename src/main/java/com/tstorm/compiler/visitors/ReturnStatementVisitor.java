package com.tstorm.compiler.visitors;

import com.tstorm.compiler.minijava.MiniJavaBaseVisitor;
import com.tstorm.compiler.minijava.MiniJavaParser;
import com.tstorm.compiler.rules.statements.ReturnStatement;
import com.tstorm.compiler.rules.statements.Statement;

/**
 * Created by tstorm on 11/2/16.
 */
public class ReturnStatementVisitor extends MiniJavaBaseVisitor<Statement> {

    @Override
    public Statement visitReturnStatement(MiniJavaParser.ReturnStatementContext ctx) {
        return new ReturnStatement(ctx.expression().accept(new ExpressionVisitor()));
    }

}
