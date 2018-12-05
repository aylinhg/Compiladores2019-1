//import Parser;
package ast;
import java.io.*;
import ast.patron.compuesto.*;
import ast.patron.visitante.*;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Compilador{

    Parser parser;
    Nodo raizAST;
    VisitorPrint v_print;
    VisitanteConcreto v_concreto;
    VisitanteGenerador v_generador;
    static String archivo;

    Compilador(Reader fuente){
        parser = new Parser(fuente);
        v_print = new VisitorPrint();
        v_concreto = new VisitanteConcreto();
        v_generador = new VisitanteGenerador();
    }

    public void ConstruyeAST(boolean debug){
        parser.yydebug = debug;
        parser.yyparse(); // analisis lexico, sintactico y constuccion del AST
        raizAST = parser.raiz;
    }

    public void imprimeAST(){
        parser.raiz.accept(v_concreto);
        parser.raiz.accept(v_generador);
        String instrucciones = v_generador.getInstrucciones();
        System.out.println("Instrucciones:\n"+instrucciones);
        FileWriter fw;
        try{
            fw=new FileWriter(archivo.substring(0, archivo.length()-2)+ ".asm");
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(instrucciones);
            bw.flush();
            bw.close();
        } catch (IOException ex){
            Logger.getLogger(Compilador.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }

    public static void main(String[] args){
            archivo = "src/main/resources/test.p";
        try{
            Reader a = new FileReader(archivo);
            Compilador c  = new Compilador(a);
            c.ConstruyeAST(true);
            c.imprimeAST();
        }catch(FileNotFoundException e){
            System.err.println("El archivo " + archivo +" no fue encontrado. ");
        }catch(ArrayIndexOutOfBoundsException e){
            System.err.println("Uso: java Compilador [archivo.p]: ");
        }
    }
}
