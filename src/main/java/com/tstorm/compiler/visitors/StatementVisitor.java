package com.tstorm.compiler.visitors;

import com.tstorm.compiler.minijava.MiniJavaBaseVisitor;
import com.tstorm.compiler.minijava.MiniJavaParser;
import com.tstorm.compiler.rules.statements.Conditional;
import com.tstorm.compiler.rules.statements.DefaultStatement;
import com.tstorm.compiler.rules.statements.Loop;
import com.tstorm.compiler.rules.statements.Statement;

import java.util.List;

/**
 * Created by tstorm on 10/31/16.
 */
public class StatementVisitor extends MiniJavaBaseVisitor<Statement> {

    @Override
    public Statement visitStatement(MiniJavaParser.StatementContext ctx) {
        MiniJavaParser.VarAssignmentContext varAssignmentContext = ctx.varAssignment();
        if (varAssignmentContext != null) {
            return varAssignmentContext.accept(new VarAssignmentVisitor());
        }
        MiniJavaParser.ElementAssignmentContext elementAssignmentContext = ctx.elementAssignment();
        if (elementAssignmentContext != null) {
            return elementAssignmentContext.accept(new ElementAssignmentVisitor());
        }
        MiniJavaParser.ConditionalContext conditionalContext = ctx.conditional();
        if (conditionalContext != null) {
            Conditional cond = conditionalContext.accept(new ConditionalVisitor());
            return cond;
        }
        MiniJavaParser.LoopContext loopContext = ctx.loop();
        if (loopContext != null) {
            Loop loop = loopContext.accept(new LoopVisitor());
            return loop;
        }
        MiniJavaParser.PrintContext printContext = ctx.print();
        if (printContext != null) {
//            System.out.println();
        }
        return new DefaultStatement(ctx.statement());
    }

}
