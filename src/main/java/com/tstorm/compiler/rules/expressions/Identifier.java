package com.tstorm.compiler.rules.expressions;

import com.tstorm.compiler.assembler.Assembler;
import com.tstorm.compiler.assembler.KlassPrintWriter;
import com.tstorm.compiler.rules.Klass;
import com.tstorm.compiler.rules.Type;
import com.tstorm.compiler.rules.Variable;
import com.tstorm.compiler.typechecker.ExpressionVisitor;

import java.io.PrintWriter;
import java.util.Optional;

/**
 * Created by tstorm on 11/1/16.
 */
public class Identifier extends Assembler implements Expression {

    public static final String UNBOUND = "Error: Identifier %s is not bound";
    private Optional<Variable> variable = Optional.empty();
    private String name;

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
            if (id >= 0) {
                load(out, variable.get());
            } else {
                getField(out, variable.get());
            }
        } else if(name.equals("this")) {
            out.println("aload_0");
        } else {
            if (out instanceof KlassPrintWriter) {
                inheritField((KlassPrintWriter) out);
            } else {
                System.err.println("requires KlassPrintWriter");
            }
        }
    }

    private void inheritField(KlassPrintWriter out) {
        Klass klass = out.getCurrentKlass();
        if (klass.hasParent()) {
            inheritField(klass.getParent(), out);
        } else {
            System.err.println(String.format(UNBOUND, name));
        }
    }

    private void inheritField(Optional<Klass> parent, PrintWriter out) {
        if (parent.isPresent()) {
            if (parent.get().hasField(name)) {
                variable = parent.get().getField(name);
                getField(out, parent.get(), variable.get());
            } else {
                inheritField(parent.get().getParent(), out);
            }
        } else {
            System.err.println(String.format(UNBOUND, name));
        }
    }

    private void load(PrintWriter out, Variable variable) {
        if (variable.isInitialized()) {
            if (variable.getType().isPrimitive()) {
                out.print("iload ");
            } else {
                out.print("aload ");
            }
            out.println(variable.getId() + " ; " + variable.toString());
        } else {
            out.println("aconst_null");
        }
    }

    private void getField(PrintWriter out, Variable variable) {
        out.println("aload_0");
        out.print("getfield ");
        if (out instanceof KlassPrintWriter) {
            out.print(((KlassPrintWriter) out).getCurrentKlass().getClassName() + "/");
        } else {
            System.err.println("Error: requires KlassPrintWriter");
        }
        out.println(variable.getVariableName() + " " + variable.getType().toJasmin());
    }

    private void getField(PrintWriter out, Klass k, Variable v) {
        out.println("aload_0");
        out.println(String.format("getfield %s/%s %s", k.getClassName(), name, v.getType().toJasmin()));
    }
}
