package ast.patron.compuesto;
import ast.patron.visitante.Visitor;

/**
 * OR.
 * @author PowerRangers
 */

public class OrNB extends NodoBinario{
    
    public OrNB(Nodo l, Nodo r){
        super(l, r);
    }
    
    @Override
    public void accept(Visitor v){
        v.visit(this);
    }
}