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
