package com.tstorm.compiler.rules;

/**
 * Created by tstorm on 10/31/16.
 */
public class Conditional implements Statement {

    private final Statement ifStatement;
    private final Statement elseStatement;

    public Conditional(Statement ifStatement, Statement elseStatement) {
        this.ifStatement = ifStatement;
        this.elseStatement = elseStatement;
    }

}
