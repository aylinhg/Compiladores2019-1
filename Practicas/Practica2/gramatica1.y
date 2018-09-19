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
		| E {$$ = $1; System.out.println("[OK] Resultado: "+ $$);}
		;

E:		T 				{$$ = $1;}
		| EADD T 		{$$ = $1 + $2;}
		| ESUB T 		{$$ = $1 - $2;}
		;

EADD:	E ADD 			{$$ = $1;}
		;

ESUB:	E SUB 			{$$ = $1;}
		;

T:		F 				{$$ = $1;}
		| TMULT F 		{$$ = $1 * $2;}
		| TDIV F 		{$$ = $1 / $2;}
		;

TMULT:	T MULT 			{$$ = $1;}
		;

TDIV:	T DIV 			{$$ = $1;}
		;

F:		NUMBER 			{$$ = $1;}
		| SUB NUMBER 	{$$ = -1 * $2;}
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
	alexico = new Letras(r, this);
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