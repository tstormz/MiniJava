package com.tstorm.compiler.rules.statements;

import com.tstorm.compiler.minijava.MiniJavaParser;
import com.tstorm.compiler.rules.expressions.Expression;
import com.tstorm.compiler.rules.statements.Statement;
import com.tstorm.compiler.typechecker.Visitor;

/**
 * Created by tstorm on 11/1/16.
 */
public class ElementAssignment extends Statement {

    private String destVarName;
    private Expression index, assignment;

    public ElementAssignment(String variableName, Expression... expression) {

        this.destVarName = variableName;
        index = expression[0];
        assignment = expression[1];
    }

    public String toString() {
        return destVarName + "[" + index.toString() + "] = " + assignment.toString();
    }

    @Override
    public void accept(Visitor v) {
        v.visit(this);
    }
}
