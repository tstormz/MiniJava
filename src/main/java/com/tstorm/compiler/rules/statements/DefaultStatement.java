package com.tstorm.compiler.rules.statements;

import com.tstorm.compiler.assembler.Assembler;
import com.tstorm.compiler.minijava.MiniJavaParser;
import com.tstorm.compiler.typechecker.Visitor;
import com.tstorm.compiler.visitors.StatementVisitor;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by tstorm on 11/2/16.
 */
public class DefaultStatement extends Assembler implements Statement {

    private final List<Statement> nestedStatements = new ArrayList<>();

    public DefaultStatement(List<MiniJavaParser.StatementContext> statements) {
        for (MiniJavaParser.StatementContext stat : statements) {
            nestedStatements.add(stat.accept(new StatementVisitor()));
        }
    }

    public List<Statement> getNestedStatements() {
        return nestedStatements;
    }

    public String toString() {
        String s = "";
        for (Statement stat : nestedStatements) {
            s += "\n" + stat.toString();
        }
        return s;
    }

    @Override
    public boolean accept(Visitor v) {
        return v.visit(this);
    }

    @Override
    public void generateCode(PrintWriter out) {
        for (Statement stmt : nestedStatements) {
            ((Assembler) stmt).generateCode(out);
        }
    }
}
