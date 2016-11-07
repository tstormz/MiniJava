package com.tstorm.compiler.rules;

import com.tstorm.compiler.rules.statements.Statement;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * Created by tstorm on 10/29/16.
 */
public class Method {

    private final String methodName;
    private final Type returnType;
    private Optional<String> classReturnType;
    private final List<Variable> parameters;
    private final Map<String, Variable> locals;
    private List<Statement> body = new ArrayList<>();

    public Method(String methodName, Type returnType, String classReturnType,
                  List<Variable> params, Map<String, Variable> locals,
                  List<Statement> body) {
        this(methodName, returnType, params, locals, body);
        this.classReturnType = Optional.of(classReturnType);
    }

    public Method(String methodName, Type returnType, List<Variable> params,
                  Map<String, Variable> locals, List<Statement> body) {
        this.methodName = methodName;
        this.returnType = returnType;
        this.parameters = params;
        this.locals = locals;
        this.body = body;
        this.classReturnType = Optional.empty();
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

    public Optional<String> getClassReturnType() {
        return classReturnType;
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

}
