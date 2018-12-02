package ast.patron.compuesto;

import ast.patron.visitante.Visitor;

/**
 * EQ (=). Nodo de Asignacion.
 * @author PowerRangers
 */
public class AsignacionNB extends NodoBinario{

    public AsignacionNB(Nodo l, Nodo r) {
        super(l, r);
    }
    
    @Override
    public void accept(Visitor v){
        v.visit(this);
    }
    
}