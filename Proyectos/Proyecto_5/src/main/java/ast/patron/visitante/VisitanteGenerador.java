package ast.patron.visitante;
import ast.Registros;
import ast.patron.compuesto.*;

/**
 * Nuevo Visitante Concreto que recorre el AST y genera el codigo correspondiente
 * en ensablador MIPS.
 */
public class VisitanteGenerador implements Visitor {

    private static final int booleano = 0;
    private static final int entero = 1;
    private static final int cadena = 3;
    private String instrucciones = ".text\n";

    Registros reg = new Registros();

    public void visit(RestaNB n){
        Nodo hi = n.getPrimerHijo();
        Nodo hd = n.getUltimoHijo();

        String objetivo = reg.getObjetivo();
        String[] siguientes = reg.getNsiguientes(2);

        // Genero el codigo del subarbol izquierdo
        reg.setObjetivo(siguientes[0]);
        hi.accept(this);

        // Genero el código del subárbol derecho
        reg.setObjetivo(siguientes[1]);
        hd.accept(this);

        String opcode =  "sub";

        System.out.println(opcode + " " + objetivo + ", " + siguientes[0] + ", " + siguientes[1]);
    }

    public void visit(IntHoja n) {

    }

    public void visit(Nodo n) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void visit(AndNB n) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void visit(AsignacionNB n) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void visit(BoolHoja n) {

    }

    public void visit(Compuesto n) {
        for(Nodo nodo : n.getHijos().getAll()){
            nodo.accept(this);
        }
    }

    public void visit(DiffNB n) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void visit(DivisionEnteraNB n) {
        Nodo hi = n.getPrimerHijo();
        Nodo hd = n.getUltimoHijo();

        String objetivo = reg.getObjetivo();
        String[] siguientes = reg.getNsiguientes(2);

        // Genera el código del subárbol izquiero
        reg.setObjetivo(siguientes[0]);
        hi.accept(this);

        // Genera el código del subárbol derecho
        reg.setObjetivo(siguientes[1]);
        hd.accept(this);

        String opcode =  "div";

        instrucciones += opcode + " " + objetivo + ", " + siguientes[0] + ", " + siguientes[1]+ "\n";
    }

    public void visit(DivisionNB n) {
        Nodo hi = n.getPrimerHijo();
        Nodo hd = n.getUltimoHijo();

        String objetivo = reg.getObjetivo();
        String[] siguientes = reg.getNsiguientes(2);

        // Genera el código del subárbol izquiero
        reg.setObjetivo(siguientes[0]);
        hi.accept(this);

        // Genera el código del subárbol derecho
        reg.setObjetivo(siguientes[1]);
        hd.accept(this);

        String opcode = "div";

        instrucciones += opcode + " " + objetivo + ", " + siguientes[0] + ", " + siguientes[1]+ "\n";
    }

    public void visit(EqualsNB n) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void visit(GrNB n) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void visit(GrqNB n) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void visit(Hoja n) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void visit(IdentifierHoja n) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void visit(LeNB n) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void visit(LeqNB n) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void visit(ModuloNB n) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void visit(MultiNB n) {
        Nodo hi = n.getPrimerHijo();
        Nodo hd = n.getUltimoHijo();

        String objetivo = reg.getObjetivo();
        String[] siguientes = reg.getNsiguientes(2);

        // Genera el código del subárbol izquiero
        reg.setObjetivo(siguientes[0]);
        hi.accept(this);

        // Genera el código del subárbol derecho
        reg.setObjetivo(siguientes[1]);
        hd.accept(this);

        String opcode =  "mul";

        instrucciones += opcode + " " + objetivo + ", " + siguientes[0] + ", " + siguientes[1]+ "\n";
    }

    public void visit(NodoBinario n) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void visit(NodoIf n) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void visit(NodoNot n) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void visit(NodoStmts n) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void visit(OrNB n) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void visit(PotenciaNB n) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void visit(PrintNB n) {
        Nodo hi = n.getPrimerHijo();
        Nodo hd = n.getUltimoHijo();

        String objetivo = reg.getObjetivo();
        String[] siguientes = reg.getNsiguientes(2);

        // Genero el código del subárbol izquiero
        reg.setObjetivo(siguientes[0]);
        hi.accept(this);

        this.instrucciones += "move $a0, " + objetivo + "\nli $v0, 1\n" + "syscall\n";
    }

    public void visit(RealHoja n) {

    }

    public void visit(StringHoja n) {
      throw new UnsupportedOperationException("Not supported yet.");
    }

    public void visit(SumaNB n) {
        Nodo hi = n.getPrimerHijo();
        Nodo hd = n.getUltimoHijo();

        String objetivo = reg.getObjetivo();
        String[] siguientes = reg.getNsiguientes(2);

        // Genera el código del subárbol izquiero
        reg.setObjetivo(siguientes[0]);
        hi.accept(this);

        // Genera el código del subárbol derecho
        reg.setObjetivo(siguientes[1]);
        hd.accept(this);

        String opcode =  "add";

        instrucciones += opcode + " " + objetivo + ", " + siguientes[0] + ", " + siguientes[1]+ "\n";
    }

    public void visit(WhileNB n) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
