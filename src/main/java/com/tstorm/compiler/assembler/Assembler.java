package com.tstorm.compiler.assembler;

import java.io.PrintWriter;
import java.util.Optional;

/**
 * Created by tstorm on 11/23/16.
 */
public abstract class Assembler {

    private static int labelNum = 1;
    private static int variableNum = 1;

    public abstract void generateCode(PrintWriter out);

    public Label generateLabel() {
        return new Label();
    }

    public int generateId() {
        return variableNum++;
    }

    public static void reset() {
        labelNum = 1;
        variableNum = 1;
    }

    public class Label {

        private final String label;

        public Label() {
            label = String.format("L%d:", labelNum++);
        }

        public String getLabel() {
            return label;
        }

        public String getLabelUse() {
            return label.substring(0, label.length() - 1);
        }

    }

}
