package com.tstorm.compiler.typechecker;

import com.tstorm.compiler.rules.Type;

import java.util.Map;

/**
 * Created by tstorm on 11/3/16.
 */
public class SymbolTable {

    private final Map<String, Type> idToTypeMap;

    public SymbolTable(Map<String, Type> table) {
        this.idToTypeMap = table;
    }

    public Type getType(String symbol) {
        return idToTypeMap.get(symbol);
    }

}
