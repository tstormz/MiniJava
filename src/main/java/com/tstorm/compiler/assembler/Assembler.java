package com.tstorm.compiler.assembler;

import java.io.PrintWriter;
import java.util.Optional;

/**
 * Created by tstorm on 11/23/16.
 */
public abstract class Assembler {

    private static int labelNum = 1;
    private static int variableNum = 1;
    private Optional<String> label = Optional.empty();

    public abstract void generateCode(PrintWriter out);

    public boolean hasLabel() {
        return label.isPresent();
    }

    public void setLabel(String label) {
        this.label = Optional.of(label);
    }

    public Optional<String> getLabel() {
        return this.label;
    }

    public Optional<String> getLabelUse() {
        if (hasLabel()) {
            String s = label.get();
            int length = s.length();
            return Optional.of(s.substring(0, length - 1));
        } else {
            return label;
        }
    }

    public String generateLabel() {
        return String.format("L%d:", labelNum++);
    }

    public int generateId() {
        return variableNum++;
    }

    public static void reset() {
        labelNum = 1;
        variableNum = 1;
    }

}
