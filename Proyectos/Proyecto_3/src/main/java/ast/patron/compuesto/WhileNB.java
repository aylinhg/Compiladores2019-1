package ast.patron.compuesto;
import ast.patron.visitante.Visitor;

/**
 * WHILE. Condicional WHILE. 
 * @author PowerRangers
 */

public class WhileNB extends NodoBinario{
    
    public WhileNB(Nodo l, Nodo r){
	super(l,r);
    }

    public void accept(Visitor v){
     	v.visit(this);
    }
    
}