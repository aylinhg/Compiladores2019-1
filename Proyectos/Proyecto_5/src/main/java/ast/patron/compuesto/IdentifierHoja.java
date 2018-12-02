package ast.patron.compuesto;
import ast.patron.visitante.*;

/**
 * Identifier.
 * @author PowerRangers
 */
public class IdentifierHoja extends Hoja {

    public IdentifierHoja(String identifier) {
        name = identifier;
    }

    public void accept(Visitor v){
     	v.visit(this);
    }
}
