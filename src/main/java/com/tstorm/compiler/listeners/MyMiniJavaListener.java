package com.tstorm.compiler.listeners;

import com.tstorm.compiler.minijava.MiniJavaBaseListener;
import com.tstorm.compiler.minijava.MiniJavaParser;
import org.antlr.v4.runtime.RuleContext;

/**
 * Created by tstorm on 10/25/16.
 */
public class MyMiniJavaListener extends MiniJavaBaseListener {

    public static MyMiniJavaListener INSTANCE = new MyMiniJavaListener();

    @Override
    public void enterExpression(MiniJavaParser.ExpressionContext context) {
        if (context != null) {
            RuleContext ruleContext = context.getRuleContext();
            if (ruleContext != null) {
                System.out.println("exp: " + ruleContext.getText());
            }
        }
    }

}
