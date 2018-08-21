//Clase de analizador léxico Python


%%

%public
%class AL
%unicode
%standalone

ENTERO = [0-9]+
IDENTIFICADOR = [[A-Z] | [a-z] | '_'][[A-Z] | [a-z] | '_' | [0-9]]* 

%%
{IDENTIFICADOR}  {System.out.println("ID");}
#.* 	 {System.out.println("COMENTARIO");}
{ENTERO} {System.out.println("ENTERO");}
