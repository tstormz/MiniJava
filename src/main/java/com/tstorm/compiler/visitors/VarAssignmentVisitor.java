package com.tstorm.compiler.visitors;

import com.tstorm.compiler.minijava.MiniJavaBaseVisitor;
import com.tstorm.compiler.minijava.MiniJavaParser;
import com.tstorm.compiler.rules.expressions.Identifier;
import com.tstorm.compiler.rules.statements.Assignment;
import com.tstorm.compiler.rules.statements.Statement;

/**
 * Created by tstorm on 10/31/16.
 */
public class VarAssignmentVisitor extends MiniJavaBaseVisitor<Statement> {

    @Override
    public Statement visitVarAssignment(MiniJavaParser.VarAssignmentContext ctx) {
        Identifier id = new Identifier(ctx.variableName().getText());
        return new Assignment(id, ctx.expression().accept(new ExpressionVisitor()));
    }

}
