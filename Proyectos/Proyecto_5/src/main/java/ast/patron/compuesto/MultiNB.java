package ast.patron.compuesto;

import ast.patron.visitante.Visitor;

/**
 * Multiplicacion(*).
 * @author PowerRangers
 */
public class MultiNB extends NodoBinario{

    public MultiNB(Nodo l, Nodo r) {
        super(l, r);
    }
    
    @Override
    public void accept(Visitor v){
        v.visit(this);
    }
    
}