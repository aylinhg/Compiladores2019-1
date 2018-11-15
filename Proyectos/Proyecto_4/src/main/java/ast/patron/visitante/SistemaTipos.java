package ast.patron.visitante;

/**
*Clase SistemaTipos
*Define el sistema de tipos de los operadores.
*/
public class SistemaTipos{
	private static final int booleano = 0;
	private static final int entero = 1;
	private static final int real = 2;
	private static final int cadena = 3; 

	/**
     * Verifica que el tipado de los operandos de la suma sea correcto
     * @param tipo1 el tipo del primer operando
     * @param tipo2 el tipo del segundo operando
     * @return el tipo del resultado
     */
    public static int verificarSuma(int tipo1, int tipo2) throws TipadoException{
        if (tipo1 == booleano || tipo2 == booleano){ 
            throw new TipadoException("Suma", tipo1, tipo2);
        }else if (tipo1 == cadena && tipo2 == cadena){
            return cadena;
        }else if( tipo1 == real || tipo2 == real){ 
            return real;
        }else{ 
            return entero;
        }
    }

    /**
     * Verifica que el tipado de los elementos de la operacion resta sea correcto
     * @param tipo1 el tipo del primer elemento
     * @param tipo2 el tipo del segundo elemento
     * @return el tipo del resultado
     */
    public static int verificaResta(int tipo1, int tipo2) throws TipadoException{
        if (tipo1 == booleano || tipo2 == booleano){ // No hay + de bools
            throw new TipadoException("Resta", tipo1, tipo2);
        }else if (tipo1 == cadena && tipo2 == cadena){ 
            return cadena;
        }else if( tipo1 == real || tipo2 == real){ 
            return real;
        }else{ 
            return entero;
        }
    }

    /**
     * Verifica que el tipado de los factores de la multiplicacion sea correcto
     * @param tipo1 el tipo del primer factor
     * @param tipo2 el tipo del segundo factor
     * @return el tipo del resultado
     */
    public static int verificaMult(int tipo1, int tipo2) throws TipadoException{
        if (tipo1 == booleano || tipo2 == booleano){ 
            throw new TipadoException("Multiplicacion", tipo1, tipo2);
        }else if ( tipo1 == cadena || tipo2 == cadena){  
            throw new TipadoException("Multiplicacion", tipo1, tipo2);
        }else if (tipo1 == real || tipo2 == real){ 
            return real;
        }else{
            return entero;
        }
    }

    /**
     * Verifica que el tipado de los elementos de la operacion division sea correcto
     * @param tipo1 el tipo del primer elemento
     * @param tipo2 el tipo del segundo elemento
     * @return el tipo del resultado
     */
    public static int verificaDiv(int tipo1, int tipo2) throws TipadoException{
       if (tipo1 == booleano || tipo2 == booleano){ 
            throw new TipadoException("Division", tipo1, tipo2);
        }else if ( tipo1 == cadena || tipo2 == cadena){  
            throw new TipadoException("Division", tipo1, tipo2);
        }else{ 
            return real;
        }
    }

    /**
     * Verifica que el tipado de los elementos de la division entera sea correcto
     * @param tipo1 el tipo del primer elemento
     * @param tipo2 el tipo del segundo elemento
     * @return el tipo del resultado
     */  
    public static int verificaDivEntera(int tipo1, int tipo2) throws TipadoException{
        if (tipo1 == booleano || tipo2 == booleano){ 
            throw new TipadoException("Division entera", tipo1, tipo2);
        }else if (tipo1 == cadena && tipo2 == cadena){  
            return cadena;
        }else {
            return entero;
        }
    }

    /**
     * Verifica que el tipado de los elementos de la potencia sea correcto
     * @param tipo1 el tipo del primer elemento
     * @param tipo2 el tipo del segundo elemento
     * @return el tipo del resultado
     */
    public static int verificaPotencia(int tipo1, int tipo2) throws TipadoException{
        if (tipo1 == booleano || tipo2 == booleano){ 
            throw new TipadoException("Potencia", tipo1, tipo2);
        }else if ( tipo1 == cadena || tipo2 == cadena){ 
            throw new TipadoException("Potencia", tipo1, tipo2);
        }else if (tipo1 == real || tipo2 == real){ 
            return real;
        }else{
            return entero;
        }
    }

