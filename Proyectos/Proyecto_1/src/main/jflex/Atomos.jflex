/********************************************************************************
**  @author Diana Montes                                                       **
**  @about Proyecto 1: Analizador léxico para p, subconjunto de Python.        **
*********************************************************************************/
package lexico;

%%

%public
%class Alexico
%unicode
%standalone

BOOLEANO        =   True | False

ENTERO          =   [1-9][0-9]* | 0+

REAL            =   \.[0-9]+ | ENTERO\.[0-9]+ | ENTERO\.

CADENA          =   \"[^\"\\\n]*\"

RESERVADA       =   and | or | not | while | if | else | elif | print

IDENTIFICADOR   =   ([a-zA-Z] | \_)([a-zA-Z] | [0-9] | \_)*

OPERADOR        =   \+ (=)? | \- (=)? | \* (=)? | \*\* | \/ (=)? | \/\/ | \% | \< | \> | \>\= | \<\= | \= | \! | \=\=  

SEPARADOR       =   \:

TABULADOR       =   "\t"

ESPACIO         =   " "

SALTO           =   "\n"
%%

{RESERVADA} 		{System.out.print("RESERVADA("+ yytext() + ")");}
{IDENTIFICADOR}  	{System.out.print("IDENTIFICADOR(" + yytext() + ")");}
#.* 	 			{System.out.print("COMENTARIO(" + yytext() + ")");}
{ENTERO} 			{System.out.print("ENTERO(" + yytext() + ")");}
{BOOLEANO} 			{System.out.print("BOOLEANO(" + yytext() + ")");}
{REAL}	 			{System.out.print("REAL(" + yytext() + ")");}
{CADENA} 			{System.out.print("CADENA(" + yytext() + ")");}
{OPERADOR} 			{System.out.print("OPERADOR(" + yytext() + ")");}
{SEPARADOR}			{System.out.print("SEPARADOR(" + yytext() + ")");}
{SALTO} 			{System.out.println("SALTO");}
