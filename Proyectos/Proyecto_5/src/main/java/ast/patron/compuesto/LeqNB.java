package ast.patron.compuesto;

import ast.patron.visitante.Visitor;

/**
 * LEQ (<=).
 * @author PowerRangers
 */
public class LeqNB extends NodoBinario{

    public LeqNB(Nodo l, Nodo r) {
        super(l, r);
    }
    
    @Override
    public void accept(Visitor v){
        v.visit(this);
    }
    
}