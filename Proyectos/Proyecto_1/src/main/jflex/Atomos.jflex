/********************************************************************************
**  @author Diana Montes                                                       **
**  @about Proyecto 1: Analizador léxico para p, subconjunto de Python.        **
*********************************************************************************/
package lexico;
import java.util.Stack;

%%

%public
%class Alexico
%unicode
%standalone

%{
	
    public Stack<Integer> pila = new Stack<Integer>();
    public int tabulado;
    public int espacios = 0;
    public String cadenaActual;

	public String identacion(){

		//Verificamos que sea la primera línea y que además la pila sea vacía
	    if(tabulado == 0 && pila.empty()){
	        pila.push(tabulado);
	    }else{ //Sino, la pila no estará vacía
	        // Si la identacion presentada es menor a la última anterior
	        if(pila.peek() < tabulado){
	            pila.push(tabulado);
	        } 
	    	// En otro caso, verifica si la identacion misma es menor a la última anterior
	        else if( pila.peek() > tabulado){
	            String tokens = "";
	            // Se va sacando un bloque y generando un token hasta que empaten
	            while( pila.peek() != tabulado ){
	                if( tabulado > pila.peek() ){
	                    //throw error
	                    System.out.println("\nError identacion, linea "+ yyline);
	                    System.exit(0);
	                    break;              
	               	}
	                	tokens += "DEINDENTA(" + pila.pop() + ")";
	            	}
	            	return tokens;
	        	}
	        	// Y si no, verificamos que tengan la misma longitud, lo cual los pondría en el mismo bloque
	        	else if( pila.peek() == tabulado ){
	            	return "";
	        	}
	    	}
	    	String identa = "INDENTA(" + tabulado + ")";
	    	return identa;
		}
%}

%eof{
    while( pila.peek() != 0 ){
        System.out.println( "DEINDENTA(" + pila.pop() + ")" );
    }
%eof}

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

%x CONTEXTO

%%
[\n]                { System.out.println("SALTO"); yybegin(CONTEXTO); this.espacios = 0;
}
{RESERVADA} 		{System.out.print("RESERVADA("+ yytext() + ")");}
{IDENTIFICADOR}  	{System.out.print("IDENTIFICADOR(" + yytext() + ")");}
#.* 	 			{System.out.print("COMENTARIO(" + yytext() + ")");}
{ENTERO} 			{System.out.print("ENTERO(" + yytext() + ")");}
{BOOLEANO} 			{System.out.print("BOOLEANO(" + yytext() + ")");}
{REAL}	 			{System.out.print("REAL(" + yytext() + ")");}
{CADENA}            { cadenaActual = yytext();
                      if(cadenaActual.contains("\\") || cadenaActual.substring(1, cadenaActual.length()-1).contains("\"")){
                       System.out.print("\nError de cadena: "+ cadenaActual +", linea:" + yyline);
                       System.exit(0);
                       }else{ 
                            System.out.print("CADENA(" + yytext() + ")");  
                            
                       }
}
{OPERADOR} 			{System.out.print("OPERADOR(" + yytext() + ")");}
{SEPARADOR}			{System.out.print("SEPARADOR(" + yytext() + ")");}
{SALTO} 			{System.out.println("SALTO");}
.                   { System.out.print("\nError, lexema no identificado, linea:" + yyline); 
System.exit(0);}
<CONTEXTO>{			{ESPACIO}      {this.espacios++; }
    				{TABULADOR}    {this.espacios+=4;}
    				. {yypushback(1); tabulado = this.espacios; System.out.println( identacion()); yybegin(YYINITIAL);}
}