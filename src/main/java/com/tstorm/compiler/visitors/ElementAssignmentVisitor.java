package com.tstorm.compiler.visitors;

import com.tstorm.compiler.minijava.MiniJavaBaseVisitor;
import com.tstorm.compiler.minijava.MiniJavaParser;
import com.tstorm.compiler.rules.expressions.Expression;
import com.tstorm.compiler.rules.statements.ElementAssignment;
import com.tstorm.compiler.rules.statements.Statement;

/**
 * Created by tstorm on 10/31/16.
 */
public class ElementAssignmentVisitor extends MiniJavaBaseVisitor<Statement> {

    @Override
    public Statement visitElementAssignment(MiniJavaParser.ElementAssignmentContext ctx) {
        Expression index = ctx.indexExpr().accept(new ExpressionVisitor());
        Expression assignment = ctx.expression().accept(new ExpressionVisitor());
        return new ElementAssignment(ctx.variableName().getText(), index, assignment);
    }

}
