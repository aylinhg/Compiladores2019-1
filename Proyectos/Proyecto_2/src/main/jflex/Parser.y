%{
	
}%

%token BOOLEANO ENTERO REAL AND NOT WHILE OR ELSE IF PRINT MAS MENOS MULT
%token POTENCIA DIVISION DIV_PISO MODULO MENOR MAYOR MAYOR_IGUAL MENOR_IGUAL
%token IGUAL DIFERENTE COMP_IGUAL DOS_PUNTOS PIZQUIERDO PDERECHO IDENTIFICADOR

%%

file_input: SALTO
			| stmt 
			| file_input SALTO 
			| file_input stmt;

stmt: simple_stmt
		| compound_stmt;

simple_stmt: small_stmt SALTO;

small_stmt: expr_stmt 
			| print_stmt;

expr_stmt: test IGUAL test;

print_stmt: PRINT test;

compound_stmt: if_stmt
				| while_stmt;

if_stmt: IF test DOS_PUNTOS suite
		| IF test DOS_PUNTOS suite ELSE DOS_PUNTOS suite;

while_stmt: WHILE test DOS_PUNTOS suite;

suite: simple_stmt
		| SALTO INDENTA stmtMas DEINDENTA;

stmtMas: stmt
		| stmtMas stmt;

test: or_test;

or_test: and_test
		| and_test orEstrella;

orEstrella: OR or_test;

and_test: not_test
		| not_test andEstrella;

andEstrella: AND and_test;

not_test: NOT not_test
		| comparison;

comparison: expr
			| expr compuesto;

compuesto: comp_op comparison;

comp_op: MENOR 
		| MAYOR
		| COMP_IGUAL
		| MAYOR_IGUAL
		| MENOR_IGUAL
		| DIFERENTE;

expr: term 
	| term auxEstrella;

auxEstrella: MAS expr
			| MENOS expr;

term: factor
	| factor auxDosEstrella;

auxDosEstrella: MULT term
				| DIVISION term
				| MODULO term
				| DIV_PISO term;

factor: MAS factor
		| MENOS factor
		| power;

power: atom
		| atom POTENCIA factor;

atom: IDENTIFICADOR
		| ENTERO
		| CADENA
		| REAL
		| BOOLEANO
		| PIZQUIERDO test PDERECHO;