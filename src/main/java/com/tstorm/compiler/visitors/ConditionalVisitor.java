package com.tstorm.compiler.visitors;

import com.tstorm.compiler.minijava.MiniJavaBaseVisitor;
import com.tstorm.compiler.minijava.MiniJavaParser;
import com.tstorm.compiler.rules.statements.Conditional;
import com.tstorm.compiler.rules.expressions.Expression;
import com.tstorm.compiler.rules.statements.Statement;

/**
 * Created by tstorm on 10/31/16.
 */
public class ConditionalVisitor extends MiniJavaBaseVisitor<Conditional> {

    @Override
    public Conditional visitConditional(MiniJavaParser.ConditionalContext ctx) {
        Expression expression = ctx.expression().accept(new ExpressionVisitor());
        Statement ifStatement = ctx.statement().accept(new StatementVisitor());
        Statement elseStatement = ctx.elseCond().statement().accept(new StatementVisitor());
        return new Conditional(expression, ifStatement, elseStatement);
    }

}
