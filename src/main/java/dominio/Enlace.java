package dominio;

public class Enlace {
    private final int latencia;
    private final int costo;

    public Enlace(int latencia, int costo) {
        this.latencia = latencia;
        this.costo = costo;
    }

    public int getLatencia() {
        return latencia;
    }

    public int getCosto() {
        return costo;
    }
}
