package ast.patron.visitante;
/**
*   Clase TipadoException
*   Define las excepciones para ciertos operadores.
*/
public class TipadoException extends Exception {
    
    private final String[] nombresTipos = {"BOOLEANO", "ENTERO", "REAL", "CADENA"};
    
    public TipadoException(String op, int t1, int t2){
        System.err.println("\n\n"+ nombresTipos[t1] + " y " + nombresTipos[t2] + " no son tipos válidos en la operación " + op);
        System.exit(0);
    }
    
    public TipadoException(int t1, int t2, String nameId){
        System.err.println("\n\nReasignación de " + nombresTipos[t1] + " a " + nombresTipos[t2] + " en la variable " + nameId ) ;
        System.exit(0);
    }
    
    public TipadoException(String op, int t1){
        System.err.println("\n\n"+ nombresTipos[t1] + " no es válido en la operación " + op);
        System.exit(0);
    }
}