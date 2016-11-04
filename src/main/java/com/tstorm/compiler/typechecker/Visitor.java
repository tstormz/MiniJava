package com.tstorm.compiler.typechecker;

import com.tstorm.compiler.rules.statements.*;

/**
 * Created by tstorm on 11/2/16.
 */
public abstract class Visitor {

    public abstract void visit(Conditional statement);
    public abstract void visit(Assignment statement);
    public abstract void visit(ElementAssignment statement);
    public abstract void visit(Loop statement);
    public abstract void visit(ReturnStatement statement);
    public abstract void visit(DefaultStatement statement);

}
