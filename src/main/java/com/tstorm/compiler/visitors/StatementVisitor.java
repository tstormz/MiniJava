package com.tstorm.compiler.visitors;

import com.tstorm.compiler.minijava.MiniJavaBaseVisitor;
import com.tstorm.compiler.minijava.MiniJavaParser;
import com.tstorm.compiler.rules.*;

import java.util.List;

/**
 * Created by tstorm on 10/31/16.
 */
public class StatementVisitor extends MiniJavaBaseVisitor<Statement> {

    @Override
    public Statement visitStatement(MiniJavaParser.StatementContext ctx) {
        MiniJavaParser.VarAssignmentContext varAssignmentContext = ctx.varAssignment();
        if (varAssignmentContext != null) {
            Integer var = varAssignmentContext.accept(new VarAssignmentVisitor());
            // TODO: return?
        }
        MiniJavaParser.ElementAssignmentContext elementAssignmentContext = ctx.elementAssignment();
        if (elementAssignmentContext != null) {
            Integer element = elementAssignmentContext.accept(new ElementAssignmentVisitor());
            // TODO: return?
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
        List<MiniJavaParser.StatementContext> nestedStatements = ctx.statement();
        if (nestedStatements != null && !nestedStatements.isEmpty()) {
            for (MiniJavaParser.StatementContext statement : nestedStatements) {
                statement.accept(new StatementVisitor());
            }
        }
//        return new Statement();
        return new DefaultStatement();
    }

}
