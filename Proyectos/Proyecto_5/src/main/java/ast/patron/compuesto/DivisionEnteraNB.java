package ast.patron.compuesto;

import ast.patron.visitante.Visitor;

/**
 * Division entera(//).
 * @author PowerRangers
 */
public class DivisionEnteraNB extends NodoBinario{

    public DivisionEnteraNB(Nodo l, Nodo r) {
        super(l, r);
    }
    
    @Override
    public void accept(Visitor v){
        v.visit(this);
    }
    
}