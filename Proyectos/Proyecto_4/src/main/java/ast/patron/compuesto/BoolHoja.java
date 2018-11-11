package ast.patron.compuesto;
import ast.patron.visitante.*;

/**
 * Booleano.
 * @author PowerRangers
 */
public class BoolHoja extends Hoja {

    public BoolHoja(String bool) {
        if (bool.equals("True")) {
            valor = new Variable(true);
        } else {
            valor = new Variable(false);
        }
    }

    public void accept(Visitor v){
     	v.visit(this);
    }
}
