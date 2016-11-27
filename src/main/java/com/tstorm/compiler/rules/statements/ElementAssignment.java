package com.tstorm.compiler.rules.statements;

import com.tstorm.compiler.assembler.Assembler;
import com.tstorm.compiler.assembler.KlassPrintWriter;
import com.tstorm.compiler.minijava.MiniJavaParser;
import com.tstorm.compiler.rules.Variable;
import com.tstorm.compiler.rules.expressions.Expression;
import com.tstorm.compiler.rules.expressions.Identifier;
import com.tstorm.compiler.rules.statements.Statement;
import com.tstorm.compiler.typechecker.Visitor;

import java.io.PrintWriter;
import java.util.Optional;

/**
 * Created by tstorm on 11/1/16.
 */
public class ElementAssignment extends Assembler implements Statement {

    public static final String ASSIGN_ERROR = "Assignment Error: Incompatible types, expected 'int' found '%s'";

    private final Identifier dest;
    private final Expression index, assignment;

    public ElementAssignment(String variableName, Expression index, Expression assignment) {
        this.dest = new Identifier(variableName);
        this.index = index;
        this.assignment = assignment;
    }

    public String getDestination() {
        return dest.toString();
    }

    public Identifier getDestIdentifier() {
        return dest;
    }

    public Expression getIndex() {
        return index;
    }

    public Expression getAssignment() {
        return assignment;
    }

    public String toString() {
        return dest.toString() + "[" + index.toString() + "] = " + assignment.toString();
    }

    @Override
    public boolean accept(Visitor v) {
        return v.visit(this);
    }

    @Override
    public void generateCode(PrintWriter out) {
        Optional<Variable> array = dest.getVariable();
        if (array.isPresent()) {
            int id = array.get().getId();
            if (id >= 0) {
                out.println("aload " + id + " ; " + array.get().toString());
            } else {
                getField(out, array.get());
            }
        } else {
            System.err.println(String.format(Identifier.UNBOUND, dest.toString()));
        }
        ((Assembler) index).generateCode(out);
        ((Assembler) assignment).generateCode(out);
        out.println("iastore");
    }

    private void getField(PrintWriter out, Variable v) {
        out.println("aload_0");
        out.print("getfield ");
        if (out instanceof KlassPrintWriter) {
            out.print(((KlassPrintWriter) out).getCurrentKlass().getClassName() + "/");
        } else {
            System.err.println("Error: requires KlassPrintWriter");
        }
        out.println(String.format("%s %s", v.getVariableName(), v.getType().toJasmin()));
    }
}
