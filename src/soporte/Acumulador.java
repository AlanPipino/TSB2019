package soporte;

public class Acumulador {
    private int cantidad;

    public Acumulador(int inicial) {
        cantidad = inicial;
    }

    public void sumar(int valor){
        cantidad += valor;
    }

    public int getCantidad() {
        return cantidad;
    }

    @Override
    public String toString() {
        return ""+cantidad;
    }
}
