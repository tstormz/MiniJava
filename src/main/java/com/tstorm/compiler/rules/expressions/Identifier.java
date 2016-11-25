package com.tstorm.compiler.rules.expressions;

import com.tstorm.compiler.assembler.Assembler;
import com.tstorm.compiler.rules.Type;
import com.tstorm.compiler.rules.Variable;
import com.tstorm.compiler.typechecker.ExpressionVisitor;

import java.io.PrintWriter;
import java.util.Optional;

/**
 * Created by tstorm on 11/1/16.
 */
public class Identifier extends Assembler implements Expression {

    public static final String UNBOUND = "Error: Identifier is not bound";
    public static final String UNDECLARED = "Error: Identifier not declared";
    private String name;
    private Optional<Variable> variable;

    public Identifier(String name) {
        this.name = name;
    }

    public String toString() {
        return name;
    }

    public void bind(Optional<Variable> v) {
        this.variable = v;
    }

    public Optional<Variable> getVariable() {
        return variable;
    }

    @Override
    public Type accept(ExpressionVisitor v) {
        return v.visit(this);
    }

    @Override
    public void generateCode(PrintWriter out) {
        if (variable.isPresent()) {
            int id = variable.get().getId();
            if (id >= 0){
                out.println("iload " + id);
            } else {
                System.err.println(UNDECLARED);
            }
        } else {
            System.err.println(UNBOUND);
        }
    }
}
