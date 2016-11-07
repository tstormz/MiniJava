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

    @Override
    public Method visitMethodDeclaration(MiniJavaParser.MethodDeclarationContext ctx) {
        Type returnType = new Type(Type.Primitive.fromString(ctx.returnType().getText()));
        List<Variable> params = new ArrayList<>();
        for (MiniJavaParser.ParameterContext param : ctx.parameter()) {
            params.add(param.accept(new ParameterVisitor()));
        }
        Map<String, Variable> locals = new HashMap<>();
        for (MiniJavaParser.VarDeclarationContext local : ctx.varDeclaration()) {
            locals.put(local.variableName().getText(), local.accept(new VarDeclarationVisitor()));
        }
        List<Statement> body = new ArrayList<>();
        for (MiniJavaParser.StatementContext statement : ctx.statement()) {
            body.add(statement.accept(new StatementVisitor()));
        }
        body.add(ctx.returnStatement().accept(new ReturnStatementVisitor()));
        return new Method(ctx.methodName().getText(), returnType, params, locals, body);
    }

}
