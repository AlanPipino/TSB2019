package soporte;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class TextFile {
    private String path;
    private TSBHashtable tablaPais;
    private TSBHashtable tablaDistritos;
    private TSBHashtable tablaSecciones;
    private TSBHashtable tablaCircuitos;
    private TSBHashtable tablaMesas;

    // public static final int PAIS = 0;
    // public static final int DISTRITO = 1;
    // public static final int SECCION = 2;
    // public static final int CIRCUITO = 3;
    // public static final int MESA = 4;

    public TextFile(String path) {
        this.path = path;
    }

    @Override
    public String toString() {
        return "TextFile{" +
                "path='" + path + '\'' +
                '}';
    }

    // TODO: sacar esta cosa de aca
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


    public void sumarPorAgrupacion() {
        Scanner scanner = null;
        try {
            scanner = new Scanner(new File(path));

            while(scanner.hasNextLine()){
                String linea = scanner.nextLine();
                String[] campos = linea.split("\\|");
                String categoria = campos[4];
                if(categoria.equals("000100000000000")){
                    // TODO: revisar posicion de cada cosa cuando esté arriba la pagina de la facu
                    String distrito = campos[0];
                    String seccion = campos[1];
                    String circuito = campos[2];
                    String mesa = campos[3];
                    String agrupacion = campos[5];
                    int cantVotos = Integer.parseInt(campos[6]);
                    // sumamos a cada hashtable
                    sumarTabla(tablaPais, "01", agrupacion, cantVotos);
                    sumarTabla(tablaDistritos, distrito, agrupacion, cantVotos);
                    sumarTabla(tablaSecciones, seccion, agrupacion, cantVotos);
                    sumarTabla(tablaCircuitos, circuito, agrupacion, cantVotos);
                    sumarTabla(tablaMesas, mesa, agrupacion, cantVotos);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Archivo no encontrado");
        } catch (Exception e){
            System.out.println("Cagamo"+ e.getMessage());
        }
    }

    private void sumarTabla(TSBHashtable tabla, String keyObjeto, String keyAgrupacion, int cantVotos){
        TSBHashtable tablaInterna = (TSBHashtable) tabla.get(keyObjeto);
        if( tablaInterna == null){
            tablaInterna = tabla.put(keyObjeto, new TSBHashtable());
        }
        Acumulador acu = (Acumulador) tablaInterna.get(keyAgrupacion);
        if( acu != null){
            acu.sumar(cantVotos);
        } else {
            tablaInterna.put(keyAgrupacion, new Acumulador(cantVotos));
        }
    }


    /**
     devuelve resultados de todas las agrupaciones por pais
     */
    public String getReportePais(){
        return "";
    }

    /**
     devuelve resultados de todas las agrupaciones por distrito
     */
    public String getReporteDitrito(String distrito){
        return "";
    }

    /**
     devuelve resultados de todas las agrupaciones por seccion
     */
    public String getReporteSeccion(String seccion){
        return "";
    }

    /**
     devuelve resultados de todas las agrupaciones por circuito
     */
    public String getReporteCircuito(String circuito){
        return "";
    }

    /**
     devuelve resultados de todas las agrupaciones por mesa
     */
    public String getReporteMesa(String mesa){
        return "";
    }

    /**
     arma el string de los resultados recorriendo el hashtable o algo
     */
    private String getReporte(TSBHashtable tabla, String key){
        return "";
    }



    /**
     devuelve resultados de una agrupaciones por pais
     */
    public int getVotosAgrpByPais(){
        return 0;
    }

    /**
     devuelve resultados de una agrupaciones por distrito
     */
    public int getVotosAgrpByDistrito(String distrito){
        return 0;
    }

    /**
     devuelve resultados de una agrupaciones por seccion
     */
    public int getVotosAgrpBySeccion(String seccion){
        return 0;
    }

    /**
     devuelve resultados de una agrupaciones por circuito
     */
    public int getVotosAgrpByCircuito(String circuito){
        return 0;
    }

    /**
     devuelve resultados de una agrupaciones por mesa
     */
    public int getVotosAgrpByMesa(String mesa){
        return 0;
    }

    /**
     busca la cantidad de votos de una agrupacion en una tabla
     */
    private int getReporte(TSBHashtable tabla, String key){
        return 0;
    }



    // tambien se puede hacer como
    // public String getReporteBy(int filtro){
    //     switch(filtro){
    //         case this.PAIS:
    //             break;
    //     }
    // }

}
