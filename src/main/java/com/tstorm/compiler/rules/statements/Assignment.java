package com.tstorm.compiler.rules.statements;

import com.tstorm.compiler.rules.expressions.Expression;

/**
 * Created by tstorm on 11/1/16.
 */
public class Assignment extends Statement {

    private String srcVariableName;
    private Expression expression;

    public Assignment(String src, Expression expression) {
        this.srcVariableName = src;
        this.expression = expression;
    }

    public String toString() {
        return srcVariableName + " = " + expression.toString();
    }

}
