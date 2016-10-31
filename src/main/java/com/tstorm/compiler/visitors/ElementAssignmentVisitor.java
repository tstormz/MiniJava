package com.tstorm.compiler.visitors;

import com.tstorm.compiler.minijava.MiniJavaBaseVisitor;
import com.tstorm.compiler.minijava.MiniJavaParser;

/**
 * Created by tstorm on 10/31/16.
 */
public class ElementAssignmentVisitor extends MiniJavaBaseVisitor<Integer> {

    @Override
    public Integer visitElementAssignment(MiniJavaParser.ElementAssignmentContext ctx) {
        return new Integer(0);
    }

}
