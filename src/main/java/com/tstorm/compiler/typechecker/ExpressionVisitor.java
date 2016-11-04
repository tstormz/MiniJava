package com.tstorm.compiler.typechecker;

import com.tstorm.compiler.rules.Type;
import com.tstorm.compiler.rules.expressions.*;

/**
 * Created by tstorm on 11/2/16.
 */
public abstract class ExpressionVisitor {

    public abstract Type visit(ArrayLength expr);
    public abstract Type visit(ArrayRef expr);
    public abstract Type visit(BinaryExpression expr);
    public abstract Type visit(Complement expr);
    public abstract Type visit(DefaultExpression expr);
    public abstract Type visit(Identifier expr);
    public abstract Type visit(Instance expr);
    public abstract Type visit(Literal expr);
    public abstract Type visit(MethodCall expr);

}
