package com.tstorm.compiler.visitors;

import com.tstorm.compiler.minijava.MiniJavaBaseVisitor;
import com.tstorm.compiler.minijava.MiniJavaParser;
import com.tstorm.compiler.rules.Conditional;
import com.tstorm.compiler.rules.Statement;

/**
 * Created by tstorm on 10/31/16.
 */
public class ConditionalVisitor extends MiniJavaBaseVisitor<Conditional> {

    @Override
    public Conditional visitConditional(MiniJavaParser.ConditionalContext ctx) {
        Statement ifStatement = ctx.statement().accept(new StatementVisitor());
        Statement elseStatement = ctx.elseCond().statement().accept(new StatementVisitor());
        return new Conditional(ifStatement, elseStatement);
    }

}
