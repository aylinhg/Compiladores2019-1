package ast.patron.visitante;

import ast.patron.compuesto.AndNB;
import ast.patron.compuesto.AsignacionNB;
import ast.patron.compuesto.BoolHoja;
import ast.patron.compuesto.Compuesto;
import ast.patron.compuesto.DiffNB;
import ast.patron.compuesto.DivisionEnteraNB;
import ast.patron.compuesto.DivisionNB;
import ast.patron.compuesto.EqualsNB;
import ast.patron.compuesto.GrNB;
import ast.patron.compuesto.GrqNB;
import ast.patron.compuesto.Hoja;
import ast.patron.compuesto.IdentifierHoja;
import ast.patron.compuesto.IntHoja;
import ast.patron.compuesto.LeNB;
import ast.patron.compuesto.LeqNB;
import ast.patron.compuesto.ModuloNB;
import ast.patron.compuesto.MultiNB;
import ast.patron.compuesto.Nodo;
import ast.patron.compuesto.NodoBinario;
import ast.patron.compuesto.NodoIf;
import ast.patron.compuesto.NodoNot;
import ast.patron.compuesto.NodoStmts;
import ast.patron.compuesto.OrNB;
import ast.patron.compuesto.PotenciaNB;
import ast.patron.compuesto.PrintNB;
import ast.patron.compuesto.RealHoja;
import ast.patron.compuesto.RestaNB;
import ast.patron.compuesto.StringHoja;
import ast.patron.compuesto.SumaNB;
import ast.patron.compuesto.WhileNB;
import java.util.Hashtable;
import java.util.logging.Level;
import java.util.logging.Logger;

public class VisitanteConcreto implements Visitor {
    
    /**
    * Lo establecemos igual que en el Sistema de Tipos 
    */
    private static final int booleano = 0;
    private static final int entero = 1;
    private static final int real = 2;
    private static final int cadena = 3;

    private TablaSimbolos tablaSimbolos;
        
    public VisitanteConcreto(){
        tablaSimbolos = new TablaSimbolos();
    }
    
