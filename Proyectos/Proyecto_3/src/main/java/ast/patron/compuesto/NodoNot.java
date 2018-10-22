package ast.patron.compuesto;
import ast.patron.visitante.Visitor;

/**
 * NOT. 
 * @author PowerRangers
 */

public class NodoNot extends Compuesto{
    
    public NodoNot(Nodo l){
        super(l);
    }
    
    @Override
    public void accept(Visitor v){
        v.visit(this);
    }
}