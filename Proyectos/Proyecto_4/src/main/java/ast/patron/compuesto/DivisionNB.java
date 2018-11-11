package ast.patron.compuesto;

import ast.patron.visitante.Visitor;

/**
 * Division(/).
 * @author PowerRangers
 */
public class DivisionNB extends NodoBinario{

    public DivisionNB(Nodo l, Nodo r) {
        super(l, r);
    }
    
    @Override
    public void accept(Visitor v){
        v.visit(this);
    }    
    
}