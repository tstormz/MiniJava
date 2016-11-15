package com.tstorm.compiler.visitors;

import com.tstorm.compiler.minijava.MiniJavaBaseVisitor;
import com.tstorm.compiler.minijava.MiniJavaParser;
import com.tstorm.compiler.rules.Method;
import com.tstorm.compiler.rules.statements.Statement;
import com.tstorm.compiler.rules.Type;
import com.tstorm.compiler.rules.Variable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by tstorm on 10/30/16.
 */
public class MethodDeclarationVisitor extends MiniJavaBaseVisitor<Method> {

    private final List<Variable> params = new ArrayList<>();
    private final Map<String, Variable> locals = new HashMap<>();

    @Override
    public Method visitMethodDeclaration(MiniJavaParser.MethodDeclarationContext ctx) {
        Type returnType = getReturnType(ctx.returnType());
        for (MiniJavaParser.ParameterContext param : ctx.parameter()) {
            params.add(param.accept(new ParameterVisitor()));
        }
        for (MiniJavaParser.VarDeclarationContext local : ctx.varDeclaration()) {
            Variable v = local.accept(new VarDeclarationVisitor());
            if (variableNameIsUnique(v.getVariableName())) {
                locals.put(local.variableName().getText(), v);
            } else {
                System.err.println(String.format(Method.REDECLARED_VAR, v.getVariableName()));
            }
        }
        List<Statement> body = new ArrayList<>();
        for (MiniJavaParser.StatementContext statement : ctx.statement()) {
            body.add(statement.accept(new StatementVisitor()));
        }
        body.add(ctx.returnStatement().accept(new ReturnStatementVisitor()));
        return new Method(ctx.methodName().getText(), returnType, params, locals, body);
    }

    private Type getReturnType(MiniJavaParser.ReturnTypeContext returnTypeContext) {
        MiniJavaParser.TContext typeContext = returnTypeContext.type().t();
        if (typeContext.className() == null) {
            return new Type(Type.Primitive.fromString(returnTypeContext.getText()));
        } else {
            return new Type(typeContext.className().getText());
        }
    }

    private boolean variableNameIsUnique(String variableName) {
        for (Variable v : params) {
            if (v.getVariableName().equals(variableName)) {
                return false;
            }
        }
        return !locals.containsKey(variableName);
    }

}
