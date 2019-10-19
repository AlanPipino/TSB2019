package negocio;

import soporte.TSBHashtable;
import soporte.TextFile;

public class Pais {
    // tablaResultados: id / cantVotos
    // tablaAgrupaciones: id / nombre
    // tablaDistritos: codigoDistrito / Distrito
    //     Distrito tiene tabla con conteo propio: agrupacionCode / cantVotos

    private TextFile mesasTotalesAgrupacion;
    private TextFile descripcionPostulaciones;
    private TSBHashtable resultados;
    private TSBHashtable agrupaciones;

    public Pais(String path) {
        mesasTotalesAgrupacion = new TextFile(path+"\\mesas_totales_agrp_politica.dsv");
        descripcionPostulaciones = new TextFile(path+"\\descripcion_postulaciones.dsv");
        cargarResultados();
    }

    @Override
    public String toString() {
        return resultados.toString() + agrupaciones.toString();
    }

    public void cargarResultados() {
        agrupaciones = descripcionPostulaciones.identificarAgrupaciones();
        resultados = mesasTotalesAgrupacion.sumarPorAgrupacion();
    }
}
