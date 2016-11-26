package com.tstorm.compiler.rules.statements;

import com.tstorm.compiler.assembler.Assembler;
import com.tstorm.compiler.assembler.KlassPrintWriter;
import com.tstorm.compiler.rules.Variable;
import com.tstorm.compiler.rules.expressions.Expression;
import com.tstorm.compiler.rules.expressions.Identifier;
import com.tstorm.compiler.typechecker.Visitor;

import java.io.PrintWriter;
import java.util.Optional;

/**
 * Created by tstorm on 11/1/16.
 */
public class Assignment extends Assembler implements Statement {

    public static final String ERROR = "Assignment Error: '%s' is expecting type '%s'";

    private final Identifier id;
    private final Expression expression;

    public Assignment(Identifier src, Expression expression) {
        this.id = src;
        this.expression = expression;
    }

    public String getSrcVariableName() {
        return id.toString();
    }

    public Identifier getSrcIdentifier() {
        return id;
    }

    public Expression getExpression() {
        return expression;
    }

    public String toString() {
        return id.toString() + " = " + expression.toString();
    }

    @Override
    public boolean accept(Visitor v) {
        return v.visit(this);
    }

    @Override
    public void generateCode(PrintWriter out) {
        Optional<Variable> variable = id.getVariable();
        if (variable.isPresent()) {
            int id = variable.get().getId();
            if (id >= 0) {
                ((Assembler) expression).generateCode(out);
                out.println("istore " + id);
            } else {
                putField(out, variable.get());
            }
        } else {
            System.err.println(String.format(Identifier.UNBOUND, id.toString()));
        }
    }

    private void putField(PrintWriter out, Variable v) {
        out.println("aload_0");
        ((Assembler) expression).generateCode(out);
        out.print("putfield ");
        if (out instanceof KlassPrintWriter) {
            out.print(((KlassPrintWriter) out).getCurrentKlass().getClassName() + "/");
        } else {
            System.err.println("Error: requires KlassPrintWriter");
        }
        out.println(String.format("%s %s", v.getVariableName(), v.getType().toJasmin()));
    }
}
