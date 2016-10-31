grammar MiniJava;

goal : mainClass (classDeclaration)*;
mainClass : 'class' mainClassName '{' 'public' 'static' 'void' 'main' '(' 'String' '[' ']' args ')' '{' statement '}' '}';
mainClassName : ID ;
args : ID ;
className : ID ;
parentClassName : ID ;
classDeclaration : 'class' className ('extends' parentClassName)? '{' (varDeclaration)* (methodDeclaration)* '}';
varDeclaration : type variableName ';' ;
variableName : ID ;
parameter : type parameterName ;
methodDeclaration : 'public' returnType methodName '(' (parameter (',' parameter)* )? ')' '{' (varDeclaration)* (statement)* returnStatement ;
methodName : ID ;
parameterName : ID ;
returnType : type ;
returnStatement : 'return' expression ';' '}' ;
type: t ('?')? ;
t : 'int' '[' ']' 
	| 'boolean'
	| 'int'
	| ID
	;
statement : conditional
	| loop
	| print
	| varAssignment
	| elementAssignment
	| '{' (statement)* '}'
	;
conditional : 'if' '(' expression ')' statement elseCond ;
elseCond : 'else' statement ;
loop: 'while' '(' expression ')' statement ;
print : 'System.out.println' '(' expression ')' ';' ;
varAssignment : variableName '=' expression ';' ;
elementAssignment : variableName '[' expression ']' '=' expression ';' ;
unwrapVariableName : ID ;
expression : unwrapVariableName '!'
	| expression ('&&' | '<' | '+' | '-' | '*') expression
	| expression '[' expression ']'
	| expression '.' 'length'
	| expression '.' methodName '(' (expression (',' expression)*)? ')'
	| INT
	| 'true'
	| 'false'
	| 'null'
	| ID
	| 'this'
	| 'new' 'int' '[' expression ']'
	| 'new' className '(' ')'
	| '!' expression
	| '(' expression ')'
	;
INT : [0-9] [0-9]*;
ID : [a-zA-Z] [a-zA-Z_$0-9]*;
WS : [\t\r\n' ']+ -> skip;
LINE_COMMENT : '//' ~('\n' | '\r')* -> skip;
MULTI_COMMENT : '/*' .* '*/' -> skip;
