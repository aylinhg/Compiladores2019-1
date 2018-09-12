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

IDENTIFICADOR = ([a-zA-Z] | \_)([a-zA-Z] | [0-9] | \_)*
BOOLEANO = 'True' | 'False'
ENTERO = [1-9][0-9]* | 0+
REAL = '.'[0-9]+ | ENTERO'.'[0-9]+ | ENTERO'.'
CADENA = \"[^\"\\\n]*\"

RESERVADA = "and" | "or" | "not" | "while" | "if" | "else" | "elif" | "print"
OPERADOR = \+ | \- | \* | \/ | \% | \< | \> | \>= | \<= | \= | \! | \=
SALTO = \n
%%
{RESERVADA} {System.out.print("RESERVADA ("+ yytext() + ")");}
{IDENTIFICADOR}  {System.out.print("ID (" + yytext() + ")");}
#.* 	 {System.out.print("COMENTARIO (" + yytext() + ")");}
{ENTERO} {System.out.print("ENTERO (" + yytext() + ")");}
{CADENA} {System.out.print("CADENA (" + yytext() + ")");}
{OPERADOR} {System.out.print("OPERADOR (" + yytext() + ")");}
{SALTO} {System.out.println("SALTO");}