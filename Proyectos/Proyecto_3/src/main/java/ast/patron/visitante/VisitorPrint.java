package ast.patron.visitante;
import ast.patron.compuesto.*;
import java.util.LinkedList;
import java.util.Iterator;

public class VisitorPrint implements Visitor
{

    public void visit(IntHoja n){
	System.out.print("[Hoja Entera] valor: " + n.getValor().ival);
    }

    public void visit(Nodo n){

    }

    public void visit(AndNB n){
    	System.out.println("[AND]");
        System.out.print("[");
        n.getPrimerHijo().accept(this);
        System.out.print("]");
        System.out.print("[");
        n.getUltimoHijo().accept(this);
		System.out.println("]");
    }

    public void visit(AsignacionNB n){
    	System.out.println("[=]");
        System.out.print("[");
        n.getPrimerHijo().accept(this);
        System.out.print("]");
        System.out.print("[");
        n.getUltimoHijo().accept(this);
		System.out.println("]");
    }

    public void visit(BoolHoja n){
    	System.out.print("[Hoja Booleano]: " + n.getValor().bval);
    }

    public void visit(Compuesto n){
    	for (Iterator i = n.getHijos().iterator(); i.hasNext(); ) {
            Nodo hijo = (Nodo) i.next();
            System.out.print("[");
            hijo.accept(this);
            System.out.println("]");
        }
    }

    public void visit(DiffNB n){
    	System.out.println("[!=]");
        System.out.print("[");
        n.getPrimerHijo().accept(this);
        System.out.print("]");
        System.out.print("[");
        n.getUltimoHijo().accept(this);
		System.out.println("]");
    }

    public void visit(DivisionEnteraNB n){
    	System.out.println("[//]");
        System.out.print("[");
        n.getPrimerHijo().accept(this);
        System.out.print("]");
        System.out.print("[");
        n.getUltimoHijo().accept(this);
		System.out.println("]");
    }

    public void visit(DivisionNB n){
    	System.out.println("[/]");
        System.out.print("[");
        n.getPrimerHijo().accept(this);
        System.out.print("]");
        System.out.print("[");
        n.getUltimoHijo().accept(this);
		System.out.println("]");
    }

    public void visit(EqualsNB n){
    	System.out.println("[==]");
        System.out.print("[");
        n.getPrimerHijo().accept(this);
        System.out.print("]");
        System.out.print("[");
        n.getUltimoHijo().accept(this);
		System.out.println("]");
    }

    public void visit(GrNB n){
    	System.out.println("[>]");
        System.out.print("[");
        n.getPrimerHijo().accept(this);
        System.out.print("]");
        System.out.print("[");
        n.getUltimoHijo().accept(this);
		System.out.println("]");
    }

    public void visit(GrqNB n){
    	System.out.println("[>=]");
        System.out.print("[");
        n.getPrimerHijo().accept(this);
        System.out.print("]");
        System.out.print("[");
        n.getUltimoHijo().accept(this);
		System.out.println("]");
    }

    public void visit(Hoja n){
		System.out.print("[Hoja]: "+ n.toString()); 	
    }
    
    public void visit(IdentifierHoja n){
    	System.out.print("[Hoja Identificador] id: "+ n.getNombre());
    }

    public void visit(LeNB n){
    	System.out.println("[<]");
        System.out.print("[");
        n.getPrimerHijo().accept(this);
        System.out.print("]");
        System.out.print("[");
        n.getUltimoHijo().accept(this);
		System.out.println("]");
    }

    public void visit(LeqNB n){
    	System.out.println("[<=]");
        System.out.print("[");
        n.getPrimerHijo().accept(this);
        System.out.print("]");
        System.out.print("[");
        n.getUltimoHijo().accept(this);
		System.out.println("]");
    }

}
