package ast.patron.compuesto;
import ast.patron.visitante.*;

/**
 * Cadena.
 * @author PowerRangers
 */
public class StringHoja extends Hoja {

    public StringHoja(String s) {
        valor = new Variable(s);
        tipo = 3;
    }

    public void accept(Visitor v) {
     	v.visit(this);
    }
}
