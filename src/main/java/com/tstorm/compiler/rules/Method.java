package com.tstorm.compiler.rules;

import com.tstorm.compiler.rules.statements.Statement;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by tstorm on 10/29/16.
 */
public class Method {

    private final String methodName;
    private final Type returnType;
    private final Map<String, Variable> parameters;
    private final Map<String, Variable> locals;
    private List<Statement> body = new ArrayList<>();

    public Method(String methodName, Type returnType, Map<String, Variable> params,
                  Map<String, Variable> locals, List<Statement> body) {
        this.methodName = methodName;
        this.returnType = returnType;
        this.parameters = params;
        this.locals = locals;
        this.body = body;
    }

    @Override
    public String toString() {
        String s = "   " + returnType + " " + methodName + "()";
        if (!parameters.isEmpty()) {
            for (Variable v : parameters.values()) {
                s += "\n   param: " + v.toString();
            }
        }
        for (Statement stat : body) {
            s += "\n      " + stat.toString();
        }
        return s;
    }

}
