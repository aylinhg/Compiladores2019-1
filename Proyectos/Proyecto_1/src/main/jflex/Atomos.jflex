/********************************************************************************
**  @author Mayra Aceves                                                       **
**  @author Aylin Huerta                                                       **
**  @author Javier Téllez                                                      **
**  @about Proyecto 1: Analizador léxico para p, subconjunto de Python.        **
*********************************************************************************/
package lexico;
import java.util.Stack;
import java.io.FileWriter;
import java.io.IOException;

%%

%{
	
    public Stack<Integer> pila = new Stack<Integer>();
    public int tabulado;
    public int espacios = 0;
    public String cadena;
    public FileWriter fw;

    /**
     * Se encarga de la indentación del analizador léxico.
     */
	public String indentacion() throws IOException {
		// Verificamos que sea la primera línea y que además la pila sea vacía
	    if (tabulado == 0 && pila.empty()) {
	        pila.push(tabulado);
	    } else { // Si no, la pila no estará vacía
	    	
	    	// Si la identacion presentada es menor a la última anterior
	        if (pila.peek() < tabulado) {
	            pila.push(tabulado);
	        } 
	    	// En otro caso, verifica si la identacion misma es menor a la última anterior
	        else if (pila.peek() > tabulado) {
	            String tokens = "";
	            // Se va sacando un bloque y generando un token hasta que empaten
	            while (pila.peek() != tabulado) {
	                if (tabulado > pila.peek()) {
	                    // Error de indentación
	                    System.out.println("\nERROR de indentación, línea "+ yyline + "\n");
	        			fw.write("\nERROR de indentación, línea "+ yyline + "\n");
	                    fw.flush();
	                    fw.close();
	                    System.exit(0);
	                    break;              
	               	}
	               	fw.write("DEINDENTA(" + pila.peek() + ")");
	    	    	tokens += "DEINDENTA(" + pila.pop() + ")";
	            }
	            	return tokens;
	        }
	        // Y si no, verificamos que tengan la misma longitud, lo cual los pondría en el mismo bloque
	        else if (pila.peek() == tabulado) {
	            return "";
	        }
	    }
	    fw.write("INDENTA(" + tabulado + ")");
	    return "INDENTA(" + tabulado + ")";
	}
%}

%class Alexico
%public
%unicode
%standalone
%line
%x CONTEXTO

%eof{
    while (pila.peek() != 0) {
        System.out.println("DEINDENTA(" + pila.pop() + ")");
    }
%eof}

BOOLEANO        =   True | False

ENTERO          =   [1-9][0-9]* | 0+

REAL            =   \.[0-9]+ | ENTERO\.[0-9]+ | ENTERO\.

CADENA          =   \"[^\"\\\n]*\"

CADENAERRONEA   =   \"

RESERVADA       =   and | or | not | while | if | else | elif | print

IDENTIFICADOR   =   ([a-zA-Z] | \_)([a-zA-Z] | [0-9] | \_)*

OPERADOR        =   \+ (=)? | \- (=)? | \* (=)? | \*\* | \/ (=)? | \/\/ | \% | \< | \> | \>\= | \<\= | \= | \! | \=\=  

SEPARADOR       =   \:

TABULADOR       =   "\t"

ESPACIO         =   " "

SALTO			=	"\n"

PARENTESIS_IZQ	= 	"("

PARENTESIS_DER	= 	")"

COMENTARIO		= 	#.*

%%
{PARENTESIS_IZQ}	{}
{PARENTESIS_DER}	{}
{ESPACIO}			{}
{SALTO}             {System.out.println("SALTO"); yybegin(CONTEXTO); this.espacios = 0;
					fw.write("SALTO");}
{RESERVADA} 		{System.out.print("RESERVADA("+ yytext() + ")");
					fw.write("RESERVADA("+ yytext() + ")");}
{IDENTIFICADOR}  	{System.out.print("IDENTIFICADOR(" + yytext() + ")");
					fw.write("IDENTIFICADOR(" + yytext() + ")");}
{COMENTARIO} 	 	{System.out.print("COMENTARIO(" + yytext() + ")");
					fw.write("COMENTARIO(" + yytext() + ")");}
{ENTERO} 			{System.out.print("ENTERO(" + yytext() + ")");
					fw.write("ENTERO(" + yytext() + ")");}
{BOOLEANO} 			{System.out.print("BOOLEANO(" + yytext() + ")");
					fw.write("BOOLEANO(" + yytext() + ")");}
{REAL}	 			{System.out.print("REAL(" + yytext() + ")");
					fw.write("REAL(" + yytext() + ")");}
{CADENAERRONEA}		{cadena = yytext();
						System.out.print("\nERROR: Cadena mal formada: "+ cadena +", línea:" + (yyline+1) + "\n");
                       	fw.write("\nERROR: Cadena mal formada: "+ cadena +", línea:" + (yyline+1) + "\n");
                       	fw.flush();
                       	fw.close();
                       	System.exit(0);}					
{CADENA}            {cadena = yytext();
                      if (cadena.contains("\\") || cadena.substring(1, cadena.length()-1).contains("\"")) {
                       		System.out.print("\nERROR: Cadena mal formada: "+ cadena +", línea:" + yyline + "\n");
                       		fw.write("\nERROR: Cadena mal formada: "+ cadena +", línea:" + yyline + "\n");
                       		fw.flush();
                       		fw.close();
                       		System.exit(0);
                       } else { 
                        	System.out.print("CADENA(" + yytext() + ")");
                        	fw.write("CADENA(" + yytext() + ")"); 
                       }
					}
{OPERADOR} 			{System.out.print("OPERADOR(" + yytext() + ")");
					fw.write("OPERADOR(" + yytext() + ")");}
{SEPARADOR}			{System.out.print("SEPARADOR(" + yytext() + ")");
					fw.write("SEPARADOR(" + yytext() + ")");}
.                   {System.out.print("\nERROR: Lexema no identificado, línea:" + yyline + "\n");
					fw.write("\nERROR: Lexema no identificado, línea:" + yyline + "\n");
					fw.flush(); 
					fw.close();
					System.exit(0);}
<CONTEXTO>{			{ESPACIO}      {this.espacios++;}
    				{TABULADOR}    {this.espacios+=4;}
    				. {yypushback(1); tabulado = this.espacios; System.out.print(indentacion()); yybegin(YYINITIAL);}
}
.					{}