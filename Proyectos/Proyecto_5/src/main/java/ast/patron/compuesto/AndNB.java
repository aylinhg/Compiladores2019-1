package ast.patron.compuesto;
import ast.patron.visitante.Visitor;

/**
 * AND. 
 * @author PowerRangers
 */

public class AndNB extends NodoBinario{
    
    public AndNB(Nodo l, Nodo r){
        super(l);
    }
    
    @Override
    public void accept(Visitor v){
        v.visit(this);
    }
}