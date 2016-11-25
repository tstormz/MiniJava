package com.tstorm.compiler.rules;

import com.tstorm.compiler.typechecker.TypeChecker;
import jasmin.ClassFile;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

/**
 * Created by tstorm on 10/29/16.
 */
public class Goal {

    private final Klass mainClass;
    private final Set<Klass> classes;

    public Goal(Klass mainClass, Set<Klass> classes) {
        this.mainClass = mainClass;
        this.classes = classes;
    }

    public boolean typeCheck() {
        TypeChecker typeChecker;
        boolean pass = true;
        for (Klass k : classes) {
            typeChecker = new TypeChecker(k);
            pass &= typeChecker.checkType();
        }
        typeChecker = new TypeChecker(mainClass);
        pass &= typeChecker.checkType();
        return pass;
    }

    public Goal generateCode() throws IOException {
        mainClass.generateCode();
        for (Klass k : classes) {
            k.generateCode();
        }
        return this;
    }

    public void print() {
        System.out.println("main: " + mainClass.toString());
        for (Klass klass : classes) {
            System.out.println("class: " + klass.toString());
            if (klass.getParent().isPresent()) {
                System.out.println(" parent: " + klass.getParent().get().toString());
            }
            if (!klass.getFields().isEmpty()) {
                for (Variable var : klass.getFields().values()) {
                    System.out.println(var.toString());
                }
            }
            if (!klass.getMethods().isEmpty()) {
                for (List<Method> methods : klass.getMethodSet().values()) {
                    for (Method method : methods) {
                        System.out.println(method.toString());
                    }
                }
            }
        }
    }

    public void compileAndRun() throws Exception {
        for (Klass k : classes) {
            compile(k);
        }
        compile(mainClass);
        ProcessBuilder builder = new ProcessBuilder("java", mainClass.getClassName());
        Process process = builder.directory(new File("build")).start();
        try (InputStream in = process.getInputStream()) {
            Scanner scanner = new Scanner(in).useDelimiter("\\A");
            while (scanner.hasNext()) {
                System.out.printf(scanner.next());
            }
        }
    }

    private void compile(Klass k) throws Exception {
        ClassFile classFile = new ClassFile();
        BufferedReader jFile = new BufferedReader(new FileReader(k.getPath("j").toFile()));
        classFile.readJasmin(jFile, "", false);
        try (OutputStream output = Files.newOutputStream(k.getPath("class"))) {
            classFile.write(output);
        }
    }
}
