package ast.patron.compuesto;

import ast.patron.visitante.Visitor;

/**
 * LE(<).
 * @author PowerRangers
 */
public class LeNB extends NodoBinario{

    public LeNB(Nodo l, Nodo r) {
        super(l, r);
    }
    
    @Override
    public void accept(Visitor v){
        v.visit(this);
    }
    
}