    /**
     * Verifica que el tipado de la operacion modulo sea correcto
     * @param tipo1 el tipo del primer elemento
     * @param tipo2 el tipo del segundo elemento
     * @return el tipo del resultado
     */
    public static int verificaModulo(int tipo1, int tipo2) throws TipadoException{
        if (tipo1 == booleano || tipo2 == booleano || tipo1 == cadena || tipo2 == cadena || tipo1 == real || tipo2 == real){ 
            throw new TipadoException("Modulo", tipo1, tipo2);
        }else{
            return entero;
        }
    }

    /**
     * Verifica que el tipado de los elementos de los comparadores (<,>, <=, >=) sea correcto
     * @param tipo1 el tipo del primer elemento
     * @param tipo2 el tipo del segundo elemento
     * @param comp el nombre del comparador
     * @return el tipo del resultado
     */
    public static int verficaComparador(String comp, int tipo1, int tipo2) throws TipadoException{
        if (tipo1 == booleano || tipo2 == booleano){
            throw new TipadoException(comp, tipo1, tipo2);
        }else if (tipo1 == cadena || tipo2 == cadena){
            throw new TipadoException(comp, tipo1, tipo2);
        }else{
            return booleano;
        }
    }

    /**
     * Verifica que el tipado de los operadores logicos sea correcto
     * @param tipo1 el tipo del primer elemento
     * @param tipo2 el tipo del segundo elemento
     * @param op el nombre del operador
     * @return el tipo del resultado
     */
    public static int verificaOpLogica(String op, int tipo1, int tipo2)throws TipadoException{
        if (tipo1 != booleano || tipo2 != booleano){
            throw new TipadoException(op, tipo1, tipo2);
        }else{  
            return booleano;
        }
    }
    
    /**
     * Verifica que el tipado de los elementos del condicional if sea correcto
     * @param tipo1 el tipo de la expresion
     * @return el tipo del resultado
     */
    public static int verificaIf(int tipo1) throws TipadoException{
        if (tipo1 != booleano){ 
            throw new TipadoException("If", tipo1);
        } else{
            return booleano;
        }
    }

    /**
     * Verifica que el tipado de los elementos del not sea correcto
     * @param tipo1 el tipo de la expresion
     * @return el tipo del resultado
     */
    public static int verificaNot(int tipo1) throws TipadoException{
        if (tipo1 != booleano){ 
            throw new TipadoException("Not", tipo1);
        } else{
            return booleano;
        }
    }

    /**
     * Verifica que el tipado de los elementos del ciclo while sea correcto
     * @param tipo1 el tipo de la expresion
     * @return el tipo del resultado
     */
    public static int verificaWhile(int tipo1) throws TipadoException{
        if (tipo1 != booleano){ 
            throw new TipadoException("While", tipo1);
        } else{
            return booleano;
        }
    }
    
    /**
     * Verifica que el tipado de los elementos de equals sea correcto
     * @param tipo1 el tipo del primer elemento
     * @param tipo2 el tipo del segundo elemento
     * @return el tipo del resultado
     */
    public static int verificaEquals(int tipo1, int tipo2) throws TipadoException{
        if (tipo1 == entero && tipo2 == entero){ 
            return booleano;
        } 
        if(tipo1 == cadena && tipo2 == cadena){
            return booleano;
        }
        if(tipo1 == real && tipo2 == real){
            return booleano;
        }
        throw new TipadoException("Equals", tipo1, tipo2);
    }
     
    /**Devuelve el tipo de lo que se esta imprimiendo
     * @param tipo1 el tipo de la expresion
     * @return el tipo del resultado
     */
    public static int verificaPrint(int tipo1) throws TipadoException{
        return tipo1;
    }       
}