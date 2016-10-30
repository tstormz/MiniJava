package com.tstorm.compiler.visitors;

import com.tstorm.compiler.minijava.MiniJavaBaseVisitor;
import com.tstorm.compiler.minijava.MiniJavaParser;
import com.tstorm.compiler.rules.Klass;

/**
 * Created by tstorm on 10/29/16.
 */
public class MainClassVisitor extends MiniJavaBaseVisitor<Klass> {

    @Override
    public Klass visitMainClass(MiniJavaParser.MainClassContext ctx) {
        String mainClassName = ctx.mainClassName().getText();
        return new Klass(mainClassName);
    }

}
