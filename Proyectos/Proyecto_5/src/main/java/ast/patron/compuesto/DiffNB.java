package ast.patron.compuesto;

import ast.patron.visitante.Visitor;

/**
 * Diff (!=).
 * @author PowerRangers
 */
public class DiffNB extends NodoBinario{

    public DiffNB(Nodo l, Nodo r) {
        super(l, r);
    }
    
    @Override
    public void accept(Visitor v){
        v.visit(this);
    }
    
}