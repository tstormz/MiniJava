package com.tstorm.compiler.rules.statements;

import com.tstorm.compiler.typechecker.Visitor;

/**
 * Created by tstorm on 10/31/16.
 */
public abstract class Statement {

    private int lineNumber = 1;

    public int getLineNumber() {
        return lineNumber;
    }

    public void setLineNumber(int i) {
        lineNumber = i;
    }

    public abstract boolean accept(Visitor v);

}
