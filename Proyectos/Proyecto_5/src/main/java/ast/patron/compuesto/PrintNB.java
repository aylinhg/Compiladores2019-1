package ast.patron.compuesto;
import ast.patron.visitante.Visitor;

/**
 * PRINT.
 * @author PowerRangers
 */

public class PrintNB extends NodoBinario{
    
    public PrintNB(Nodo l, Nodo r){
        super(l, r);
    }
    
    @Override
    public void accept(Visitor v){
        v.visit(this);
    }
}