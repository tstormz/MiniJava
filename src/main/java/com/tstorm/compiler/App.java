package com.tstorm.compiler;

import com.tstorm.compiler.listeners.MyErrorListener;
import com.tstorm.compiler.minijava.MiniJavaLexer;
import com.tstorm.compiler.minijava.MiniJavaParser;
import com.tstorm.compiler.rules.Goal;
import com.tstorm.compiler.visitors.GoalVisitor;
import org.antlr.v4.runtime.ANTLRFileStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;

import java.io.IOException;

/**
 * Parse MiniJava grammar using antlr4
 */
public class App {
    public static void main( String[] args ) throws IOException {
        MiniJavaLexer lexer = new MiniJavaLexer(new ANTLRFileStream(args[0]));
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        MiniJavaParser parser = new MiniJavaParser(tokens);

        lexer.removeErrorListeners();
        parser.removeErrorListeners();

        lexer.addErrorListener(MyErrorListener.INSTANCE);
        parser.addErrorListener(MyErrorListener.INSTANCE);

        ParseTree tree = parser.goal();
        Goal g = new GoalVisitor().visit(tree);
        System.out.println(g.toString());
    }
}
