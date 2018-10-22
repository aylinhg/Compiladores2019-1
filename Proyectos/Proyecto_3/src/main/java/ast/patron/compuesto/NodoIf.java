package ast.patron.compuesto;
import ast.patron.visitante.Visitor;

/**
 * IF. Nodo de condicional If.
 * @author PowerRangers
 */

public class NodoIf extends Compuesto {
    
    public NodoIf(Nodo t, Nodo l, Nodo r){
        super(t);
        this.getHijos().agregaHijoFinal(l);
        this.getHijos().agregaHijoFinal(r);
    }
    
    public NodoIf(Nodo t, Nodo l){
        super(t);
        this.getHijos().agregaHijoFinal(l);
    }    
    
    @Override
    public void accept(Visitor v){
        v.visit(this);
    }    
}