// tokens.flex
%%
%{
	private Parser parser;

	public Letras (java.io.Reader r, Parser p){
    	this(r);
		parser = p;
	}
%}

%class Letras
%public
%unicode
%byaccj

NUMBER = [0-9][0-9]* | [0-9][0-9]* \. [0-9]+

%%

"+" 		{parser.yyval = new ParserVal(yytext()); return Parser.ADD;}

"-"			{parser.yyval = new ParserVal(yytext()); return Parser.SUB;}

"*" 		{parser.yyval = new ParserVal(yytext()); return Parser.MULT;}

"/"			{parser.yyval = new ParserVal(yytext()); return Parser.DIV;}

{NUMBER}	{double d = Double.parseDouble(yytext());
parser.yylval = new ParserVal(d); return Parser.NUMBER;}

" "			{}

.			{System.out.println("ERROR: Token no reconocido.");
			System.exit(1);}