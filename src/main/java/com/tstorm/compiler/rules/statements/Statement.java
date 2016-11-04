package com.tstorm.compiler.rules.statements;

import com.tstorm.compiler.typechecker.Visitor;

/**
 * Created by tstorm on 10/31/16.
 */
public abstract class Statement {

    public abstract void accept(Visitor v);

}
