package com.tstorm.compiler.visitors;

import com.tstorm.compiler.minijava.MiniJavaBaseVisitor;
import com.tstorm.compiler.minijava.MiniJavaParser;
import com.tstorm.compiler.rules.expressions.Expression;
import com.tstorm.compiler.rules.statements.Loop;
import com.tstorm.compiler.rules.statements.Statement;

/**
 * Created by tstorm on 10/31/16.
 */
public class LoopVisitor extends MiniJavaBaseVisitor<Loop> {

    @Override
    public Loop visitLoop(MiniJavaParser.LoopContext ctx)  {
        Expression exp = ctx.expression().accept(new ExpressionVisitor());
        Statement stat  = ctx.statement().accept(new StatementVisitor());
        return new Loop(exp, stat);
    }

}
