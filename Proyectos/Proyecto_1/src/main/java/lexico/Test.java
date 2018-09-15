package lexico;
import java.io.*;
import java.util.Scanner;

public class Test {

	/**
	 * Escribe la salida del analizador léxico para archivos con errores léxicos.
	 */
	public static void analizarArchivo(String ruta, String archivo, String nuevoArchivo) throws FileNotFoundException {
		Scanner sc = new Scanner(new File(ruta + archivo));
        String linea = "";
        int numLinea = 1;
        while (sc.hasNextLine()) {
            System.out.println(linea);
            linea = sc.nextLine();
            if (!linea.isEmpty()) {
                break;
            }
            numLinea++;
        }
        String primerCaracter = linea.substring(0, 1);
        if (primerCaracter.equals(" ")) {
            File f = new File("out/" + nuevoArchivo + ".plx");
            FileWriter w = null;
            try {
                w = new FileWriter(f);
            } catch (IOException ex) {
                System.out.println("Error al inicializar el fileWriter del archivo: "+archivo);
            }
            BufferedWriter bw = new BufferedWriter(w);
            PrintWriter wr = new PrintWriter(bw);
            wr.write("ERROR de indentación. Línea " + numLinea + ".");
            wr.close();
            try {
                bw.close();
            } catch (IOException ex) {
                System.out.println("Error al cerrar el buffer del archivo: "+archivo);
            }
            System.out.println("ERROR de indentación. Línea " + numLinea + ".");
            System.exit(0);
		}
	}

    public static void main (String[] args) throws IOException {
    	System.out.println("Ingresa el nombre del archivo a analizar: ");
        Scanner sc = new Scanner(System.in);
        String archivo = sc.nextLine(); 
		int iend = archivo.indexOf("."); 
		String nuevoArchivo = "";
		if (iend != -1) {
    		nuevoArchivo = archivo.substring(0, iend);
		}
        File f = new File("out/"+nuevoArchivo+".plx");
        try {
            FileWriter fw = new FileWriter(f);
            AnalizadorLexico al = new AnalizadorLexico("src/main/resources/" + archivo, fw);
        	System.out.println("Análisis léxico:");
        	analizarArchivo("src/main/resources/", archivo, nuevoArchivo);
        	al.analiza();
        	fw.flush();
        	fw.close();
        } catch (IOException ex) {
            System.out.println("Error al empezar a analizar el archivo: "+archivo);
		} 
    }
}
