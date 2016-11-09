package com.tstorm.compiler.visitors;

import com.tstorm.compiler.minijava.MiniJavaBaseVisitor;
import com.tstorm.compiler.minijava.MiniJavaParser;
import com.tstorm.compiler.rules.Klass;
import com.tstorm.compiler.rules.Method;
import com.tstorm.compiler.rules.Type;
import com.tstorm.compiler.rules.Variable;

import java.util.Map;
import java.util.Optional;

/**
 * Created by tstorm on 10/29/16.
 */
public class ClassDeclarationVisitor extends MiniJavaBaseVisitor<Klass> {

    @Override
    public Klass visitClassDeclaration(MiniJavaParser.ClassDeclarationContext ctx) {
        Klass klass;
        if (ctx.parentClassName() != null) {
            klass = new Klass(ctx.className().getText(), ctx.parentClassName().getText());
        } else {
            klass = new Klass(ctx.className().getText(), null);
        }
        VarDeclarationVisitor varDeclarationVisitor = new VarDeclarationVisitor();
        for (MiniJavaParser.VarDeclarationContext var : ctx.varDeclaration()) {
            klass.addField(var.accept(varDeclarationVisitor));
        }
        MethodDeclarationVisitor methodDeclarationVisitor = new MethodDeclarationVisitor();
        for (MiniJavaParser.MethodDeclarationContext method : ctx.methodDeclaration()) {
            Method m = method.accept(methodDeclarationVisitor);
            if (signatureIsUnique(klass.getMethodSet(), m)) {
                klass.addMethod(m);
            } else {
                System.err.println(String.format("%s is already defined", m.printMethodSignature()));
            }
        }
        return klass;
    }

    private boolean signatureIsUnique(Map<String, Method> definedMethods, Method newMethod) {
        if (definedMethods.containsKey(newMethod.getMethodName())) {
            int paramIndex = 0;
            boolean isUnique = false;
            for (Variable v : definedMethods.get(newMethod.getMethodName()).getParameters()) {
                Type t = newMethod.getParameters().get(paramIndex++).getType();
                if (!v.getType().equals(t)) {
                    isUnique = true;
                }
            }
            return isUnique || (paramIndex != newMethod.getParameters().size());
        } else {
            return true;
        }
    }

}
