// tokens.flex
%%
%class Letras
%public
%unicode
%byaccj

NUMBER = [0-9][0-9]* | [0-9][0-9]* \. [0-9]+

%%

"+" 		{return Parser.ADD;}

"-"			{return Parser.SUB;}

"*" 		{return Parser.MULT;}

"/"			{return Parser.DIV;}

{NUMBER}	{return Parser.NUMBER;}

" "			{}

.			{System.out.println("ERROR: Token no reconocido.");
			System.exit(1);}