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

    private final Map<String, Type> varIdentifierMap;
    private final Map<String, Type> methodIdentifierMap;
    private final Map<String, String> idToClassNameMap = new HashMap<>();

    public SymbolTable(Klass k) {
        this.varIdentifierMap = k.getFields();
        this.methodIdentifierMap = k.getMethods();
        for (Variable v : k.getFieldSet().values()) {
//            System.out.println("--- " + v.getVariableName());
            if (v.getClassType().isPresent()) {
//                System.out.println("adding " + v.getClassType().get());
                idToClassNameMap.put(v.getVariableName(), v.getClassType().get());
            }
        }
    }

    public Type getVarType(String symbol) {
        return varIdentifierMap.get(symbol);
    }

    public Type getMethodType(String symbol) {
        return methodIdentifierMap.get(symbol);
    }

    public Optional<String> getClassReference(String symbol) {
        return Optional.ofNullable(idToClassNameMap.get(symbol));
    }

}
