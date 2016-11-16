package com.tstorm.compiler.visitors;

import com.tstorm.compiler.minijava.MiniJavaBaseVisitor;
import com.tstorm.compiler.minijava.MiniJavaParser;
import com.tstorm.compiler.rules.expressions.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tstorm on 10/28/16.
 */
public class ExpressionVisitor extends MiniJavaBaseVisitor<Expression> {

    @Override
    public Expression visitExpression(MiniJavaParser.ExpressionContext ctx) {
        // left recursive cases
        if (ctx.getChild(0).getClass() == MiniJavaParser.ExpressionContext.class) {
            String op = ctx.getChild(1).getText();
            // arithmetic expressions
            if (op.matches("[+*<\\-]") || op.equals("&&")) {
                MiniJavaParser.ExpressionContext left = ctx.expression(0);
                MiniJavaParser.ExpressionContext right = ctx.expression(1);
                return new BinaryExpression(left.accept(new ExpressionVisitor()), op, right.accept(new ExpressionVisitor()));
            }
            // array reference
            if (op.equals("[")) {
                MiniJavaParser.ExpressionContext left = ctx.expression(0);
                MiniJavaParser.ExpressionContext right = ctx.expression(1);
                return new ArrayRef(left.accept(new ExpressionVisitor()), right.accept(new ExpressionVisitor()));
            }
            if (op.equals(".")) {
                // length reference
                if (ctx.getChild(2).getText().equals("length")) {
                    return new ArrayLength(ctx.expression(0).accept(new ExpressionVisitor()));
                }
                // method reference
                if (ctx.methodName() != null) {
                    Expression caller = ctx.expression(0).accept(new ExpressionVisitor());
                    List<Expression> args = new ArrayList<>();
                    for (int i = 1; i < ctx.expression().size(); i++) {
                        args.add(ctx.expression(i).accept(new ExpressionVisitor()));
                    }
                    MethodCall methodCall = new MethodCall(caller, ctx.methodName().getText(), args);
                    methodCall.lineNumber = ctx.start.getLine();
                    return methodCall;
                }
            }
        }
        // new
        if (ctx.getChild(0).getText().equals("new")) {
            // array
            if (ctx.getChild(1).getText().equals("int")) {
                return new Instance(ctx.expression(0).accept(new ExpressionVisitor()));
            // object
            } else {
                return new Instance(ctx.className().getText());
            }
        }
        // NOT
        if (ctx.getChild(0).getText().equals("!")) {
            return new Complement(ctx.expression(0).accept(new ExpressionVisitor()));
        }
        // ID
        if (ctx.identifier() != null) {
            return new Identifier(ctx.getText());
        }
        // Literal
        if (ctx.constant() != null) {
            return new Literal(ctx.getText());
        }
        // parenthesis
        if (ctx.getChild(0).getText().equals("(")) {
            return new DefaultExpression(ctx.expression(0).accept(new ExpressionVisitor()));
        }
        return new DefaultExpression();
    }

}
