package com.tstorm.compiler.visitors;

import com.tstorm.compiler.minijava.MiniJavaBaseVisitor;
import com.tstorm.compiler.minijava.MiniJavaParser;
import com.tstorm.compiler.rules.statements.Assignment;
import com.tstorm.compiler.rules.statements.Statement;

/**
 * Created by tstorm on 10/31/16.
 */
public class VarAssignmentVisitor extends MiniJavaBaseVisitor<Statement> {

    @Override
    public Statement visitVarAssignment(MiniJavaParser.VarAssignmentContext ctx) {
        return new Assignment(ctx.variableName().getText(), ctx.expression().accept(new ExpressionVisitor()));
    }

}
