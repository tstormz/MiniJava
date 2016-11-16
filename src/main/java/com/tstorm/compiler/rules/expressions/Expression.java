package com.tstorm.compiler.rules.expressions;

import com.tstorm.compiler.rules.Type;
import com.tstorm.compiler.typechecker.ExpressionVisitor;

/**
 * Created by tstorm on 11/1/16.
 */
public abstract class Expression {

    public int lineNumber = 1;

    public abstract Type accept(ExpressionVisitor v);

}
