package com.tstorm.compiler.rules.statements;

import com.tstorm.compiler.rules.expressions.Expression;
import com.tstorm.compiler.typechecker.Visitor;

/**
 * Created by tstorm on 11/1/16.
 */
public class Assignment implements Statement {

    public static final String ERROR = "Assignment Error: '%s' is expecting type '%s'";

    private final String srcVariableName;
    private final Expression expression;

    public Assignment(String src, Expression expression) {
        this.srcVariableName = src;
        this.expression = expression;
    }

    public String getSrcVariableName() {
        return srcVariableName;
    }

    public Expression getExpression() {
        return expression;
    }

    public String toString() {
        return srcVariableName + " = " + expression.toString();
    }

    @Override
    public boolean accept(Visitor v) {
        return v.visit(this);
    }
}
