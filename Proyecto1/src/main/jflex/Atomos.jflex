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

IDENTIFICADOR = ([a-zA-Z] | '_')([a-zA-Z] | [0-9] | '_')*
BOOLEANO = 'True' | 'False'
ENTERO = [1-9][0-9]* | 0+
REAL = '.'[0-9]+ | ENTERO'.'[0-9]+ | ENTERO'.'

%%
{IDENTIFICADOR}  {System.out.println("ID");}
#.* 	 {System.out.println("COMENTARIO");}
{ENTERO} {System.out.println("ENTERO");}