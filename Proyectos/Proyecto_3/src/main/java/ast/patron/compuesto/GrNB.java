package ast.patron.compuesto;

import ast.patron.visitante.Visitor;

/**
 * GR (>).
 * @author PowerRangers
 */
public class GrNB extends NodoBinario{

    public GrNB(Nodo l, Nodo r) {
        super(l, r);
    }
    
    @Override
    public void accept(Visitor v){
        v.visit(this);
    }
    
}