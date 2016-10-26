package com.tstorm.compiler;

import org.antlr.v4.runtime.ANTLRFileStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

import java.io.IOException;

/**
 * Parse MiniJava grammar using antlr4
 */
public class App 
{
    public static void main( String[] args ) throws IOException {
        MiniJavaLexer lexer = new MiniJavaLexer(new ANTLRFileStream(args[0]));
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        MiniJavaParser parser = new MiniJavaParser(tokens);

        lexer.removeErrorListeners();
        parser.removeErrorListeners();

        lexer.addErrorListener(MyErrorListener.INSTANCE);
        parser.addErrorListener(MyErrorListener.INSTANCE);

        ParseTree tree = parser.goal();
        ParseTreeWalker walker = new ParseTreeWalker();
        walker.walk(MyMiniJavaListener.INSTANCE, tree);
    }
}
