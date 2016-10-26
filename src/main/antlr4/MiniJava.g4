grammar MiniJava;

goal : mainClass (classDeclaration)*;
mainClass : 'class' ID '{' 'public' 'static' 'void' 'main' '(' 'String' '[' ']' ID ')' '{' statement '}' '}';
classDeclaration : 'class' ID ('extends' ID)? '{' (varDeclaration)* (methodDeclaration)* '}';
varDeclaration : type ID ';' ;
methodDeclaration : 'public' rType=type ID '(' (type ID(',' type ID)*)? ')' '{' (varDeclaration)* (statement)* returnStat ;
returnStat : 'return' expression ';' '}' ;
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
varAssignment : ID '=' expression ';' ;
elementAssignment : ID '[' expression ']' '=' expression ';' ;
expression : ID '!'
	| expression ('&&' | '<' | '+' | '-' | '*') expression
	| expression '[' expression ']'
	| expression '.' 'length'
	| expression '.' ID '(' (expression (',' expression)*)? ')'
	| INT
	| 'true'
	| 'false'
	| 'null'
	| ID
	| 'this'
	| 'new' 'int' '[' expression ']'
	| 'new' ID '(' ')'
	| '!' expression
	| '(' expression ')'
	;
INT : [0-9] [0-9]*;
ID : [a-zA-Z] [a-zA-Z_$0-9]*;
WS : [\t\r\n' ']+ -> skip;
LINE_COMMENT : '//' ~('\n' | '\r')* -> skip;
MULTI_COMMENT : '/*' .* '*/' -> skip;
