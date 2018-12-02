package ast.patron.compuesto;

import ast.patron.visitante.Visitor;

/**
 * Potencia(**).
 * @author PowerRangers
 */
public class PotenciaNB extends NodoBinario{

    public PotenciaNB(Nodo l, Nodo r) {
        super(l, r);
    }
    
    public void accept(Visitor v){
        v.visit(this);
    }
    
}