package ast.patron.compuesto;
import ast.patron.visitante.*;

/**
 * Resta(-).
 * @author PowerRangers
 */
public class RestaNB extends NodoBinario
{

    public RestaNB(Nodo l, Nodo r){
	super(l,r);
    }

    public void accept(Visitor v){
	v.visit(this);
    }
}