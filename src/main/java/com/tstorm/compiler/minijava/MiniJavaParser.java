// Generated from /Users/tstorm/Documents/csc444/minijava/src/main/antlr4/MiniJava.g4 by ANTLR 4.5.3
package com.tstorm.compiler.minijava;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class MiniJavaParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.5.3", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, T__8=9, 
		T__9=10, T__10=11, T__11=12, T__12=13, T__13=14, T__14=15, T__15=16, T__16=17, 
		T__17=18, T__18=19, T__19=20, T__20=21, T__21=22, T__22=23, T__23=24, 
		T__24=25, T__25=26, T__26=27, T__27=28, T__28=29, T__29=30, T__30=31, 
		T__31=32, T__32=33, T__33=34, T__34=35, T__35=36, T__36=37, INT=38, ID=39, 
		WS=40, LINE_COMMENT=41, MULTI_COMMENT=42;
	public static final int
		RULE_goal = 0, RULE_mainClass = 1, RULE_mainClassName = 2, RULE_args = 3, 
		RULE_className = 4, RULE_parentClassName = 5, RULE_classDeclaration = 6, 
		RULE_varDeclaration = 7, RULE_variableName = 8, RULE_methodDeclaration = 9, 
		RULE_methodName = 10, RULE_parameterName = 11, RULE_returnStat = 12, RULE_type = 13, 
		RULE_t = 14, RULE_statement = 15, RULE_conditional = 16, RULE_elseCond = 17, 
		RULE_loop = 18, RULE_print = 19, RULE_varAssignment = 20, RULE_elementAssignment = 21, 
		RULE_unwrapVariableName = 22, RULE_expression = 23;
	public static final String[] ruleNames = {
		"goal", "mainClass", "mainClassName", "args", "className", "parentClassName", 
		"classDeclaration", "varDeclaration", "variableName", "methodDeclaration", 
		"methodName", "parameterName", "returnStat", "type", "t", "statement", 
		"conditional", "elseCond", "loop", "print", "varAssignment", "elementAssignment", 
		"unwrapVariableName", "expression"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "'class'", "'{'", "'public'", "'static'", "'void'", "'main'", "'('", 
		"'String'", "'['", "']'", "')'", "'}'", "'extends'", "';'", "','", "'return'", 
		"'?'", "'int'", "'boolean'", "'if'", "'else'", "'while'", "'System.out.println'", 
		"'='", "'!'", "'&&'", "'<'", "'+'", "'-'", "'*'", "'.'", "'length'", "'true'", 
		"'false'", "'null'", "'this'", "'new'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, null, null, null, null, null, null, null, null, null, null, null, 
		null, null, null, null, null, null, null, null, null, null, null, null, 
		null, null, null, null, null, null, null, null, null, null, null, null, 
		null, null, "INT", "ID", "WS", "LINE_COMMENT", "MULTI_COMMENT"
	};
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}

	@Override
	public String getGrammarFileName() { return "MiniJava.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public MiniJavaParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}
	public static class GoalContext extends ParserRuleContext {
		public MainClassContext mainClass() {
			return getRuleContext(MainClassContext.class,0);
		}
		public List<ClassDeclarationContext> classDeclaration() {
			return getRuleContexts(ClassDeclarationContext.class);
		}
		public ClassDeclarationContext classDeclaration(int i) {
			return getRuleContext(ClassDeclarationContext.class,i);
		}
		public GoalContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_goal; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MiniJavaVisitor ) return ((MiniJavaVisitor<? extends T>)visitor).visitGoal(this);
			else return visitor.visitChildren(this);
		}
	}

	public final GoalContext goal() throws RecognitionException {
		GoalContext _localctx = new GoalContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_goal);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(48);
			mainClass();
			setState(52);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__0) {
				{
				{
				setState(49);
				classDeclaration();
				}
				}
				setState(54);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class MainClassContext extends ParserRuleContext {
		public MainClassNameContext mainClassName() {
			return getRuleContext(MainClassNameContext.class,0);
		}
		public ArgsContext args() {
			return getRuleContext(ArgsContext.class,0);
		}
		public StatementContext statement() {
			return getRuleContext(StatementContext.class,0);
		}
		public MainClassContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_mainClass; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MiniJavaVisitor ) return ((MiniJavaVisitor<? extends T>)visitor).visitMainClass(this);
			else return visitor.visitChildren(this);
		}
	}

	public final MainClassContext mainClass() throws RecognitionException {
		MainClassContext _localctx = new MainClassContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_mainClass);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(55);
			match(T__0);
			setState(56);
			mainClassName();
			setState(57);
			match(T__1);
			setState(58);
			match(T__2);
			setState(59);
			match(T__3);
			setState(60);
			match(T__4);
			setState(61);
			match(T__5);
			setState(62);
			match(T__6);
			setState(63);
			match(T__7);
			setState(64);
			match(T__8);
			setState(65);
			match(T__9);
			setState(66);
			args();
			setState(67);
			match(T__10);
			setState(68);
			match(T__1);
			setState(69);
			statement();
			setState(70);
			match(T__11);
			setState(71);
			match(T__11);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class MainClassNameContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(MiniJavaParser.ID, 0); }
		public MainClassNameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_mainClassName; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MiniJavaVisitor ) return ((MiniJavaVisitor<? extends T>)visitor).visitMainClassName(this);
			else return visitor.visitChildren(this);
		}
	}

	public final MainClassNameContext mainClassName() throws RecognitionException {
		MainClassNameContext _localctx = new MainClassNameContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_mainClassName);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(73);
			match(ID);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ArgsContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(MiniJavaParser.ID, 0); }
		public ArgsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_args; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MiniJavaVisitor ) return ((MiniJavaVisitor<? extends T>)visitor).visitArgs(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ArgsContext args() throws RecognitionException {
		ArgsContext _localctx = new ArgsContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_args);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(75);
			match(ID);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ClassNameContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(MiniJavaParser.ID, 0); }
		public ClassNameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_className; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MiniJavaVisitor ) return ((MiniJavaVisitor<? extends T>)visitor).visitClassName(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ClassNameContext className() throws RecognitionException {
		ClassNameContext _localctx = new ClassNameContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_className);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(77);
			match(ID);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ParentClassNameContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(MiniJavaParser.ID, 0); }
		public ParentClassNameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_parentClassName; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MiniJavaVisitor ) return ((MiniJavaVisitor<? extends T>)visitor).visitParentClassName(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ParentClassNameContext parentClassName() throws RecognitionException {
		ParentClassNameContext _localctx = new ParentClassNameContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_parentClassName);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(79);
			match(ID);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ClassDeclarationContext extends ParserRuleContext {
		public ClassNameContext className() {
			return getRuleContext(ClassNameContext.class,0);
		}
		public ParentClassNameContext parentClassName() {
			return getRuleContext(ParentClassNameContext.class,0);
		}
		public List<VarDeclarationContext> varDeclaration() {
			return getRuleContexts(VarDeclarationContext.class);
		}
		public VarDeclarationContext varDeclaration(int i) {
			return getRuleContext(VarDeclarationContext.class,i);
		}
		public List<MethodDeclarationContext> methodDeclaration() {
			return getRuleContexts(MethodDeclarationContext.class);
		}
		public MethodDeclarationContext methodDeclaration(int i) {
			return getRuleContext(MethodDeclarationContext.class,i);
		}
		public ClassDeclarationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_classDeclaration; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MiniJavaVisitor ) return ((MiniJavaVisitor<? extends T>)visitor).visitClassDeclaration(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ClassDeclarationContext classDeclaration() throws RecognitionException {
		ClassDeclarationContext _localctx = new ClassDeclarationContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_classDeclaration);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(81);
			match(T__0);
			setState(82);
			className();
			setState(85);
			_la = _input.LA(1);
			if (_la==T__12) {
				{
				setState(83);
				match(T__12);
				setState(84);
				parentClassName();
				}
			}

			setState(87);
			match(T__1);
			setState(91);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__17) | (1L << T__18) | (1L << ID))) != 0)) {
				{
				{
				setState(88);
				varDeclaration();
				}
				}
				setState(93);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(97);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__2) {
				{
				{
				setState(94);
				methodDeclaration();
				}
				}
				setState(99);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(100);
			match(T__11);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class VarDeclarationContext extends ParserRuleContext {
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public VariableNameContext variableName() {
			return getRuleContext(VariableNameContext.class,0);
		}
		public VarDeclarationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_varDeclaration; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MiniJavaVisitor ) return ((MiniJavaVisitor<? extends T>)visitor).visitVarDeclaration(this);
			else return visitor.visitChildren(this);
		}
	}

	public final VarDeclarationContext varDeclaration() throws RecognitionException {
		VarDeclarationContext _localctx = new VarDeclarationContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_varDeclaration);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(102);
			type();
			setState(103);
			variableName();
			setState(104);
			match(T__13);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class VariableNameContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(MiniJavaParser.ID, 0); }
		public VariableNameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_variableName; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MiniJavaVisitor ) return ((MiniJavaVisitor<? extends T>)visitor).visitVariableName(this);
			else return visitor.visitChildren(this);
		}
	}

	public final VariableNameContext variableName() throws RecognitionException {
		VariableNameContext _localctx = new VariableNameContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_variableName);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(106);
			match(ID);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class MethodDeclarationContext extends ParserRuleContext {
		public List<TypeContext> type() {
			return getRuleContexts(TypeContext.class);
		}
		public TypeContext type(int i) {
			return getRuleContext(TypeContext.class,i);
		}
		public MethodNameContext methodName() {
			return getRuleContext(MethodNameContext.class,0);
		}
		public ReturnStatContext returnStat() {
			return getRuleContext(ReturnStatContext.class,0);
		}
		public List<ParameterNameContext> parameterName() {
			return getRuleContexts(ParameterNameContext.class);
		}
		public ParameterNameContext parameterName(int i) {
			return getRuleContext(ParameterNameContext.class,i);
		}
		public List<VarDeclarationContext> varDeclaration() {
			return getRuleContexts(VarDeclarationContext.class);
		}
		public VarDeclarationContext varDeclaration(int i) {
			return getRuleContext(VarDeclarationContext.class,i);
		}
		public List<StatementContext> statement() {
			return getRuleContexts(StatementContext.class);
		}
		public StatementContext statement(int i) {
			return getRuleContext(StatementContext.class,i);
		}
		public MethodDeclarationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_methodDeclaration; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MiniJavaVisitor ) return ((MiniJavaVisitor<? extends T>)visitor).visitMethodDeclaration(this);
			else return visitor.visitChildren(this);
		}
	}

	public final MethodDeclarationContext methodDeclaration() throws RecognitionException {
		MethodDeclarationContext _localctx = new MethodDeclarationContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_methodDeclaration);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(108);
			match(T__2);
			setState(109);
			type();
			setState(110);
			methodName();
			setState(111);
			match(T__6);
			setState(123);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__17) | (1L << T__18) | (1L << ID))) != 0)) {
				{
				setState(112);
				type();
				setState(113);
				parameterName();
				setState(120);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==T__14) {
					{
					{
					setState(114);
					match(T__14);
					setState(115);
					type();
					setState(116);
					parameterName();
					}
					}
					setState(122);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
			}

			setState(125);
			match(T__10);
			setState(126);
			match(T__1);
			setState(130);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,6,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(127);
					varDeclaration();
					}
					} 
				}
				setState(132);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,6,_ctx);
			}
			setState(136);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__1) | (1L << T__19) | (1L << T__21) | (1L << T__22) | (1L << ID))) != 0)) {
				{
				{
				setState(133);
				statement();
				}
				}
				setState(138);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(139);
			returnStat();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class MethodNameContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(MiniJavaParser.ID, 0); }
		public MethodNameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_methodName; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MiniJavaVisitor ) return ((MiniJavaVisitor<? extends T>)visitor).visitMethodName(this);
			else return visitor.visitChildren(this);
		}
	}

	public final MethodNameContext methodName() throws RecognitionException {
		MethodNameContext _localctx = new MethodNameContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_methodName);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(141);
			match(ID);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ParameterNameContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(MiniJavaParser.ID, 0); }
		public ParameterNameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_parameterName; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MiniJavaVisitor ) return ((MiniJavaVisitor<? extends T>)visitor).visitParameterName(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ParameterNameContext parameterName() throws RecognitionException {
		ParameterNameContext _localctx = new ParameterNameContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_parameterName);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(143);
			match(ID);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ReturnStatContext extends ParserRuleContext {
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public ReturnStatContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_returnStat; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MiniJavaVisitor ) return ((MiniJavaVisitor<? extends T>)visitor).visitReturnStat(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ReturnStatContext returnStat() throws RecognitionException {
		ReturnStatContext _localctx = new ReturnStatContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_returnStat);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(145);
			match(T__15);
			setState(146);
			expression(0);
			setState(147);
			match(T__13);
			setState(148);
			match(T__11);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class TypeContext extends ParserRuleContext {
		public TContext t() {
			return getRuleContext(TContext.class,0);
		}
		public TypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_type; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MiniJavaVisitor ) return ((MiniJavaVisitor<? extends T>)visitor).visitType(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TypeContext type() throws RecognitionException {
		TypeContext _localctx = new TypeContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_type);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(150);
			t();
			setState(152);
			_la = _input.LA(1);
			if (_la==T__16) {
				{
				setState(151);
				match(T__16);
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class TContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(MiniJavaParser.ID, 0); }
		public TContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_t; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MiniJavaVisitor ) return ((MiniJavaVisitor<? extends T>)visitor).visitT(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TContext t() throws RecognitionException {
		TContext _localctx = new TContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_t);
		try {
			setState(160);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,9,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(154);
				match(T__17);
				setState(155);
				match(T__8);
				setState(156);
				match(T__9);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(157);
				match(T__18);
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(158);
				match(T__17);
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(159);
				match(ID);
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class StatementContext extends ParserRuleContext {
		public ConditionalContext conditional() {
			return getRuleContext(ConditionalContext.class,0);
		}
		public LoopContext loop() {
			return getRuleContext(LoopContext.class,0);
		}
		public PrintContext print() {
			return getRuleContext(PrintContext.class,0);
		}
		public VarAssignmentContext varAssignment() {
			return getRuleContext(VarAssignmentContext.class,0);
		}
		public ElementAssignmentContext elementAssignment() {
			return getRuleContext(ElementAssignmentContext.class,0);
		}
		public List<StatementContext> statement() {
			return getRuleContexts(StatementContext.class);
		}
		public StatementContext statement(int i) {
			return getRuleContext(StatementContext.class,i);
		}
		public StatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_statement; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MiniJavaVisitor ) return ((MiniJavaVisitor<? extends T>)visitor).visitStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final StatementContext statement() throws RecognitionException {
		StatementContext _localctx = new StatementContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_statement);
		int _la;
		try {
			setState(175);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,11,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(162);
				conditional();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(163);
				loop();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(164);
				print();
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(165);
				varAssignment();
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(166);
				elementAssignment();
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(167);
				match(T__1);
				setState(171);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__1) | (1L << T__19) | (1L << T__21) | (1L << T__22) | (1L << ID))) != 0)) {
					{
					{
					setState(168);
					statement();
					}
					}
					setState(173);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(174);
				match(T__11);
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ConditionalContext extends ParserRuleContext {
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public StatementContext statement() {
			return getRuleContext(StatementContext.class,0);
		}
		public ElseCondContext elseCond() {
			return getRuleContext(ElseCondContext.class,0);
		}
		public ConditionalContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_conditional; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MiniJavaVisitor ) return ((MiniJavaVisitor<? extends T>)visitor).visitConditional(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ConditionalContext conditional() throws RecognitionException {
		ConditionalContext _localctx = new ConditionalContext(_ctx, getState());
		enterRule(_localctx, 32, RULE_conditional);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(177);
			match(T__19);
			setState(178);
			match(T__6);
			setState(179);
			expression(0);
			setState(180);
			match(T__10);
			setState(181);
			statement();
			setState(182);
			elseCond();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ElseCondContext extends ParserRuleContext {
		public StatementContext statement() {
			return getRuleContext(StatementContext.class,0);
		}
		public ElseCondContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_elseCond; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MiniJavaVisitor ) return ((MiniJavaVisitor<? extends T>)visitor).visitElseCond(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ElseCondContext elseCond() throws RecognitionException {
		ElseCondContext _localctx = new ElseCondContext(_ctx, getState());
		enterRule(_localctx, 34, RULE_elseCond);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(184);
			match(T__20);
			setState(185);
			statement();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class LoopContext extends ParserRuleContext {
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public StatementContext statement() {
			return getRuleContext(StatementContext.class,0);
		}
		public LoopContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_loop; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MiniJavaVisitor ) return ((MiniJavaVisitor<? extends T>)visitor).visitLoop(this);
			else return visitor.visitChildren(this);
		}
	}

	public final LoopContext loop() throws RecognitionException {
		LoopContext _localctx = new LoopContext(_ctx, getState());
		enterRule(_localctx, 36, RULE_loop);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(187);
			match(T__21);
			setState(188);
			match(T__6);
			setState(189);
			expression(0);
			setState(190);
			match(T__10);
			setState(191);
			statement();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class PrintContext extends ParserRuleContext {
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public PrintContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_print; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MiniJavaVisitor ) return ((MiniJavaVisitor<? extends T>)visitor).visitPrint(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PrintContext print() throws RecognitionException {
		PrintContext _localctx = new PrintContext(_ctx, getState());
		enterRule(_localctx, 38, RULE_print);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(193);
			match(T__22);
			setState(194);
			match(T__6);
			setState(195);
			expression(0);
			setState(196);
			match(T__10);
			setState(197);
			match(T__13);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class VarAssignmentContext extends ParserRuleContext {
		public VariableNameContext variableName() {
			return getRuleContext(VariableNameContext.class,0);
		}
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public VarAssignmentContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_varAssignment; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MiniJavaVisitor ) return ((MiniJavaVisitor<? extends T>)visitor).visitVarAssignment(this);
			else return visitor.visitChildren(this);
		}
	}

	public final VarAssignmentContext varAssignment() throws RecognitionException {
		VarAssignmentContext _localctx = new VarAssignmentContext(_ctx, getState());
		enterRule(_localctx, 40, RULE_varAssignment);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(199);
			variableName();
			setState(200);
			match(T__23);
			setState(201);
			expression(0);
			setState(202);
			match(T__13);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ElementAssignmentContext extends ParserRuleContext {
		public VariableNameContext variableName() {
			return getRuleContext(VariableNameContext.class,0);
		}
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public ElementAssignmentContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_elementAssignment; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MiniJavaVisitor ) return ((MiniJavaVisitor<? extends T>)visitor).visitElementAssignment(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ElementAssignmentContext elementAssignment() throws RecognitionException {
		ElementAssignmentContext _localctx = new ElementAssignmentContext(_ctx, getState());
		enterRule(_localctx, 42, RULE_elementAssignment);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(204);
			variableName();
			setState(205);
			match(T__8);
			setState(206);
			expression(0);
			setState(207);
			match(T__9);
			setState(208);
			match(T__23);
			setState(209);
			expression(0);
			setState(210);
			match(T__13);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class UnwrapVariableNameContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(MiniJavaParser.ID, 0); }
		public UnwrapVariableNameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_unwrapVariableName; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MiniJavaVisitor ) return ((MiniJavaVisitor<? extends T>)visitor).visitUnwrapVariableName(this);
			else return visitor.visitChildren(this);
		}
	}

	public final UnwrapVariableNameContext unwrapVariableName() throws RecognitionException {
		UnwrapVariableNameContext _localctx = new UnwrapVariableNameContext(_ctx, getState());
		enterRule(_localctx, 44, RULE_unwrapVariableName);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(212);
			match(ID);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ExpressionContext extends ParserRuleContext {
		public UnwrapVariableNameContext unwrapVariableName() {
			return getRuleContext(UnwrapVariableNameContext.class,0);
		}
		public TerminalNode INT() { return getToken(MiniJavaParser.INT, 0); }
		public TerminalNode ID() { return getToken(MiniJavaParser.ID, 0); }
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public ClassNameContext className() {
			return getRuleContext(ClassNameContext.class,0);
		}
		public MethodNameContext methodName() {
			return getRuleContext(MethodNameContext.class,0);
		}
		public ExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expression; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MiniJavaVisitor ) return ((MiniJavaVisitor<? extends T>)visitor).visitExpression(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ExpressionContext expression() throws RecognitionException {
		return expression(0);
	}

	private ExpressionContext expression(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		ExpressionContext _localctx = new ExpressionContext(_ctx, _parentState);
		ExpressionContext _prevctx = _localctx;
		int _startState = 46;
		enterRecursionRule(_localctx, 46, RULE_expression, _p);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(241);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,12,_ctx) ) {
			case 1:
				{
				setState(215);
				unwrapVariableName();
				setState(216);
				match(T__24);
				}
				break;
			case 2:
				{
				setState(218);
				match(INT);
				}
				break;
			case 3:
				{
				setState(219);
				match(T__32);
				}
				break;
			case 4:
				{
				setState(220);
				match(T__33);
				}
				break;
			case 5:
				{
				setState(221);
				match(T__34);
				}
				break;
			case 6:
				{
				setState(222);
				match(ID);
				}
				break;
			case 7:
				{
				setState(223);
				match(T__35);
				}
				break;
			case 8:
				{
				setState(224);
				match(T__36);
				setState(225);
				match(T__17);
				setState(226);
				match(T__8);
				setState(227);
				expression(0);
				setState(228);
				match(T__9);
				}
				break;
			case 9:
				{
				setState(230);
				match(T__36);
				setState(231);
				className();
				setState(232);
				match(T__6);
				setState(233);
				match(T__10);
				}
				break;
			case 10:
				{
				setState(235);
				match(T__24);
				setState(236);
				expression(2);
				}
				break;
			case 11:
				{
				setState(237);
				match(T__6);
				setState(238);
				expression(0);
				setState(239);
				match(T__10);
				}
				break;
			}
			_ctx.stop = _input.LT(-1);
			setState(272);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,16,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(270);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,15,_ctx) ) {
					case 1:
						{
						_localctx = new ExpressionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(243);
						if (!(precpred(_ctx, 14))) throw new FailedPredicateException(this, "precpred(_ctx, 14)");
						setState(244);
						_la = _input.LA(1);
						if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__25) | (1L << T__26) | (1L << T__27) | (1L << T__28) | (1L << T__29))) != 0)) ) {
						_errHandler.recoverInline(this);
						} else {
							consume();
						}
						setState(245);
						expression(15);
						}
						break;
					case 2:
						{
						_localctx = new ExpressionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(246);
						if (!(precpred(_ctx, 13))) throw new FailedPredicateException(this, "precpred(_ctx, 13)");
						setState(247);
						match(T__8);
						setState(248);
						expression(0);
						setState(249);
						match(T__9);
						}
						break;
					case 3:
						{
						_localctx = new ExpressionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(251);
						if (!(precpred(_ctx, 12))) throw new FailedPredicateException(this, "precpred(_ctx, 12)");
						setState(252);
						match(T__30);
						setState(253);
						match(T__31);
						}
						break;
					case 4:
						{
						_localctx = new ExpressionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(254);
						if (!(precpred(_ctx, 11))) throw new FailedPredicateException(this, "precpred(_ctx, 11)");
						setState(255);
						match(T__30);
						setState(256);
						methodName();
						setState(257);
						match(T__6);
						setState(266);
						_la = _input.LA(1);
						if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__6) | (1L << T__24) | (1L << T__32) | (1L << T__33) | (1L << T__34) | (1L << T__35) | (1L << T__36) | (1L << INT) | (1L << ID))) != 0)) {
							{
							setState(258);
							expression(0);
							setState(263);
							_errHandler.sync(this);
							_la = _input.LA(1);
							while (_la==T__14) {
								{
								{
								setState(259);
								match(T__14);
								setState(260);
								expression(0);
								}
								}
								setState(265);
								_errHandler.sync(this);
								_la = _input.LA(1);
							}
							}
						}

						setState(268);
						match(T__10);
						}
						break;
					}
					} 
				}
				setState(274);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,16,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	public boolean sempred(RuleContext _localctx, int ruleIndex, int predIndex) {
		switch (ruleIndex) {
		case 23:
			return expression_sempred((ExpressionContext)_localctx, predIndex);
		}
		return true;
	}
	private boolean expression_sempred(ExpressionContext _localctx, int predIndex) {
		switch (predIndex) {
		case 0:
			return precpred(_ctx, 14);
		case 1:
			return precpred(_ctx, 13);
		case 2:
			return precpred(_ctx, 12);
		case 3:
			return precpred(_ctx, 11);
		}
		return true;
	}

	public static final String _serializedATN =
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\3,\u0116\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\3\2\3\2\7\2\65\n\2\f\2\16\28\13\2\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3"+
		"\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\4\3\4\3\5\3\5\3\6\3\6\3\7\3\7\3"+
		"\b\3\b\3\b\3\b\5\bX\n\b\3\b\3\b\7\b\\\n\b\f\b\16\b_\13\b\3\b\7\bb\n\b"+
		"\f\b\16\be\13\b\3\b\3\b\3\t\3\t\3\t\3\t\3\n\3\n\3\13\3\13\3\13\3\13\3"+
		"\13\3\13\3\13\3\13\3\13\3\13\7\13y\n\13\f\13\16\13|\13\13\5\13~\n\13\3"+
		"\13\3\13\3\13\7\13\u0083\n\13\f\13\16\13\u0086\13\13\3\13\7\13\u0089\n"+
		"\13\f\13\16\13\u008c\13\13\3\13\3\13\3\f\3\f\3\r\3\r\3\16\3\16\3\16\3"+
		"\16\3\16\3\17\3\17\5\17\u009b\n\17\3\20\3\20\3\20\3\20\3\20\3\20\5\20"+
		"\u00a3\n\20\3\21\3\21\3\21\3\21\3\21\3\21\3\21\7\21\u00ac\n\21\f\21\16"+
		"\21\u00af\13\21\3\21\5\21\u00b2\n\21\3\22\3\22\3\22\3\22\3\22\3\22\3\22"+
		"\3\23\3\23\3\23\3\24\3\24\3\24\3\24\3\24\3\24\3\25\3\25\3\25\3\25\3\25"+
		"\3\25\3\26\3\26\3\26\3\26\3\26\3\27\3\27\3\27\3\27\3\27\3\27\3\27\3\27"+
		"\3\30\3\30\3\31\3\31\3\31\3\31\3\31\3\31\3\31\3\31\3\31\3\31\3\31\3\31"+
		"\3\31\3\31\3\31\3\31\3\31\3\31\3\31\3\31\3\31\3\31\3\31\3\31\3\31\3\31"+
		"\3\31\5\31\u00f4\n\31\3\31\3\31\3\31\3\31\3\31\3\31\3\31\3\31\3\31\3\31"+
		"\3\31\3\31\3\31\3\31\3\31\3\31\3\31\3\31\7\31\u0108\n\31\f\31\16\31\u010b"+
		"\13\31\5\31\u010d\n\31\3\31\3\31\7\31\u0111\n\31\f\31\16\31\u0114\13\31"+
		"\3\31\2\3\60\32\2\4\6\b\n\f\16\20\22\24\26\30\32\34\36 \"$&(*,.\60\2\3"+
		"\3\2\34 \u011f\2\62\3\2\2\2\49\3\2\2\2\6K\3\2\2\2\bM\3\2\2\2\nO\3\2\2"+
		"\2\fQ\3\2\2\2\16S\3\2\2\2\20h\3\2\2\2\22l\3\2\2\2\24n\3\2\2\2\26\u008f"+
		"\3\2\2\2\30\u0091\3\2\2\2\32\u0093\3\2\2\2\34\u0098\3\2\2\2\36\u00a2\3"+
		"\2\2\2 \u00b1\3\2\2\2\"\u00b3\3\2\2\2$\u00ba\3\2\2\2&\u00bd\3\2\2\2(\u00c3"+
		"\3\2\2\2*\u00c9\3\2\2\2,\u00ce\3\2\2\2.\u00d6\3\2\2\2\60\u00f3\3\2\2\2"+
		"\62\66\5\4\3\2\63\65\5\16\b\2\64\63\3\2\2\2\658\3\2\2\2\66\64\3\2\2\2"+
		"\66\67\3\2\2\2\67\3\3\2\2\28\66\3\2\2\29:\7\3\2\2:;\5\6\4\2;<\7\4\2\2"+
		"<=\7\5\2\2=>\7\6\2\2>?\7\7\2\2?@\7\b\2\2@A\7\t\2\2AB\7\n\2\2BC\7\13\2"+
		"\2CD\7\f\2\2DE\5\b\5\2EF\7\r\2\2FG\7\4\2\2GH\5 \21\2HI\7\16\2\2IJ\7\16"+
		"\2\2J\5\3\2\2\2KL\7)\2\2L\7\3\2\2\2MN\7)\2\2N\t\3\2\2\2OP\7)\2\2P\13\3"+
		"\2\2\2QR\7)\2\2R\r\3\2\2\2ST\7\3\2\2TW\5\n\6\2UV\7\17\2\2VX\5\f\7\2WU"+
		"\3\2\2\2WX\3\2\2\2XY\3\2\2\2Y]\7\4\2\2Z\\\5\20\t\2[Z\3\2\2\2\\_\3\2\2"+
		"\2][\3\2\2\2]^\3\2\2\2^c\3\2\2\2_]\3\2\2\2`b\5\24\13\2a`\3\2\2\2be\3\2"+
		"\2\2ca\3\2\2\2cd\3\2\2\2df\3\2\2\2ec\3\2\2\2fg\7\16\2\2g\17\3\2\2\2hi"+
		"\5\34\17\2ij\5\22\n\2jk\7\20\2\2k\21\3\2\2\2lm\7)\2\2m\23\3\2\2\2no\7"+
		"\5\2\2op\5\34\17\2pq\5\26\f\2q}\7\t\2\2rs\5\34\17\2sz\5\30\r\2tu\7\21"+
		"\2\2uv\5\34\17\2vw\5\30\r\2wy\3\2\2\2xt\3\2\2\2y|\3\2\2\2zx\3\2\2\2z{"+
		"\3\2\2\2{~\3\2\2\2|z\3\2\2\2}r\3\2\2\2}~\3\2\2\2~\177\3\2\2\2\177\u0080"+
		"\7\r\2\2\u0080\u0084\7\4\2\2\u0081\u0083\5\20\t\2\u0082\u0081\3\2\2\2"+
		"\u0083\u0086\3\2\2\2\u0084\u0082\3\2\2\2\u0084\u0085\3\2\2\2\u0085\u008a"+
		"\3\2\2\2\u0086\u0084\3\2\2\2\u0087\u0089\5 \21\2\u0088\u0087\3\2\2\2\u0089"+
		"\u008c\3\2\2\2\u008a\u0088\3\2\2\2\u008a\u008b\3\2\2\2\u008b\u008d\3\2"+
		"\2\2\u008c\u008a\3\2\2\2\u008d\u008e\5\32\16\2\u008e\25\3\2\2\2\u008f"+
		"\u0090\7)\2\2\u0090\27\3\2\2\2\u0091\u0092\7)\2\2\u0092\31\3\2\2\2\u0093"+
		"\u0094\7\22\2\2\u0094\u0095\5\60\31\2\u0095\u0096\7\20\2\2\u0096\u0097"+
		"\7\16\2\2\u0097\33\3\2\2\2\u0098\u009a\5\36\20\2\u0099\u009b\7\23\2\2"+
		"\u009a\u0099\3\2\2\2\u009a\u009b\3\2\2\2\u009b\35\3\2\2\2\u009c\u009d"+
		"\7\24\2\2\u009d\u009e\7\13\2\2\u009e\u00a3\7\f\2\2\u009f\u00a3\7\25\2"+
		"\2\u00a0\u00a3\7\24\2\2\u00a1\u00a3\7)\2\2\u00a2\u009c\3\2\2\2\u00a2\u009f"+
		"\3\2\2\2\u00a2\u00a0\3\2\2\2\u00a2\u00a1\3\2\2\2\u00a3\37\3\2\2\2\u00a4"+
		"\u00b2\5\"\22\2\u00a5\u00b2\5&\24\2\u00a6\u00b2\5(\25\2\u00a7\u00b2\5"+
		"*\26\2\u00a8\u00b2\5,\27\2\u00a9\u00ad\7\4\2\2\u00aa\u00ac\5 \21\2\u00ab"+
		"\u00aa\3\2\2\2\u00ac\u00af\3\2\2\2\u00ad\u00ab\3\2\2\2\u00ad\u00ae\3\2"+
		"\2\2\u00ae\u00b0\3\2\2\2\u00af\u00ad\3\2\2\2\u00b0\u00b2\7\16\2\2\u00b1"+
		"\u00a4\3\2\2\2\u00b1\u00a5\3\2\2\2\u00b1\u00a6\3\2\2\2\u00b1\u00a7\3\2"+
		"\2\2\u00b1\u00a8\3\2\2\2\u00b1\u00a9\3\2\2\2\u00b2!\3\2\2\2\u00b3\u00b4"+
		"\7\26\2\2\u00b4\u00b5\7\t\2\2\u00b5\u00b6\5\60\31\2\u00b6\u00b7\7\r\2"+
		"\2\u00b7\u00b8\5 \21\2\u00b8\u00b9\5$\23\2\u00b9#\3\2\2\2\u00ba\u00bb"+
		"\7\27\2\2\u00bb\u00bc\5 \21\2\u00bc%\3\2\2\2\u00bd\u00be\7\30\2\2\u00be"+
		"\u00bf\7\t\2\2\u00bf\u00c0\5\60\31\2\u00c0\u00c1\7\r\2\2\u00c1\u00c2\5"+
		" \21\2\u00c2\'\3\2\2\2\u00c3\u00c4\7\31\2\2\u00c4\u00c5\7\t\2\2\u00c5"+
		"\u00c6\5\60\31\2\u00c6\u00c7\7\r\2\2\u00c7\u00c8\7\20\2\2\u00c8)\3\2\2"+
		"\2\u00c9\u00ca\5\22\n\2\u00ca\u00cb\7\32\2\2\u00cb\u00cc\5\60\31\2\u00cc"+
		"\u00cd\7\20\2\2\u00cd+\3\2\2\2\u00ce\u00cf\5\22\n\2\u00cf\u00d0\7\13\2"+
		"\2\u00d0\u00d1\5\60\31\2\u00d1\u00d2\7\f\2\2\u00d2\u00d3\7\32\2\2\u00d3"+
		"\u00d4\5\60\31\2\u00d4\u00d5\7\20\2\2\u00d5-\3\2\2\2\u00d6\u00d7\7)\2"+
		"\2\u00d7/\3\2\2\2\u00d8\u00d9\b\31\1\2\u00d9\u00da\5.\30\2\u00da\u00db"+
		"\7\33\2\2\u00db\u00f4\3\2\2\2\u00dc\u00f4\7(\2\2\u00dd\u00f4\7#\2\2\u00de"+
		"\u00f4\7$\2\2\u00df\u00f4\7%\2\2\u00e0\u00f4\7)\2\2\u00e1\u00f4\7&\2\2"+
		"\u00e2\u00e3\7\'\2\2\u00e3\u00e4\7\24\2\2\u00e4\u00e5\7\13\2\2\u00e5\u00e6"+
		"\5\60\31\2\u00e6\u00e7\7\f\2\2\u00e7\u00f4\3\2\2\2\u00e8\u00e9\7\'\2\2"+
		"\u00e9\u00ea\5\n\6\2\u00ea\u00eb\7\t\2\2\u00eb\u00ec\7\r\2\2\u00ec\u00f4"+
		"\3\2\2\2\u00ed\u00ee\7\33\2\2\u00ee\u00f4\5\60\31\4\u00ef\u00f0\7\t\2"+
		"\2\u00f0\u00f1\5\60\31\2\u00f1\u00f2\7\r\2\2\u00f2\u00f4\3\2\2\2\u00f3"+
		"\u00d8\3\2\2\2\u00f3\u00dc\3\2\2\2\u00f3\u00dd\3\2\2\2\u00f3\u00de\3\2"+
		"\2\2\u00f3\u00df\3\2\2\2\u00f3\u00e0\3\2\2\2\u00f3\u00e1\3\2\2\2\u00f3"+
		"\u00e2\3\2\2\2\u00f3\u00e8\3\2\2\2\u00f3\u00ed\3\2\2\2\u00f3\u00ef\3\2"+
		"\2\2\u00f4\u0112\3\2\2\2\u00f5\u00f6\f\20\2\2\u00f6\u00f7\t\2\2\2\u00f7"+
		"\u0111\5\60\31\21\u00f8\u00f9\f\17\2\2\u00f9\u00fa\7\13\2\2\u00fa\u00fb"+
		"\5\60\31\2\u00fb\u00fc\7\f\2\2\u00fc\u0111\3\2\2\2\u00fd\u00fe\f\16\2"+
		"\2\u00fe\u00ff\7!\2\2\u00ff\u0111\7\"\2\2\u0100\u0101\f\r\2\2\u0101\u0102"+
		"\7!\2\2\u0102\u0103\5\26\f\2\u0103\u010c\7\t\2\2\u0104\u0109\5\60\31\2"+
		"\u0105\u0106\7\21\2\2\u0106\u0108\5\60\31\2\u0107\u0105\3\2\2\2\u0108"+
		"\u010b\3\2\2\2\u0109\u0107\3\2\2\2\u0109\u010a\3\2\2\2\u010a\u010d\3\2"+
		"\2\2\u010b\u0109\3\2\2\2\u010c\u0104\3\2\2\2\u010c\u010d\3\2\2\2\u010d"+
		"\u010e\3\2\2\2\u010e\u010f\7\r\2\2\u010f\u0111\3\2\2\2\u0110\u00f5\3\2"+
		"\2\2\u0110\u00f8\3\2\2\2\u0110\u00fd\3\2\2\2\u0110\u0100\3\2\2\2\u0111"+
		"\u0114\3\2\2\2\u0112\u0110\3\2\2\2\u0112\u0113\3\2\2\2\u0113\61\3\2\2"+
		"\2\u0114\u0112\3\2\2\2\23\66W]cz}\u0084\u008a\u009a\u00a2\u00ad\u00b1"+
		"\u00f3\u0109\u010c\u0110\u0112";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}