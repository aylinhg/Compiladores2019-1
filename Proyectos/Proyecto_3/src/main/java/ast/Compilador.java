//import Parser;
package ast;
import java.io.*;
import ast.patron.compuesto.*;
import ast.patron.visitante.*;


public class Compilador{

    Parser parser;
    Nodo raizAST;
    VisitorPrint v_print;

    Compilador(Reader fuente){
        parser = new Parser(fuente);
        v_print = new VisitorPrint();
    }

    public void ConstruyeAST(boolean debug){
        parser.yydebug = debug;
        parser.yyparse(); // analisis lexico, sintactico y constuccion del AST
        raizAST = parser.raiz;
    }

    public void imprimeAST(){
        parser.raiz.accept(v_print);
    }

    public static void main(String[] args){
            String archivo = "src/main/resources/test.p";
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
