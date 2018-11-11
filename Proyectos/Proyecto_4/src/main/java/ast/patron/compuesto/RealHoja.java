package ast.patron.compuesto;
import ast.patron.visitante.*;

/**
 * Real.
 * @author PowerRangers
 */
public class RealHoja extends Hoja {

    public RealHoja(float r) {
        valor = new Variable(r);
        tipo = 1;
    }

    public void accept(Visitor v){
     	v.visit(this);
    }
}
