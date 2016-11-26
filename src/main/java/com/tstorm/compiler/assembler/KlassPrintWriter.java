package com.tstorm.compiler.assembler;

import com.tstorm.compiler.rules.Klass;

import java.io.PrintWriter;
import java.io.Writer;

/**
 * Created by tstorm on 11/26/16.
 */
public class KlassPrintWriter extends PrintWriter {

    private final Klass currentKlass;

    public KlassPrintWriter(Klass currentKlass, Writer writer) {
        super(writer);
        this.currentKlass = currentKlass;
    }

    public Klass getCurrentKlass() {
        return currentKlass;
    }

}
