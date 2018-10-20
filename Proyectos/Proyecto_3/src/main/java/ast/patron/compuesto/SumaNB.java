package ast.patron.compuesto;
import ast.patron.visitante.*;

/**
 * Suma (+).
 * @author PowerRangers
 */
public class SumaNB extends NodoBinario
{

    public SumaNB(Nodo l, Nodo r){
	super(l,r);
    }

    public void accept(Visitor v){
     	v.visit(this);
    }
}