    //AndNB
    public void visit(AndNB n) {
        n.getPrimerHijo().accept(this);
        n.getUltimoHijo().accept(this);
        try {
            n.setTipo(SistemaTipos.checkOpLogica(" && ", n.getPrimerHijo().getType(), n.getUltimoHijo().getType()));
        } catch (TipadoException ex) {
            Logger.getLogger(VisitanteConcreto.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    //AsignacionNB
    public void visit(AsignacionNB n) {
        n.getUltimoHijo().accept(this);
        String name = n.getPrimerHijo().getNombre();  
        // Verifica en la tabla de símbolos si existe
        if (tablaSimbolos.contieneElem(name)){            
            // Verifica si el tipo que tenía es igual al nuevo
            if(tablaSimbolos.lookUp(name) != n.getUltimoHijo().getType()){
                try {
                    throw new TipadoException(tablaSimbolos.lookUp(name), n.getUltimoHijo().getType(), name);
                } catch (TipadoException ex) {
                    Logger.getLogger(VisitanteConcreto.class.getName()).log(Level.SEVERE, null, ex);
                }
            }else{
                n.setTipo(tablaSimbolos.lookUp(name));
            }
        }else{  // Si no lo contiene, registra
            tablaSimbolos.insert(name, n.getUltimoHijo().getType());
        }
        
        n.getPrimerHijo().accept(this);
    }

    //BoolHoja
    public void visit(BoolHoja n) {
        n.setTipo(booleano);
    }

    //Compuesto
    public void visit(Compuesto n) {
        for(Nodo nodo : n.getHijos().getAll()){
            nodo.accept(this);
        }
    }

    //DiffNB
    public void visit(DiffNB n) {
        n.getPrimerHijo().accept(this);
        n.getUltimoHijo().accept(this);
        try {
            n.setTipo(SistemaTipos.verficaComparador(" != ", n.getPrimerHijo().getType(), n.getUltimoHijo().getType()));
        } catch (TipadoException ex) {
            Logger.getLogger(VisitanteConcreto.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    //DivisionEnteraNB
    public void visit(DivisionEnteraNB n) {
        try {
            n.getPrimerHijo().accept(this);
            n.getUltimoHijo().accept(this);
            n.setTipo(SistemaTipos.verificaDivEntera(n.getPrimerHijo().getType(), n.getUltimoHijo().getType()));
        } catch (TipadoException ex) {
            Logger.getLogger(VisitanteConcreto.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    //DivisionNB
    public void visit(DivisionNB n) {
        try {
            n.getPrimerHijo().accept(this);
            n.getUltimoHijo().accept(this);
            n.setTipo(SistemaTipos.verificaDiv(n.getPrimerHijo().getType(), n.getUltimoHijo().getType()));
        } catch (TipadoException ex) {
            Logger.getLogger(VisitanteConcreto.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    //EqualsNB
    public void visit(EqualsNB n) {
        try {
            n.getPrimerHijo().accept(this);
            n.getUltimoHijo().accept(this);
            n.setTipo(SistemaTipos.verificaEquals(n.getPrimerHijo().getType(), n.getUltimoHijo().getType()));
        } catch (TipadoException ex) {
            Logger.getLogger(VisitanteConcreto.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    //GrNB
    public void visit(GrNB n) {
        n.getPrimerHijo().accept(this);
        n.getUltimoHijo().accept(this);
        try {
            n.setTipo(SistemaTipos.verficaComparador(" > ", n.getPrimerHijo().getType(), n.getUltimoHijo().getType()));
        } catch (TipadoException ex) {
            Logger.getLogger(VisitanteConcreto.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    //GrqNB
    public void visit(GrqNB n) {
        n.getPrimerHijo().accept(this);
        n.getUltimoHijo().accept(this);
        try {
            n.setTipo(SistemaTipos.verficaComparador(" >= ", n.getPrimerHijo().getType(), n.getUltimoHijo().getType()));
        } catch (TipadoException ex) {
            Logger.getLogger(VisitanteConcreto.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    //Hoja
    public void visit(Hoja n) { //Nada hacemos
    }

    //IdentifierHoja
    public void visit(IdentifierHoja n) {
        if( !tablaSimbolos.contieneElem(n.getNombre())){
            System.err.println("\n\nLa variable " + n.getNombre() + " no tiene un valor definido");
            System.exit(0);
        }else{
            n.setTipo(tablaSimbolos.get(n.getNombre()));
        }
    }

    //IntHoja
    public void visit(IntHoja n) {
        n.setTipo(entero);
    }

    //LeNB
    public void visit(LeNB n) {
        n.getPrimerHijo().accept(this);
        n.getUltimoHijo().accept(this);
        try {
            n.setTipo(SistemaTipos.verficaComparador(" < ", n.getPrimerHijo().getType(), n.getUltimoHijo().getType()));
        } catch (TipadoException ex) {
            Logger.getLogger(VisitanteConcreto.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    //LeqNB
    public void visit(LeqNB n) {
        n.getPrimerHijo().accept(this);
        n.getUltimoHijo().accept(this);
        try {
            n.setTipo(SistemaTipos.verficaComparador(" <= ", n.getPrimerHijo().getType(), n.getUltimoHijo().getType()));
        } catch (TipadoException ex) {
            Logger.getLogger(VisitanteConcreto.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    //ModuloNB
    public void visit(ModuloNB n) {
        n.getPrimerHijo().accept(this);
        n.getUltimoHijo().accept(this);
        try {
            n.setTipo(SistemaTipos.verificaModulo(n.getPrimerHijo().getType(), n.getUltimoHijo().getType()));
        } catch (TipadoException ex) {
            Logger.getLogger(VisitanteConcreto.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    //MultiNB
    public void visit(MultiNB n) {
        try {
            n.getPrimerHijo().accept(this);
            n.getUltimoHijo().accept(this);
            n.setTipo(SistemaTipos.verificaMult(n.getPrimerHijo().getType(), n.getUltimoHijo().getType()));
        } catch (TipadoException ex) {
            Logger.getLogger(VisitanteConcreto.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    //Nodo
    public void visit(Nodo n) { //Nada hacemos   
    }

    //NodoBinario
    public void visit(NodoBinario n) { //Nada hacemos
    }

    //NodoIf
    public void visit(NodoIf n){          
        if(n.getPrimerHijo() != null){
            n.getPrimerHijo().accept(this);            
        }
        if(n.getHijos().getAll().get(1) != null){
            n.getHijos().getAll().get(1).accept(this);            
        }
        if(n.getHijos().size() == 3){
            if(n.getHijos().getAll().get(2) != null){
                n.getHijos().getAll().get(2).accept(this);
            }
        }
        try {         
            n.setTipo(SistemaTipos.verificaIf(n.getPrimerHijo().getType()));
        } catch (TipadoException ex) {
            Logger.getLogger(VisitanteConcreto.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    //NodoNot
    public void visit(NodoNot n) {
        n.getPrimerHijo().accept(this);        
        try {
            n.setTipo(SistemaTipos.verificaNot(n.getPrimerHijo().getType()));
        } catch (TipadoException ex) {
            Logger.getLogger(VisitanteConcreto.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    //NodoStmts
    public void visit(NodoStmts n) { // Nada hacemos
    }

    //OrNB
    public void visit(OrNB n) {
        n.getPrimerHijo().accept(this);
        n.getUltimoHijo().accept(this);
        try {
            n.setTipo(SistemaTipos.verificaOpLogica(" OR ", n.getPrimerHijo().getType(), n.getUltimoHijo().getType()));
        } catch (TipadoException ex) {
            Logger.getLogger(VisitanteConcreto.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    //PotenciaNB
    public void visit(PotenciaNB n) {
        try {
            n.getPrimerHijo().accept(this);
            n.getUltimoHijo().accept(this);
            n.setTipo(SistemaTipos.verificaPotencia(n.getPrimerHijo().getType(), n.getUltimoHijo().getType()));
        } catch (TipadoException ex) {
            Logger.getLogger(VisitanteConcreto.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    //PrintNB
    public void visit(PrintNB n) {
        n.getPrimerHijo().accept(this);        
        try {
            n.setTipo(SistemaTipos.verificaPrint(n.getPrimerHijo().getType()));
        } catch (TipadoException ex) {
            Logger.getLogger(VisitanteConcreto.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    //RealHoja
    public void visit(RealHoja n) {
        n.setTipo(real);
    }

    //RestaNB
    public void visit(RestaNB n) {
        try {
            n.getPrimerHijo().accept(this);
            n.getUltimoHijo().accept(this);
            n.setTipo(SistemaTipos.verificaResta(n.getPrimerHijo().getType(), n.getUltimoHijo().getType()));
        } catch (TipadoException ex) {
            Logger.getLogger(VisitanteConcreto.class.getName()).log(Level.SEVERE, null, ex);
        }     
    }

    //StringHoja
    public void visit(StringHoja n) {
        n.setTipo(cadena);
    }

    //SumaNB
    public void visit(SumaNB n) {
        try {
            n.getPrimerHijo().accept(this);
            n.getUltimoHijo().accept(this);            
            n.setTipo(SistemaTipos.verificarSuma(n.getPrimerHijo().getType(), n.getUltimoHijo().getType()));
        } catch (TipadoException ex) {
            Logger.getLogger(VisitanteConcreto.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    //WhileNB
    public void visit(WhileNB n) {
        try {
            n.getPrimerHijo().accept(this);
            n.getUltimoHijo().accept(this);            
            n.setTipo(SistemaTipos.verificaWhile(n.getPrimerHijo().getType(), n.getUltimoHijo().getType()));
        } catch (TipadoException ex) {
            Logger.getLogger(VisitanteConcreto.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}