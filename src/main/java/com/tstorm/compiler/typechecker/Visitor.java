package com.tstorm.compiler.typechecker;

import com.tstorm.compiler.rules.statements.*;

/**
 * Created by tstorm on 11/2/16.
 */
public abstract class Visitor {

    public abstract boolean visit(Conditional statement);
    public abstract boolean visit(Assignment statement);
    public abstract boolean visit(ElementAssignment statement);
    public abstract boolean visit(Loop statement);
    public abstract boolean visit(ReturnStatement statement);
    public abstract boolean visit(DefaultStatement statement);

}
