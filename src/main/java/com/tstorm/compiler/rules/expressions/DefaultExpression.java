package com.tstorm.compiler.rules.expressions;

import com.tstorm.compiler.assembler.Assembler;
import com.tstorm.compiler.rules.Type;
import com.tstorm.compiler.typechecker.ExpressionVisitor;

import java.io.PrintWriter;
import java.util.Optional;

/**
 * Created by tstorm on 11/1/16.
 */
public class DefaultExpression extends Assembler implements Expression {

    private Optional<Expression> expression = Optional.empty();

    public DefaultExpression(){}

    public DefaultExpression(Expression expression) {
        this.expression = Optional.of(expression);
    }

    public Optional<Expression> getExpression() {
        return expression;
    }

    public String toString() {
        if (expression.isPresent()) {
            return "(" + expression.get().toString() + ")";
        } else {
            return "DEFAULT_STATEMENT";
        }
    }

    @Override
    public Type accept(ExpressionVisitor v) {
        if (expression.isPresent()) {
            return v.visit(expression.get());
        } else {
            return null;
        }
    }

    @Override
    public void generateCode(PrintWriter out) {
        if (expression.isPresent()) {
            ((Assembler) expression.get()).generateCode(out);
        }
    }
}
