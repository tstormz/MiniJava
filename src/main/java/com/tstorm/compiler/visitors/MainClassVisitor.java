package com.tstorm.compiler.visitors;

import com.tstorm.compiler.minijava.MiniJavaBaseVisitor;
import com.tstorm.compiler.minijava.MiniJavaParser;
import com.tstorm.compiler.rules.Klass;
import com.tstorm.compiler.rules.Method;
import com.tstorm.compiler.rules.Type;
import com.tstorm.compiler.rules.Variable;
import com.tstorm.compiler.rules.statements.Statement;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by tstorm on 10/29/16.
 */
public class MainClassVisitor extends MiniJavaBaseVisitor<Klass> {

    @Override
    public Klass visitMainClass(MiniJavaParser.MainClassContext ctx) {
        String mainClassName = ctx.mainClassName().getText();
        Klass main = new Klass(mainClassName, null);
        Type returnType = new Type();
        List<Variable> params = new ArrayList<>();
        Map<String, Variable> locals = new HashMap<>();
        List<Statement> body = new ArrayList<>();
        body.add(ctx.statement().accept(new StatementVisitor()));
        Method m = new Method("main", returnType, params, locals, body);
        main.addMethod(m);
        return main;
    }

}
