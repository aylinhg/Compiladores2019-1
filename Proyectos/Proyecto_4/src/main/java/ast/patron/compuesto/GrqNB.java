package ast.patron.compuesto;

import ast.patron.visitante.Visitor;

/**
 * GRQ (>=).
 * @author PowerRangers
 */
public class GrqNB extends NodoBinario{

    public GrqNB(Nodo l, Nodo r) {
        super(l, r);
    }
    
    @Override
    public void accept(Visitor v){
        v.visit(this);
    }
    
}