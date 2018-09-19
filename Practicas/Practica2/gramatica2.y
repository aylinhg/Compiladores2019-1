// parser.y

%{
import java.io.*;	
%}

%token <sval> ADD, SUB, MULT, DIV
%token <dval> NUMBER
%type <dval> EXPR
%type <dval> E, T, EADD, ESUB, TMULT, TDIV, F 

%%
EXPR: 	{System.out.println("[OK]");}
		| E {System.out.println("[OK]");}
		;

E:		T
		| T EADD 
		| T ESUB
		;

EADD:	ADD E
		;

ESUB:	SUB E
		;

T:		F
		| F TMULT
		| F TDIV
		;

TMULT:	MULT T
		;

TDIV:	DIV T
		;

F:		NUMBER
		| SUB NUMBER
		;

%%

private Letras alexico;

/* Regresar átomos */
private int yylex() {
	int yyl_return = -1;
	try {
		yyl_return = alexico.yylex();
	} catch (IOException e) {
		System.err.println("Error de IO. "+e);
	}
	return yyl_return;
}

/* Reportar error. */
public void yyerror(String error) {
	System.err.println("[ERROR] "+error);
	System.exit(1);
}

/* Constructor. */
public Parser(Reader r) {
	alexico = new Letras(r);
}

/* Analizador sintáctico sobre un archivo. */
public static void main(String[] args) {
	try {
		Parser yyparser = new Parser(new FileReader(args[0]));
		yyparser.yyparse();
	} catch(FileNotFoundException e) {
		System.err.println("El archivo " + args[0] + " no existe.");
	}
}