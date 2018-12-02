package ast.patron.compuesto;

import ast.patron.visitante.Visitor;

/**
 * Equals (==).
 * @author PowerRangers
 */
public class EqualsNB extends NodoBinario{

    public EqualsNB(Nodo l, Nodo r) {
        super(l, r);
    }
    
    @Override
    public void accept(Visitor v){
        v.visit(this);
    }
    
}