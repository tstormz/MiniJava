// Generated from /Users/tstorm/Documents/csc444/minijava/src/main/antlr4/MiniJava.g4 by ANTLR 4.5.3
package com.tstorm.compiler.minijava;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link MiniJavaParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface MiniJavaVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link MiniJavaParser#goal}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitGoal(MiniJavaParser.GoalContext ctx);
	/**
	 * Visit a parse tree produced by {@link MiniJavaParser#mainClass}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMainClass(MiniJavaParser.MainClassContext ctx);
	/**
	 * Visit a parse tree produced by {@link MiniJavaParser#mainClassName}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMainClassName(MiniJavaParser.MainClassNameContext ctx);
	/**
	 * Visit a parse tree produced by {@link MiniJavaParser#args}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArgs(MiniJavaParser.ArgsContext ctx);
	/**
	 * Visit a parse tree produced by {@link MiniJavaParser#className}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitClassName(MiniJavaParser.ClassNameContext ctx);
	/**
	 * Visit a parse tree produced by {@link MiniJavaParser#parentClassName}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParentClassName(MiniJavaParser.ParentClassNameContext ctx);
	/**
	 * Visit a parse tree produced by {@link MiniJavaParser#classDeclaration}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitClassDeclaration(MiniJavaParser.ClassDeclarationContext ctx);
	/**
	 * Visit a parse tree produced by {@link MiniJavaParser#varDeclaration}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVarDeclaration(MiniJavaParser.VarDeclarationContext ctx);
	/**
	 * Visit a parse tree produced by {@link MiniJavaParser#variableName}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVariableName(MiniJavaParser.VariableNameContext ctx);
	/**
	 * Visit a parse tree produced by {@link MiniJavaParser#methodDeclaration}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMethodDeclaration(MiniJavaParser.MethodDeclarationContext ctx);
	/**
	 * Visit a parse tree produced by {@link MiniJavaParser#methodName}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMethodName(MiniJavaParser.MethodNameContext ctx);
	/**
	 * Visit a parse tree produced by {@link MiniJavaParser#parameterName}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParameterName(MiniJavaParser.ParameterNameContext ctx);
	/**
	 * Visit a parse tree produced by {@link MiniJavaParser#returnStat}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitReturnStat(MiniJavaParser.ReturnStatContext ctx);
	/**
	 * Visit a parse tree produced by {@link MiniJavaParser#type}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitType(MiniJavaParser.TypeContext ctx);
	/**
	 * Visit a parse tree produced by {@link MiniJavaParser#t}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitT(MiniJavaParser.TContext ctx);
	/**
	 * Visit a parse tree produced by {@link MiniJavaParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStatement(MiniJavaParser.StatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link MiniJavaParser#conditional}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitConditional(MiniJavaParser.ConditionalContext ctx);
	/**
	 * Visit a parse tree produced by {@link MiniJavaParser#elseCond}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitElseCond(MiniJavaParser.ElseCondContext ctx);
	/**
	 * Visit a parse tree produced by {@link MiniJavaParser#loop}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLoop(MiniJavaParser.LoopContext ctx);
	/**
	 * Visit a parse tree produced by {@link MiniJavaParser#print}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPrint(MiniJavaParser.PrintContext ctx);
	/**
	 * Visit a parse tree produced by {@link MiniJavaParser#varAssignment}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVarAssignment(MiniJavaParser.VarAssignmentContext ctx);
	/**
	 * Visit a parse tree produced by {@link MiniJavaParser#elementAssignment}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitElementAssignment(MiniJavaParser.ElementAssignmentContext ctx);
	/**
	 * Visit a parse tree produced by {@link MiniJavaParser#unwrapVariableName}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUnwrapVariableName(MiniJavaParser.UnwrapVariableNameContext ctx);
	/**
	 * Visit a parse tree produced by {@link MiniJavaParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpression(MiniJavaParser.ExpressionContext ctx);
}