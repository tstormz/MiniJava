package com.tstorm.compiler.visitors;

import com.tstorm.compiler.minijava.MiniJavaBaseVisitor;
import com.tstorm.compiler.minijava.MiniJavaParser;

/**
 * Created by tstorm on 10/31/16.
 */
public class VarAssignmentVisitor extends MiniJavaBaseVisitor<Integer> {

    @Override
    public Integer visitVarAssignment(MiniJavaParser.VarAssignmentContext ctx) {
        return new Integer(0);
    }

}
