/********************************************************************************
**  @author Mayra Aceves                                                       **
**  @author Aylin Huerta                                                       **
**  @author Javier Téllez                                                      **
**  @about Proyecto 2: Analizador léxico para la gramática.			  	       **
*********************************************************************************/
package lexico;
import java.util.Stack;

%%

%{
	
    public Stack<Integer> pila = new Stack<Integer>();
    static Integer espacios = 0;
    static int contIndenta = 0;
	static int contDeindenta = 0;

	private Parser yyparser;

	public Alexico(java.io.Reader r, Parser parser){
    	   this(r);
    	   yyparser = parser;
	}

	public void indentacion(int espacios) {
		if (pila.empty()) {
			pila.push(new Integer(0));
		}
		Integer topePila = pila.peek();
		if (topePila != espacios) {
			if (topePila > espacios) {
				while (pila.peek() > espacios && pila.peek() != 0) {
					pila.pop();
					contDeindenta += 1;
				}
				if (pila.peek() == espacios) {
					yybegin(DEINDENTA);
				} else {
					System.out.println("Error de indentación, línea " + (yyline+1));
					System.exit(1);
				}
				return;
			}
			pila.push(espacios);
			yybegin(ATOMOS);
			contIndenta = 1;
		} else {
			yybegin(ATOMOS);
		}
	}

%}

%class Alexico
%public
%unicode
%standalone
%line
%state CADENA ATOMOS INDENTA DEINDENTA 

BOOLEANO       		  = 	  ("True" | "False")

DIGITO 				  =		  [0-9]

CERO 				  = 	  0+

ENTERO          	  =   	  {CERO} | {DIGITO}+ 

PUNTO				  =    	  \.

REAL            	  =  	  {ENTERO}? {PUNTO} {ENTERO} | {ENTERO} {PUNTO} {ENTERO}?   
	
AND                   =       and

NOT                   =       not

WHILE                 =       while

OR                    =       or

ELSE                  =       else

IF                    =       if

PRINT                 =       print

MAS                   =       "+"

MENOS                 =       "-"

MULT                  =       "*"

POTENCIA              =       "**"

DIVISION              =       "/"

DIV_PISO              =       \/\/

MODULO                =       "%"

MENOR                 =       "<"

MAYOR                 =       ">"

MAYOR_IGUAL           =       ">="

MENOR_IGUAL           =       "<=" 

IGUAL                 =       "="

DIFERENTE             =       "!="

COMP_IGUAL            =       "=="

DOS_PUNTOS            =       \:

OPERADOR        	  =   	  (   "+" | "+=" | "-" | "-=" | "*" | "**" | "/" | "//" | "%" | "<" | "<=" | ">" | ">=" | "=" | "=="| "!="| "<>" )

SEPARADOR       	  =   	  ( "(" | ")" | ":" | ";") 
	 
CARACTER   			  =	      ([:letter:] | [:digit:] | "_" | "$" | " " | "#" | {OPERADOR} | {SEPARADOR})

IDENTIFICADOR   	  =       ([:letter:] | "_") ([:letter:] | "_" | [0-9])*

SALTO			      =	      "\n"

COMENTARIO			  =       #.*{SALTO}

%%

{COMENTARIO}			{}
<CADENA> {
	{CARACTER}*\"		{yybegin(ATOMOS); return Parser.CADENA;}
	{SALTO}				{System.out.println("Cadena mal construída, línea "+(yyline+1)); 
						System.exit(1);}
}

<YYINITIAL> {
	" "+				{System.out.println("Error de indentación, línea "+(yyline+1)); 
						System.exit(1);}
	.					{yypushback(1); yybegin(ATOMOS);}
}

<ATOMOS> {
	\"					{yybegin(CADENA);}
	{BOOLEANO}			{return Parser.BOOLEANO;}
	{ENTERO}			{return Parser.ENTERO;}
	{REAL}				{return Parser.REAL;}
	{AND}				{return Parser.AND;}
	{NOT}				{return Parser.NOT;}
	{WHILE}				{return Parser.WHILE;}
	{OR}				{return Parser.OR;}
	{ELSE}				{return Parser.ELSE;}
	{IF}				{return Parser.IF;}
	{PRINT}				{return Parser.PRINT;}
	{MAS}				{return Parser.MAS;}
	{MENOS}				{return Parser.MENOS;}
	{MULT}				{return Parser.MULT;}
	{POTENCIA}			{return Parser.POTENCIA;}
	{DIVISION}			{return Parser.DIVISION;}
	{DIV_PISO}			{return Parser.DIV_PISO;}
	{MODULO}			{return Parser.MODULO;}
	{MENOR}				{return Parser.MENOR;}
	{MAYOR}				{return Parser.MAYOR;}
	{MAYOR_IGUAL}		{return Parser.MAYOR_IGUAL;}
	{MENOR_IGUAL}		{return Parser.MENOR_IGUAL;}
	{IGUAL}				{return Parser.IGUAL;}
	{DIFERENTE}			{return Parser.DIFERENTE;}
	{COMP_IGUAL}		{return Parser.COMP_IGUAL;}
	{DOS_PUNTOS}		{return Parser.DOS_PUNTOS;}
	"("					{return Parser.PIZQUIERDO;}
	")"					{return Parser.PDERECHO;}
	{IDENTIFICADOR}		{return Parser.IDENTIFICADOR;}
	{SALTO}				{yybegin(INDENTA); actual=0; return Parser.SALTO;}
	" "					{ }
}

<DEINDENTA> {
	.					{yypushback(1);
							if(contIndenta > 0) {
								contDeindenta--;
								System.out.println("DEINDENTACIÓN");
								return Parser.DEINDENTA;
							}
							yybegin(ATOMOS);
						}
}
<INDENTA> {
	{SALTO}				{espacios = 0;}
	" "					{espacios++;}
	\t 					{espacios+=4;}
	.					{yypushback(1);
						this.indentacion(espacios);
						if (contIndenta == 1) {
							contIndenta = 0;
							System.out.println("INDENTACIÓN");
							return Parser.INDENTA;
						}}
}

<<EOF>>					{this.indentacion(0);
						if (contDeindenta > 0) {
							contDeindenta--;
							return Parser.DEINDENTA;
						} else {
							return 0;
						}}

.						{ System.err.println("Error léxico, línea " + (yyline+1)) ; System.exit(1); }