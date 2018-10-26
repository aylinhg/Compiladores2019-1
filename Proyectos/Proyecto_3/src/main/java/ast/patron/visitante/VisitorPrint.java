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

        public void visit(ModuloNB n){
        System.out.println("[mod]");
        System.out.print("[");
        n.getPrimerHijo().accept(this);
        System.out.print("]");
        System.out.print("[");
        n.getUltimoHijo().accept(this);
        System.out.println("]");
    }

    public void visit(MultiNB n){
        System.out.println("[*]");
        System.out.print("[");
        n.getPrimerHijo().accept(this);
        System.out.print("]");
        System.out.print("[");
        n.getUltimoHijo().accept(this);
        System.out.println("]");
    }

    public void visit(NodoBinario n){

    }

    public void visit(NodoIf n){
        System.out.println("[if]");
        System.out.println("[");
        if(n.getPrimerHijo() != null){
            n.getPrimerHijo().accept(this);
        }
        System.out.println("]");
        System.out.println("[");
        if(n.getHijos().getAll().get(1) != null){
            n.getHijos().getAll().get(1).accept(this);
        }
        System.out.println("]");
        if(n.getHijos().size() == 3){
            System.out.println("[else]");
            System.out.println("[");
            if(n.getHijos().getAll().get(2) != null){
                n.getHijos().getAll().get(2).accept(this);
            }
            System.out.println("]");
        }
    }
    
    public void visit(NodoNot n){
        System.out.println("[!]");
        System.out.print("[");
        n.getPrimerHijo().accept(this);
        System.out.print("]");
        System.out.print("[");
        n.getUltimoHijo().accept(this);
        System.out.println("]");
    }

    public void visit(NodoStmts n){

    }

    public void visit(OrNB n){
        System.out.println("[OR]");
        System.out.print("[");
        n.getPrimerHijo().accept(this);
        System.out.print("]");
        System.out.print("[");
        n.getUltimoHijo().accept(this);
        System.out.println("]");
    }

    public void visit(PotenciaNB n){
        System.out.println("[^]");
        System.out.print("[");
        n.getPrimerHijo().accept(this);
        System.out.print("]");
        System.out.print("[");
        n.getUltimoHijo().accept(this);
        System.out.println("]");
    }

    public void visit(PrintNB n){
        System.out.println("[print]");
        System.out.print("[");
        n.getPrimerHijo().accept(this);
        System.out.print("]");
        System.out.print("[");
        if(n.getUltimoHijo()!= null){
            n.getUltimoHijo().accept(this);            
        }        
        System.out.println("]");
    }

    public void visit(RealHoja n){
        System.out.print("[Hoja Real]: " + n.getValor().dval);
    }

    public void visit(RestaNB n){
        System.out.println("[-]");
        System.out.print("[");
        n.getPrimerHijo().accept(this);
        System.out.print("]");
        n.getUltimoHijo().accept(this);
    }
    
    public void visit(StringHoja n){
        System.out.print("[Hoja Cadena]: " + n.getValor().sval);
    }

    public void visit(SumaNB n){
        System.out.println("[+]");
        System.out.print("[");
        n.getPrimerHijo().accept(this);
        System.out.print("]");
        System.out.print("[");
        n.getUltimoHijo().accept(this);
        System.out.println("]");
    }

    public void visit(Variable n){

    }

    public void visit(WhileNB n){
        System.out.println("[while]");
        System.out.print("[");
        n.getPrimerHijo().accept(this);
        System.out.print("]");
        System.out.print("[");
        n.getUltimoHijo().accept(this);
        System.out.println("]");
    }
}
