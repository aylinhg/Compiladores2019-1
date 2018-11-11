package ast.patron.visitante;
import ast.patron.compuesto.*;

public interface Visitor
{
    public void visit(IntHoja n); 
    public void visit(Nodo n); 
    public void visit(AndNB n); 
    public void visit(AsignacionNB n); 
    public void visit(BoolHoja n); 
    public void visit(Compuesto n); 
    public void visit(DiffNB n); 
    public void visit(DivisionEnteraNB n); 
    public void visit(DivisionNB n); 
    public void visit(EqualsNB n); 
    public void visit(GrNB n); 
    public void visit(GrqNB n);
    public void visit(Hoja n); 
    public void visit(IdentifierHoja n); 
    public void visit(LeNB n); 
    public void visit(LeqNB n); 
    public void visit(ModuloNB n); 
    public void visit(MultiNB n); 
    public void visit(NodoBinario n); 
    public void visit(NodoIf n);
    public void visit(NodoNot n); 
    public void visit(NodoStmts n); 
    public void visit(OrNB n); 
    public void visit(PotenciaNB n);   
    public void visit(PrintNB n); 
    public void visit(RealHoja n);
    public void visit(RestaNB n); 
    public void visit(StringHoja n);
    public void visit(SumaNB n);
	public void visit(WhileNB n);

}
