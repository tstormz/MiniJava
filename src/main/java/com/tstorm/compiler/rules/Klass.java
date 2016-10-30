package com.tstorm.compiler.rules;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Created by tstorm on 10/29/16.
 */
public class Klass {

    private final String className;
    private final List<Field> fields = new ArrayList<>();
    private final List<Method> methods = new ArrayList<>();
    private Optional<Klass> parent = Optional.empty();

    public Klass(String className, Klass parent) {
        this(className);
        this.parent = Optional.of(parent);
    }

    public Klass(String className) {
        this.className = className;
    }

    @Override
    public String toString() {
        return className;
    }

}
