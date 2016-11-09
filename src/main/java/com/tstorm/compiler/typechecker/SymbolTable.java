package com.tstorm.compiler.typechecker;

import com.tstorm.compiler.rules.Klass;
import com.tstorm.compiler.rules.Type;
import com.tstorm.compiler.rules.Variable;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * Created by tstorm on 11/3/16.
 */
public class SymbolTable {

    private final Klass klass;
    private final Map<String, Type> varIdentifierMap;
    private final Map<String, Type> methodIdentifierMap;
    private final Map<String, String> idToClassNameMap = new HashMap<>();

    public SymbolTable(Klass k) {
        this.klass = k;
        this.varIdentifierMap = k.getFields();
        this.methodIdentifierMap = k.getMethods();
        for (Variable v : k.getFieldSet().values()) {
            if (v.getType().getClassName().isPresent()) {
                idToClassNameMap.put(v.getVariableName(), v.getType().getClassName().get());
            }
        }
    }

    public Optional<Type> getVarType(String symbol) {
        return Optional.ofNullable(varIdentifierMap.get(symbol));
    }

    public Optional<Type> getMethodType(String symbol) {
        return Optional.ofNullable(methodIdentifierMap.get(symbol));
    }

}
