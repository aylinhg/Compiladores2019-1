// parser.y

%{
import java.io.*;	
%}

%token <sval> ADD, SUB, MULT, DIV
%token <dval> NUMBER
%type <dval> EXPR
%type <dval> E, T, EADD, ESUB, TMULT, TDIV, F, EAUX

%%
EXPR: 	{System.out.println("[OK]");}
		| E {$$ = $1; System.out.println("[OK] Resultado: " + $$);}
		;

E:		T 			{$$ = $1; dump_stacks(stateptr);}
		| T EADD 	{$$ = $1 + $2; dump_stacks(stateptr);}
		| T ESUB	{$$ = $1 - $2; dump_stacks(stateptr);}
		;

EADD:	ADD E 		{$$ = $2; dump_stacks(stateptr);}
		;

ESUB:	SUB EAUX		{$$ = $2; dump_stacks(stateptr);}
		;

EAUX: 	T 			{$$ = -1 * $1; dump_stacks(stateptr);}
		|	T EADD	{$$ = $1 - $2; dump_stacks(stateptr);}
		|	T ESUB	{$$ = $1 + $2; dump_stacks(stateptr);}


T:		F 			{$$ = $1; dump_stacks(stateptr);}
		| F TMULT	{$$ = $1 * $2; dump_stacks(stateptr);}
		| F TDIV 	{$$ = $1 / $2; dump_stacks(stateptr);}
		;

TMULT:	MULT T 		{$$ = $2; dump_stacks(stateptr);}
		;

TDIV:	DIV T 		{ $$ = $2; dump_stacks(stateptr);}
		;

F:		NUMBER 		{$$ = $1; dump_stacks(stateptr);}
		| SUB NUMBER	{$$ = -1 * $2; dump_stacks(stateptr);}
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
	alexico = new Letras(r,this);
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