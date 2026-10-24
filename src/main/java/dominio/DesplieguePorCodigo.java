package dominio;

public class DesplieguePorCodigo implements Comparable<DesplieguePorCodigo> {

    private Despliegue despliegue;

    public DesplieguePorCodigo(Despliegue despliegue) {
        this.despliegue = despliegue;
    }

    public Despliegue getDespliegue() {
        return despliegue;
    }

    @Override
    public int compareTo(DesplieguePorCodigo otro) {
        return this.despliegue.getCodigo().compareTo(otro.despliegue.getCodigo());
    }

    @Override
    public String toString() {
        return despliegue.toString();
    }
}