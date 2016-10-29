package com.tstorm.compiler.visitors;

import com.tstorm.compiler.minijava.MiniJavaBaseVisitor;
import com.tstorm.compiler.minijava.MiniJavaParser;
import org.antlr.v4.runtime.RuleContext;

/**
 * Created by tstorm on 10/28/16.
 */
public class ExpressionVisitor extends MiniJavaBaseVisitor<Integer> {

    private int count = 0;

    @Override
    public Integer visitExpression(MiniJavaParser.ExpressionContext ctx) {
        if (ctx != null) {
            RuleContext ruleContext = ctx.getRuleContext();
            if (ruleContext != null) {
                System.out.println("exp: " + ruleContext.getText());
            }
        }
        return ++count;
    }

}
