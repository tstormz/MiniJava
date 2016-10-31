package com.tstorm.compiler.visitors;

import com.tstorm.compiler.minijava.MiniJavaBaseVisitor;
import com.tstorm.compiler.minijava.MiniJavaParser;
import com.tstorm.compiler.rules.Loop;

/**
 * Created by tstorm on 10/31/16.
 */
public class LoopVisitor extends MiniJavaBaseVisitor<Loop> {

    @Override
    public Loop visitLoop(MiniJavaParser.LoopContext ctx)  {
        ctx.statement().accept(new StatementVisitor());
        return new Loop();
    }

}
