package com.tstorm.compiler.rules.statements;

import com.tstorm.compiler.minijava.MiniJavaParser;
import com.tstorm.compiler.rules.expressions.Expression;
import com.tstorm.compiler.rules.statements.Statement;
import com.tstorm.compiler.typechecker.Visitor;

/**
 * Created by tstorm on 11/1/16.
 */
public class ElementAssignment extends Statement {

    public static final String ASSIGN_ERROR = "Assignment Error: Incompatible types, expected 'int' found '%s'";

    private String destVarName;
    private Expression index, assignment;

    public ElementAssignment(String variableName, Expression... expression) {

        this.destVarName = variableName;
        index = expression[0];
        assignment = expression[1];
    }

    public String getDestination() {
        return destVarName;
    }

    public Expression getIndex() {
        return index;
    }

    public Expression getAssignment() {
        return assignment;
    }

    public String toString() {
        return destVarName + "[" + index.toString() + "] = " + assignment.toString();
    }

    @Override
    public boolean accept(Visitor v) {
        return v.visit(this);
    }
}
