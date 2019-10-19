package soporte;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class TextFile {
    private String path;

    public TextFile(String path) {
        this.path = path;
    }

    @Override
    public String toString() {
        return "TextFile{" +
                "path='" + path + '\'' +
                '}';
    }


    public TSBHashtable sumarPorAgrupacion() {
        TSBHashtable tabla = new TSBHashtable();
        Scanner scanner = null;
        try {
            scanner = new Scanner(new File(path));

            while(scanner.hasNextLine()){
                String linea = scanner.nextLine();
                String[] campos = linea.split("\\|");
                String categoria = campos[4];
                if(categoria.equals("000100000000000")){
                    String agrupacion = campos[5];
                    int cantVotos = Integer.parseInt(campos[6]);
                    Acumulador acu = (Acumulador) tabla.get(agrupacion);
                    if( acu != null){
                        acu.sumar(cantVotos);
                    } else {
                        tabla.put(agrupacion, new Acumulador(cantVotos));
                    }
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Archivo no encontrado");
        } catch (Exception e){
            System.out.println("Cagamo"+ e.getMessage());
        }
        return tabla;
    }

    public TSBHashtable identificarAgrupaciones() {
        TSBHashtable tabla = new TSBHashtable();
        Scanner scanner = null;
        try {
            scanner = new Scanner(new File(path));

            while(scanner.hasNextLine()){
                String linea = scanner.nextLine();
                String[] campos = linea.split("\\|");
                String categoria = campos[0];
                if(categoria.equals("000100000000000")){
                    String idAgrupacion = campos[2];
                    String nomAgrupacion = campos[3];
                    String acu = (String) tabla.get(idAgrupacion);
                    tabla.put(idAgrupacion, nomAgrupacion);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Archivo no encontrado");
        } catch (Exception e){
            System.out.println("Cagamo"+ e.getMessage());
        }
        return tabla;
    }
}
