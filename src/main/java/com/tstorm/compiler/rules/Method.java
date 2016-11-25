package com.tstorm.compiler.rules;

import com.tstorm.compiler.assembler.Assembler;
import com.tstorm.compiler.rules.statements.Statement;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * Created by tstorm on 10/29/16.
 */
public class Method {

    public static final String REDECLARED_VAR = "Variable '%s' is already defined in this scope";
    public static final String NOT_FOUND_ERROR = "no %s method found for %s on line %d";

    private final String methodName;
    private final Type returnType;
    private final List<Variable> parameters;
    private final Map<String, Variable> locals;
    private List<Statement> body = new ArrayList<>();

    public Method(String methodName, Type returnType, List<Variable> params,
                  Map<String, Variable> locals, List<Statement> body) {
        this.methodName = methodName;
        this.returnType = returnType;
        this.parameters = params;
        this.locals = locals;
        this.body = body;
    }

    public String getMethodName() {
        return methodName;
    }

    public List<Statement> getBody() {
        return body;
    }

    public Type getReturnType() {
        return returnType;
    }

    public List<Variable> getParameters() {
        return parameters;
    }

    public Optional<Variable> findVariable(String callerId) {
        Optional<Variable> variable;
        variable = Optional.ofNullable(locals.get(callerId));
        if (!variable.isPresent()) {
            variable = Optional.ofNullable(findParameter(callerId));
        }
        return variable;
    }

    private Variable findParameter(String callerId) {
        for (Variable v : parameters) {
            if (v.getVariableName().equals(callerId)) {
                return v;
            }
        }
        return null;
    }

    public String printMethodSignature() {
        String paramList = "";
        for (Variable param : parameters) {
            paramList += ", " + param.getType().toString();
        }
        if (parameters.size() > 0) {
            return methodName + "(" + paramList.substring(2) + ")";
        } else {
            return methodName + "()";
        }
    }

    public void generateBody(PrintWriter out) {
        Assembler.resetLabels();
        for (Statement stmt : body) {
            ((Assembler) stmt).generateCode(out);
        }
    }

    @Override
    public String toString() {
        String s = "   " + returnType + " " + methodName + "()";
        if (!parameters.isEmpty()) {
            for (Variable v : parameters) {
                s += "\n   param: " + v.toString();
            }
        }
        for (Variable v : locals.values()) {
            s += "\n      " + v.toString();
        }
        for (Statement stat : body) {
            s += "\n      " + stat.toString();
        }
        return s;
    }

    public void declaration(PrintWriter out) {
        out.print(".method public " + methodName + "(");
        for (Variable param : parameters) {
            out.print(param.getType().toJasmin());
        }
        out.println(")" + returnType.toJasmin());
    }

}
