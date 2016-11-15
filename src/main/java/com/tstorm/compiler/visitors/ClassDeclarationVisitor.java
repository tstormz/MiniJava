package com.tstorm.compiler.visitors;

import com.tstorm.compiler.minijava.MiniJavaBaseVisitor;
import com.tstorm.compiler.minijava.MiniJavaParser;
import com.tstorm.compiler.rules.Klass;
import com.tstorm.compiler.rules.Method;
import com.tstorm.compiler.rules.Type;

import java.util.List;
import java.util.Map;

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
        for (MiniJavaParser.VarDeclarationContext var : ctx.varDeclaration()) {
            klass.addField(var.accept(new VarDeclarationVisitor()));
        }
        for (MiniJavaParser.MethodDeclarationContext method : ctx.methodDeclaration()) {
            Method m = method.accept(new MethodDeclarationVisitor());
            if (signatureIsUnique(klass.getMethodSet(), m)) {
                klass.addMethod(m);
            } else {
                System.err.println(String.format("%s is already defined", m.printMethodSignature()));
            }
        }
        return klass;
    }

    /**
     * Checks if the method name is already declared, then checks the parameter list
     *
     * @param definedMethods list of all methods previously declared in this class
     * @param newMethod the new method declaration
     * @return true if the method signature is unique
     */
    private boolean signatureIsUnique(Map<String, List<Method>> definedMethods, Method newMethod) {
        if (definedMethods.containsKey(newMethod.getMethodName())) {
            boolean isUnique = true;
            // for each method already defined using this name
            for (Method m : definedMethods.get(newMethod.getMethodName())) {
                // check that none have the same parameter types
                isUnique &= compareParameters(m, newMethod, 0);
            }
            return isUnique;
        } else {
            // if the method name isn't previously declared
            return true;
        }
    }

    /**
     * Compares the type of each parameter for two given methods
     *
     * @param prevDefMethod the previously defined method
     * @param newDefMethod the new method under inspection
     * @param pc the parameter counter
     * @return true if the two methods have different signatures, false otherwise
     */
    private boolean compareParameters(Method prevDefMethod, Method newDefMethod, int pc) {
        // if we reached the end of the parameter list
        if (prevDefMethod.getParameters().size() == pc) {
            // return true if the new method has more parameters left
            return newDefMethod.getParameters().size() > pc;
        } else if(newDefMethod.getParameters().size() == pc) {
            // and the other way around
            return prevDefMethod.getParameters().size() > pc;
        }
        else {
            Type prevDefParam = prevDefMethod.getParameters().get(pc).getType();
            Type newDefParam = newDefMethod.getParameters().get(pc).getType();
            // compare these params and recurse
            return (!prevDefParam.equals(newDefParam)) || compareParameters(prevDefMethod, newDefMethod, pc + 1);
        }
    }

}
