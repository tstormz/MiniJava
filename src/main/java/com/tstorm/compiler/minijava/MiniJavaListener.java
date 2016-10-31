// Generated from /Users/tstorm/Documents/csc444/minijava/src/main/grammar/MiniJava.g4 by ANTLR 4.5.3
package com.tstorm.compiler.minijava;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link MiniJavaParser}.
 */
public interface MiniJavaListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link MiniJavaParser#goal}.
	 * @param ctx the parse tree
	 */
	void enterGoal(MiniJavaParser.GoalContext ctx);
	/**
	 * Exit a parse tree produced by {@link MiniJavaParser#goal}.
	 * @param ctx the parse tree
	 */
	void exitGoal(MiniJavaParser.GoalContext ctx);
	/**
	 * Enter a parse tree produced by {@link MiniJavaParser#mainClass}.
	 * @param ctx the parse tree
	 */
	void enterMainClass(MiniJavaParser.MainClassContext ctx);
	/**
	 * Exit a parse tree produced by {@link MiniJavaParser#mainClass}.
	 * @param ctx the parse tree
	 */
	void exitMainClass(MiniJavaParser.MainClassContext ctx);
	/**
	 * Enter a parse tree produced by {@link MiniJavaParser#mainClassName}.
	 * @param ctx the parse tree
	 */
	void enterMainClassName(MiniJavaParser.MainClassNameContext ctx);
	/**
	 * Exit a parse tree produced by {@link MiniJavaParser#mainClassName}.
	 * @param ctx the parse tree
	 */
	void exitMainClassName(MiniJavaParser.MainClassNameContext ctx);
	/**
	 * Enter a parse tree produced by {@link MiniJavaParser#args}.
	 * @param ctx the parse tree
	 */
	void enterArgs(MiniJavaParser.ArgsContext ctx);
	/**
	 * Exit a parse tree produced by {@link MiniJavaParser#args}.
	 * @param ctx the parse tree
	 */
	void exitArgs(MiniJavaParser.ArgsContext ctx);
	/**
	 * Enter a parse tree produced by {@link MiniJavaParser#className}.
	 * @param ctx the parse tree
	 */
	void enterClassName(MiniJavaParser.ClassNameContext ctx);
	/**
	 * Exit a parse tree produced by {@link MiniJavaParser#className}.
	 * @param ctx the parse tree
	 */
	void exitClassName(MiniJavaParser.ClassNameContext ctx);
	/**
	 * Enter a parse tree produced by {@link MiniJavaParser#parentClassName}.
	 * @param ctx the parse tree
	 */
	void enterParentClassName(MiniJavaParser.ParentClassNameContext ctx);
	/**
	 * Exit a parse tree produced by {@link MiniJavaParser#parentClassName}.
	 * @param ctx the parse tree
	 */
	void exitParentClassName(MiniJavaParser.ParentClassNameContext ctx);
	/**
	 * Enter a parse tree produced by {@link MiniJavaParser#classDeclaration}.
	 * @param ctx the parse tree
	 */
	void enterClassDeclaration(MiniJavaParser.ClassDeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link MiniJavaParser#classDeclaration}.
	 * @param ctx the parse tree
	 */
	void exitClassDeclaration(MiniJavaParser.ClassDeclarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link MiniJavaParser#varDeclaration}.
	 * @param ctx the parse tree
	 */
	void enterVarDeclaration(MiniJavaParser.VarDeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link MiniJavaParser#varDeclaration}.
	 * @param ctx the parse tree
	 */
	void exitVarDeclaration(MiniJavaParser.VarDeclarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link MiniJavaParser#variableName}.
	 * @param ctx the parse tree
	 */
	void enterVariableName(MiniJavaParser.VariableNameContext ctx);
	/**
	 * Exit a parse tree produced by {@link MiniJavaParser#variableName}.
	 * @param ctx the parse tree
	 */
	void exitVariableName(MiniJavaParser.VariableNameContext ctx);
	/**
	 * Enter a parse tree produced by {@link MiniJavaParser#parameter}.
	 * @param ctx the parse tree
	 */
	void enterParameter(MiniJavaParser.ParameterContext ctx);
	/**
	 * Exit a parse tree produced by {@link MiniJavaParser#parameter}.
	 * @param ctx the parse tree
	 */
	void exitParameter(MiniJavaParser.ParameterContext ctx);
	/**
	 * Enter a parse tree produced by {@link MiniJavaParser#methodDeclaration}.
	 * @param ctx the parse tree
	 */
	void enterMethodDeclaration(MiniJavaParser.MethodDeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link MiniJavaParser#methodDeclaration}.
	 * @param ctx the parse tree
	 */
	void exitMethodDeclaration(MiniJavaParser.MethodDeclarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link MiniJavaParser#methodName}.
	 * @param ctx the parse tree
	 */
	void enterMethodName(MiniJavaParser.MethodNameContext ctx);
	/**
	 * Exit a parse tree produced by {@link MiniJavaParser#methodName}.
	 * @param ctx the parse tree
	 */
	void exitMethodName(MiniJavaParser.MethodNameContext ctx);
	/**
	 * Enter a parse tree produced by {@link MiniJavaParser#parameterName}.
	 * @param ctx the parse tree
	 */
	void enterParameterName(MiniJavaParser.ParameterNameContext ctx);
	/**
	 * Exit a parse tree produced by {@link MiniJavaParser#parameterName}.
	 * @param ctx the parse tree
	 */
	void exitParameterName(MiniJavaParser.ParameterNameContext ctx);
	/**
	 * Enter a parse tree produced by {@link MiniJavaParser#returnType}.
	 * @param ctx the parse tree
	 */
	void enterReturnType(MiniJavaParser.ReturnTypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link MiniJavaParser#returnType}.
	 * @param ctx the parse tree
	 */
	void exitReturnType(MiniJavaParser.ReturnTypeContext ctx);
	/**
	 * Enter a parse tree produced by {@link MiniJavaParser#returnStatement}.
	 * @param ctx the parse tree
	 */
	void enterReturnStatement(MiniJavaParser.ReturnStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link MiniJavaParser#returnStatement}.
	 * @param ctx the parse tree
	 */
	void exitReturnStatement(MiniJavaParser.ReturnStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link MiniJavaParser#type}.
	 * @param ctx the parse tree
	 */
	void enterType(MiniJavaParser.TypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link MiniJavaParser#type}.
	 * @param ctx the parse tree
	 */
	void exitType(MiniJavaParser.TypeContext ctx);
	/**
	 * Enter a parse tree produced by {@link MiniJavaParser#t}.
	 * @param ctx the parse tree
	 */
	void enterT(MiniJavaParser.TContext ctx);
	/**
	 * Exit a parse tree produced by {@link MiniJavaParser#t}.
	 * @param ctx the parse tree
	 */
	void exitT(MiniJavaParser.TContext ctx);
	/**
	 * Enter a parse tree produced by {@link MiniJavaParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterStatement(MiniJavaParser.StatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link MiniJavaParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitStatement(MiniJavaParser.StatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link MiniJavaParser#conditional}.
	 * @param ctx the parse tree
	 */
	void enterConditional(MiniJavaParser.ConditionalContext ctx);
	/**
	 * Exit a parse tree produced by {@link MiniJavaParser#conditional}.
	 * @param ctx the parse tree
	 */
	void exitConditional(MiniJavaParser.ConditionalContext ctx);
	/**
	 * Enter a parse tree produced by {@link MiniJavaParser#elseCond}.
	 * @param ctx the parse tree
	 */
	void enterElseCond(MiniJavaParser.ElseCondContext ctx);
	/**
	 * Exit a parse tree produced by {@link MiniJavaParser#elseCond}.
	 * @param ctx the parse tree
	 */
	void exitElseCond(MiniJavaParser.ElseCondContext ctx);
	/**
	 * Enter a parse tree produced by {@link MiniJavaParser#loop}.
	 * @param ctx the parse tree
	 */
	void enterLoop(MiniJavaParser.LoopContext ctx);
	/**
	 * Exit a parse tree produced by {@link MiniJavaParser#loop}.
	 * @param ctx the parse tree
	 */
	void exitLoop(MiniJavaParser.LoopContext ctx);
	/**
	 * Enter a parse tree produced by {@link MiniJavaParser#print}.
	 * @param ctx the parse tree
	 */
	void enterPrint(MiniJavaParser.PrintContext ctx);
	/**
	 * Exit a parse tree produced by {@link MiniJavaParser#print}.
	 * @param ctx the parse tree
	 */
	void exitPrint(MiniJavaParser.PrintContext ctx);
	/**
	 * Enter a parse tree produced by {@link MiniJavaParser#varAssignment}.
	 * @param ctx the parse tree
	 */
	void enterVarAssignment(MiniJavaParser.VarAssignmentContext ctx);
	/**
	 * Exit a parse tree produced by {@link MiniJavaParser#varAssignment}.
	 * @param ctx the parse tree
	 */
	void exitVarAssignment(MiniJavaParser.VarAssignmentContext ctx);
	/**
	 * Enter a parse tree produced by {@link MiniJavaParser#elementAssignment}.
	 * @param ctx the parse tree
	 */
	void enterElementAssignment(MiniJavaParser.ElementAssignmentContext ctx);
	/**
	 * Exit a parse tree produced by {@link MiniJavaParser#elementAssignment}.
	 * @param ctx the parse tree
	 */
	void exitElementAssignment(MiniJavaParser.ElementAssignmentContext ctx);
	/**
	 * Enter a parse tree produced by {@link MiniJavaParser#unwrapVariableName}.
	 * @param ctx the parse tree
	 */
	void enterUnwrapVariableName(MiniJavaParser.UnwrapVariableNameContext ctx);
	/**
	 * Exit a parse tree produced by {@link MiniJavaParser#unwrapVariableName}.
	 * @param ctx the parse tree
	 */
	void exitUnwrapVariableName(MiniJavaParser.UnwrapVariableNameContext ctx);
	/**
	 * Enter a parse tree produced by {@link MiniJavaParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterExpression(MiniJavaParser.ExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link MiniJavaParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitExpression(MiniJavaParser.ExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link MiniJavaParser#rhs}.
	 * @param ctx the parse tree
	 */
	void enterRhs(MiniJavaParser.RhsContext ctx);
	/**
	 * Exit a parse tree produced by {@link MiniJavaParser#rhs}.
	 * @param ctx the parse tree
	 */
	void exitRhs(MiniJavaParser.RhsContext ctx);
}