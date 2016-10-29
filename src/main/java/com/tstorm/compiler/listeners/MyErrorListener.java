package com.tstorm.compiler.listeners;

import com.tstorm.compiler.minijava.MiniJavaParser;
import org.antlr.v4.runtime.*;

/**
 * Created by tstorm on 10/25/16.
 */
public class MyErrorListener extends BaseErrorListener {

    public static MyErrorListener INSTANCE = new MyErrorListener();

    @Override
    public void syntaxError(Recognizer<?, ?> recognizer, Object offendingSymbol,
                            int line, int charPositionInLine,
                            String msg, RecognitionException e)
    {
        String sourceName = recognizer.getInputStream().getSourceName();
        if (!sourceName.isEmpty()) {
            sourceName = String.format("%s:%d:%d: ", sourceName, line, charPositionInLine);
        }
        String offendingSymbolName = ((CommonToken) offendingSymbol).getText();
        if (offendingSymbolName.equals("else")) {
            msg = "'else' without matching 'if'";
        }
        if (e != null) {
            RuleContext rc = e.getCtx();
            if (rc != null && rc instanceof MiniJavaParser.ExpressionContext) {
                if (checkIllegalStart(rc.getText())) {
                    msg = "illegal start of expression -> '" + rc.getText() + "'";
                }
            }
        }
        System.err.println(sourceName+" "+msg);
    }

    private boolean checkIllegalStart(String line) {
        String first = " ";
        if (line.length() > 0) {
            first = line.substring(0,1);
        }
        if (!first.matches("[a-zA-Z0-9!(]")) {
            return true;
        } else {
            return false;
        }

    }

}
