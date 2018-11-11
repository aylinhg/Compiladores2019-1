package ast.patron.visitante;

import java.util.Hashtable;

/**
 * Representa la tabla de simbolos h que usaremos para almacenar el contexto en el que
 * aparece cada identificador y conocer el tipo con el que fue creado.
 * Hay dos operaciones: lookUp e insert.
 */
public class TablaSimbolos {

    // Tabla de simbolos
    public Hashtable<String, Integer> tablaSimbolos;
        
    /**
     * Constructor por omision.
     */
    public TablaSimbolos(){
        tablaSimbolos = new Hashtable<String, Integer>();
    }

    /**
     * Regresa el valor asociado a name en la tabla de simbolos o null en caso 
     * de que no haya sido encontrado en la tabla.
     * @param name
     * @return
     */
    public Object lookUp(String name) {
        return tablaSimbolos.get(name);
    }

    /**
     * Guarda info en h(name).
     * @param name
     * @param info
     */
    public void insert(String name, Integer info) {
        tablaSimbolos.put(name, info);
    }

}