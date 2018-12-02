package ast.patron.compuesto;

import ast.patron.visitante.Visitor;

/**
 * Modulo(%).
 * @author PowerRangers
 */
public class ModuloNB extends NodoBinario{

    public ModuloNB(Nodo l, Nodo r) {
        super(l, r);
    }
    
    @Override
    public void accept(Visitor v){
        v.visit(this);
    }
    
}