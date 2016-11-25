package com.tstorm.compiler.rules.expressions;

import com.tstorm.compiler.assembler.Assembler;
import com.tstorm.compiler.rules.Type;
import com.tstorm.compiler.typechecker.ExpressionVisitor;

import java.io.PrintWriter;

/**
 * Created by tstorm on 11/1/16.
 */
public class BinaryExpression extends Assembler implements Expression {

    private final Expression left, right;
    private final String op;

    public BinaryExpression(Expression left, String op, Expression right) {
        this.left = left;
        this.right = right;
        this.op = op;
    }

    public Expression getLeft() {
        return left;
    }

    public Expression getRight() {
        return right;
    }

    public String getOperator() {
        return op;
    }

    public String toString() {
        return left.toString() + op + right.toString();
    }

    @Override
    public Type accept(ExpressionVisitor v) {
        return v.visit(this);
    }

    @Override
    public void generateCode(PrintWriter out) {
        if (op.equals("*")) {
            ((Assembler) right).generateCode(out);
            ((Assembler) left).generateCode(out);
        } else if (op.equals("&&")) {
            Assembler leftExpr = (Assembler) left;
            leftExpr.setLabel(getLabel().get());
            leftExpr.generateCode(out);
            Assembler rightExpr = (Assembler) right;
            rightExpr.setLabel(getLabel().get());
            rightExpr.generateCode(out);
        } else {
            ((Assembler) left).generateCode(out);
            ((Assembler) right).generateCode(out);
        }
        switch (op) {
            case "+":
                out.println("iadd");
                break;
            case "-":
                out.println("isub");
                break;
            case "*":
                out.println("imul");
                break;
            case "<":
                if (hasLabel()) {
                    out.println("if_icmpge " + getLabelUse().get());
                } else {
                    System.err.println("expecting label");
                }
                break;
        }
    }
}
